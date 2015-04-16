/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1.dragon;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

import computergraphics.scenegraph.Node;

/**
 * This {@link Node} represents a dragons wing. This is an animated version.
 */
public class WingNode extends Node {

	float x1, y1, z1, x2, y2, z2, x3, y3, z3;
	float uppest;
	float lowest;
	boolean up = false;

	public WingNode(float x1, float y1, float z1, float x2, float y2, float z2,
			float x3, float y3, float z3) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.z1 = z1;
		this.z2 = z2;
		this.z3 = z3;

		this.uppest = z3 + 0.5f;
		this.lowest = z3 - 0.5f;
	}

	@Override
	public void drawGl(GL2 gl) {

		gl.glColor3d(0.0, 0.0, 0.5); // blue
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glVertex3f(x1, y1, z1);
		gl.glVertex3f(x2, y2, z2);

		// update vertex
		if (up) {
			z3 = z3 + 0.3f;
		} else {
			z3 = z3 - 0.3f;
		}
		if (z3 <= lowest || z3 >= uppest) {
			up = !up;
		}
		gl.glVertex3f(x3, y3, z3);
		gl.glEnd();

	}

}
