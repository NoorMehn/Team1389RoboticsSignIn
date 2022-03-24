public class Time {
    
    private String date;
    private int signInTime;
    private int signOutTime;
    private int elapsedTime;

    public Time() {
        this(null, 0, 0);
    }

    public Time(String date, int signInTime) {
        this(date, signInTime, 0);
    }

    public Time(String date, int signInTime, int signOutTime) {
        this.date = date;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
    }

    public void calculateElapsedTime() {
        elapsedTime = signOutTime - signInTime;
    }

    public void setSignOutTime(int signOutTime) {
        this.signOutTime = signOutTime;
    }

    public String getDate() {
        return date;
    }

    public int getSignInTime() {
        return signInTime;
    }

    public int getSignOutTime() {
        return signOutTime;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

}
