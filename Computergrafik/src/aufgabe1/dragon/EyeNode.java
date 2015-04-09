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
 * This {@link Node} represents a dragons eye.
 */
public class EyeNode extends Node {

	/**
	 * Constructor.
	 * 
	 */
	public EyeNode() {
		
	}

	@Override
	public void drawGl(GL2 gl) {
		GLU glu = new GLU();
		gl.glColor3d(2.0, 0.0, 0.0); // red
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);

		glu.gluSphere(earth, 0.05f, 20, 20);
		glu.gluDeleteQuadric(earth);
	}

}
