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
 * This represents an implicit function, which describes a mathematically
 * describable object.
 */
public abstract class ImplicitFunction {

	protected double iso = 0.0;
	protected Vector3 center;
	protected double boundingBoxRadius = 1.1;

	/**
	 * Calculates the iso value for a given vertex.
	 * 
	 * @param vertex
	 *            the vertex
	 * @return the calculated iso value
	 */
	abstract double getIsoValueFor(Vector3 vertex);

	/**
	 * Calculates the bounding box for the object and returns it as a
	 * {@link Cube}.
	 * 
	 * @return the bounding box
	 */
	Cube getBoundingBox() {
		List<Vector3> boundingBox = new ArrayList<Vector3>();

		boundingBox
				.add(new Vector3(center.get(0) - boundingBoxRadius, center
						.get(1) - boundingBoxRadius, center.get(2)
						- boundingBoxRadius));
		boundingBox
				.add(new Vector3(center.get(0) + boundingBoxRadius, center
						.get(1) - boundingBoxRadius, center.get(2)
						- boundingBoxRadius));
		boundingBox
				.add(new Vector3(center.get(0) + boundingBoxRadius, center
						.get(1) + boundingBoxRadius, center.get(2)
						- boundingBoxRadius));
		boundingBox
				.add(new Vector3(center.get(0) - boundingBoxRadius, center
						.get(1) + boundingBoxRadius, center.get(2)
						- boundingBoxRadius));

		boundingBox
				.add(new Vector3(center.get(0) - boundingBoxRadius, center
						.get(1) - boundingBoxRadius, center.get(2)
						+ boundingBoxRadius));
		boundingBox
				.add(new Vector3(center.get(0) + boundingBoxRadius, center
						.get(1) - boundingBoxRadius, center.get(2)
						+ boundingBoxRadius));
		boundingBox
				.add(new Vector3(center.get(0) + boundingBoxRadius, center
						.get(1) + boundingBoxRadius, center.get(2)
						+ boundingBoxRadius));
		boundingBox
				.add(new Vector3(center.get(0) - boundingBoxRadius, center
						.get(1) + boundingBoxRadius, center.get(2)
						+ boundingBoxRadius));

		return new Cube(boundingBox);
	}

	/**
	 * Returns the iso.
	 * 
	 * @return the iso
	 */
	double getIso() {
		return iso;
	}
}
