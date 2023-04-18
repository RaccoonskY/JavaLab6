/*
  Вариант 12 - 556
  УО + ДО
* 1000101100 - двоичная запись числа
0:  Число ФиО 1 – любое
0:  ФиО 1 –фигуры
1:  Задание цвета текста и заливки ФиО
    2 – стандарт.элемент выбора цвета; [+]

1: Выбор запускаемого ФиО
    2 – из текст.поля (круг,овал…); [+]
0: Задание начальной скорости ФиО
    1 – указанием в текст. поле; [+]
1: Способ выбора запущенного ФиО
    2 – из текст.поля, где вводится номер ФиО; [+]
0: Присвоение номера ФиО
    1 – авто; [+]
0: Возможность смены номера ФиО из УО
    1 – нет [+]
0: Регулировка скорости перемещения выбран-го ФиО
    1 – указанием в текст. поле; [+]
1: Изменения размера окна отображения ФиО
    2 – да; (т.е.отражение ФиО в новых границах) [+]

*
*
*
*
* */


package Individual;
import javax.swing.*;
import java.util.*;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
public class Individual {
    static int count;
    public static void main(String[] args){
        count = 0;
        Figures balls = new Figures();
    }
}
class Figures extends Frame implements Observer, ActionListener, ItemListener {
    private LinkedList LL = new LinkedList();

    private Frame f;
    private Container f_speedChange;

    private Button b;
    private Button b_spChange;

    private JColorChooser c;
    private TextField tf;
    private TextField tf_type;

    private TextField tf_speed;
    private TextField tf_spChange;


    Figures(){
        this.addWindowListener(new WindowAdapter2());
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Figure._height = e.getComponent().getHeight();
                Figure._width = e.getComponent().getWidth();
            }
        });
        f = new Frame();
        f_speedChange = new Container();
        b_spChange = new Button("Speed");
        b_spChange.setSize(100,100);
        tf_spChange = new TextField();
        tf_spChange.setSize(100,100);
        f_speedChange.setLayout(new GridLayout(2,1));
        f_speedChange.add(tf_spChange);
        f_speedChange.add(b_spChange);
        f.setSize(new Dimension(1000,500));
        f.setTitle("Контроль");
        f.setLayout(new GridLayout(1,6));
        f.addWindowListener(new WindowAdapter2());
        b = new Button("Пуск");
        //b.setSize(new Dimension(10,40));
        b.setActionCommand("OK");
        b_spChange.setActionCommand("СКОРОСТЬ");
        b.addActionListener(this);
        b_spChange.addActionListener(this);
        f.add(b, new Point(20,20));
        c = new JColorChooser();

/*        c.addItem("Синий");
        c.addItem("Зелёный");
        c.addItem("Красный");
        c.addItem("Чёрный");c.addItem("Жёлтый");
        c.addItemListener(this);
        ВЫБОР ЦВЕТА ИЗ ТЕСТ
*/
        f.add(c, new Point(60,20));
        tf = new TextField();
        tf_type = new TextField();
        tf_type.setSize(100,100);
        tf_speed = new TextField();
        tf_speed.setSize(100,100);
        f.add(tf_type);
        f.add(tf_speed);
        f.add(tf);
        f.add(f_speedChange);
        f.setVisible(true);
        this.setSize(500,200);
        this.setVisible(true);
        this.setLocation(100, 150);
    }
    public void update(Observable o, Object arg) {
        Figure figure = (Figure)arg;
        System.out.println ("x= " + figure.thr.getName() + figure.x+" d_X: "+figure.x_start+" d_Y: "+ figure.y_start+ " speed: " +figure.f_speed);
        repaint();
    }
    public void paint (Graphics g) {
        if (!LL.isEmpty()){
            for (Object LL1 : LL) {
                Figure figure = (Figure) LL1;
                g.setColor(figure.col);
                switch (figure.f_type) {
                case "oval"->g.drawOval(figure.x, figure.y, 20, 20);
                case "rect"->g.drawRect(figure.x, figure.y, 20, 20);
                case "line"->g.drawLine(figure.x, figure.y, figure.x+20, figure.y+20);
                default-> g.drawOval(figure.x, figure.y, 20, 20);
                }
                //g.drawOval(figure.x, figure.y, 20, 20);
            }
        }
    }
    public void itemStateChanged (ItemEvent iE) {}
    public void actionPerformed (ActionEvent aE) {
        String str = aE.getActionCommand();
        if (str.equals ("OK") && !this.tf_type.getText().equals("")){
            var speed = 1;
/*            switch (c.getSelectedIndex()) {
                case 0: col= Color.blue; break;
                case 1: col= Color.green; break;
                case 2: col= Color.red; break;
                case 3: col= Color.black; break;
                case 4: col= Color.yellow; break;
            }*/
            if(!this.tf_speed.getText().equals("")){
                speed = Integer.parseInt(this.tf_speed.getText());
            }

            Figure figure = new Figure(c.getColor(), this.tf_type.getText(), speed);
            LL.add(figure);
            figure.addObserver(this);
        } else if (str.equals("СКОРОСТЬ") && !this.tf.getText().isEmpty() && !this.tf_spChange.getText().isEmpty()) {

            var id = Integer.parseInt(this.tf.getText());
            if(id >= 0 && id < LL.size()){
                var cur_fig = (Figure)LL.get(id);
                cur_fig.f_speed = Integer.parseInt(tf_spChange.getText());
                LL.set(id,cur_fig);
            }
        }
        repaint();
    }
}
class Figure extends Observable implements Runnable {

    static int _width;
    static int _height;
    Thread thr;
    private boolean xplus;
    private boolean yplus;
    int x; int y;
    Color col;
    String f_type;
    Integer f_speed;

    int x_start;
    int y_start;
    public Figure(Color col, String type, Integer speed) {
        f_speed = speed;
        f_type = type;
        var random = new Random();
        xplus = random.nextBoolean(); yplus = random.nextBoolean();
        x = getRandomNumber(25,_width-25); y =getRandomNumber(25,_height-25);
        x_start = getRandomNumber(-5,5); y_start=getRandomNumber(-5,5);
        while (x_start == 0 && y_start == 0){
            x_start = getRandomNumber(-5,5); y_start=getRandomNumber(-5,5);
        }

        this.col = col;
        Individual.count++;
        thr = new Thread(this, Individual.count+":");
        thr.start();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void run(){
        while (true){
            if(x>_width-25) xplus = false;
            if(x<5) xplus = true;
            if(y>_height-25) yplus = false;
            if(y<29) yplus = true;
            if(xplus) x+= Math.abs(x_start*f_speed); else x-=Math.abs(x_start*f_speed); ///!
            if(yplus) y+= Math.abs(y_start*f_speed); else y-=Math.abs(y_start*f_speed); ///!
            setChanged();notifyObservers (this);
            try{Thread.sleep (10);}
            catch (InterruptedException e){}
        }
    }
}
class WindowAdapter2 extends WindowAdapter {
    public void windowClosing (WindowEvent wE) {System.exit (0);}


}
