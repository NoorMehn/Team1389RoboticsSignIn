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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.lang.ClassNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


public class SignInApp { 

    private AppUsers appUsers;
    private final JFrame app;
    private JPanel topPanel;
    private JPanel bodyPanel;
    private JPanel bottomPanel;

    public SignInApp() {
        deserialize();
        app = new JFrame();
        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serialize();
                e.getWindow().dispose();
            }
        });
        app.setTitle("Sign-In Sheet");
        app.setBounds(100, 100, 400, 400);
        app.setLayout(new BorderLayout());
        app.setResizable(false);

        createHeader();
        createBody();
        app.add(topPanel, BorderLayout.NORTH);
        app.add(bodyPanel, BorderLayout.CENTER);
        app.add(bottomPanel, BorderLayout.SOUTH);
        app.setVisible(true);
    }

    private void createHeader() {
        // Create header -> Sign-in text
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Sign-in Sheet", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 24));
        topPanel.add(title);
    }

    private void createBody() {
        // Create body -> everything else
        bodyPanel = new JPanel();
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 31));
        bodyPanel.setLayout(new GridLayout(5, 1, 0, 5));
        bodyPanel.setBackground(Color.WHITE);

        JComboBox<User> namesComboBox = new JComboBox<>();
        namesComboBox.setPreferredSize(new Dimension(210, 20));
        namesComboBox.setBackground(Color.WHITE);
        namesComboBox.setFont(new Font("Helvetica", Font.BOLD, 12));
        for(User u : appUsers.users) {
            namesComboBox.addItem(u);
        }

        JButton signIn = new JButton("Sign  In  ");
        signIn.setPreferredSize(new Dimension(210, 20));
        signIn.setFont(new Font("Helvetica", Font.BOLD, 16));
        signIn.setBackground(Color.WHITE);
        signIn.addActionListener(e -> {
            if(namesComboBox.getSelectedItem() != null) {
                User user = (User) namesComboBox.getSelectedItem();
                user.signIn(getCurrentTime());
                JOptionPane.showMessageDialog(app, "Hello, " + user.getName());
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
                JOptionPane.showMessageDialog(app, "Goodbye, " + user.getName());
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
                appUsers.users.add(new User(name));
                namesComboBox.addItem(appUsers.users.get(appUsers.users.size()-1));
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
                Optional<User> temp = appUsers.users.parallelStream().filter(o -> o.getName().equals(name)).findFirst();
                if(temp.isPresent()) {
                    appUsers.users.remove(temp.get());
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
        int hourInt = Integer.parseInt(new SimpleDateFormat("kk").format(currentDate));
        int minuteInt = Integer.parseInt(new SimpleDateFormat("mm").format(currentDate));
        return ((hourInt * 60) + minuteInt);
    }

    private void serialize() {
        /*
          Serialize users ArrayList
         */
        try {
            FileOutputStream file = new FileOutputStream(".sign-in-app-data.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(appUsers);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deserialize() {
        /*
          If today's date is different from the serialized data's date
          then 1. export csv 2. reset all time objects
          If not, populate users array with serialized data.
         */
        try {
            FileInputStream file = new FileInputStream(".sign-in-app-data.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            appUsers = (AppUsers) in.readObject();
            in.close();
            file.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            appUsers = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        processData();
    }

    private void processData() {
        // Make sure objects are from same day.
        if(appUsers == null) {
            appUsers = new AppUsers(getDate());
        } else if(appUsers.users.size() != 0 && !appUsers.date.equals(getDate())) {
            writeToCSV();
            appUsers = new AppUsers(getDate());
        }
    }

    private void writeToCSV() {
        try {
            File file = new File("sign-in.csv");
            boolean createdNew = file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);

            if(createdNew) {
                fileWriter.append("date,name,sign-in,sign-out,elapsed\n");
            }

            for(User user : appUsers.users) {
                fileWriter.write(getDate() + "," + user.getCSV() + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignInApp();
    }

}
