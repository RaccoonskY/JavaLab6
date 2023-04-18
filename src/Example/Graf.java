package Example;
import java.awt.*;

public class Graf extends Frame {
    public void paint (Graphics g) {
        g.drawLine (5, 35, 100, 35); // Нарисовать линию
        g.setColor (Color.blue); // Установить синий цвет
        g.drawRect (5, 40, 40, 60); // Нарисовать прямоугольник
        g.setColor (Color.red); // Установить красный цвет
        g.fillOval (70, 40, 40, 60); // Нарисовать заполненный эллипс
        Font f= new Font ("Verdana", Font.BOLD, 30);
        g.setFont (f);
        // Установить шрифт
        g.drawString ("KAI", 5, 130); // Нарисовать текст
    }
    public static void main (String[] args) {
        Graf gr= new Graf ( ); // Создать объект окна
        gr.setSize (100, 150); // Установить размеры окна
        gr.show ( ); // Показать окно
    }
}



