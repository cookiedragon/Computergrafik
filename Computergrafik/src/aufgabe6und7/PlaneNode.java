package aufgabe6und7;

import com.jogamp.opengl.GL2;
import computergraphics.math.Vector3;
import computergraphics.projects.IntersectionResult;
import computergraphics.projects.Ray3D;
import computergraphics.scenegraph.Node;

public class PlaneNode extends Node {

	private Vector3 point;
	private Vector3 normal;

	public PlaneNode(Vector3 point, Vector3 normal, Vector3 colour) {
		this.point = point;
		this.normal = normal;
		this.colour = colour;
		this.normal.normalize();
	}

	@Override
	public void drawGl(GL2 gl) {

	}

	@Override
	public IntersectionResult intersection(Ray3D ray) {

		double oben = normal.multiply(point)
				- (normal.multiply(ray.getPoint()));
		double unten = normal.multiply(ray.getDirection());
		if (unten != 0) { // no intersection at all
			double lambda = oben / unten;
			if (lambda > 0) { // intersection happened behind us
				Vector3 intersection = ray.getPoint().add(
						ray.getDirection().multiply(lambda));
				IntersectionResult result = new IntersectionResult();
				result.normal = normal;
				result.point = intersection;
				result.object = this;
				result.reflectionLevel = 0.5;

				// calc the colour
				double i = 1.0;
				Vector3 assi = new Vector3(i, i, i).getNormalized();

				Vector3 one = normal.cross(assi).getNormalized();
				Vector3 two = normal.cross(one).getNormalized();

				double u = one.multiply(intersection);
				double v = two.multiply(intersection);

				if (u < 0) {
					u = -u;
				}
				if (v < 0) {
					v = -v;
				}

				if (((int) u) % 2 == ((int) v) % 2) {
					result.colour = new Vector3(1, 1, 0);
				} else {
					result.colour = colour;
				}
				return result;
			}
		}
		return null;
	}
}