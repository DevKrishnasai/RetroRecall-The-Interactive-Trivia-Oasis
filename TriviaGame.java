import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

class Login extends JFrame {
    JButton loginButton;
    JPanel panel;
    JLabel userLabel, passLabel, err;
    JTextField usernameField, passwordField;

    Login() {
        Color customGreen = new Color(0, 153, 51);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        userLabel = new JLabel("Username:");
        userLabel.setFont(labelFont);
        userLabel.setPreferredSize(new Dimension(100, 25));

        usernameField = new JTextField(10);
        usernameField.setFont(labelFont);

        passLabel = new JLabel("Password:");
        passLabel.setFont(labelFont);
        passLabel.setPreferredSize(new Dimension(100, 25));

        passwordField = new JPasswordField(8);

        loginButton = new JButton("Login");
        loginButton.setFont(buttonFont);
        loginButton.setBackground(customGreen);
        loginButton.setForeground(Color.WHITE);

        err = new JLabel();
        err.setFont(labelFont);
        err.setForeground(Color.RED);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(loginButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add extra space
        panel.add(err); // Add the error label

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getText());

                if (!username.isEmpty() && !password.isEmpty()) {
                    
                    loginButton.setText("Logging in...");
                    loginButton.setEnabled(false);

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            err.setText("Login successful!");
                            loginButton.setText("Login"); // Reset the login button text
                            loginButton.setEnabled(true);
                            dispose();
                            openMenuScreen();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else {
                    err.setText("Enter both username and password");
                }
            }
        });

        setTitle("Login Form");
        getContentPane().setBackground(new Color(240, 240, 240));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
    }

    private void openMenuScreen() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}

// ... (Other classes and main method remain the same)

class Menu extends JFrame {
    JButton subjectButton1, subjectButton2, subjectButton3, logoutButton;
    JPanel panel;

    Menu() {
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        subjectButton1 = createStyledButton("Subject 1", buttonFont);
        subjectButton2 = createStyledButton("Subject 2", buttonFont);
        subjectButton3 = createStyledButton("Subject 3", buttonFont);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(subjectButton1);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(subjectButton2);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(subjectButton3);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createLogoutButton(), BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        subjectButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OnlineTestBegin("Subject 1 Test");
            }
        });
        subjectButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code for Subject 2
            }
        });
        subjectButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code for Subject 3
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                openLoginScreen();
            }
        });

        setTitle("Subject Selection");
        getContentPane().setBackground(new Color(240, 240, 240));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
    }

    private JButton createStyledButton(String label, Font font) {
        JButton button = new JButton(label);
        button.setFont(font);
        button.setPreferredSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JPanel createLogoutButton() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Set the background color of the bottom panel to white
        bottomPanel.setBackground(Color.WHITE);

        // Create a red-colored logout button
        logoutButton = new JButton("Logout");
        // logoutButton.setFont(buttonFont);
        logoutButton.setBackground(Color.RED); // Set the background color to red
        logoutButton.setForeground(Color.WHITE);

        bottomPanel.add(logoutButton);
        return bottomPanel;
    }

    private void openLoginScreen() {
        Login login = new Login();
        login.setVisible(true);
    }
}

class OnlineTestBegin extends JFrame implements ActionListener {
    private JLabel questionLabel, timeLabel;
    private JRadioButton[] options;
    private JButton saveAndNextButton, saveForLaterButton, resultButton;
    private ButtonGroup optionGroup;
    private int currentQuestion = 0;
    private int score = 0;
    private List<Integer> savedQuestions = new ArrayList<>();

    private int[] correctAnswers = { 2, 1, 0, 1, 2, 1, 2, 2, 2, 1 }; // Correct answers for each question
    private String[] questions = {
            "Who is known as the father of the Java programming language?",
            "What is the number of primitive data types in Java?",
            "Where is the system class defined?",
            "Which component is used to compile, debug, and execute the Java programs?",
            "What is the extension of Java code files?",
            "What is the extension of compiled Java classes?",
            "Which of these are selection statements in Java?",
            "Which keyword is used to define interfaces in Java?",
            "What is the numerical range of a char data type in Java?",
            "Which provides runtime environment for Java byte code to be executed?"
    };
    private String[][] optionsText = {
            { "Charles Babbage", "James Ford", "M.P. Java", "Blais Pascal" },
            { "6", "7", "8", "9" },
            { "java.lang package", "java.util package", "java.io package", "None" },
            { "JRE", "JDK", "JVM", "JIT" },
            { ".js", ".JVM", ".JAVA", ".CLASS" },
            { ".TEXT", ".CLASS", ".JS", "(All of the above)" },
            { "BREAK", "IFELSE", "IF", "None" },
            { "Intf", "intf", "interface", "Interface" },
            { "1 to 256", "-128 to 127", "0 to 65535", "0 to 32767" },
            { "JDK", "JVM", "JRE", "JAVAC" }
    };

    private ScheduledExecutorService executorService;
    private int timeRemaining = 20; // Initial time for each question

    private JPanel savedQuestionsPanel;

    OnlineTestBegin(String title) {
        super(title);
        setLayout(null);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        questionLabel.setBounds(20, 30, 550, 40);
        add(questionLabel);

        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, 80 + i * 30, 500, 30);
            options[i].addActionListener(this);
            optionGroup.add(options[i]);
            add(options[i]);
        }

        timeLabel = new JLabel();
        timeLabel.setBounds(500, 10, 100, 20);
        add(timeLabel);

        saveAndNextButton = new JButton("Save and Next");
        saveAndNextButton.setBounds(100, 300, 140, 30);
        saveAndNextButton.addActionListener(this);
        add(saveAndNextButton);

        saveForLaterButton = new JButton("Save for later");
        saveForLaterButton.setBounds(260, 300, 150, 30);
        saveForLaterButton.addActionListener(this);
        add(saveForLaterButton);

        resultButton = new JButton("Result");
        resultButton.setBounds(420, 300, 100, 30);
        resultButton.addActionListener(this);
        add(resultButton);

        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(this::updateTimer, 0, 1, TimeUnit.SECONDS);

        savedQuestionsPanel = new JPanel();
        savedQuestionsPanel.setLayout(new BoxLayout(savedQuestionsPanel, BoxLayout.Y_AXIS));
        savedQuestionsPanel.setBounds(600, 0, 200, 400);
        add(savedQuestionsPanel);

        loadQuestion(currentQuestion);
        setVisible(true);
    }

    private void loadQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < questions.length) {
            questionLabel.setText(questions[questionIndex]);
            for (int i = 0; i < options.length; i++) {
                options[i].setText(optionsText[questionIndex][i]);
                options[i].setSelected(false);
            }
        }
    }

    private void updateTimer() {
        SwingUtilities.invokeLater(() -> {
            timeLabel.setText("Time left: " + timeRemaining);
            if (timeRemaining <= 0) {
                timeLabel.setText("Time's up!");
                saveAndNextButton.setEnabled(false);
                saveForLaterButton.setEnabled(false);
                resultButton.doClick(); // Automatically switch to the next question
            }
            timeRemaining--;
        });
    }

    private void switchQuestion() {
        if (currentQuestion < questions.length - 1) {
            currentQuestion++;
            clearOptionSelection();
            loadQuestion(currentQuestion);
            clearOptionBackground();
            timeRemaining = 20; // Reset the timer for the next question
            saveAndNextButton.setEnabled(true);
            saveForLaterButton.setEnabled(true);
        } else {
            showResult();
        }
    }

    private void clearOptionSelection() {
        optionGroup.clearSelection();
        for (JRadioButton option : options) {
            option.setBackground(null);
        }
    }

    private void showResult() {
        int totalQuestions = questions.length;
        double percentage = (double) score / totalQuestions * 100;
        String resultMessage = "Your score: " + score + "/" + totalQuestions + " (" + percentage + "%)";
        JOptionPane.showMessageDialog(this, resultMessage);
        executorService.shutdownNow();
        dispose();
        Menu menu = new Menu(); // Return to the menu
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveAndNextButton) {
            if (checkAnswer()) {
                score++;
            }
            switchQuestion();
        } else if (e.getSource() == saveForLaterButton) {
            savedQuestions.add(currentQuestion);
            updateSavedQuestionsPanel();
            switchQuestion();
        } else if (e.getSource() == resultButton) {
            if (checkAnswer()) {
                score++;
            }
            switchQuestion();
        }
    }

    private boolean checkAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected() && i == correctAnswers[currentQuestion]) {
                return true;
            }
        }
        return false;
    }

    private void updateSavedQuestionsPanel() {
        savedQuestionsPanel.removeAll();
        for (Integer question : savedQuestions) {
            JButton questionButton = new JButton("Q" + (question + 1));
            questionButton.addActionListener(e -> loadSavedQuestion(question));
            savedQuestionsPanel.add(questionButton);
        }
        savedQuestionsPanel.revalidate();
        savedQuestionsPanel.repaint();
    }

    private void loadSavedQuestion(int questionIndex) {
        currentQuestion = questionIndex;
        loadQuestion(questionIndex);
        clearOptionBackground();
        timeRemaining = 20;
        saveAndNextButton.setEnabled(true);
        saveForLaterButton.setEnabled(true);
    }

    private void clearOptionBackground() {
        for (JRadioButton option : options) {
            option.setBackground(null);
        }
    }
}

public class TriviaGame {
    public static void main(String args[]) {
        Login login = new Login();
        login.setVisible(true);
    }
}
