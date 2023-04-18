package Example;
import java.awt.*;
import java.awt.event.*;
class Graf4 extends Frame {
    public Graf4() {
        // Подписаться на объект внутреннего анонимного класса
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent wE) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(70, 40, 40, 60);
    }

    public static void main(String[] args) {
        Graf4 gr = new Graf4();
        gr.setSize(100, 150);
        gr.show();
    }
}
