package distance;
import java.util.*;

public class Dist {

	public Dist() {
		// TODO Auto-generated constructor stub
	}
	//vector dot
	public static double dot(Vector<Double> v1, Vector<Double> v2) {
		return (v1.get(0) * v2.get(0)) + (v1.get(1) * v2.get(1)) + (v1.get(2) * v2.get(2));
	}
	public static Vector dot(Vector<Double> v1, double s) {
		Vector<Double> v = new Vector();
		v.addElement(v1.get(0)*s);
		v.addElement(v1.get(1)*s);
		v.addElement(v1.get(2)*s);
		return v;
	}
	// distance between 2 points
	public static double dist(Point p1, Point p2) {
		
		return Math.sqrt((Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2) + Math.pow((p1.z - p2.z),2)));
	}
	//distance between edge and point
	public static double dist(Point P, Edge E) {
		Vector v = E.p2.sub(E.p1);
		Vector w = P.sub(E.p1);
		double c1 = dot(w,v);
	    if ( c1 <= 0 )
	    	return dist(P, E.p1);
	    double c2 = dot(v,v);
	    if ( c2 <= c1 )
	        return dist(P, E.p2);
	    double b = c1 / c2;
	    Point Pb = E.p1.add(dot(v, b));
	    return dist(P, Pb);		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(0,0,0);
		Point p2 = new Point(2,0,0);
		Point p3 = new Point(1,4,0);
		double pdist = dist(p1, p2);
		Edge e1 = new Edge(p1, p2);
		double edist = dist(p3, e1);
		System.out.print(pdist + " " + edist);
		
	}
}


class Point {
	double x, y, z;
	public Point(int point1, int point2, int point3) {
		this.x = point1;
		this.y = point2;
		this.z = point3;
	}
	public Vector sub(Point P) {
		Vector<Double> v = new Vector<>(3);
		v.addElement(this.x - P.x);
		v.addElement(this.y - P.y);
		v.addElement(this.z - P.z);
		return v;
	}
	public Point add(Vector<Double> v) {
		Point p = new Point(0,0,0);
		p.x = this.x + v.get(0);
		p.y = this.y + v.get(1);
		p.z = this.z + v.get(2);
		return p;
	}
}

class Edge {
	Point p1, p2;
	public Edge(Point point1, Point point2) {
		this.p1 = point1;
		this.p2 = point2;
	}
}
