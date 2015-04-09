/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabe2;

import java.util.ArrayList;
import java.util.List;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;

/**
 * This is an implementation of {@link ITriangleMesh}. It handles all the
 * {@link Triangle}s needed for a model and all its {@link Vertex}es.
 */
public class TriangleMesh implements ITriangleMesh {

	private List<Triangle> triangles = new ArrayList<Triangle>();
	private List<Vertex> vertices = new ArrayList<Vertex>();

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
	 * @param t
	 *            the {@link Triangle}, will be changed
	 */
	public void calculateNormalVector(Triangle t) {

		Vector3 a = vertices.get(t.getA()).getPosition();
		Vector3 b = vertices.get(t.getB()).getPosition();
		Vector3 c = vertices.get(t.getC()).getPosition();
		Vector3 ab = b.subtract(a);
		Vector3 ac = c.subtract(a);
		t.setNormal(ab.cross(ac).getNormalized());
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
	}

	@Override
	public void setTextureFilename(String filename) {
		// Dummy Implementation

	}

	@Override
	public String getTextureFilename() {
		// Dummy Implementation
		return null;
	}

	@Override
	public void addTextureCoordinate(Vector3 texCoord) {
		// Dummy Implementation

	}

	@Override
	public Vector3 getTextureCoordinate(int index) {
		// Dummy Implementation
		return null;
	}

}
