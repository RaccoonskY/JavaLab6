package Example;

import java.awt.*;
class Grid extends Frame {
    public Grid() {
        this.setSize(200, 100);
        String[] items = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        setLayout(new GridLayout (2, 5));
        for (String item : items) {
            add(new Button(item));
        }

        this.setVisible(true);
    }
    public static void main (String[] args) {
        Grid g = new Grid();
    }
}
