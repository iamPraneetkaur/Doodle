package Doodle;
import Doodle.DrawingCanvas;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Doodle {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Doodle");
		frame.setSize(1500, 1000);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		Font myFont= new Font("Comic Sans MS", Font.BOLD, 25);
		ImageIcon paint= new ImageIcon(Doodle.class.getResource("paint.png"));
		frame.setIconImage(paint.getImage());


		JLabel header= new JLabel("Doodle");
		header.setFont(new Font("Comic Sans MS", Font.BOLD, 100));
		header.setBounds(20, 0, 500, 150);
		frame.add(header);

		DrawingCanvas canvas = new DrawingCanvas();
		canvas.setBounds(0, 150, 1500, 850);
		frame.add(canvas);

		
		//Ribbon
		JPanel ribbon = new JPanel();
		ribbon.setLayout(null);
		ribbon.setBackground(Color.decode("#FAB1CD"));
		ribbon.setBounds(0, 0, 1500, 150);

		//Night mode Button
		JToggleButton toggle = new JToggleButton("☀");
		toggle.setBounds(460, 30, 80, 80);
		toggle.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
		toggle.setBackground(Color.decode("#ffd0e2"));
		toggle.setFocusPainted(false);
		//Behavior		
		toggle.addItemListener(e -> {
    		if (toggle.isSelected()) {
        		toggle.setText("🌙");
				canvas.darkMode();
				
    		}
			else{
        		toggle.setText("☀");
        		canvas.lightMode();
    		}
		});
		ribbon.add(toggle);

		//Color Pallete Button
		JButton colorButton = new JButton("🖍");
		colorButton.setBounds(550, 30, 80, 80);
		colorButton.setBackground(Color.decode("#ffd0e2"));
		colorButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
		ribbon.add(colorButton);		
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color selectedColor = JColorChooser.showDialog(null, "Pick any color you like!", Color.BLACK);
				if (selectedColor != null) {
					canvas.setCurrentColor(selectedColor);
				}
			}
		});

		//Save Button
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(640, 30, 80, 80);
		saveButton.setBackground(Color.decode("#ffd0e2"));
		saveButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		ribbon.add(saveButton); 
		//Listener
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				canvas.saveImageToFile();
			}
		});
		
		//Eraser Button
		JButton eraserButton = new JButton("🧽");
		eraserButton.setBounds(730, 30, 80, 80);
		eraserButton.setBackground(Color.decode("#ffd0e2"));
		eraserButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
		ribbon.add(eraserButton);
		//Listener
		eraserButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(toggle.isSelected()){
					canvas.setCurrentColor(Color.BLACK);
				}
				else{
					canvas.setCurrentColor(Color.WHITE);
				}
			}
		}); 

		// Clear Button
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(820, 40, 100, 50);
		clearButton.setBackground(Color.decode("#ffe2ed"));
		clearButton.setFont(myFont);
		ribbon.add(clearButton);
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(toggle.isSelected()){
					canvas.nightCanvas();
				}
				else{
					canvas.clearCanvas();
				}
			}
		});

		//Brush size
		JLabel brushLabel = new JLabel("Pointer Size");
		brushLabel.setBounds(1200, 20, 100, 20);
		brushLabel.setBackground(Color.decode("#ffe2ed"));
		ribbon.add(brushLabel);
		//Slider for pointer size
		JSlider brushSlider = new JSlider(1, 50, 2);
		brushSlider.setBounds(1150, 40, 200, 50);
		brushSlider.setMajorTickSpacing(10);
		brushSlider.setMinorTickSpacing(1);
		brushSlider.setPaintTicks(true);
		brushSlider.setPaintLabels(true);
		ribbon.add(brushSlider);
		//Listener to update pointer size
		brushSlider.addChangeListener(e -> {
			int newSize = brushSlider.getValue();
			canvas.setBrushSize(newSize);
		});
		frame.add(ribbon);

		frame.setVisible(true);
	}
}