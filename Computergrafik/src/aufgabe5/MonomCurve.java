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
 * This is an monom specialised implementation of a {@link Curve}.
 */
public class MonomCurve extends Curve {

	/**
	 * Constructor.
	 * 
	 * @param pes
	 *            the points to interpolate into control points
	 * @param t
	 *            param specifying the location of the middle point
	 */
	public MonomCurve(List<Vector3> pes, double t) {

		// interpolate control points
		Vector3 p0 = pes.get(0); // p0 == c0
		Vector3 p1 = pes.get(1);
		Vector3 p2 = pes.get(2);

		Vector3 c1 = p0.multiply(t - (1 / t)).add(p1.multiply(1 / t))
				.subtract(p2.multiply(t)).multiply(1 / (1 - t));
		Vector3 c2 = p2.subtract(p0).subtract(c1);

		controlPoints.add(p0);
		controlPoints.add(c1);
		controlPoints.add(c2);
	}

	@Override
	public Vector3 getVertexForParameter(double t) {

		Vector3 c0 = controlPoints.get(0);
		Vector3 c1 = controlPoints.get(1);
		Vector3 c2 = controlPoints.get(2);

		Vector3 v = c0.add(c1.multiply(t)).add(c2.multiply(t * t));
		return v;
	}

	@Override
	public Vector3 getTangent(double t) {
		return controlPoints.get(1).add(controlPoints.get(2).multiply(2 * t));
	}

}
