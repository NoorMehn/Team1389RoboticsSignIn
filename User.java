import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private int timeSpent;
    private int startTime;
    private int endTime;
    private String name;

    public User(String name){
        this.name = name;
        this.timeSpent = 0;
        this.startTime = 0;
        this.endTime = 0;
    }

    public int getTime(){
        Date currentDate = new Date();
        SimpleDateFormat hour = new SimpleDateFormat("kk");
        int hourInt = Integer.parseInt(hour.format(currentDate));
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        int minuteInt = Integer.parseInt(minute.format(currentDate));
        int totalTime = ((hourInt * 60) + minuteInt);
        return totalTime;
    }

    public String getFormattedTime() {
        //To-Do
        Date currentDate = new Date();
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        return time.format(currentDate);
    }

    public void calculateElapsedTime() {
        //To-Do
        timeSpent = endTime - startTime;
    }

    public String getElapsedFormattedTime() {
        //To-Do
        int hours;
        int minutes;
        hours = timeSpent/60;
        minutes = timeSpent - (hours*60);
        return hours + ":" + minutes;
    }

      
    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int newTimeSpent) {
        this.timeSpent = newTimeSpent;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int newStartTime) {
        this.startTime = newStartTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEntTime(int newEndTime) {
        this.endTime = newEndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }


}
