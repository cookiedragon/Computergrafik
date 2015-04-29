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

/**
 * This is an {@link ImplicitFunction} which describes a cuboid superquadric.
 */
public class ImplicitSuperquadric implements ImplicitFunction {

	private Vector3 center;
	private double iso = 1.0;

	public ImplicitSuperquadric(Vector3 center) {
		this.center = center;
	}

	@Override
	public double getIsoValueFor(Vector3 vertex) {

		double x = vertex.get(0) - center.get(0);
		double y = vertex.get(1) - center.get(0);
		double z = vertex.get(2) - center.get(0);

		double cuboid = Math.pow(x, 10) + Math.pow(y, 10) + Math.pow(z, 10);
		return cuboid;
	}

	@Override
	public Cube getBoundingBox() {

		List<Vector3> boundingBox = new ArrayList<Vector3>();

		double puffer = 1.1;

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
