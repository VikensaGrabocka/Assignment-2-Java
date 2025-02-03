package logfiles;

public class Request {
    private final String ip;
    private final String time;
    private final String method;
    private final String url;
    private final long responseTime;


    public Request(String ip, String method, String time, String url, long responseTime) {
        this.ip = ip;
        this.method = method;
        this.time = time;
        this.url = url;
        this.responseTime = responseTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public String toString() {
        return  ip + ' ' + time + ' ' + method + ' '+ url + ' ' + responseTime;
    }
}


