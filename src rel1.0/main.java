import JGJAR.libraries.MyMath.*;
//Main class
public class main {

	public static void main(String[] args) 
	{
		//Function which will be drawn
		ComplexFunction f = new ComplexFunction() 
		{
			@Override
			public Complex reversed(Complex z) 
			{
				return z;
			}
			//This will be 
			//NOT REVERSED!!!
			@Override
			public Complex execute(Complex z) 
			{
				return Complex.ln(z);
			}
			
		};
		//Creating window
		//Function, height, width, scale (The size of a pixel, recommended 1 pixel :) )
		new window(f, 1000, 1000, 1, ColorModel.RGB);

	}

}
