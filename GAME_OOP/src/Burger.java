import javax.swing.*;

public class Burger extends Makanan{
    public Burger(int posisiX, int posisiY){
        setPoint(30);
        setPosisiX(posisiX);
        setPosisiY(posisiY);
        ImageIcon imgChar = new ImageIcon("src/Assets/burger2.png");
        setMakananLabel(new JLabel(imgChar));
    }

    @Override
    public String toString() {
        return "Avatar Memakan Burger, poin bertambah " + getPoint();
    }
}