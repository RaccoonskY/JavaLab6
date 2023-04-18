package Example;
import java.awt.*;
import java.awt.event.*;
class Graf5 extends Frame {
    String str = null;
    public Graf5() {
        str = "";
        // Подписаться на объект внутреннего анонимного класса (для мыши)
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent mE) {
                str = "x= " + mE.getX() + " y= " + mE.getY();
                repaint();
            }
        });
        // Подписаться на объект внутреннего анонимного класса (для окна)
        this.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent wE){System.exit (0);}
        });
    }
    public void paint (Graphics g) {
        g.setColor(Color.red);
        g.fillOval(70, 40, 40, 60);
        g.drawString(str, 5, 120);
    }
    public static void main (String[] args) {
        Graf5 gr= new Graf5();
        gr.setSize(100, 150);
        gr.show();
    }
}
