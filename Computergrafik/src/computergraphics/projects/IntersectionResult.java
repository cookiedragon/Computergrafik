package computergraphics.projects;

import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;

/**
 * Representation of the intersection result.
 */
public class IntersectionResult {

	/**
	 * The intersection happens at this point.
	 */
	public Vector3 point;

	/**
	 * Normal at the given point.
	 */
	public Vector3 normal;

	/**
	 * Intersected object
	 */
	public Node object;

	/**
	 * The colour
	 */
	public Vector3 colour;

	/**
	 * The level of reflection between 0 and 1.
	 */
	public double reflectionLevel;
}
