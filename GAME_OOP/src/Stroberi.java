import javax.swing.*;

public class Stroberi extends Makanan{
    public Stroberi(int posisiX, int posisiY){
        setPoint(50);
        setPosisiX(posisiX);
        setPosisiY(posisiY);
        ImageIcon imgChar = new ImageIcon("src/Assets/strawberry.png");
        setMakananLabel(new JLabel(imgChar));
    }

    @Override
    public String toString() {
        return "Avatar Memakan Stroberi, poin bertambah " + getPoint();
    }
}