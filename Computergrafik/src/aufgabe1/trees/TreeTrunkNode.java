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
 * A scene graph {@link Node} which represents a tree trunk.
 */
public class TreeTrunkNode extends Node {

	/**
	 * The height of the trunk.
	 */
	private float height = 1.0f;

	/**
	 * Constructor.
	 * 
	 * @param height
	 *            the height
	 */
	public TreeTrunkNode(float height) {
		this.height = height;
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

		gl.glColor3d(1.0, 0.4, 0.25); // brown
		final float radius = 0.1f;
		// paint the actual cylinder
		glu.gluCylinder(earth, radius, radius, height, slices, stacks);
		glu.gluDeleteQuadric(earth);
	}

}
