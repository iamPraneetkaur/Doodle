package Doodle;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class DrawingCanvas extends JPanel{
    BufferedImage canvasImage;
    int lastX, lastY;
    Color currentColor= Color.BLACK;
    
    public DrawingCanvas(){
        canvasImage= new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();

        //Mouse Press(storing starting point)
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                lastX= e.getX();
                lastY= e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int currentX = e.getX();
                int currentY = e.getY();

                Graphics2D g2 = canvasImage.createGraphics();
                g2.setColor(currentColor);
                g2.setStroke(new BasicStroke(2)); // brush thickness
                g2.drawLine(lastX, lastY, currentX, currentY);
                g2.dispose();

                lastX = currentX;
                lastY = currentY;

                repaint(); // refresh panel
            }
        });
    }
    public void clearCanvas() {
        Graphics2D g2 = canvasImage.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        g2.dispose();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(canvasImage, 0, 0, null);
    }
    
}
