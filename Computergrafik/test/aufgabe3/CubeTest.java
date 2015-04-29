package aufgabe3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import computergraphics.math.Vector3;

public class CubeTest {

	@Test
	public void testMakeLittleOnes() {

		List<Vector3> vertices = new ArrayList<Vector3>();
		vertices.add(new Vector3(0, 0, 0));
		vertices.add(new Vector3(50, 0, 0));
		vertices.add(new Vector3(50, 50, 0));
		vertices.add(new Vector3(0, 50, 0));

		vertices.add(new Vector3(0, 0, 50));
		vertices.add(new Vector3(50, 0, 50));
		vertices.add(new Vector3(50, 50, 50));
		vertices.add(new Vector3(0, 50, 50));

		Cube cube = new Cube(vertices);
		
		List<Cube> littleCubes = cube.makeLittleCubes(2);
		assertEquals(8, littleCubes.size());
	}
}
