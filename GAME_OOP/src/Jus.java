import javax.swing.*;

public class Jus extends Makanan{
    public Jus(int posisiX, int posisiY){
        setPoint(20);
        setPosisiX(posisiX);
        setPosisiY(posisiY);
        ImageIcon imgChar = new ImageIcon("src/Assets/jus.png");
        setMakananLabel(new JLabel(imgChar));
    }

    @Override
    public String toString() {
        return "Avatar Memakan Jus, poin bertambah " + getPoint();
    }
}