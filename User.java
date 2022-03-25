public class User implements java.io.Serializable {
    private Time today;
    private String name;

    public User(String name){
        this.name = name;
    }

    public void signIn(int time) {
        if(today == null) {
            today = new Time(time);
        }
    }

    public void signOut(int time) {
        if(today != null) {
            today.setSignOutTime(time);
            today.calculateElapsedTime();
        }
    }

    public String getName() {
        return name;
    }

    /**
     * Outputs the CSV format of each time variable
     * @return String name,sign-in time,sign-out time,elapsed time
     */
    public String getCSV() {
        return name + "," + today.getSignInTime() + "," + today.getSignOutTime() + "," + today.getElapsedTime();
    }

    @Override
    public String toString() {
        return "   " + name;
    }
}
