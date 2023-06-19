import javax.swing.*;
import java.awt.*;

public class TitleFrame extends JFrame {
    private JLabel characterLabel;

    public TitleFrame() {
        setTitle("GAME POU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("src/Assets/bg6.gif");
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("GAME POU");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        ImageIcon gameImageIcon = new ImageIcon("src/Assets/char.gif");
        characterLabel = new JLabel(gameImageIcon);
        characterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(characterLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());

        ImageIcon rulesIcon = new ImageIcon("src/Assets/rules.png");
        JButton rulesButton = new JButton(rulesIcon);
        rulesButton.setPreferredSize(new Dimension(175, 40));
        rulesButton.setBorderPainted(false);
        rulesButton.setFocusPainted(false);
        rulesButton.setContentAreaFilled(false);

        rulesButton.addActionListener(e -> {
            // Menampilkan dialog dengan aturan main
            JOptionPane.showMessageDialog(null, "Game Rules:\n\n1. Collect as many points as you can by catching the food items.\n2. Avoid the bombs, they will end the game.\n3. Use the Left and Right arrow keys to move the character.\n\nGood luck and have fun!", "Game Rules", JOptionPane.INFORMATION_MESSAGE);
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 0, 5, 0);
        buttonPanel.add(rulesButton, constraints);

        ImageIcon startIcon = new ImageIcon("src/Assets/start.png");
        JButton startButton = new JButton(startIcon);
        startButton.setPreferredSize(new Dimension(175, 40));
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);

        startButton.addActionListener(e -> {
            dispose();
            new GameGUI();
        });

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 0, 10, 0);
        buttonPanel.add(startButton, constraints);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);

        setVisible(true);
    ;
}}
