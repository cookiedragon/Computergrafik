package aufgabe3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import computergraphics.math.Vector3;

public class ImplicitSphereTest {

	@Test
	public void testIsoValues() {

		ImplicitSphere sphere = new ImplicitSphere(1,
				new Vector3(1.0, 1.0, 1.0));

		// blauer Punkt
		assertEquals(0.0, sphere.getIsoValueFor(new Vector3(2.0, 1.0, 1.0)),
				0.1);
		// grauer Punkt
		assertEquals(1.0, sphere.getIsoValueFor(new Vector3(2.0, 2.0, 1.0)),
				0.1);
		// weisser Punkt
		assertEquals(-0.5, sphere.getIsoValueFor(new Vector3(1.5, 0.5, 1.0)),
				0.1);
	}
}
