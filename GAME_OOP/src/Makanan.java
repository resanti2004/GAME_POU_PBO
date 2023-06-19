import javax.swing.*;

public abstract class Makanan {
    private int posisiX, posisiY, point;
    private ImageIcon gambar;
    private JLabel makananLabel;

    public JLabel getMakananLabel() {
        return makananLabel;
    }

    public void setMakananLabel(JLabel makananLabel) {
        this.makananLabel = makananLabel;
    }

    public int getPosisiX() {
        return posisiX;
    }

    public void setPosisiX(int posisiX) {
        this.posisiX = posisiX;
    }

    public int getPosisiY() {
        return posisiY;
    }

    public void setPosisiY(int posisiY) {
        this.posisiY = posisiY;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ImageIcon getGambar() {
        return gambar;
    }

    public void setGambar(ImageIcon gambar) {
        this.gambar = gambar;
    }

    public abstract String toString();
}