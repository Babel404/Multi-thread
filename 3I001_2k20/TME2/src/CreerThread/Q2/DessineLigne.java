import graphic.Window;
import java.awt.Point;

public class DessineLigne extends Thread{
	Point p1;
	Point p2;
	Window w;
	
	public DessineLigne(Point p1, Point p2, Window w){
		this.p1=p1;
		this.p2=p2;
		this.w=w;
	}
	
	public void run(){
		w.plotLine(p1,p2);
	}
}
