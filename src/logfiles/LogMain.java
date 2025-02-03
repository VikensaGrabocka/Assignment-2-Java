package logfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LogMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the path: ");
        String path = input.nextLine();


        Path pathOut = Paths.get(path);
        Map<String, Statistics> data = new HashMap<>();
        PriorityQueue<Request> slowestR = new PriorityQueue<>
                (Comparator.comparing(Request::getResponseTime));

        Path directoryOut = pathOut.getParent();
        int requestNum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestNum++;
                String[] record = line.split(" ");
                String ip = record[0];
                String time = record[1];
                String method = record[2];
                String url = record[3];
                long responseTime = record[4].equals("-") ? 0 : Long.parseLong(record[4]);
                Request req = new Request(ip, time, method, url, responseTime);

                if (data.containsKey(ip)) {
                        IP currentIp = (IP) data.get(ip);
                        currentIp.setFrequency(((IP) data.get(ip)).getFrequency() + 1);
                        data.put(ip, currentIp);
                } else {
                    IP ipAddress = new IP(ip);
                    data.put(ip, ipAddress);
                }

                String routeName = method + " " + url;

                if (data.containsKey(routeName)) {//route already in the hashmap
                        Route route = (Route) data.get(routeName);
                        route.setRouteFrequency(route.getRouteFrequency() + 1);
                        route.setMissingResponseTimeFreq(responseTime == 0 ? route.getMissingResponseTimeFreq() + 1 : route.getMissingResponseTimeFreq());
                        route.setRouteTotalResponseTime(route.getRouteTotalResponseTime() + responseTime);
                        data.put(routeName, route);

                } else { // new route encountered
                    int missingResponseTimeFreq = responseTime == 0 ? 1 : 0;
                    Route route = new Route(method + " " + url, missingResponseTimeFreq,
                            responseTime);

                    data.put(routeName, route);
                }
                // top 10 slowest requests
                if (responseTime != 0) {
                    if (slowestR.size() < 10) {
                        slowestR.offer(req);

                    } else if (responseTime > slowestR.peek().getResponseTime()) {
                        slowestR.offer(req);
                    }
                    if (slowestR.size() > 10) {
                        slowestR.poll();

                    }
                }
            }
            List<IP> top10Ip = data.values().stream()
                               .filter(ip -> ip instanceof IP)
                               .map(ip-> (IP) ip)
                               .sorted(Comparator.comparingInt( IP :: getFrequency).reversed())
                               .limit(10).toList();
            List<Route> fastestRoutes = data.values().stream()
                                        .filter(r-> r instanceof Route)
                                        .map(r-> (Route) r)
                                        .sorted(Comparator.comparingDouble(Route::computeStatistics))
                                        .limit(10)
                                        .toList();


            try (Formatter output = new Formatter(directoryOut + "output.txt")) {

                output.format("Total number of requests:  " + requestNum + '\n');
                output.format("\n");
                output.format("Top 10 client IPs: \n ");
                output.format("%15s %18s %12s\n", "IP address", "Number of requests", "Percentage ");

                int finalRequestNum = requestNum;
                top10Ip.forEach(ip -> output.format("%15s %18d %6%%.02f\n", ip.getIp(), ip.getFrequency(),
                        (double) ip.getFrequency() / finalRequestNum * 100));
                output.format("\n");
                output.format("Top fastest routes:\n ");
                output.format("%30s %18s %15s\n", "Route name", "Number of requests", "Average response time");
                fastestRoutes.forEach(route -> output.format("%30s  %18d  %11.2f\n", route.getRoute(),
                        route.getRouteFrequency(), route.computeStatistics()));
                output.format("\n");
                output.format("Top 10 slowest requests:\n ");
                slowestR.forEach(r -> output.format("%s\n", r));
            }

        }catch (Exception e){
            System.out.println("An error occurred!");

        }
    }
}