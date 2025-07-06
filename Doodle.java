import java.util.*;
import java.awt.*;
import javax.swing.*;

class Doodle
{
	public static void main(String args[])
	{	
		JFrame frame= new JFrame("Doodle");
		frame.setSize(1500, 1000);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	
		//Ribbon
		JPanel ribbon= new JPanel();
		ribbon.setLayout(null);
		ribbon.setBackground(Color.MAGENTA);
		ribbon.setBounds(0, 0, 1500, 150);
		//Color Button
		JButton colorButton= new JButton("Color");
		colorButton.setBounds(300, 30, 70, 70);
		ribbon.add(colorButton);
		//Pointer Button
		JButton pointerButton= new JButton("Pointer");
		pointerButton.setBounds(380, 30, 70, 70);
		ribbon.add(pointerButton);
		//Brush Button
		JButton brushButton= new JButton("Brush");
		brushButton.setBounds(460, 30, 70, 70);
		ribbon.add(brushButton);
		//Shape Button
		JButton shapeButton= new JButton("Shape");
		shapeButton.setBounds(540, 30, 70, 70);
		ribbon.add(shapeButton);
		
		
		frame.add(ribbon);

		
		
		frame.setVisible(true);	
	}

	
}