import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.awt.Font;

public class SignInApp { 

    private ArrayList<User> users;
    private JFrame app;
    private JPanel topPanel;
    private JPanel bodyPanel;
    private JPanel bottomPanel;
    private String[] strNames;

    public SignInApp() {
        users = new ArrayList<User>();
        strNames = getNames();
        app = new JFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setTitle("Sign-In Sheet");
        app.setBounds(100, 100, 500, 300);
        app.setLayout(new BorderLayout());
        app.setResizable(false);

        createElements();
        app.add(topPanel, BorderLayout.NORTH);
        app.add(bodyPanel, BorderLayout.CENTER);
        app.add(bottomPanel, BorderLayout.SOUTH);
        app.setVisible(true);
    }

    private void createElements() {
        // Create header -> Sign-in text
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Sign-in Sheet", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 24));
        topPanel.add(title);

        // Create body -> everything else
        bodyPanel = new JPanel();
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 31));
        bodyPanel.setLayout(new GridLayout(3, 1, 0, 5));
        bodyPanel.setBackground(Color.WHITE);

        JComboBox<String> namesComboBox = new JComboBox<String>(strNames);
        namesComboBox.setPreferredSize(new Dimension(210, 20));
        namesComboBox.setBackground(Color.WHITE);

        JButton signIn = new JButton("Sign  In  ");
        signIn.setPreferredSize(new Dimension(210, 20));
        signIn.setFont(new Font("Helvetica", Font.BOLD, 16));
        signIn.setBackground(Color.WHITE);
        signIn.addActionListener(e -> {

        });

        JButton signOut = new JButton("Sign Out");
        signOut.setPreferredSize(new Dimension(210, 20));
        signOut.setFont(new Font("Helvetica", Font.BOLD, 16));
        signOut.setBackground(Color.WHITE);
        signOut.addActionListener(e -> {

        });

        bodyPanel.add(namesComboBox);
        bodyPanel.add(signIn);
        bodyPanel.add(signOut);

        // Create footer -> addUser and removeUser buttons
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 15, 25));
        bottomPanel.setBackground(Color.WHITE);

        JButton addUser = new JButton("Add User");
        addUser.setPreferredSize(new Dimension(210, 50));
        addUser.setFont(new Font("Helvetica", Font.BOLD, 18));
        addUser.setBackground(Color.WHITE);
        addUser.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(null, "Enter new member name");
            if(name != null || name == "") {
                users.add(new User(name));
            } 
        });

        JButton removeUser = new JButton("Remove User");
        removeUser.setPreferredSize(new Dimension(210, 50));
        removeUser.setFont(new Font("Helvetica", Font.BOLD, 18));
        removeUser.setBackground(Color.WHITE);
        removeUser.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(null, "Enter member name");
            if(name != null || name != "") {
                users.remove(users.parallelStream().filter(o -> o.getName().equals(name)).findFirst().get());
            } 
        });

        bottomPanel.add(addUser);
        bottomPanel.add(removeUser);
    }

    private String[] getNames() {
        String[] temp = new String[users.size()];
        
        for(int i = 0; i < users.size(); i++) {
            temp[i] = users.get(i).getName();
        }
        return temp;
    }

    private void writeToCSV() {
    }

    public static void main(String[] args) {
        new SignInApp();
    }

}
