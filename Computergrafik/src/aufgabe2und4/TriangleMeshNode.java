/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabe2und4;

import com.jogamp.opengl.GL2;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;

/**
 * This {@link Node} handels a mesh of {@link Triangle}s.
 */
public class TriangleMeshNode extends Node {

	private ITriangleMesh triangleMesh;

	/**
	 * Index for the display list that consists of the {@link Triangle}s.
	 */
	private int triangleDisplayList = -1;

	/**
	 * Constructor.
	 * 
	 * @param triangleMesh
	 */
	public TriangleMeshNode(ITriangleMesh triangleMesh) {
		this.triangleMesh = triangleMesh;
	}

	@Override
	public void drawGl(GL2 gl) {

		if (triangleDisplayList == -1) {
			init(gl);
		}
		gl.glCallList(triangleDisplayList);
		gl.glPopMatrix();
		gl.glFlush();
	}

	/**
	 * Initializes the display list that consists of the {@link Triangle}s.
	 * 
	 * @param gl
	 *            needed to draw
	 */
	private void init(GL2 gl) {

		triangleDisplayList = gl.glGenLists(1);
		gl.glNewList(triangleDisplayList, GL2.GL_COMPILE);
		gl.glColor3d(1.0, 0.0, 0.8);
		gl.glBegin(GL2.GL_TRIANGLES);

		int numberOfTriangles = triangleMesh.getNumberOfTriangles();
		for (int i = 0; i < numberOfTriangles; i++) {

			Triangle triangle = triangleMesh.getTriangle(i);
			Vertex a = triangleMesh.getVertex(triangle.getA());
			Vertex b = triangleMesh.getVertex(triangle.getB());
			Vertex c = triangleMesh.getVertex(triangle.getC());
			Vector3 normal = triangle.getNormal();

			gl.glNormal3d(normal.get(0), normal.get(1), normal.get(2));
			gl.glVertex3d(a.getPosition().get(0), a.getPosition().get(1), a
					.getPosition().get(2));
			gl.glVertex3d(b.getPosition().get(0), b.getPosition().get(1), b
					.getPosition().get(2));
			gl.glVertex3d(c.getPosition().get(0), c.getPosition().get(1), c
					.getPosition().get(2));
		}
		gl.glEnd();
		gl.glEndList();
	}
}
