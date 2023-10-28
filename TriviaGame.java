import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

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
    JButton subjectButton1, subjectButton2, subjectButton3, logoutButton,retroGamesButton;
    JPanel panel;

    Menu() {
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        subjectButton1 = createStyledButton("Subject 1", buttonFont);
        subjectButton2 = createStyledButton("Subject 2", buttonFont);
        subjectButton3 = createStyledButton("Subject 3", buttonFont);
        retroGamesButton = createStyledButton("Retro Games", buttonFont);
        

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
        panel.add(retroGamesButton);


        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createLogoutButton(), BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

       retroGamesButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        dispose(); // Close the Menu window
        RetroGameHub retroGameHub = new RetroGameHub(); // Create the RetroGameHub window
        retroGameHub.setVisible(true); // Display the RetroGameHub window
    }
});

        subjectButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OnlineTestBegin1("Subject 1 Test");
            }
        });
        subjectButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OnlineTestBegin2("Subject 2 Test");
                
            }
        });
        subjectButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OnlineTestBegin3("Subject 3 Test");
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

class RetroGameHub extends JFrame {
    JButton retroGame1Button,logoutButton,retroGame2Button,retroGame3Button;
    JPanel panel;

    RetroGameHub() {
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        retroGame1Button = createStyledButton("Snake", buttonFont);
        retroGame2Button = createStyledButton("GuessTheNumber", buttonFont);
        retroGame3Button = createStyledButton("Pong", buttonFont);
        logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(retroGame1Button);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(retroGame2Button);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(retroGame3Button);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createLogoutButton(), BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

       
        retroGame1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Write code here
                dispose();
                SnakeGame snakeGame = new SnakeGame();
                snakeGame.setVisible(true);
            }
        });
         retroGame2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Write code here
                dispose();
                GuessTheNumber guessTheNumber = new GuessTheNumber();
                guessTheNumber.setVisible(true);
            }
        });
        retroGame3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Write code here
                dispose();
                HangmanGame pong= new HangmanGame();
                pong.setVisible(true);
            }
        });
        
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                openLoginScreen();
            }
        });

        setTitle("Retro Hub");
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


class HangmanGame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    
    private String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
    private String wordToGuess;
    private StringBuilder guessedWord;
    private int attemptsLeft = 6;
    private JTextField letterInputField;
    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JLabel resultLabel;
    
    public HangmanGame() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        initializeGame();
        setupUI();
        addListeners();
    }
    
    private void initializeGame() {
        Random random = new Random();
        wordToGuess = words[random.nextInt(words.length)];
        guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));
    }
    
    private void setupUI() {
        setLayout(new FlowLayout());
        
        wordLabel = new JLabel("Word: " + guessedWord);
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft);
        resultLabel = new JLabel();
        
        letterInputField = new JTextField(1);
        
        add(wordLabel);
        add(attemptsLabel);
        add(letterInputField);
        add(resultLabel);
    }
    
    private void addListeners() {
        letterInputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (attemptsLeft > 0) {
                    String input = letterInputField.getText().toLowerCase();
                    if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                        processGuess(input.charAt(0));
                        letterInputField.setText("");
                    }
                }
            }
        });
    }
    
    private void processGuess(char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess && guessedWord.charAt(i) == '_') {
                guessedWord.setCharAt(i, guess);
                correctGuess = true;
            }
        }
        
        if (!correctGuess) {
            attemptsLeft--;
        }
        
        updateUI();
        checkGameStatus();
    }
    
    private void updateUI() {
        wordLabel.setText("Word: " + guessedWord);
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
    }
    
    private void checkGameStatus() {
        if (guessedWord.toString().equals(wordToGuess)) {
            resultLabel.setText("Congratulations! You guessed the word: " + wordToGuess);
            letterInputField.setEnabled(false);
            dispose();
            new GameOverDialog2(this, "You Won", "You Won").setVisible(true);
        } else if (attemptsLeft == 0) {
            resultLabel.setText("You ran out of attempts. The word was: " + wordToGuess);
            letterInputField.setEnabled(false);
            dispose();
            new GameOverDialog2(this, "You Lost", "You Won").setVisible(true);
        }
    }
    
}

class GameOverDialog2 extends JDialog {
    public GameOverDialog2(JFrame parent, String title, String message) {
        super(parent,title, true);
        setSize(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel messageLabel = new JLabel(message);
        JButton closeButton = new JButton("Close");
        JButton goBackButton = new JButton("Go Back");
        JButton restartButton = new JButton("Restart");

        
        // Add action listeners for the buttons
        restartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Close the current SnakeGame instance
        parent.dispose();

        // Create and show a new HangMan instance
        SwingUtilities.invokeLater(() -> {
            HangmanGame newGame = new HangmanGame();
            newGame.setVisible(true);
        });
    }
});

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose(); // Close the dialog
            }
        });

         goBackButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.dispose();
        // Create and show the RetroGameHub window
        RetroGameHub retroGameHub = new RetroGameHub();
        retroGameHub.setVisible(true);

        // Close the current GameOverDialog
        dispose();
    }
    });

        add(messageLabel);
        add(closeButton);
        add(goBackButton);
        add(restartButton);
    }
}



class GuessTheNumber extends JFrame {
    private int numberToGuess;
    private int numberOfTries;
    private final JLabel messageLabel;
    private final JTextField inputField;
    private final JButton guessButton;

    public GuessTheNumber() {
        setTitle("Guess the Number");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        messageLabel = new JLabel("I'm thinking of a number between 1 and 100.");
        inputField = new JTextField(10);
        guessButton = new JButton("Guess");

        numberToGuess = new Random().nextInt(10) + 1;
        numberOfTries = 0;

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(messageLabel);
        add(inputField);
        add(guessButton);
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(inputField.getText());
            numberOfTries++;

            if (userGuess < numberToGuess) {
                messageLabel.setText("Higher. Try again.");
            } else if (userGuess > numberToGuess) {
                messageLabel.setText("Lower. Try again.");
            } else {
                messageLabel.setText("Congratulations! You guessed the number in " + numberOfTries + " tries.");
                guessButton.setEnabled(false);
                dispose();
                new GameOverDialog1(this, "You Won", "You Won in " + numberOfTries + " tries.").setVisible(true);
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Invalid input. Enter a number between 1 and 100.");
        }
    }
}
class GameOverDialog1 extends JDialog {
    public GameOverDialog1(JFrame parent, String title, String message) {
        super(parent, title, true);
        setSize(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel messageLabel = new JLabel(message);
        JButton closeButton = new JButton("Close");
        JButton goBackButton = new JButton("Go Back");
        JButton restartButton = new JButton("Restart");

        
        // Add action listeners for the buttons
        restartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Close the current SnakeGame instance
        parent.dispose();

        // Create and show a new SnakeGame instance
        SwingUtilities.invokeLater(() -> {
            GuessTheNumber newGame = new GuessTheNumber();
            newGame.setVisible(true);
        });
    }
});

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose(); // Close the dialog
            }
        });

         goBackButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.dispose();
        // Create and show the RetroGameHub window
        RetroGameHub retroGameHub = new RetroGameHub();
        retroGameHub.setVisible(true);

        // Close the current GameOverDialog
        dispose();
    }
    });

        add(messageLabel);
        add(closeButton);
        add(goBackButton);
        add(restartButton);
    }
}
class SnakeGame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private int snakeX, snakeY, foodX, foodY;
    private int[] snakeXs, snakeYs;
    private int snakeLength;
    
    private boolean gameover = false;
    private Timer timer;
    private Random random;
    private String direction = "RIGHT"; // Initialize with a default direction

    SnakeGame() {
    setTitle("Snake Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setResizable(false);
    addKeyListener(new KeyHandler());
    snakeX = WIDTH / 2;
    snakeY = HEIGHT / 2;
    snakeXs = new int[WIDTH * HEIGHT];
    snakeYs = new int[WIDTH * HEIGHT];
    snakeXs[0] = snakeX; // Initialize snakeXs[0]
    snakeYs[0] = snakeY; // Initialize snakeYs[0]
    snakeLength = 1;
    random = new Random();
    foodX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
    foodY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    timer = new Timer(100, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (!gameover) {
                move();
                checkCollision();
                repaint();
            }
        }
    });
    timer.start();
}


    @Override
    public void paint(Graphics g) {
        // Draw the game elements, including the snake and food
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the food
        g.setColor(Color.RED);
        g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

        // Draw the snake
        for (int i = 0; i < snakeLength; i++) {
            if (i == 0) {
                g.setColor(Color.GREEN);
                g.fillRect(snakeXs[i], snakeYs[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.setColor(Color.CYAN);
                g.fillRect(snakeXs[i], snakeYs[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    private void move() {
    // Store the current head position
    int prevX = snakeXs[0];
    int prevY = snakeYs[0];

    // Move the head of the snake in the current direction
    switch (direction) {
        case "UP":
            snakeYs[0] -= UNIT_SIZE;
            break;
        case "DOWN":
            snakeYs[0] += UNIT_SIZE;
            break;
        case "LEFT":
            snakeXs[0] -= UNIT_SIZE;
            break;
        case "RIGHT":
            snakeXs[0] += UNIT_SIZE;
            break;
    }

    // Move the rest of the body
    for (int i = 1; i < snakeLength; i++) {
        int tempX = snakeXs[i];
        int tempY = snakeYs[i];
        snakeXs[i] = prevX;
        snakeYs[i] = prevY;
        prevX = tempX;
        prevY = tempY;
    }
}

   private void checkCollision() {
    // Check for collision with the wall
    if (snakeXs[0] < 0) {
        snakeXs[0] = WIDTH - UNIT_SIZE; // Wrap the snake to the right side
    } else if (snakeXs[0] >= WIDTH) {
        snakeXs[0] = 0; // Wrap the snake to the left side
    }

    if (snakeYs[0] < 0) {
        snakeYs[0] = HEIGHT - UNIT_SIZE; // Wrap the snake to the bottom
    } else if (snakeYs[0] >= HEIGHT) {
        snakeYs[0] = 0; // Wrap the snake to the top
    }

    // Check for collision with the food
    if (snakeXs[0] == foodX && snakeYs[0] == foodY) {
        // Increase the snake's length
        snakeLength++;
        // Generate new food coordinates
        foodX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        foodY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    // Check for self-collision
    for (int i = 1; i < snakeLength; i++) {
        if (snakeXs[0] == snakeXs[i] && snakeYs[0] == snakeYs[i]) {
            gameover = true;
        }
    }
    if (gameover) {
        new GameOverDialog(this).setVisible(true);
    }
}




    private class KeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP && !direction.equals("DOWN")) {
            direction = "UP";
        } else if (key == KeyEvent.VK_DOWN && !direction.equals("UP")) {
            direction = "DOWN";
        } else if (key == KeyEvent.VK_LEFT && !direction.equals("RIGHT")) {
            direction = "LEFT";
        } else if (key == KeyEvent.VK_RIGHT && !direction.equals("LEFT")) {
            direction = "RIGHT";
        }
    }
}

    
}
class GameOverDialog extends JDialog {
    private SnakeGame previousGame;  // Reference to the previous SnakeGame instance

    GameOverDialog(SnakeGame parent) {
        super(parent, "Game Over", true);
        this.previousGame = parent;  // Store a reference to the previous game

        // Create components for the dialog
        JLabel messageLabel = new JLabel("Game Over");
        JButton restartButton = new JButton("Restart");
        JButton goBackButton = new JButton("Go Back to Retro Game Menu");

        // Set up the dialog layout
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(restartButton);
        buttonPanel.add(goBackButton);
        add(messageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();

        // Add action listeners for the buttons
        restartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Close the current SnakeGame instance
        parent.dispose();

        // Create and show a new SnakeGame instance
        SwingUtilities.invokeLater(() -> {
            SnakeGame newGame = new SnakeGame();
            newGame.setVisible(true);
        });
    }
});


        goBackButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.dispose();
        // Create and show the RetroGameHub window
        RetroGameHub retroGameHub = new RetroGameHub();
        retroGameHub.setVisible(true);

        // Close the current GameOverDialog
        dispose();
    }
});

    }
}


class OnlineTestBegin1 extends JFrame implements ActionListener {
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

    OnlineTestBegin1(String title) {
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

class OnlineTestBegin2 extends JFrame implements ActionListener {
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

    OnlineTestBegin2(String title) {
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


class OnlineTestBegin3 extends JFrame implements ActionListener {
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

    OnlineTestBegin3(String title) {
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
