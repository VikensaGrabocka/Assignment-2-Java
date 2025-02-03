package calendarprocessing;

public class Time {
    private final String startTime;
    private final int duration;

    public Time(String startTime, int duration) {
        this.startTime = startTime;
        this.duration = duration;
    }


    public int calculateStartTimeInMinutes (){
        String[] parts = startTime.split(":");
        int startHour = Integer.parseInt(parts[0]);
        int startMinute = Integer.parseInt(parts[1]);

        return startHour * 60 + startMinute;

    }

    public int calculateEndTimeInMinutes(){
        int startTime = calculateStartTimeInMinutes();
        return startTime + duration;
    }

    public boolean isTimeConflict (Time other){
        return !(other.calculateEndTimeInMinutes() <= this.calculateStartTimeInMinutes() ||
                other.calculateStartTimeInMinutes() >= this.calculateEndTimeInMinutes());

    }

    @Override
    public String toString() {
        return  startTime + ',' + duration;
    }
}

