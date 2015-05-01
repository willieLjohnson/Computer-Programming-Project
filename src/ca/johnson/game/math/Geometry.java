package ca.johnson.game.math;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Geometry {
	private static double x;
	private static double y;

	public Geometry(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public static double sphereVolume(double r) {
		double sphereVol = (1.3333333) * Math.PI * Math.pow(r, 3);
		return sphereVol;

	}

	public static double sphereSurface(double r) {
		double sphereSur = 4 * Math.PI * Math.pow(r, 2);
		return sphereSur;
	}

	public static double cylinderVolume(double r, double h) {
		double cylinderVol = Math.PI * Math.pow(r, 2) * h;
		return cylinderVol;
	}

	public static double cylinderSurface(double r, double h) {
		double cylinderSur = (2 * Math.PI * r * h)
				+ (2 * Math.PI * Math.pow(r, 2));
		return cylinderSur;
	}

	public static double coneVolume(double r, double h) {
		double coneVol = Math.PI * Math.pow(r, 2) * (h / 3);
		return coneVol;
	}

	public static double coneSurface(double r, double h) {
		double coneSur = Math.PI * r
				* (r + Math.sqrt(Math.pow(h, 2) + Math.pow(r, 2)));
		return coneSur;
	}

	public static boolean isInside(Point2D p, Ellipse2D q) {
		if (((Math.pow((x - 5), 2) / 25) + (Math.pow((y - 5), 2) / 25)) <= 1)
			return true;
		else
			return false;
	}

	public static boolean isOnBoundary(Point2D p, Ellipse2D q) {
		if (((Math.pow((x - 5), 2) / 25) + (Math.pow((y - 5), 2) / 25)) == 1)
			return true;
		else
			return false;
	}
}
