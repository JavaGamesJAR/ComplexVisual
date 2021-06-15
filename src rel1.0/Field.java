import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import JGJAR.libraries.MyMath.Complex;
import JGJAR.libraries.MyMath.ComplexFunction;
import JGJAR.libraries.MyMath.MyMath;

public class Field extends JPanel
{
	private int scale = 1; //Scale
	private Timer tm; //Timer
	public JButton export; //Button to export your image to .png
	private logic Logic; //The object of type logic, which fills the field
	private ColorModel c; //Color model
	//Constructor
	public Field(ComplexFunction f, int height, int width, int scale, ColorModel c)
	{
		this.scale = scale;
		this.c = c;
		Logic = new logic(height, width, f);  //Init of logic
		
		//Init of btn
		export = new JButton("Export");
		export.setBounds(1250, 100, 300, 100);
		//What's happening on button press
		export.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Exporting image
				export.setVisible(false); //Making btn invisible
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //Creating buffered image
				Graphics2D g = image.createGraphics(); //getting the graphics inside field
				printAll(g); //putting graphics into an image
				g.dispose(); //deleting graphics
				//Writing image as a file
				try { 
				    ImageIO.write(image, "png", new File("graph.png")); 
				} catch (IOException exp)
				{exp.printStackTrace();}
				
				export.setVisible(true); //Making btn visible
				
			}
		});
		add(export);
		
		//Init of timer
		tm = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				repaint();
				
			}
		});
		tm.start();
	}
	
	
	//Repainting function
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		
		double[][] re = new double[Logic.h][Logic.w];
		double[][] im = new double[Logic.h][Logic.w];
		
		for(int i=0;i<Logic.h;i++)
		{
			for(int j=0;j<Logic.w;j++)
			{
				re[i][j] = Logic.visual[i][j].Re;
				im[i][j] = Logic.visual[i][j].Im;
				
			}
			
		}
		//Getting max real, min real and the difference between them
		double maxre = max(re);
		double minre = min(re);
		double meare = maxre-minre;
		
		//Same with imaginary
		double maxim = max(im);
		double minim = min(im);
		double meaim = maxim-minim;
		
		//Color
		Color col = null;
		//Drawing
		for(int i=0;i<Logic.h;i++)
		{
			for(int j=0;j<Logic.w;j++)
			{
				//Doing some infinity cases
				if(Logic.visual[i][j].Im==MyMath.POSITIVE_INFINITY)
					col = new Color((float)(Logic.visual[i][j].Re/meare), (float)1.0/*(Logic.visual[i][j].Im/meaim)*/, (float)1.0);
				 if(Logic.visual[i][j].Re==MyMath.POSITIVE_INFINITY)
					col = new Color((float)1.00, (float)(Logic.visual[i][j].Im/meaim), (float)0.0);
				 else //Choosing color
				 {
					 if(c==ColorModel.RGB)
						 col = new Color((float)((Logic.visual[i][Logic.w-j-1].Re/meare)+Math.abs(minre/meare)), (float)(Logic.visual[i][Logic.w-1-j].Im/meaim+Math.abs(minim/meaim)), (float)0.0, 1);
					 else if(c==ColorModel.HSB)
						 col = Color.getHSBColor((float)(Logic.visual[i][j].Arg()), (float)(Logic.visual[i][j].abs()), 1.0F);
				 }
					 
				 //Setting color
				gr.setColor(col);
				//Drawing a square of size scale*scale
				gr.fillRect(i*scale, j*scale, scale, scale);
				
			}
			
		}
		//The labels Re & Im
		gr.setColor(Color.BLUE);
		gr.drawString("Re", 15, this.getHeight()-10);
		
		Graphics2D g2 = (Graphics2D) gr;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.RED);
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 80, 100);
        g2.setTransform(at);
        g2.setColor(Color.BLUE);
		g2.drawString("Im", -10, 175);
		at.setToRotation(0, 80, 100);
		g2.setTransform(at);
		this.setBounds(0, 0, 1000, 1000);
		
	}
	//Get max number in a field
	public double max(double[][] arr)
	{
		double res = Double.MIN_VALUE;
		for(int i = 0; i<arr.length;i++)
		{
			for(int j = 0; j<arr[0].length;j++)
			{
				if(arr[i][j]>res)
					res = arr[i][j];
				
			}
			
		}
		return res;
		
	}
	//Get min number in a field
	public double min(double[][] arr)
	{
		double res = Double.MAX_VALUE;
		for(int i = 0; i<arr.length;i++)
		{
			for(int j = 0; j<arr[0].length;j++)
			{
				if(arr[i][j]<res)
				res = arr[i][j];
				
			}
			
		}
		return res;
		
	}

}
