import javax.swing.*;

public class Bom extends Makanan{
    public Bom(){
        setPoint(-100);
        ImageIcon imgChar = new ImageIcon("src/Assets/bom.png");
        setMakananLabel(new JLabel(imgChar));
    }
    public Bom(int posisiX, int posisiY){
        setPoint(0);
        setPosisiX(posisiX);
        setPosisiY(posisiY);
        ImageIcon imgChar = new ImageIcon("src/Assets/bom.png");
        setMakananLabel(new JLabel(imgChar));
    }

    @Override
    public String toString() {
        return "DUARRRR!!! Meledak - TAMAT -";
    }
}
