public class User implements java.io.Serializable {
    private Time today;
    private String name;

    public User(String name){
        this.name = name;
    }

    public void signIn(String date, int time) {
        if(today == null) {
            today = new Time(date, time);
        }
    }

    public void signOut(int time) {
        if(today != null) {
            today.setSignOutTime(time);
            today.calculateElapsedTime();
        }
    }

    public void resetTime() {
        today = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getTodayDate() {
        return today.getDate();
    }

    public String getTimes() {
        return today.getDate() + "," + today.getSignInTime() + "," + today.getSignOutTime() + "," + today.getElapsedTime();
    }

    @Override
    public String toString() {
        return "   " + name;
    }
}
