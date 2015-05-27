/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 5
 */
package aufgabe5;

import java.util.List;

import computergraphics.math.Vector3;

/**
 * This is an bezier specialised implementation of a {@link Curve}. 
 */
public class BezierCurve extends Curve {

	/**
	 * Constructor.
	 * @param controlPoints the control points
	 */
	public BezierCurve(List<Vector3> controlPoints) {
		this.controlPoints = controlPoints;
	}

	@Override
	public Vector3 getVertexForParameter(double t) {

		Vector3 p = new Vector3();
		int n = controlPoints.size() - 1;
		int i = 0;
		for (Vector3 c : controlPoints) {
			p = p.add(c.multiply(bezier(n, i++, t)));
		}
		return p;
	}

	private double bezier(int n, int i, double t) {
		int fac = (faculty(n) / (faculty(i) * faculty(n - i)));
		return fac * Math.pow(t, i) * Math.pow((1 - t), (n - i));
	}

	private int faculty(int max) {
		int fac = 1;
		for (int f = 1; f <= max; f++) {
			fac = fac * f;
		}
		return fac;
	}

	@Override
	public Vector3 getTangent(double value) {
		
		return null;
	}

}
