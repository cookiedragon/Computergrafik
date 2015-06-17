package aufgabe6und7;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import computergraphics.math.Vector3;
import computergraphics.projects.IntersectionResult;
import computergraphics.projects.Ray3D;

public class PlaneNodeTest {
	
	private Ray3D ray;
	private Vector3 p;
	private Vector3 r;
	
	private PlaneNode plane;
	private Vector3 point;
	private Vector3 normal;
	private Vector3 colour;
	
	private Vector3 intersectionPoint;
	
	private IntersectionResult expected;
	
	@Before
	public void setup() {
		point = new Vector3();
		normal = new Vector3(0.0, 1.0, 0.0);
		colour = new Vector3();
		
		p = new Vector3(-5.0, 5.0, 5.0);
		r = new Vector3(1.0, -1.0, 1.0);
		ray = new Ray3D(p, r);
		
		intersectionPoint = new Vector3(0.0, 0.0, 10.0);

		expected = new IntersectionResult();
		expected.normal = normal;
		expected.point = intersectionPoint;
	}

	@Test
	public void testRayIntersection() {
		plane = new PlaneNode(point, normal, colour);
		IntersectionResult result = plane.intersection(ray);
		assertNotNull(result);
		
		expected.object = plane;
		
		assertEquals(expected.object, result.object);
		assertEquals(expected.normal, result.normal);
		assertEquals(expected.point, result.point);
	}

}
