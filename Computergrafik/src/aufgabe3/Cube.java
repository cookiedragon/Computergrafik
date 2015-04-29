package aufgabe3;

import java.util.ArrayList;
import java.util.List;

import computergraphics.math.Vector3;

public class Cube {

	private List<Vector3> vertices;
	private double length;

	public Cube(List<Vector3> vertices) {
		this.vertices = vertices;
		length = Math.abs(vertices.get(0).get(0) - vertices.get(1).get(0));
	}

	public List<Vector3> getVertices() {
		return vertices;
	}

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
//					System.out.println(cube.toString());
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
