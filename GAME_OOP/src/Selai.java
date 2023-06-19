import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Selai extends Makanan{
    public Selai(int posisiX, int posisiY){
        setPoint(10);
        setPosisiX(posisiX);
        setPosisiY(posisiY);
        ImageIcon imgChar = new ImageIcon("src/Assets/cake.png");
        setMakananLabel(new JLabel(imgChar));
    }

    @Override
    public String toString() {
        return "Avatar Memakan Selai, poin bertambah " + getPoint();
    }
}