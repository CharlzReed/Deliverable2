package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.example.CSVReader;
import com.example.Course;
import com.example.Item;
import com.example.Library;
import com.example.User;

public class FacultyMainMenu extends JFrame {
    private User currentUser;
    private JPanel viewTextbooksPanel;
    private JPanel cardPanel = new JPanel(new CardLayout());
    private static final Color backgroundColor = new Color(245, 245, 245);
    private static final Color buttonColor = new Color(220, 220, 220);
    private static final Color textColor = new Color(50, 50, 50);

    public FacultyMainMenu(User currentUser) {
        this.currentUser = currentUser;
        setupUIManager();
        initializeUI();
    }

    private void setupUIManager() {
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
    }

    private void initializeUI() {
        setTitle("Faculty Dashboard - Welcome, " + currentUser.getName());
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel userInfoPanel = createUserInfoPanel();
        JPanel navigationPanel = createNavigationPanel();

        setupCardPanel();

        add(userInfoPanel, BorderLayout.NORTH);
        add(navigationPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createUserInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        JLabel helloUser = new JLabel("Hello, " + currentUser.getName() + " - Faculty", JLabel.LEFT);
        helloUser.setBorder(new EmptyBorder(0, 10, 0, 0));

        panel.add(helloUser, BorderLayout.WEST);

        return panel;
    }

    private JPanel createNavigationPanel() {
        String[] features = {
                "View Courses", "View Textbooks", "Check Textbook Updates", "Notify Library"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (String feature : features) {
            JButton button = createButton(feature);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        return panel;
    }

    private JPanel createViewCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea coursesArea = new JTextArea();
        coursesArea.setEditable(false);

        StringBuilder coursesText = new StringBuilder("You are teaching the following courses this semester:\n");
        for (Course course : currentUser.getCourses()) {
            coursesText.append(course.courseName)
                    .append(" (")
                    .append(course.courseCode)
                    .append(") - Duration: ")
                    .append(ChronoUnit.DAYS.between(course.startDate, course.endDate))
                    .append(" days left\n");
        }

        coursesArea.setText(coursesText.toString());
        panel.add(new JScrollPane(coursesArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createViewTextbooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textbooksArea = new JTextArea();
        textbooksArea.setEditable(false);

        StringBuilder textbooksText = new StringBuilder();
        for (Course course : currentUser.getCourses()) {
            Item textbook = Library.course2textbook.get(course);
            textbooksText.append("For \"").append(course.courseName).append("\" the textbook is: ")
                    .append(textbook.name).append("\n");
        }

        textbooksArea.setText(textbooksText.toString());
        panel.add(new JScrollPane(textbooksArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCheckAndUpdateTextbooksPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        boolean updatesAvailable = false;

        for (Course course : currentUser.getCourses()) {
            List<Item> newEditions = Library.checkForNewEditions(course);
            for (Item newEdition : newEditions) {
                updatesAvailable = true;
                JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel courseLabel = new JLabel("Course: " + course.getCourseName() + " - New Textbook Edition: "
                        + newEdition.getName() + " (" + newEdition.getEdition() + ")");
                JButton updateButton = new JButton("Update to this edition");
                updateButton.addActionListener(e -> {
                    Library.course2textbook.put(course, newEdition);
                    JOptionPane.showMessageDialog(FacultyMainMenu.this,
                            "Textbook for " + course.getCourseName() + " updated to " + newEdition.getName(),
                            "Update Successful", JOptionPane.INFORMATION_MESSAGE);

                    CSVReader.writeALL();
                    refreshViewTextbooksPanel();
                });
                coursePanel.add(courseLabel);
                coursePanel.add(updateButton);
                panel.add(coursePanel);
            }
        }

        if (!updatesAvailable) {
            panel.add(new JLabel("No new textbook editions available."));
        }

        return panel;
    }

    private JPanel createNotificationPanel() {
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));

        String notifications = Library.getNotification();

        if (!notifications.isEmpty()) {
            JTextArea notificationArea = new JTextArea(notifications);
            notificationArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(notificationArea);
            notificationPanel.add(scrollPane);
        } else {
            notificationPanel.add(new JLabel("All textbooks are currently available."));
        }

        return notificationPanel;
    }

    private void setupCardPanel() {
        JPanel viewCoursesPanel = createViewCoursesPanel();
        viewTextbooksPanel = createViewTextbooksPanel();
        JPanel checkUpdatesPanel = createCheckAndUpdateTextbooksPanel();
        JPanel notificationPanel = createNotificationPanel();
        
        cardPanel.add(viewCoursesPanel, "View Courses");
        cardPanel.add(viewTextbooksPanel, "View Textbooks");
        cardPanel.add(checkUpdatesPanel, "Check Textbook Updates");
        cardPanel.add(notificationPanel, "Notify Library");
    }

    private void refreshViewTextbooksPanel() {
        cardPanel.remove(viewTextbooksPanel);
        viewTextbooksPanel = createViewTextbooksPanel();
        cardPanel.add(viewTextbooksPanel, "View Textbooks");
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "View Textbooks");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.setBackground(buttonColor);
        button.setForeground(textColor);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.addActionListener(e -> ((CardLayout) cardPanel.getLayout()).show(cardPanel, name));
        return button;
    }

    private JPanel createFeatureCard(String featureName) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h1>" + featureName + "</h1></html>"));

        return panel;
    }

}
