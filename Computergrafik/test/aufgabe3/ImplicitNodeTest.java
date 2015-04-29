package aufgabe3;

import org.junit.Test;

import computergraphics.math.Vector3;


public class ImplicitNodeTest {

	@Test
	public void testMakeLittleOnes() {
		Vector3 center = new Vector3(1.0, 2.0, 1.0);
		double radius = 2.0;
		ImplicitSphere sphere = new ImplicitSphere(radius, center);
		ImplicitNode node = new ImplicitNode(sphere);
	}
}
