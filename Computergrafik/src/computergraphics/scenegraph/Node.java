package computergraphics.scenegraph;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

import computergraphics.math.Vector3;
import computergraphics.projects.IntersectionResult;
import computergraphics.projects.Ray3D;

/**
 * Parent class for all scene graph nodes
 * 
 * @author Philipp Jenke
 *
 */
public abstract class Node {

	/**
	 * List of child nodes
	 */
	private List<Node> children = new ArrayList<Node>();

	/**
	 * The colour of the node
	 */
	protected Vector3 colour = new Vector3(1, 1, 1);

	/**
	 * Add a child node.
	 */
	public void addChild(Node child) {
		children.add(child);
	}

	/**
	 * Return the child at the given index.
	 */
	public Node getChildNode(int index) {
		if (index < 0 || index >= getNumberOfChildren()) {
			System.out.println("getChildNode: invalid index.");
			return null;
		}
		return children.get(index);
	}

	/**
	 * Return the number of children
	 */
	public int getNumberOfChildren() {
		return children.size();
	}

	/**
	 * This method is called to draw the node using OpenGL commands. Override in
	 * implementing nodes. Do not forget to call the same method for the
	 * children.
	 */
	public abstract void drawGl(GL2 gl);

	/**
	 * Calculate intersection.
	 * 
	 * @param ray
	 *            the ray
	 * @return an {@link IntersectionResult} or null, if no intersection
	 */
	public IntersectionResult intersection(Ray3D ray) {
		return null;
	}

	/**
	 * Get the colour of the node.
	 * 
	 * @return the colour
	 */
	public Vector3 getColour() {
		return colour;
	}
}
