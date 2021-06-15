import JGJAR.libraries.MyMath.*;

public class logic 
{
	//Function which will be drawn
	private ComplexFunction CF;
	public Complex[][] visual; //The array where the f(z) will be written
	public int h, w; //height and width
	//function returns the function CF, easier for me
	private Complex f(Complex z)
	{
		return CF.execute(z);
		
	}
	//Constructor
	public logic(int height, int width, ComplexFunction CF)
	{
		//Setting args
		this.CF = CF;
		h = height;
		w = width;
		visual = new Complex[width][height];
		//Filling the array
		for(int i = -height/2;i<height/2;i++)
		{
			for(int j = -width/2;j<width/2;j++)
			{
				if(f(new Complex(i, j))==new Complex(MyMath.NEGATIVE_INFINITY, 0.0))
					visual[i+height/2][j+width/2] = new Complex(-100000, 0);
				else if(f(new Complex(i, j))==new Complex(MyMath.POSITIVE_INFINITY, 0.0))
					visual[i+height/2][j+width/2] = new Complex(100000.0 ,0.0);
				else
					visual[i+height/2][j+width/2] = f(new Complex(i, j));
					
			}
				
		}
	}
	

}
