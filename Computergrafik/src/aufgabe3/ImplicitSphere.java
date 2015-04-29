/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3
 */
package aufgabe3;

import computergraphics.math.Vector3;

/**
 * This is an {@link ImplicitFunction} which describes a sphere.
 */
public class ImplicitSphere extends ImplicitFunction {

	/**
	 * The radius.
	 */
	private double radius;

	/**
	 * Constructor.
	 * 
	 * @param radius
	 *            the radius
	 * @param center
	 *            the center
	 */
	public ImplicitSphere(double radius, Vector3 center) {
		this.radius = radius;
		this.center = center;

		boundingBoxRadius = radius + 0.1;
	}

	@Override
	public double getIsoValueFor(Vector3 vertex) {
		// (x-m)² + (y-m)² + (z-m)² - r²
		double xSqr = Math.pow(vertex.get(0) - center.get(0), 2);
		double ySqr = Math.pow(vertex.get(1) - center.get(1), 2);
		double zSqr = Math.pow(vertex.get(2) - center.get(2), 2);
		double rSqr = Math.pow(radius, 2);
		return xSqr + ySqr + zSqr - rSqr;
	}
}
