/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3
 */
package aufgabe3;

import java.util.ArrayList;
import java.util.List;

import computergraphics.math.Vector3;

/**
 * This is a cube, which can grid itself. Needed as helper for the Marching
 * Cubes algorithm.
 */
public class Cube {

	/**
	 * The vertices.
	 */
	private List<Vector3> vertices;

	/**
	 * The length of the sides of this cube.
	 */
	private double length;

	/**
	 * Constructor.
	 * 
	 * @param vertices
	 *            the vertices
	 * @throws RuntimeException
	 *             if amount of vertices is not exactly 8
	 */
	public Cube(List<Vector3> vertices) throws RuntimeException {
		if (vertices.size() != 8) {
			throw new RuntimeException(
					"A cube should have exactly 8 vertices, dooofus!");
		}
		this.vertices = vertices;
		length = Math.abs(vertices.get(0).get(0) - vertices.get(1).get(0));
	}

	/**
	 * Gets a list of all the 8 vertices of this cube.
	 * 
	 * @return a list of vertices
	 */
	public List<Vector3> getVertices() {
		return vertices;
	}

	/**
	 * Calculates all the little {@link Cube}s, that fill the big one.
	 * 
	 * @param gridSize
	 *            the size of the grid, i.e. how many cubes per axis
	 * @return the list of grid cubes
	 */
	public List<Cube> makeLittleCubes(double gridSize) {
		List<Cube> grid = new ArrayList<Cube>();
		double step = length / gridSize;
		Vector3 start = vertices.get(0);
		Vector3 end = vertices.get(7);
		for (double i = start.get(2); i < end.get(2); i += step) {
			for (double j = start.get(1); j < end.get(2); j += step) {
				for (double k = start.get(0); k < end.get(2); k += step) {
					List<Vector3> littleVertices = new ArrayList<Vector3>();
					littleVertices.add(new Vector3(k, j, i));
					littleVertices.add(new Vector3(k + step, j, i));
					littleVertices.add(new Vector3(k + step, j + step, i));
					littleVertices.add(new Vector3(k, j + step, i));
					littleVertices.add(new Vector3(k, j, i + step));
					littleVertices.add(new Vector3(k + step, j, i + step));
					littleVertices
							.add(new Vector3(k + step, j + step, i + step));
					littleVertices.add(new Vector3(k, j + step, i + step));
					Cube cube = new Cube(littleVertices);
					// System.out.println(cube.toString());
					grid.add(cube);
				}
			}
		}
		return grid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hier startet ein Cube: ");
		for (Vector3 vector3 : vertices) {
			builder.append(vector3.toString());
		}
		return builder.toString();
	}

}
