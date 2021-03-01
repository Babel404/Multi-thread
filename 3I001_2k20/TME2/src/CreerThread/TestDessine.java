
import graphic.Window;
import java.awt.Point;

public class TestDessine {
	public static void main(String[] args) {
		Window w = new Window (250,250,"Test");
		Point p1 = new Point(1,125);
		Point p2 = new Point(249,1);
		Point p3 = new Point(249,249);
		new DessineLigne(p1,p2,w).start();
		new DessineLigne(p3,p2,w).start();
		new DessineLigne(p1,p3,w).start();
	}
}
		
