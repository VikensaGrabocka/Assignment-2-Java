package logfiles;

public class Route implements Statistics {
    private final String route;
    private int routeFrequency;
    private int missingResponseTimeFreq;
    private long routeTotalResponseTime;

    public Route(String route, int missingResponseTimeFreq, long routeTotalResponseTime) {
        this.route = route;
        this.missingResponseTimeFreq = missingResponseTimeFreq;
        this.routeFrequency = 1;
        this.routeTotalResponseTime = routeTotalResponseTime;
    }

    public String getRoute() {
        return route;
    }

    public int getRouteFrequency() {
        return routeFrequency;
    }

    public void setRouteFrequency(int routeFrequency) {
        this.routeFrequency = routeFrequency;
    }

    public int getMissingResponseTimeFreq() {
        return missingResponseTimeFreq;
    }

    public void setMissingResponseTimeFreq(int missingResponseTimeFreq) {
        this.missingResponseTimeFreq = missingResponseTimeFreq;
    }

    public long getRouteTotalResponseTime() {
        return routeTotalResponseTime;
    }

    public void setRouteTotalResponseTime(long routeTotalResponseTime) {
        this.routeTotalResponseTime = routeTotalResponseTime;
    }

    //Compute route average response time
    @Override
    public double computeStatistics() {
        return (double) routeTotalResponseTime / (routeFrequency - missingResponseTimeFreq);
    }
}
