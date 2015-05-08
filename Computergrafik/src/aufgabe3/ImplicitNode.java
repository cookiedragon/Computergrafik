/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3
 */
package aufgabe3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import aufgabe2und4.TriangleMesh;
import aufgabe2und4.TriangleMeshNode;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.GroupNode;

/**
 * This is a {@link GroupNode}, that uses an {@link ImplicitFunction} as its
 * object to draw. This Object will be tesselated via the Marching Cubes
 * algorithm and stored in an {@link ITriangleMesh}. Which in turn will be
 * passed to an {@link TriangleMeshNode} that handles the actual drawing.
 */
public class ImplicitNode extends GroupNode {

	/**
	 * The {@link ITriangleMesh} that holds the data.
	 */
	private ITriangleMesh triangleMesh = new TriangleMesh();

	/**
	 * The path for the lookup table.
	 */
	private static final String lookup = "resources/caseLookupTable.txt";

	/**
	 * The lookup table.
	 */
	private int[] table;

	/**
	 * The iso value of the {@link ImplicitFunction}.
	 */
	private double iso = 0.0;

	/**
	 * Constructor.
	 * 
	 * @param implicitFunction
	 *            the {@link ImplicitFunction}
	 */
	public ImplicitNode(ImplicitFunction implicitFunction) {

		iso = implicitFunction.getIso();

		// make grid
		double gridSize = 25;
		Cube boundingBox = implicitFunction.getBoundingBox();
		List<Cube> grid = boundingBox.makeLittleCubes(gridSize);

		// load the lookup table file
		loadLookupTable();

		// determine triangles
		for (Cube cube : grid) {
			List<Double> values = new ArrayList<Double>();
			// calculate function values (iso) for all the vertices
			for (Vector3 vertex : cube.getVertices()) {
				double iso = implicitFunction.getIsoValueFor(vertex);
				values.add(iso);
			}
			createTriangles(cube.getVertices(), values);
		}

		// hand it over to the child node to actually draw
		this.addChild(new TriangleMeshNode(triangleMesh));
	}

	private void loadLookupTable() {

		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new File(lookup))
					.useDelimiter("\\s*,\\s*|\\s*\\{\\s*|\\s*\\}\\s*");
			table = new int[15 * 256];
			int i = 0;
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					table[i] = scanner.nextInt();
					i++;
				} else {
					scanner.next();
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This creates the necessary triangles for a {@link Cube}. The algorithm
	 * used is the Marching Cubes algorithm.
	 * 
	 * @param points
	 *            the vertices
	 * @param values
	 *            the iso values
	 */
	private void createTriangles(List<Vector3> points, List<Double> values) {

		double caseIndex = 0;
		Iterator<Double> iterator = values.iterator();
		// are isos out or in?
		for (int i = 1; i < 129; i *= 2) {
			caseIndex += i * (iterator.next() > iso ? 1 : 0);
		}

		// if implicit function cuts through our cube
		if (caseIndex != 0 && caseIndex != 255) {
			// lookup
			int start = (int) (caseIndex * 15);
			for (int i = start; i < (start + 15); i += 3) {
				int e1 = table[i];
				int e2 = table[i + 1];
				int e3 = table[i + 2];
				if ((e1 > -1) && (e2 > -1) && (e3 > -1)) {
					// calculate vertices
					Vertex v1 = getIntersectionVertex(e1, points, values);
					Vertex v2 = getIntersectionVertex(e2, points, values);
					Vertex v3 = getIntersectionVertex(e3, points, values);

					// and hand them to our triangleMesh
					int v1i = triangleMesh.addVertex(v1);
					int v2i = triangleMesh.addVertex(v2);
					int v3i = triangleMesh.addVertex(v3);
					triangleMesh.addTriangle(new Triangle(v1i, v2i, v3i));
				}
			}
		}
	}

	/**
	 * Calculates the vertex at the intersection of the given edge.
	 * 
	 * @param edge
	 *            the edge
	 * @param points
	 *            the list of vertices
	 * @param values
	 */
	private Vertex getIntersectionVertex(int edge, List<Vector3> points,
			List<Double> values) {

		// find the two points of the edge
		Vector3 p1;
		Vector3 p2;
		double iso1;
		double iso2;
		if (edge == 0) {
			p1 = points.get(0);
			p2 = points.get(1);
			iso1 = values.get(0);
			iso2 = values.get(1);
		} else if (edge == 1) {
			p1 = points.get(1);
			p2 = points.get(2);
			iso1 = values.get(1);
			iso2 = values.get(2);
		} else if (edge == 2) {
			p1 = points.get(2);
			p2 = points.get(3);
			iso1 = values.get(2);
			iso2 = values.get(3);
		} else if (edge == 3) {
			p1 = points.get(3);
			p2 = points.get(0);
			iso1 = values.get(3);
			iso2 = values.get(0);
		} else if (edge == 4) {
			p1 = points.get(4);
			p2 = points.get(5);
			iso1 = values.get(4);
			iso2 = values.get(5);
		} else if (edge == 5) {
			p1 = points.get(5);
			p2 = points.get(6);
			iso1 = values.get(5);
			iso2 = values.get(6);
		} else if (edge == 6) {
			p1 = points.get(6);
			p2 = points.get(7);
			iso1 = values.get(6);
			iso2 = values.get(7);
		} else if (edge == 7) {
			p1 = points.get(7);
			p2 = points.get(4);
			iso1 = values.get(7);
			iso2 = values.get(4);
		} else if (edge == 8) {
			p1 = points.get(0);
			p2 = points.get(4);
			iso1 = values.get(0);
			iso2 = values.get(4);
		} else if (edge == 9) {
			p1 = points.get(1);
			p2 = points.get(5);
			iso1 = values.get(1);
			iso2 = values.get(5);
		} else if (edge == 10) {
			p1 = points.get(3);
			p2 = points.get(7);
			iso1 = values.get(3);
			iso2 = values.get(7);
		} else { // edge == 11
			p1 = points.get(2);
			p2 = points.get(6);
			iso1 = values.get(2);
			iso2 = values.get(6);
		}

		// calculate basic intersection coordinates
		// double x = p1.get(0) + ((p2.get(0) - p1.get(0)) * 0.5);
		// double y = p1.get(1) + ((p2.get(1) - p1.get(1)) * 0.5);
		// double z = p1.get(2) + ((p2.get(2) - p1.get(2)) * 0.5);
		// return new Vertex(new Vector3(x, y, z));

		// calculate properly interpolated coordinates
		double t = (iso - iso1) / (iso2 - iso1);
		Vector3 p = p1.multiply(1 - t).add(p2.multiply(t));
		return new Vertex(p);
	}
}
