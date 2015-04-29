/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3
 */
package aufgabe3;

import java.util.ArrayList;
import java.util.List;

import computergraphics.math.Vector3;

public class ImplicitSphere implements ImplicitFunction {

	private double radius;
	private Vector3 center;
	private double iso = 0.0;

	public ImplicitSphere(double radius, Vector3 mittelPunkt) {
		this.radius = radius;
		this.center = mittelPunkt;
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

	@Override
	public Cube getBoundingBox() {

		List<Vector3> boundingBox = new ArrayList<Vector3>();

		double puffer = radius + 0.1;

		boundingBox.add(new Vector3(center.get(0) - puffer, center.get(1)
				- puffer, center.get(2) - puffer));
		boundingBox.add(new Vector3(center.get(0) + puffer, center.get(1)
				- puffer, center.get(2) - puffer));
		boundingBox.add(new Vector3(center.get(0) + puffer, center.get(1)
				+ puffer, center.get(2) - puffer));
		boundingBox.add(new Vector3(center.get(0) - puffer, center.get(1)
				+ puffer, center.get(2) - puffer));

		boundingBox.add(new Vector3(center.get(0) - puffer, center.get(1)
				- puffer, center.get(2) + puffer));
		boundingBox.add(new Vector3(center.get(0) + puffer, center.get(1)
				- puffer, center.get(2) + puffer));
		boundingBox.add(new Vector3(center.get(0) + puffer, center.get(1)
				+ puffer, center.get(2) + puffer));
		boundingBox.add(new Vector3(center.get(0) - puffer, center.get(1)
				+ puffer, center.get(2) + puffer));

		return new Cube(boundingBox);
	}

	@Override
	public double getIso() {
		return iso;
	}

}