package calendarprocessing;

public class Task {
    private final Date date;
    private final String taskId;
    private final Time time;
    private final String description;



    public Task(Date date, String taskId, Time time, String description) {
        this.date = date;
        this.taskId = taskId;
        this.time = time;
        this.description = description;
    }



    public Date getDate() {
        return date;
    }

    public String getTaskId() {
        return taskId;
    }

    public int getStartTimeInMinutes() {
        return time.calculateStartTimeInMinutes();
    }


    public boolean isConflictingTask(Task other){
        return other.time.isTimeConflict(this.time);
    }


    @Override
    public String toString() {
        return
                date.toString() + ','+ taskId + ',' + time.toString()  + ',' + description;

    }

}

