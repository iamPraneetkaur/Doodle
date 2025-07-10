package Doodle;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class DrawingCanvas extends JPanel{
    BufferedImage canvasImage;
    int lastX, lastY;
    Color currentColor= Color.BLACK;
    
    int brushSize = 2; // default brush size
    public void setBrushSize(int size) {
        this.brushSize = size;
    }

    public void setCurrentColor(Color color){
        this.currentColor= color;
    }

    public DrawingCanvas(){
        canvasImage= new BufferedImage(1500, 900, BufferedImage.TYPE_INT_ARGB);
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
                g2.setStroke(new BasicStroke(brushSize)); // brush thickness
                g2.drawLine(lastX, lastY, currentX, currentY);
                g2.dispose();

                lastX = currentX;
                lastY = currentY;
                repaint(); // refresh panel
            }
        });
    }
    //Make canvas White
    public void clearCanvas() {
        Graphics2D g2 = canvasImage.createGraphics();
        g2.setColor(Color.WHITE);
        setCurrentColor(Color.BLACK);
        g2.fillRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        g2.dispose();
        repaint();
    }
    //Make canvas Black
    public void nightCanvas() {
        Graphics2D g2 = canvasImage.createGraphics();
        g2.setColor(Color.BLACK);
        setCurrentColor(Color.WHITE);
        g2.fillRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        g2.dispose();
        repaint();
    }
    public void darkMode() {
        Graphics2D g2 = canvasImage.createGraphics();
        g2.setColor(Color.BLACK);
        setCurrentColor(Color.WHITE);
        g2.fillRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        repaint();
    }
    public void lightMode() {
        Graphics2D g2 = canvasImage.createGraphics();
        g2.setColor(Color.WHITE);
        setCurrentColor(Color.BLACK);
        g2.fillRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        repaint();
    }
    public void saveImageToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Image");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try{
                // Add .png if not included
                if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
                }
                ImageIO.write(canvasImage, "png", fileToSave);
                JOptionPane.showMessageDialog(this, "Image saved successfully!");
            } 
            catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save image: " + e.getMessage());
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(canvasImage, 0, 0, null);
    }
    
}
