import java.util.*;
import java.io.*;

public class Cleanup 
{
	public static void main (String args[]) throws IOException
	{
		File f = new File("points.txt");
		Random rand = new Random();
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
		HashMap<Point,HashMap<Double,Point>> pointDis = new HashMap<>();// binary heap? map point with distance
		HashMap<Point,PriorityQueue<Double>> pointShort = new HashMap<>();
		for(int i = 0; i<points.size(); i++)
		{
			HashMap<Double,Point> distance = new HashMap<>();
			PriorityQueue<Double> dis = new PriorityQueue<>();
			for(int j = 0; j<points.size();j++)
			{
				distance.put(calculateDistance(points.get(i),points.get(j)),points.get(j));
				dis.add(calculateDistance(points.get(i),points.get(j)));
			}
			pointDis.put(points.get(i), distance);
			pointShort.put(points.get(i), dis);
		}
		
		ArrayList<Point> route = new ArrayList<>();
		HashSet<Point> used = new HashSet<>();
		Point newp = points.get(rand.nextInt(points.size()));
		route.add(newp);
		used.add(newp);
		while(used.size()<points.size())
		{
			double sd = pointShort.get(newp).poll();
			Point p = pointDis.get(newp).get(sd); //get the point with shortest distance
			
			while(used.contains(p))
			{
				sd = pointShort.get(newp).poll();
				p = pointDis.get(newp).get(sd);
			}
			if(!used.contains(p))
			{
				used.add(p);
				route.add(p);
				newp = p;
			}
		}
		PrintWriter writer = new PrintWriter("path.txt");
		for (Point p: route)
		{
			writer.println(p.getloc(1)+","+p.getloc(2)+","+p.getloc(3)+","+p.getloc(4));
		}
		
	    writer.close();
	}
	
	
	public static Double calculateDistance(Point a, Point b)
	{
		double x = Math.pow(a.getloc(1)-b.getloc(1), 2);
		double y = Math.pow(a.getloc(2)-b.getloc(2), 2);
		double z = Math.pow(a.getloc(3)-a.getloc(3), 2);
		double w = Math.pow(a.getloc(4)-b.getloc(4), 2);
		return Math.sqrt(x+y+z+w);
	}
	
	
}
