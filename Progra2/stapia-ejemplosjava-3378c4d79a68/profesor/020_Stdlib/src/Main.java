import stdlib.StdDraw;

public class Main {

	public static void main(String[] args) {
		StdDraw.setYscale(-1.2, 1.2);
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		StdDraw.line(0, 0, 1.0, 0);
		StdDraw.line(0, -0.02, 0.03, -0.02);
		StdDraw.line(1-0.03, -0.02, 1.0, -0.02);
		StdDraw.setPenRadius(0.02);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.1, 0.5+0.1);
		StdDraw.point(0.1, -0.5-0.1);
		StdDraw.point(0.5, 0.0+0.1);
		StdDraw.point(0.5, -0.0-0.1);
		StdDraw.point(0.9, 1+0.1);
		StdDraw.point(0.9, -1-0.1);
	}
}
