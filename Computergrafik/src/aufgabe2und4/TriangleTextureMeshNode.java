/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 4
 */
package aufgabe2und4;

import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;

/**
 * This {@link Node} handels a mesh of {@link Triangle}s with added texture.
 */
public class TriangleTextureMeshNode extends Node {

	/**
	 * The {@link ITriangleMesh} that holds the data.
	 */
	private ITriangleMesh triangleMesh;

	/**
	 * Index for the display list that consists of the {@link Triangle}s.
	 */
	private int triangleDisplayList = -1;

	/**
	 * The texture to be used on this triangle mesh.
	 */
	private Texture textureFile = null;
	private String texturePath;
	private int tid;

	/**
	 * Constructor.
	 * 
	 * @param triangleMesh
	 */
	public TriangleTextureMeshNode(ITriangleMesh triangleMesh,
			String texturePath) {
		this.triangleMesh = triangleMesh;
		this.texturePath = texturePath;
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

		IntBuffer idBuffer = IntBuffer.allocate(1);
		gl.glGenTextures(1, idBuffer);
		tid = idBuffer.get();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, tid);
		try {
			textureFile = TextureIO.newTexture(new File(texturePath), false);
			textureFile.bind(gl);
			textureFile.enable(gl);
		} catch (GLException | IOException e) {
			e.printStackTrace();
		}

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

			Vector3 textureCoordinateA = triangleMesh
					.getTextureCoordinate(triangle.getTextureCoordinate(0));
			Vector3 textureCoordinateB = triangleMesh
					.getTextureCoordinate(triangle.getTextureCoordinate(1));
			Vector3 textureCoordinateC = triangleMesh
					.getTextureCoordinate(triangle.getTextureCoordinate(2));

			gl.glTexCoord2d(textureCoordinateA.get(0),
					textureCoordinateA.get(1));
			gl.glVertex3d(a.getPosition().get(0), a.getPosition().get(1), a
					.getPosition().get(2));

			gl.glTexCoord2d(textureCoordinateB.get(0),
					textureCoordinateB.get(1));
			gl.glVertex3d(b.getPosition().get(0), b.getPosition().get(1), b
					.getPosition().get(2));

			gl.glTexCoord2d(textureCoordinateC.get(0),
					textureCoordinateC.get(1));
			gl.glVertex3d(c.getPosition().get(0), c.getPosition().get(1), c
					.getPosition().get(2));
		}
		gl.glEnd();
		gl.glEndList();
	}
}
