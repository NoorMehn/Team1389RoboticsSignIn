import java.util.ArrayList;

public class AppUsers implements java.io.Serializable {

    public ArrayList<User> users;
    public String date;

    public AppUsers(String date) {
        this.users = new ArrayList<>();
        this.date = date;
    }
}
