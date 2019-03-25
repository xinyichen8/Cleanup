
public class Point 
{
	private double x;
	private double y;
	private double z;
	private double w;
	public Point(double x, double y, double z, double w)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;	
	}
	
	public double getloc(int index)
	{
		switch(index)
		{
			case 1:
				return x;
			case 2:
				return y;
			case 3:
				return z;
			case 4:
				return w;
		}
		return 0;
	}
}
