/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1.trees;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import computergraphics.scenegraph.Node;

/**
 * A scene graph {@link Node} which represents a cone with a potentially cut off
 * tip and a closed base.
 */
public class ConeNode extends Node {

	/**
	 * The base radius of the cone.
	 */
	private float radius = 0.1f;

	/**
	 * The height of the cone.
	 */
	private float height = 0.5f;

	/**
	 * Constructor.
	 * 
	 * @param baseRadius
	 *            the radius of the base
	 * @param height
	 *            the height
	 */
	public ConeNode(float baseRadius, float height) {
		this.radius = baseRadius;
		this.height = height;
	}

	@Override
	public void drawGl(GL2 gl) {

		gl.glColor3d(0.25, 1.0, 0.0); // green
		GLU glu = new GLU();
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
		final int slices = 24;
		final int stacks = 24;
		// paint the actual cylinder
		glu.gluCylinder(earth, radius, radius - 0.3f, height, slices, stacks);
		// and close the cylinder at the base
		glu.gluDisk(earth, 0.0f, radius, slices, stacks);
		glu.gluDeleteQuadric(earth);
	}

}
