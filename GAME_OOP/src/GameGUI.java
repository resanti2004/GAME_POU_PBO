import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class GameGUI extends JFrame {
    private static int highScore = 0;
    private static int totalScore = 0;
    private JLabel backgroundLabel, characterLabel, totalScoreLabel, highScoreLabel;
    private Timer timer2, timer3, timer4;
    private int characterX, characterY;
    private Makanan objek1;
    private JButton restartButton;
    public GameGUI(){
        setTitle("GAME POU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        characterX = 175;
        characterY = 550;

        ImageIcon imgBg = new ImageIcon("src/Assets/bg.jpg");
        backgroundLabel = new JLabel(imgBg);
        backgroundLabel.setBounds(0,0,800, 1500);
        add(backgroundLabel);

        totalScoreLabel = new JLabel();
        totalScoreLabel.setBounds(200, 0, 50, 20);
        backgroundLabel.add(totalScoreLabel);

        System.out.println(highScore);

        highScoreLabel = new JLabel("Highscore : " + String.valueOf(highScore));
        highScoreLabel.setBounds(175, 50, 100, 20);
        highScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundLabel.add(highScoreLabel);

        ImageIcon imgChar = new ImageIcon("src/Assets/char.gif");
        characterLabel = new JLabel(imgChar);
        characterLabel.setBounds(characterX,characterY,100, 100);
        backgroundLabel.add(characterLabel);

        ImageIcon restartIcon = new ImageIcon("src/Assets/restart.png");
        restartButton = new JButton(restartIcon);
        restartButton.setBounds(175, 300, 100, 40);
        setVisible(true);

        objek1 = getRandomObjek();
        backgroundLabel.add(objek1.getMakananLabel());

        timer2 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveObjek(objek1);
            }
        });

        timer2.setInitialDelay(1000);
        timer2.start();

        timer3 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (totalScore > 100){
                    moveObjek2(objek1);
                }
            }
        });
        timer3.setInitialDelay(100);
        timer3.start();

        timer4 = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (totalScore > 200){
                    moveObjek3(objek1);
                }
            }
        });
        timer4.setInitialDelay(100);
        timer4.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        if (characterX > 0){
                            characterX -= 15;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (characterX < 335){
                            characterX += 15;
                        }
                        break;
                }
                characterLabel.setLocation(characterX, characterY);
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void moveObjek(Makanan makanan){
        if (makanan.getPosisiX() == 0 && makanan.getPosisiY() == 0){
            int randomInt = 1 + (int) (Math.random() * ((335 - 1) + 1));
            makanan.setPosisiX(randomInt);
            makanan.setPosisiY(-50);
            makanan.getMakananLabel().setBounds(makanan.getPosisiX(), makanan.getPosisiY(), 50, 50);
        } else if (makanan.getPosisiY() == getHeight()) {
            backgroundLabel.remove(makanan.getMakananLabel());
            timer2.stop();
            Makanan finalMakanan = getRandomObjek();
            backgroundLabel.add(finalMakanan.getMakananLabel());
            backgroundLabel.revalidate();
            backgroundLabel.repaint();
            if (totalScore>100){
                timer2 = new Timer(5, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (finalMakanan.getPosisiY() <= getHeight()){
                            moveObjek(finalMakanan);
                        }
                    }
                });
            } else {
                timer2 = new Timer(15, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (finalMakanan.getPosisiY() <= getHeight()){
                            moveObjek(finalMakanan);
                        }
                    }
                });
            }
            timer2.start();
        } else if (((characterX - 30) < makanan.getPosisiX() && makanan.getPosisiX() < (characterX + 50)) &&
                ((characterY - 30) < makanan.getPosisiY() && makanan.getPosisiX() < (characterY))) {
            System.out.println(makanan.toString());
            backgroundLabel.remove(makanan.getMakananLabel());
            String namaMakanan = makanan.getClass().getSimpleName();
            timer2.stop();
            if (namaMakanan == "Bom" && ((characterX - 30) < makanan.getPosisiX() && makanan.getPosisiX() < (characterX + 50)) &&
                    ((characterY - 30) < makanan.getPosisiY() && makanan.getPosisiX() < (characterY))){
                if (totalScore > highScore){
                    highScore = totalScore;
                }
                timer3.stop();
                timer4.stop();
                totalScore = 0;
                restartButton.setBounds(175, 300, 100, 40);
                backgroundLabel.add(restartButton);
                backgroundLabel.revalidate();
                backgroundLabel.repaint();
                restartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new GameGUI();
                            }
                        });
                    }
                });
            } else {
                backgroundLabel.remove(totalScoreLabel);
                totalScore += makanan.getPoint();
                totalScoreLabel = new JLabel(String.valueOf(totalScore));
                totalScoreLabel.setBounds(175, 0, 60, 40);
                totalScoreLabel.setFont(new Font("SansSerif", Font.PLAIN,25));
                backgroundLabel.add(totalScoreLabel);
                Makanan finalMakanan = getRandomObjek();
                backgroundLabel.add(finalMakanan.getMakananLabel());
                backgroundLabel.revalidate();
                backgroundLabel.repaint();
                if (totalScore>100){
                    timer2 = new Timer(5, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (finalMakanan.getPosisiY() <= getHeight()){
                                moveObjek(finalMakanan);
                            }
                        }
                    });
                } else {
                    timer2 = new Timer(10, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (finalMakanan.getPosisiY() <= getHeight()){
                                moveObjek(finalMakanan);
                            }
                        }
                    });
                }
                timer2.start();
            }
        } else {
            makanan.getMakananLabel().setBounds(makanan.getPosisiX(),makanan.getPosisiY(),50, 50);
            makanan.setPosisiY(makanan.getPosisiY()+5);
        }
    }

    public void moveObjek2(Makanan makanan){
        if (makanan.getPosisiX() == 0 && makanan.getPosisiY() == 0){
            int randomInt = 1 + (int) (Math.random() * ((335 - 1) + 1));
            makanan.setPosisiX(randomInt);
            makanan.setPosisiY(-50);
            makanan.getMakananLabel().setBounds(makanan.getPosisiX(), makanan.getPosisiY(), 50, 50);
        } else if (makanan.getPosisiY() == getHeight()) {
            backgroundLabel.remove(makanan.getMakananLabel());
            timer3.stop();
            Makanan finalMakanan = getRandomObjek();
            backgroundLabel.add(finalMakanan.getMakananLabel());
            backgroundLabel.revalidate();
            backgroundLabel.repaint();
            if (totalScore>100){
                timer3 = new Timer(3, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (finalMakanan.getPosisiY() <= getHeight()){
                            moveObjek2(finalMakanan);
                        }
                    }
                });
            }
            timer3.start();
        } else if (((characterX - 30) < makanan.getPosisiX() && makanan.getPosisiX() < (characterX + 50)) &&
                ((characterY - 30) < makanan.getPosisiY() && makanan.getPosisiX() < (characterY))) {
            System.out.println(makanan.toString());
            backgroundLabel.remove(makanan.getMakananLabel());
            String namaMakanan = makanan.getClass().getSimpleName();
            timer3.stop();
            if (namaMakanan == "Bom" && ((characterX - 30) < makanan.getPosisiX() && makanan.getPosisiX() < (characterX + 50)) &&
                    ((characterY - 30) < makanan.getPosisiY() && makanan.getPosisiX() < (characterY))){
                if (totalScore > highScore){
                    highScore = totalScore;
                }
                timer2.stop();
                timer4.stop();
                totalScore = 0;
                restartButton.setBounds(175, 300, 100, 40);
                backgroundLabel.add(restartButton);
                backgroundLabel.revalidate();
                backgroundLabel.repaint();
                restartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new GameGUI();
                            }
                        });
                    }
                });
            } else {
                backgroundLabel.remove(totalScoreLabel);
                totalScore += makanan.getPoint();
                totalScoreLabel = new JLabel(String.valueOf(totalScore));
                totalScoreLabel.setBounds(175, 0, 60, 40);
                totalScoreLabel.setFont(new Font("SansSerif", Font.PLAIN,25));
                backgroundLabel.add(totalScoreLabel);
                Makanan finalMakanan = getRandomObjek();
                backgroundLabel.add(finalMakanan.getMakananLabel());
                backgroundLabel.revalidate();
                backgroundLabel.repaint();
                timer3 = new Timer(15, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (finalMakanan.getPosisiY() <= getHeight()){
                            moveObjek2(finalMakanan);
                        }
                    }
                });
                timer3.start();
            }
        } else {
            makanan.getMakananLabel().setBounds(makanan.getPosisiX(),makanan.getPosisiY(),50, 50);
            makanan.setPosisiY(makanan.getPosisiY()+5);
        }
    }

    public void moveObjek3(Makanan makanan){
        if (makanan.getPosisiX() == 0 && makanan.getPosisiY() == 0){
            int randomInt = 1 + (int) (Math.random() * ((335 - 1) + 1));
            makanan.setPosisiX(randomInt);
            makanan.setPosisiY(-50);
            makanan.getMakananLabel().setBounds(makanan.getPosisiX(), makanan.getPosisiY(), 50, 50);
        } else if (makanan.getPosisiY() == getHeight()) {
            backgroundLabel.remove(makanan.getMakananLabel());
            timer4.stop();
            Makanan finalMakanan = getRandomObjek();
            backgroundLabel.add(finalMakanan.getMakananLabel());
            backgroundLabel.revalidate();
            backgroundLabel.repaint();
            if (totalScore>200){
                timer4 = new Timer(3, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (finalMakanan.getPosisiY() <= getHeight()){
                            moveObjek3(finalMakanan);
                        }
                    }
                });
            }
            timer4.start();
        } else if (((characterX - 30) < makanan.getPosisiX() && makanan.getPosisiX() < (characterX + 50)) &&
                ((characterY - 30) < makanan.getPosisiY() && makanan.getPosisiX() < (characterY))) {
            System.out.println(makanan.toString());
            backgroundLabel.remove(makanan.getMakananLabel());
            String namaMakanan = makanan.getClass().getSimpleName();
            timer4.stop();
            if (namaMakanan == "Bom" && ((characterX - 30) < makanan.getPosisiX() && makanan.getPosisiX() < (characterX + 50)) &&
                    ((characterY - 30) < makanan.getPosisiY() && makanan.getPosisiX() < (characterY))){
                if (totalScore > highScore){
                    highScore = totalScore;
                }
                timer2.stop();
                timer3.stop();
                totalScore = 0;
                restartButton.setBounds(175, 300, 100, 40);
                backgroundLabel.add(restartButton);
                backgroundLabel.revalidate();
                backgroundLabel.repaint();
                restartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new GameGUI();
                            }
                        });
                    }
                });
            } else {
                backgroundLabel.remove(totalScoreLabel);
                totalScore += makanan.getPoint();
                totalScoreLabel = new JLabel(String.valueOf(totalScore));
                totalScoreLabel.setBounds(175, 0, 60, 40);
                totalScoreLabel.setFont(new Font("SansSerif", Font.PLAIN,25));
                backgroundLabel.add(totalScoreLabel);
                Makanan finalMakanan = getRandomObjek();
                backgroundLabel.add(finalMakanan.getMakananLabel());
                backgroundLabel.revalidate();
                backgroundLabel.repaint();
                timer4 = new Timer(15, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (finalMakanan.getPosisiY() <= getHeight()){
                            moveObjek3(finalMakanan);
                        }
                    }
                });
                timer4.start();
            }
        } else {
            makanan.getMakananLabel().setBounds(makanan.getPosisiX(),makanan.getPosisiY(),50, 50);
            makanan.setPosisiY(makanan.getPosisiY()+5);
        }
    }

    public Makanan getRandomObjek(){
        int randomInt = 1 + (int) (Math.random() * ((5 - 1) + 1));
        if (randomInt == 1){
            return new Stroberi(0,0);
        } else if (randomInt == 2) {
            return new Jus(0,0);
        } else if (randomInt == 3) {
            return new Selai(0,0);
        } else if (randomInt == 4) {
            return new Burger(0,0);
        } else {
            return new Bom(0,0);
        }
    }
}


