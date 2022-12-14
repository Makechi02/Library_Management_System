package makbe.library.librarian;

import javax.swing.*;
import java.awt.*;

public class LibrarianHome extends JFrame {

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screen.width - 100;
    int height = screen.height - 100;

    JPanel topBar = new JPanel();
    JLabel universityLabel = new JLabel("MAKBE UNIVERSITY");
    JButton signOut = new JButton("Sign Out");

    JPanel bottomBar = new JPanel();
    JLabel developer = new JLabel(" Developed by Makechi™");
    JLabel version = new JLabel("Library Management System | Version 1.0");
    JRadioButton connected = new JRadioButton("Database Connected.");

    UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();

    Color accent = Color.BLACK;
    boolean connect = false;
    String user;

    public LibrarianHome(JDialog dialog, String user) {
        super("Librarian Panel");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setFont(new Font("Iosevka Term", Font.PLAIN, 20));

        topBar.setBackground(accent);
        topBar.setBounds(0, 0, width, 50);
        topBar.setLayout(null);

        //universityLabel.setIcon(new ImageIcon("image1.jpg"));
        universityLabel.setBounds(20, 0, 250, 50);
        universityLabel.setHorizontalAlignment(JLabel.CENTER);
        universityLabel.setFont(new Font("Iosevka Term", Font.PLAIN, 20));
        universityLabel.setForeground(Color.WHITE);
        topBar.add(universityLabel);

        signOut.setBounds(1020, 10, 100, 30);
        signOut.setFont(new Font("Iosevka Term", Font.PLAIN, 16));
        signOut.addActionListener(e -> {
            setVisible(false);
            dialog.setVisible(true);
        });
        topBar.add(signOut);

        add(topBar);

        JTabbedPane optionsPane = new JTabbedPane(JTabbedPane.LEFT);
        optionsPane.setBounds(10, 55, width - 40, 530);
        optionsPane.setFont(new Font("Iosevka Term", Font.PLAIN, 20));
        optionsPane.add("Home", new LibrarianHomePanel());
        optionsPane.add("Manage Student", new ManageStudentPanel());
        optionsPane.add("Manage Book", new ManageBookPanel());
        optionsPane.add("Search Book", new SearchBookPanel());
        optionsPane.add("Manage Borrower", new ManageBorrowerPanel());
        optionsPane.add("Book Request", new BookRequestPanel());
        optionsPane.add("Report", new ReportPanel());
        optionsPane.add("Soft Book", new SoftBookPanel());
        optionsPane.add("Upload Book", new UploadBookPanel());
        optionsPane.add("Messenger", new MessengerPanel());
        optionsPane.add("Notice Board", new LibrarianNoticeBoardPanel());
        optionsPane.add("Edit Profile", new ProfilePanel());
        optionsPane.add("Change Password", new LibrarianPasswordPanel(user));
        add(optionsPane);

        bottomBar.setBackground(accent);
        bottomBar.setBounds(0, 600, width, 40);
        bottomBar.setLayout(new GridLayout(1, 3));

        developer.setForeground(Color.WHITE);
        developer.setFont(new Font("Iosevka Term", Font.PLAIN, 13));
        bottomBar.add(developer);

        version.setForeground(Color.WHITE);
        version.setHorizontalAlignment(JLabel.CENTER);
        version.setFont(new Font("Iosevka Term", Font.PLAIN, 13));
        bottomBar.add(version);

        connected.setBackground(accent);
        connected.setForeground(Color.WHITE);
        connected.setHorizontalAlignment(JRadioButton.RIGHT);
        connected.setFont(new Font("Iosevka Term", Font.PLAIN, 13));
        connected.setSelected(connect);
        bottomBar.add(connected);

        add(bottomBar);

        try {
            UIManager.setLookAndFeel(looks[3].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        setVisible(true);

    }

    public static void main(String[] args) {
        new LibrarianHome(new JDialog(), "user");
    }

}
