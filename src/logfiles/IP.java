package logfiles;

public class IP implements Statistics{
    private final String ip;
    private int frequency;

    public IP(String ip) {
        this.ip = ip;
        this.frequency = 1;
    }

    public String getIp() {
        return ip;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public double computeStatistics() {
        return getFrequency();
    }
}
