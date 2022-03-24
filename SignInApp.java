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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Serialize everything everytime?
 * Serialize everything as a temp-save, then after each day save everything to csv then delete serialize stuff?
 */

public class SignInApp { 

    private ArrayList<User> users;
    private JFrame app;
    private JPanel topPanel;
    private JPanel bodyPanel;
    private JPanel bottomPanel;

    public SignInApp() {
        users = new ArrayList<User>();
        app = new JFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Serialize data and close
        app.setTitle("Sign-In Sheet");
        app.setBounds(100, 100, 400, 400);
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
        bodyPanel.setLayout(new GridLayout(5, 1, 0, 5));
        bodyPanel.setBackground(Color.WHITE);

        JComboBox<User> namesComboBox = new JComboBox<User>();
        namesComboBox.setPreferredSize(new Dimension(210, 20));
        namesComboBox.setBackground(Color.WHITE);
        namesComboBox.setFont(new Font("Helvetica", Font.BOLD, 12));

        JButton signIn = new JButton("Sign  In  ");
        signIn.setPreferredSize(new Dimension(210, 20));
        signIn.setFont(new Font("Helvetica", Font.BOLD, 16));
        signIn.setBackground(Color.WHITE);
        signIn.addActionListener(e -> {
            if(namesComboBox.getSelectedItem() != null) {
                User user = (User) namesComboBox.getSelectedItem();
                user.signIn(getDate(), getCurrentTime());
            }
        });

        JButton signOut = new JButton("Sign Out");
        signOut.setPreferredSize(new Dimension(210, 20));
        signOut.setFont(new Font("Helvetica", Font.BOLD, 16));
        signOut.setBackground(Color.WHITE);
        signOut.addActionListener(e -> {
            if(namesComboBox.getSelectedItem() != null) {
                User user = (User) namesComboBox.getSelectedItem();
                user.signOut(getCurrentTime());
            }            
        });

        bodyPanel.add(namesComboBox);
        bodyPanel.add(signIn);
        bodyPanel.add(signOut);

        // Create footer -> addUser and removeUser buttons
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 15, 25));
        bottomPanel.setBackground(Color.WHITE);

        JButton addUser = new JButton("Add User");
        addUser.setPreferredSize(new Dimension(160, 50));
        addUser.setFont(new Font("Helvetica", Font.BOLD, 18));
        addUser.setBackground(Color.WHITE);
        addUser.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(app, "Enter new member name");
            if(name != null && !name.equals("")) {
                users.add(new User(name));
                namesComboBox.addItem(users.get(users.size()-1));
                namesComboBox.revalidate();
                JOptionPane.showMessageDialog(app, name + " added");
            } 
        });

        JButton removeUser = new JButton("Remove User");
        removeUser.setPreferredSize(new Dimension(160, 50));
        removeUser.setFont(new Font("Helvetica", Font.BOLD, 18));
        removeUser.setBackground(Color.WHITE);
        removeUser.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(app, "Enter member name");
            if(name != null && !name.equals("")) {
                Optional<User> temp = users.parallelStream().filter(o -> o.getName().equals(name)).findFirst();
                if(temp.isPresent()) {
                    users.remove(temp.get());
                    namesComboBox.removeItem(temp.get());
                    namesComboBox.revalidate();
                    JOptionPane.showMessageDialog(app, name + " removed");
                } else {
                    JOptionPane.showMessageDialog(app, name + " not found");
                }
            }
        });

        bottomPanel.add(addUser);
        bottomPanel.add(removeUser);
    }

    private String getDate() {
        return Calendar.getInstance().get(Calendar.MONTH)+1 + "/" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    private int getCurrentTime(){
        Date currentDate = new Date();
        SimpleDateFormat hour = new SimpleDateFormat("kk");
        int hourInt = Integer.parseInt(hour.format(currentDate));
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        int minuteInt = Integer.parseInt(minute.format(currentDate));
        return ((hourInt * 60) + minuteInt);
    }

    private void serialize() {}

    private void deserialize() {}

    private void writeToCSV() {
    }

    public static void main(String[] args) {
        new SignInApp();
    }

}
