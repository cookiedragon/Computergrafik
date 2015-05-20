/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2 und 4
 */
package aufgabe2und4;

import java.io.File;
import java.io.IOException;
import java.util.Random;

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
 * This {@link Node} displays a model stored in an {@link ITriangleMesh}. With
 * or without a {@link Texture}
 */
public class TriangleMeshNode extends Node {

	/**
	 * The {@link ITriangleMesh} that holds the data.
	 */
	private ITriangleMesh triangleMesh;

	/**
	 * Index for the display list that consists of the {@link Triangle}s.
	 */
	private int triangleDisplayList = -1;

	/**
	 * Flag set to true, if the {@link ITriangleMesh} has a texture.
	 */
	private boolean hasTexture = false;

	/**
	 * Constructor.
	 * 
	 * @param an
	 *            {@link ITriangleMesh}
	 */
	public TriangleMeshNode(ITriangleMesh triangleMesh) {
		this.triangleMesh = triangleMesh;
		hasTexture = (triangleMesh.getTextureFilename() == null) ? false : true;
	}

	@Override
	public void drawGl(GL2 gl) {

		if (triangleDisplayList == -1) {
			init(gl);
		}
		gl.glCallList(triangleDisplayList);
	}

	/**
	 * Initializes the display list that consists of the {@link Triangle}s and
	 * ocasionally a {@link Texture}.
	 * 
	 * @param gl
	 *            needed to draw
	 */
	private void init(GL2 gl) {

		triangleDisplayList = gl.glGenLists(1);
		gl.glNewList(triangleDisplayList, GL2.GL_COMPILE);

		Texture texture = loadTexture(gl);

		gl.glBegin(GL2.GL_TRIANGLES);
		drawTriangles(gl);
		gl.glEnd();
		if (hasTexture) {
			texture.disable(gl);
		}
		gl.glEndList();
	}

	/**
	 * Loads the {@link Texture} from the {@link ITriangleMesh}.
	 * 
	 * @param gl
	 *            needed to draw
	 * @return the {@link Texture}
	 */
	private Texture loadTexture(GL2 gl) {

		if (hasTexture) {
			try {
				Texture texture = TextureIO.newTexture(
						new File(triangleMesh.getTextureFilename()), false);
				texture.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S,
						GL2.GL_CLAMP_TO_EDGE);
				texture.setTexParameterf(gl, GL2.GL_TEXTURE_MIN_FILTER,
						GL2.GL_NEAREST);
				gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE,
						GL2.GL_DECAL);
				texture.enable(gl);
				texture.bind(gl);
				return texture;
			} catch (GLException | IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Draws the {@link Triangle}s, with or without {@link Texture}.
	 * 
	 * @param gl
	 *            needed to draw
	 */
	private void drawTriangles(GL2 gl) {

		for (int i = 0; i < triangleMesh.getNumberOfTriangles(); i++) {

			Triangle triangle = triangleMesh.getTriangle(i);
			Vector3 normal = triangle.getNormal();
			gl.glNormal3d(normal.get(0), normal.get(1), normal.get(2));

			for (int j = 0; j < 3; j++) {
				if (hasTexture) {
					Vector3 tc = triangleMesh.getTextureCoordinate(triangle
							.getTextureCoordinate(j));
					gl.glTexCoord2d(tc.get(0), tc.get(1));
				} else {
					Random r = new Random();
					gl.glColor3d(r.nextDouble(), r.nextDouble(), r.nextDouble());
				}
				Vertex v = triangleMesh.getVertex(triangle.get(j));
				gl.glVertex3d(v.getPosition().get(0), v.getPosition().get(1), v
						.getPosition().get(2));
			}
		}
	}
}
