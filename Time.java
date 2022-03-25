public class Time implements java.io.Serializable {
    
    private int signInTime;
    private int signOutTime;
    private int elapsedTime;

    public Time(int signInTime) {
        this(signInTime, 0);
    }

    public Time(int signInTime, int signOutTime) {
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
    }

    public void calculateElapsedTime() {
        elapsedTime = signOutTime - signInTime;
    }

    public void setSignOutTime(int signOutTime) {
        this.signOutTime = signOutTime;
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
