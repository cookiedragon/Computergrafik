/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1;

import com.jogamp.opengl.GL2;
import computergraphics.scenegraph.Node;

/**
 * A scene graph {@link Node} which represents a single flat square.
 *
 */
public class SquareNode extends Node {

	/**
	 * Width of the square.
	 */
	private float width;
	
	/**
	 * Zero.
	 */
	private float zero;

	/**
	 * Constructor.
	 * 
	 * @param width the width of the square
	 */
	public SquareNode(float width) {
		this.width = width / 2;
	}
	
	@Override
	public void drawGl(GL2 gl) {
		
		gl.glColor3d(zero, zero, zero); // black
		gl.glBegin(GL2.GL_QUADS);
		gl.glNormal3d(0, 0, 1);
		
		gl.glVertex3f(-width, zero, width);
		gl.glVertex3f(-width, zero, -width);
		gl.glVertex3f(width, zero, -width);
		gl.glVertex3f(width, zero, width);
		
		gl.glEnd();
	}

}
