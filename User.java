public class User {
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

    @Override
    public String toString() {
        return "   " + name;
    }
}
