/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1;

import com.jogamp.opengl.GL2;

import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;

/**
 * Scene graph {@link Node} which rotates all its child nodes.
 *
 */
public class RotationNode extends Node {

	/**
	 * Rotation axis in x-, y- and z-direction.
	 */
	private Vector3 axis = new Vector3(1, 1, 1);

	/**
	 * Rotation angle.
	 */
	private float angle;

	/**
	 * Scene graph {@link Node} which rotates all its child nodes according to
	 * the given angle around the given axis.
	 * 
	 * @param axis
	 *            the rotation axis per {@link Vector3}
	 * @param angle
	 *            the rotation angle in degrees
	 */
	public RotationNode(Vector3 axis, float angle) {
		this.axis.copy(axis);
		this.angle = angle;
	}

	@Override
	public void drawGl(GL2 gl) {

		// Remember current state of the render system
		gl.glPushMatrix();

		// Apply rotation
		gl.glRotatef(angle, (float) axis.get(0), (float) axis.get(1),
				(float) axis.get(2));

		// Draw all children
		for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}

		// Restore original state
		gl.glPopMatrix();

	}

	/**
	 * Returns the rotation angle.
	 * 
	 * @return the rotation angle
	 */
	public float getAngle() {
		return angle;
	}

	/**
	 * Sets the new rotation angle. To be used in animations.
	 * 
	 * @param trans
	 *            the new rotation angle
	 */
	public void setAngle(float angle) {
		this.angle = angle;
	}

}
