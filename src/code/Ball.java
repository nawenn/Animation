package code;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Ball {
	private double x;
	private double y;
	private boolean ack;
	private String layer = null;
	private boolean digitalSection = true;
	private String name;
	private String message = null;
	private int previous = 0;
	List<Integer> routerXpoints = new ArrayList<Integer>();
	List<Integer> routerYpoints = new ArrayList<Integer>();

	public void setMessage(String m) {
		message = m;
	}

	public String getMessage() {
		return message;
	}

	public void setLayer(String l) {
		layer = l;
	}
	
	public String getLessage() {
		return layer;
	}

	public boolean getack() {
		return ack;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public String getLayer() {
		return layer;
	}

	public Ball(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		layer = null;
		ack = false;
		addRouterPoints();
	}

	//adding router x and y values so the ball can stop when it reach to this point
	public void addRouterPoints() {
		routerXpoints.add(0);
		routerYpoints.add(0);
		// router1
		routerXpoints.add(325);
		routerYpoints.add(445);
		// router2
		routerXpoints.add(415);
		routerYpoints.add(295);
		// router3
		routerXpoints.add(912);
		routerYpoints.add(271);
		// router4
		routerXpoints.add(1002);
		routerYpoints.add(446);
		// router5
		routerXpoints.add(513);
		routerYpoints.add(467);
		// router6
		routerXpoints.add(912);
		routerYpoints.add(445);
		// router7
		routerXpoints.add(430);
		routerYpoints.add(647);
		// router8
		routerXpoints.add(910);
		routerYpoints.add(647);

	}

	//defines the ball's path
	public void update(List<Integer> path, String type) {
		
		if (digitalSection == true) {
			start();
		} else if (path.size() >= 2) {
			//((destination router) - (source router))/weights for slope.
			if (x < routerXpoints.get(path.get(1))) {
				double dx = (routerXpoints.get(path.get(1)) - routerXpoints.get(path.get(0))) / 20;
				double dy = (routerYpoints.get(path.get(1)) - routerYpoints.get(path.get(0))) / 20;
				x += dx;
				y += dy;
			} else {
				previous = path.get(0);
				path.remove(0);
			}
		} else if (type == "regular") {
			end();
		} else if (type == "stopAndWait") {
			if (x > routerXpoints.get(previous)) {
				double dx = (routerXpoints.get(previous) - routerXpoints.get(path.get(0))) / 50;
				double dy = (routerYpoints.get(previous) - routerYpoints.get(path.get(0))) / 50;
				x += dx;
				y += dy;
			} else {
				ack = true;
			}
		}
	}

	//start and end path before the ball going into the routing phase.
	public void start() {
		if (y < 445 && x == 90) {
			y += 5;
		} else if (y == 445 && x <= 324) {
			x += 5;
			setLayer("Analog");
		} else {
			digitalSection = false;
		}
	}
	

	public void end() {
		if (x <= 1270) {
			x += 5;
		} else if (y >= 16) {
			y -= 5;
			setLayer("Digital");
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.drawOval((int) x, (int) y, 30, 30);
		g2d.fillOval((int) x, (int) y, 30, 30);
	}

}
