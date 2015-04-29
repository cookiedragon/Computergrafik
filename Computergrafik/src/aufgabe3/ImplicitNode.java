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

import aufgabe2und4.TriangleMeshNode;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.GroupNode;

/**
 * This is a {@link GroupNode}, that uses an {@link ImplicitFunction} as its
 * object to draw. This Object will be tesselated via the Marching Cubes
 * algorithm and stored in an {@link ITriangleMesh}. Which in turn will be
 * passed to an {@link TriangleMeshNode} that handles the actual drawing.
 */
public class ImplicitNode extends GroupNode {

	private static final String lookup = "resources/caseLookupTable.txt";

	/**
	 * The {@link ITriangleMesh} that holds the data.
	 */
	private ITriangleMesh triangleMesh;

	/**
	 * The {@link ImplicitFunction} that describes the object.
	 */
	private ImplicitFunction implicitFunction;

	public ImplicitNode(ImplicitFunction implicitFunction) {
		this.implicitFunction = implicitFunction;
		Cube boundingBox = implicitFunction.getBoundingBox();

		// make grid
		double gridSize = 50;
		List<Cube> grid = boundingBox.makeLittleCubes(gridSize);

		// load the lookup table file
		loadLookupTable();

		// determine triangles
		for (Cube cube : grid) {
			List<Double> values = new ArrayList<Double>();
			// calculate function values (iso) for all the vertices
			for (Vector3 vertex : cube.getVertices()) {
				values.add(implicitFunction.getIsoValueFor(vertex));
			}
			createTriangles(cube.getVertices(), values);
		}
	}

	private void loadLookupTable() {

		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new File(lookup))
					.useDelimiter("\\s*,\\s*|\\s*\\{\\s*|\\s*\\}\\s*");
			int[] table = new int[15*256];
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

		int caseIndex = 0;
		Iterator<Double> iterator = values.iterator();
		// are isos out or in?
		for (int i = 1; i < 128; i *= 2) {
			caseIndex += i * (iterator.next() > 0 ? 1 : 0);
		}

		if (caseIndex != 0 && caseIndex != 255) {
			// lookup
			// Schnittstellen berechnen
			// Dreiecke bauen
			// im TriangleMesh speichern
		}
	}

}
