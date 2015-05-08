/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 5
 */
package aufgabe5;

import java.util.ArrayList;
import java.util.List;

import computergraphics.math.Vector3;

/**
 * This is a generic curve.
 */
public abstract class Curve {

	/**
	 * {@link List} of control points.
	 */
	protected List<Vector3> controlPoints = new ArrayList<Vector3>();

	/**
	 * Get the control points as {@link Vector3}.
	 * 
	 * @return the control points
	 */
	public List<Vector3> getControlPoints() {
		return controlPoints;
	}

	/**
	 * Calculates the vertex for a given parameter.
	 * 
	 * @param param
	 *            the parameter
	 * @return the vertex
	 */
	public abstract Vector3 getVertexForParameter(double param);

	/**
	 * Get the tangent {@link Vector3} for the curve at a given value.
	 * 
	 * @param value
	 *            the value
	 * @return the tangent {@link Vector3}
	 */
	public abstract Vector3 getTangent(double value);
}
