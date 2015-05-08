/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 5
 */
package aufgabe5;

import com.jogamp.opengl.GL2;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;

public class ArrowNode extends Node {

	private Vector3 rootVertex;
	private Vector3 pointVertex;

	/**
	 * Constructor.
	 * 
	 * @param rootVertex
	 *            the root vertex
	 * @param pointVertex
	 *            the point vertex
	 */
	public ArrowNode(Vector3 rootVertex, Vector3 pointVertex) {
		this.rootVertex = rootVertex;
		this.pointVertex = pointVertex;
	}

	@Override
	public void drawGl(GL2 gl) {
		
		// draw the line
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3d(rootVertex.get(0), rootVertex.get(1), rootVertex.get(2));
		gl.glVertex3d(pointVertex.get(0), pointVertex.get(1),
				pointVertex.get(2));
		gl.glEnd();
	}

}
