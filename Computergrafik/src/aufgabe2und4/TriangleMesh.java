/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2 und 4
 */
package aufgabe2und4;

import java.util.ArrayList;
import java.util.List;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;

/**
 * This is an implementation of {@link ITriangleMesh}. It handles all the
 * {@link Triangle}s needed for a model and all its {@link Vertex}es.
 * <p>
 * It also handles all the data needed to use textures with this mesh.
 */
public class TriangleMesh implements ITriangleMesh {

	/**
	 * List of all the {@link Triangle}s in this mesh.
	 */
	private List<Triangle> triangles = new ArrayList<Triangle>();
	/**
	 * List of all the {@link Vertex}s in this mesh.
	 */
	private List<Vertex> vertices = new ArrayList<Vertex>();

	/**
	 * The filename of the used texture.
	 */
	private String textureFileName = null;
	/**
	 * List of the texture coordinates in this mesh.
	 */
	private List<Vector3> textureCoordinates = new ArrayList<Vector3>();

	@Override
	public void addTriangle(Triangle t) {
		calculateNormalVector(t);
		triangles.add(t);
	}

	@Override
	public int addVertex(Vertex v) {
		vertices.add(v);
		return vertices.size() - 1;
	}

	/**
	 * Calculates the normal {@link Vector3} of the {@link Triangle}.
	 * 
	 * @param triangle
	 *            the {@link Triangle}, will be changed
	 */
	public void calculateNormalVector(Triangle triangle) {
		Vector3 a = vertices.get(triangle.getA()).getPosition();
		Vector3 b = vertices.get(triangle.getB()).getPosition();
		Vector3 c = vertices.get(triangle.getC()).getPosition();
		Vector3 ab = b.subtract(a);
		Vector3 ac = c.subtract(a);
		triangle.setNormal(ab.cross(ac).getNormalized());
	}

	@Override
	public int getNumberOfTriangles() {
		return triangles.size();
	}

	@Override
	public int getNumberOfVertices() {
		return vertices.size();
	}

	@Override
	public Triangle getTriangle(int index) {
		return triangles.get(index);
	}

	@Override
	public Vertex getVertex(int index) {
		return vertices.get(index);
	}

	@Override
	public void clear() {
		triangles.clear();
		vertices.clear();
		textureCoordinates.clear();
	}

	@Override
	public void setTextureFilename(String filename) {
		textureFileName = filename;
	}

	@Override
	public String getTextureFilename() {
		return textureFileName;
	}

	@Override
	public void addTextureCoordinate(Vector3 texCoord) {
		textureCoordinates.add(texCoord);
	}

	@Override
	public Vector3 getTextureCoordinate(int index) {
		return textureCoordinates.get(index);
	}

}
