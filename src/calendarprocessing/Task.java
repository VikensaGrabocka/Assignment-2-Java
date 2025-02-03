package calendarprocessing;

public class Task {
    private Date date;
    private String taskId;
    private Time time;
    private String description;



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

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getStartTimeInMinutes() {
        return time.calculateStartTimeInMinutes();
    }

    public int getEndTimeInMinutes() {
        return time.calculateEndTimeInMinutes();
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

