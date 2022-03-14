import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.ArrayList;

public class SignInApp { 

    private ArrayList<User> users;

    public SignInApp() {
        users = new ArrayList<User>();
        String[] strNames = getNames();

        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5, 1));
        frame.setTitle("Sign-In Sheet");
        
        JComboBox names = new JComboBox(strNames);
        JButton signIn = new JButton("Sign In");
        JButton signOut = new JButton("Sign Out");
        JButton addUser = new JButton("Add User");
        JButton removeUser = new JButton("Remove User");

        frame.add(names);
        frame.add(signIn);
        frame.add(signOut);
        frame.add(addUser);
        frame.add(removeUser);

        frame.setSize(400, 400);
        frame.setVisible(true);
        
    }

    private String[] getNames() {
        String[] temp = new String[users.size()];
        
        for(int i = 0; i < users.size(); i++) {
            temp[i] = users.get(i).getName();
        }
        return temp;
    }

    public static void main(String[] args) {
        new SignInApp();
    }

}
