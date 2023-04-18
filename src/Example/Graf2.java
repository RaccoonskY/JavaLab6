package Example;
import java.awt.*;
class Graf2 extends Frame {
    public void paint (Graphics g) {
        g.setColor(Color.red);
        g.fillOval(70, 40, 40, 60);}
    public boolean handleEvent (Event e) {
        if (e.id == Event.WINDOW_DESTROY) System.exit(0);
        return (super.handleEvent(e));
    }
    public static void main (String[] args) {
        Graf2 gr= new Graf2();
        gr.setSize(100, 150); gr.show();
    }
}