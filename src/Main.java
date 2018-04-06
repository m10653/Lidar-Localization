import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame testFrame = new JFrame();
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setSize(800, 800);
		Display display = new Display();
		testFrame.add(display);

		double angleRad, angle, distance;
		Scanner scan = new Scanner(System.in);
		System.out.println("Input Geometry by point (Format: x, y ) Input start point to end ");
		Shape room = new Shape(getPointInput(scan));
		System.out.println(room);

		System.out.println("Input lidar Distance 1: ");
		distance = scan.nextDouble();
		System.out.println("Input groscope angle (relative) (0-360): ");
		angle = scan.nextDouble();
		angleRad = Math.toRadians(angle);
		Shape shiftedRoom1 = room.traslate(distance * Math.sin(angleRad), distance * Math.cos(angleRad));

		System.out.println("Input lidar Distance 2: ");
		distance = scan.nextDouble();
		System.out.println("Input groscope angle (relative) (0-360): ");
		angle = scan.nextDouble();
		angleRad = Math.toRadians(angle);
		Shape shiftedRoom2 = room.traslate(distance * Math.sin(angleRad), distance * Math.cos(angleRad));

		display.addMap(room);
		display.addOffsetMap(shiftedRoom1);
		display.addOffsetMap(shiftedRoom2);

		System.out.println("Input Shape : " + room);
		System.out.println("Possable location mesh 1 (unbounded): " + shiftedRoom1);
		System.out.println("Possable location mesh 2 (unbounded): " + shiftedRoom2);

		ArrayList<Line> shift1 = getLines(shiftedRoom1.getPoints());
		ArrayList<Line> shift2 = getLines(shiftedRoom2.getPoints());
		ArrayList<Point> outPoints = new ArrayList<Point>();
		for (Line line1 : shift1) {
			for (Line line2 : shift2) {
				Point t = Line.getLineIntersets(line1.getPoint1(), line1.getPoint2(), line2.getPoint1(), line2.getPoint2());
				if (t != null && room.contains(t)) {
					outPoints.add(t);
				} else if (t != null) {
					outPoints.add(t);
				}
			}
		}
		System.out.println("Location : " + outPoints);
		for (Point p : outPoints) {
			display.addInersect(p);
		}

		testFrame.setVisible(true);

	}

	public static ArrayList<Point> getPointInput(Scanner scan) {
		ArrayList<Point> points = new ArrayList<Point>();
		boolean isBuilding = true;
		Point startPoint = null;
		while (isBuilding) {
			String[] data = scan.next().replaceAll(" ", "").split(",");
			Point point = new Point(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
			if (startPoint != null) {
				if (point.equals(startPoint)) {
					isBuilding = false;
					continue;
				}
			} else {
				startPoint = point;
			}
			points.add(point);
		}
		return points;
	}

	public static ArrayList<Line> getLines(ArrayList<Point> points) {
		ArrayList<Line> out = new ArrayList<Line>();
		Point first = null;
		Point last = null;
		for (Point p : points) {
			if (last != null) {
				out.add(new Line(p, last));
				last = p;
			} else {
				last = p;
				first = p;
			}
		}
		out.add(new Line(first, last));
		return out;
	}

}
