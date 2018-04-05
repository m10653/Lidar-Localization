import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Display extends JComponent {
	ArrayList<Line2D> lines = new ArrayList<Line2D>();
	ArrayList<Point> points = new ArrayList<Point>();
	Color c;
	int num = 0;
	int last = 0;
	@Override
	public synchronized void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		int i = 0;
		for(Line2D l: lines){
			if( i >= num-last  ){
				g2D.setColor(Color.RED);
			}else{
				g2D.setColor(Color.BLACK);
			}
			g2D.draw(l);
			i++;
		}
		for(Point p: points){
			g.setColor(Color.RED);
			g.drawOval(796-(int)(p.getX()*10), 796-(int)(p.getY()*10), 8, 8);
			g.setColor(Color.GREEN);
			g.fillOval(796-(int)(p.getX()*10), 796-(int)(p.getY()*10), 8, 8);
		}
		super.paint(g);
	}
	public synchronized void addPoints(ArrayList<Point> points){
		this.points.addAll(points);
	}
	public synchronized void  AddLine(Point p1, Point p2){
		lines.add( new Line2D.Double(p1.getX()*10, p1.getY()*10, p2.getX()*10,  p2.getY()*10));
		repaint();
	}
	public synchronized void Addline(Line2D line){
		lines.add(line);
		repaint();
	}
	public synchronized void  AddLines(ArrayList<Line2D> newLines){
//		if(num == 0){
			num +=newLines.size();
			last = newLines.size();
//		}
		lines.addAll(newLines);
		repaint();
	}
//	public static Point getLineIntersets(Point p1, Point p2, Point q1, Point q2){
//		Line2D line1 = new Line2D.Double(p1.getX(),p1.getX(),p2.getX(),p2.getY());
//		Line2D line2 = new Line2D.Double(q1.getX(),q1.getX(),q2.getX(),q2.getY());
//		if(!line1.intersectsLine(line2)){
//			return null;
//		}
//		double slope1 = (p1.getY()-p2.getY())/(p1.getX()-p2.getX());
//		double slope2 = (q1.getY()-q2.getY())/(q1.getX()-q2.getX());

}
