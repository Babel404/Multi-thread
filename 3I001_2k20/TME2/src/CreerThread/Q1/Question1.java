
import graphic.Window;
import java.awt.Point;

public class Question1 {
	public static void main(String[] args) {
		Window w = new Window (250,250,"Test");
		Point p1 = new Point(1,125);
		Point p2 = new Point(249,1);
		Point p3 = new Point(249,249);
		w.plotLine(p1,p2);
		w.plotLine(p2,p3);
		w.plotLine(p3,p1);
	}
}
