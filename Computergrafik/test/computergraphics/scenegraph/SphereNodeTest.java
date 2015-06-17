package computergraphics.scenegraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import computergraphics.math.Vector3;
import computergraphics.projects.IntersectionResult;
import computergraphics.projects.Ray3D;

public class SphereNodeTest {

	private Vector3 center;
	private Ray3D ray;
	private Vector3 p;
	private Vector3 r;

	private SphereNode sphere;
	private Vector3 normal;
	private Vector3 colour;

	private Vector3 intersectionPoint;

	private IntersectionResult expected;

	@Before
	public void setup() {

		center = new Vector3();
		colour = new Vector3();

		p = new Vector3(-5.0, 0.0, 0.0);
		r = new Vector3(1.0, 0.0, 0.0);
		ray = new Ray3D(p, r);

		intersectionPoint = new Vector3(-1.0, 0.0, 0.0);
		normal = new Vector3(-1.0, 0.0, 0.0);

		expected = new IntersectionResult();
		expected.normal = normal;
		expected.point = intersectionPoint;
	}

	@Test
	public void testRayIntersection() {
		sphere = new SphereNode(1.0, 20, center, colour);
		IntersectionResult result = sphere.intersection(ray);
		assertNotNull(result);

		expected.object = sphere;

		assertEquals(expected.object, result.object);
		assertEquals(expected.point, result.point);
		assertEquals(expected.normal, result.normal);
	}
}
