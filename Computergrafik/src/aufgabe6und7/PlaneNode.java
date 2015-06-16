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
		normal.normalize();
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
				return result;
			}
		}
		return null;
	}
}