import javax.swing.*;
import java.awt.*;
import JGJAR.libraries.MyMath.ComplexFunction;

//Window class
public class window extends JFrame
{
	//Constructor
	public window(ComplexFunction f, int height, int width, int scale, ColorModel c)
	{
		//Panel with same args
		Field pan = new Field(f, height, width, scale, c);
		Container con = getContentPane(); //Container
		con.add(pan);
		
		pan.export.setBounds(1300, 100, 250, 100); //The thing you don't really need
		
		//Final Init
		
		setTitle("Complex numbers visualised");
		setBounds(0,0,height*scale,width*scale);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		
	}

}
