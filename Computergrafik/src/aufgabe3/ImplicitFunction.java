/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3
 */
package aufgabe3;

import computergraphics.math.Vector3;

/**
 * This represents an implicit function, which describes a mathematically
 * describable object.
 */
public interface ImplicitFunction {

	/**
	 * Calculates the iso value for a given vertex.
	 * 
	 * @param vertex
	 *            the vertex
	 * @return the calculated iso value
	 */
	double getIsoValueFor(Vector3 vertex);

	/**
	 * Calculates the bounding box for the object and returns it as a
	 * {@link Cube}.
	 * 
	 * @return the bounding box
	 */
	Cube getBoundingBox();
}
