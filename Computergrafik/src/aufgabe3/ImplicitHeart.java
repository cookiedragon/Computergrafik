package aufgabe3;

import computergraphics.math.Vector3;

public class ImplicitHeart extends ImplicitFunction {

	/**
	 * Constructor.
	 * 
	 * @param center
	 *            the center
	 */
	public ImplicitHeart(Vector3 center) {
		this.center = center;
	}

	@Override
	double getIsoValueFor(Vector3 vertex) {
		double xSqr = Math.pow(vertex.get(0) - center.get(0), 2);
		double ySqr = Math.pow(vertex.get(1) - center.get(1), 2);
		double zSqr = Math.pow(vertex.get(2) - center.get(2), 2);
		double zCube = Math.pow(vertex.get(2) - center.get(2), 3);

		double heart = Math.pow((xSqr + (9/4)*ySqr + zSqr - 1), 3) - (xSqr * zCube)
				- (9 / 80) * (ySqr * zCube);
		return heart;
	}

}
