
public class Line {
	private Point p1;
	private Point p2;
	public Line(Point p1,Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	public Point getPoint1(){
		return p1;
	}
	public Point getPoint2(){
		return p2;
	}
	public static Point getLineIntersets(Point p1, Point p2, Point q1, Point q2){
		double x1,x2,x3,x4,y1,y2,y3,y4,p;
		x1=p1.getX();
		x2=p2.getX();
		x3=q1.getX();
		x4=q2.getX();
		y1=p1.getY();
		y2=p2.getY();
		y3=q1.getY();
		y4=q2.getY();
//		if(Math.min(x1, x2)>Math.max(x3, x4)||Math.min(x3, x4)>Math.max(x1, x2)||
//				Math.min(y1, y2)>Math.max(y3, y4)||Math.min(y3, y4)>Math.max(y1, y2)){ 
//			return null;
//			
//		}
//		
//		
//		if(x2==x1&&x1<Math.max(x3, x4)&&x1>Math.min(x3, x4)){
//			p=(Math.max(x3, x4)-x1)/(Math.abs(x4-x3));
//			y=p*(Math.abs(y4-y3))+Math.min(y4, y3);
//			x=x2;
//		}
//		else if(x4==x3&&x3<Math.max(x1, x2)&&x3>Math.min(x1, x2)){
//			p=(Math.max(x1, x2)-x3)/(Math.abs(x2-x1));
//			y=p*(Math.abs(y2-y1))+Math.min(y2, y1);
//			x=x3;
//		}
////		else if(y2==y1&&y1<Math.max(y3, y4)&&y1>Math.min(y3, y4)){
////			p=(Math.max(y3, y4)-y1)/(Math.abs(y4-y3));
////			x=p*(Math.abs(x4-x3))+Math.min(x4, x3);
////			System.out.println("set1");
////			y=y1;
////		}
////		else if(y4==y3&&y3<Math.max(y1, y2)&&y3>Math.min(y1, y2)){
////			p=(Math.max(y1, y2)-y3)/(Math.abs(y2-y1));
////			x=p*(Math.abs(x2-x1))+Math.min(x2, x1);
////			y=y3;
////			System.out.println("set2");
////		}
//		else{
//		m1=(y2-y1)/(x2-x1);
//		m2=(y4-y3)/(x4-x3);
//		x=((m2*x3)-y3-(m1*x1)+y1)/(m2-m1);
//		p=(Math.max(x1, x2)-x)/Math.abs(x1-x2);
//		y=p*(Math.abs(y1-y2))+Math.min(y1, y2);
//		System.out.println(x+"else"+y);
//		}	
//		
//		
//		if(x>Math.max(x1, x2)||x<Math.min(x1, x2)||x>Math.max(x3, x4)||x<Math.min(x4, x3))
//			return null;
//		if(y>Math.max(y1, y2)||y<Math.min(y1, y2)||y>Math.max(y3, y4)||y<Math.min(y4, y3))
//			return null;
//		System.out.println("x"+x+"y"+y);
//			return new Point(x,y);
		 double bot = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
		  if (bot == 0.0) { // Lines are parallel.
		     return null;
		  }
		  double m1 = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3))/bot;
		  double m2 = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3))/bot;
		    if (m1 >= 0.0f && m1 <= 1.0f && m2 >= 0.0f && m2 <= 1.0f) {
		    	return new Point( (x1 + m1*(x2 - x1)), (y1 + m1*(y2 - y1)));
		    }

		  return null;
		  }
	}


