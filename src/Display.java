import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Display extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int DISPLAY_SCALE = 10;
	private static int DISPLAY_OFFSET_Y = 50;
	private static int DISPLAY_OFFSET_X = 50;
	private static int DISPLAY_PONT_SIZE = 8;
	private ArrayList<Shape> offsetMaps = new ArrayList<Shape>();
	private Shape map;
	private ArrayList<Point> intersects = new ArrayList<Point>();

	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.setColor(Color.BLACK);
		for (Shape shape : offsetMaps) {
			renderShape(g2D, shape);
		}
		g2D.setColor(Color.RED);
		renderShape(g2D, map);
		for (Point p : intersects) {
			g.setColor(Color.RED);
			g.drawOval(DISPLAY_OFFSET_X+(int) (p.getX()*DISPLAY_SCALE)-DISPLAY_PONT_SIZE/2, DISPLAY_OFFSET_Y+((int) p.getY()*DISPLAY_SCALE)-DISPLAY_PONT_SIZE/2, DISPLAY_PONT_SIZE, DISPLAY_PONT_SIZE);
			g.setColor(Color.GREEN);
			g.fillOval(DISPLAY_OFFSET_X+(int) (p.getX()*DISPLAY_SCALE)-DISPLAY_PONT_SIZE/2, DISPLAY_OFFSET_Y+((int) p.getY()*DISPLAY_SCALE)-DISPLAY_PONT_SIZE/2, DISPLAY_PONT_SIZE, DISPLAY_PONT_SIZE);
		}
		super.paint(g);
	}

	public void addOffsetMap(Shape shape) {
		offsetMaps.add(shape);
	}

	public void addMap(Shape shape) {
		map = shape;
	}

	public void addInersect(Point p) {
		intersects.add(p);

	}

	private void renderShape(Graphics2D g, Shape shape) {
		if (shape == null) {
			return;
		}
		Point first = null;
		Point last = null;
		for (Point p : shape.vertices) {
			if (last != null) {
				g.draw(new Line2D.Double(DISPLAY_OFFSET_X+p.getX() * DISPLAY_SCALE, DISPLAY_OFFSET_Y+p.getY() * DISPLAY_SCALE, DISPLAY_OFFSET_X+last.getX() * DISPLAY_SCALE, DISPLAY_OFFSET_Y+last.getY() * DISPLAY_SCALE));
				last = p;
			} else {
				last = p;
				first = p;
			}
		}
		g.draw(new Line2D.Double(DISPLAY_OFFSET_X+first.getX() * DISPLAY_SCALE, DISPLAY_OFFSET_Y+first.getY() * DISPLAY_SCALE, DISPLAY_OFFSET_X+last.getX() * DISPLAY_SCALE, DISPLAY_OFFSET_Y+last.getY() * DISPLAY_SCALE));

	}

}
