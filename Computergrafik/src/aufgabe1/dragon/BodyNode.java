/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1.dragon;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import computergraphics.scenegraph.Node;

/**
 * This {@link Node} represents a dragons body.
 */
public class BodyNode extends Node {

	/**
	 * The size of the head.
	 */
	private float size = 0.4f;

	/**
	 * Constructor.
	 * 
	 * @param size
	 *            the size
	 */
	public BodyNode(float size) {
		this.size = size;
	}

	@Override
	public void drawGl(GL2 gl) {

		GLU glu = new GLU();
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
		glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
		final int slices = 24;
		final int stacks = 24;

		gl.glColor3d(0.0, 0.0, 0.5); // blue
		final float radius = size;
		float height = size * 4.5f;
		// paint the actual cylinder
		glu.gluDisk(earth, 0.0, radius, slices, stacks);
		glu.gluCylinder(earth, radius, radius, height, slices, stacks);
		gl.glTranslatef(0, 0, height);
		glu.gluDisk(earth, 0.0, radius, slices, stacks);
		glu.gluDeleteQuadric(earth);
	}

}
