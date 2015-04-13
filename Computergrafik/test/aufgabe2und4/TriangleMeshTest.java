package aufgabe2und4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aufgabe2und4.TriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;

public class TriangleMeshTest {

	private Triangle triangle;
	private Vertex a, b, c;
	private Vector3 normal;

	private TriangleMesh mesh;

	@Before
	public void setUp() throws Exception {

		a = new Vertex(new Vector3(1, 1, 0));
		b = new Vertex(new Vector3(5, 1, 0));
		c = new Vertex(new Vector3(1, 3, 0));
		normal = new Vector3(0, 0, 1);

		triangle = new Triangle(0, 1, 2);
		mesh = new TriangleMesh();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddTriangleNeedsVerticesFirst() {

		mesh.addTriangle(triangle);
		assertEquals(1, mesh.getNumberOfTriangles());
	}

	@Test
	public void testAddTriangle() {

		mesh.addVertex(a);
		mesh.addVertex(b);
		mesh.addVertex(c);
		mesh.addTriangle(triangle);

		assertEquals(1, mesh.getNumberOfTriangles());
		assertEquals(triangle, mesh.getTriangle(0));
	}

	@Test
	public void testAddVertices() {

		int iA = mesh.addVertex(a);
		int iB = mesh.addVertex(b);
		int iC = mesh.addVertex(c);

		assertEquals(0, iA);
		assertEquals(1, iB);
		assertEquals(2, iC);

		assertEquals(3, mesh.getNumberOfVertices());
		assertEquals(a, mesh.getVertex(iA));
		assertEquals(b, mesh.getVertex(iB));
		assertEquals(c, mesh.getVertex(iC));
	}

	@Test
	public void testCalculateNormal() {

		assertNotEquals(normal, triangle.getNormal());
		
		mesh.addVertex(a);
		mesh.addVertex(b);
		mesh.addVertex(c);
		mesh.addTriangle(triangle);

		assertEquals(normal, mesh.getTriangle(0).getNormal());
	}

}
