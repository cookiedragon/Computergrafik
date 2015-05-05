package aufgabe3;

import computergraphics.math.Vector3;

public class ImplicitGenus extends ImplicitFunction {

	/**
	 * Constructor.
	 * 
	 * @param center
	 *            the center
	 */
	public ImplicitGenus(Vector3 center) {
		this.center = center;
	}

	@Override
	double getIsoValueFor(Vector3 vertex) {
		double x = vertex.get(0) - center.get(0);
		double y = vertex.get(1) - center.get(0);
		double z = vertex.get(2) - center.get(0);

		double zweitesGeschlecht = 2 * y * (y * y - 3 * x * x) * (1 - z * z)
				+ Math.pow((x * x + y * y), 2) - (9 * z * z - 1) * (1 - z * z);
		return zweitesGeschlecht;
	}
}
