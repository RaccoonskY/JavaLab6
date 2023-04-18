package Example;
import java.awt.*;
import java.awt.event.*;
class Graf3 extends Frame {
    public Graf3 () {
        // Подписаться на обработчик закрытия окна
        this.addWindowListener (new OurWindowAdapter());
    }
    public void paint (Graphics g) {
        g.setColor(Color.red);
        g.fillOval(70, 40, 40, 60);
    }
    public static void main (String[] args) {
        Graf3 gr= new Graf3();
        gr.setSize(100, 150);
        gr.show();}
}
class OurWindowAdapter extends WindowAdapter {
    public void windowClosing (WindowEvent wE) {System.exit (0);}
}
