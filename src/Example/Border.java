package Example;
import java.awt.*;
class Border extends Frame {
    public Border() {
        this.setSize(200, 100);
        String[] items = {"1", "2", "3", "4", "5"};
        String[] locs = {"North", "South", "East", "West", "Center"};
        setLayout(new BorderLayout ( ));
        for (int i= 0; i < 5; i++) {
            /*this.*/add (locs[i], new Button (items[i]));
        }
        this.show ( );
    }
    public static void main (String[] args) {
        Border b = new Border();
    }
}