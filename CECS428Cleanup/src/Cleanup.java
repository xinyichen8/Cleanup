import java.util.*;
import java.io.*;

public class Cleanup 
{
	public static void main (String args[]) throws IOException
	{
		File f = new File("points.txt");
		ArrayList<String> data = new ArrayList<>();
		ArrayList<Point>points = new ArrayList<>();
		try 
		{
			String line;
			BufferedReader bf = new BufferedReader(new FileReader(f));
			while((line = bf.readLine())!=null)
			{
				data.add(line);
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		//put points into arraylist
		for (String point:data)
		{
			String[] loc = point.split(",");
			points.add(new Point(Double.valueOf(loc[0]),Double.valueOf(loc[1]),Double.valueOf(loc[2]),Double.valueOf(loc[3])));
		}
		
		//calculating distance
		HashSet<Point> used = new HashSet<>();
		
		
	}
	
	public static void quickSort(ArrayList<Double> sensors, int low, int high) 
	{
		if(low<high)
		{
			int pet = petition(sensors,low,high);
			
			quickSort(sensors,low,pet-1);
			quickSort(sensors,pet+1,high);
		}
	}
	
	private static int petition(ArrayList<Double> unsorted, int low, int high) 
	{
		double pivot = unsorted.get(high);
		int i = low-1;
		for(int j= low; j<high; j++)
		{
			if (unsorted.get(j)<=pivot)
			{
				i++;
				double temp = unsorted.get(i);
				unsorted.set(i, unsorted.get(j));
				unsorted.set(j, temp);
			}
		}
		
		double temp = unsorted.get(i+1);
		unsorted.set(i+1,unsorted.get(high));
		unsorted.set(high, temp);
		
		return i+1;
	}
	
}
