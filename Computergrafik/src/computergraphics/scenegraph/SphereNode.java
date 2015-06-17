package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import computergraphics.math.Vector3;
import computergraphics.projects.IntersectionResult;
import computergraphics.projects.Ray3D;

/**
 * Geomtrie of a simple sphere
 * 
 * @author abo781
 *
 */
public class SphereNode extends Node {

	/**
	 * The center of the sphere.
	 */
	private Vector3 center = new Vector3();

	/**
	 * Flag if center is specified.
	 */
	private boolean customCenter = false;

	/**
	 * Sphere radius.
	 */
	private double radius;

	/**
	 * Resolution (in one dimension) of the mesh.
	 */
	private int resolution;

	/**
	 * The level of reflection between 0 and 1.
	 */
	private double reflectionLevel = 0.0;

	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution) {
		this.radius = radius;
		this.resolution = resolution;
	}

	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution, Vector3 center,
			Vector3 colour) {
		this.radius = radius;
		this.resolution = resolution;
		this.center = center;
		this.colour = colour;
		customCenter = true;
	}

	public SphereNode(double radius, int resolution, Vector3 center,
			Vector3 colour, double reflectionLevel) {
		this.radius = radius;
		this.resolution = resolution;
		this.center = center;
		this.colour = colour;
		customCenter = true;
		this.reflectionLevel = reflectionLevel;
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glPushMatrix();
		if (customCenter) {
			gl.glTranslated(center.get(0), center.get(1), center.get(2));
		}
		GLU glu = new GLU();
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
		glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
		final int slices = resolution;
		final int stacks = resolution;
		glu.gluSphere(earth, radius, slices, stacks);
		gl.glPopMatrix();
	}

	@Override
	public IntersectionResult intersection(Ray3D ray) {
		double p = (2 * ray.getPoint().multiply(ray.getDirection()) - (2 * center
				.multiply(ray.getDirection())))
				/ ray.getDirection().multiply(ray.getDirection());
		double q = (ray.getPoint().multiply(ray.getPoint()) - 2
				* ray.getPoint().multiply(center) + center.multiply(center) - radius
				* radius)
				/ ray.getDirection().multiply(ray.getDirection());
		double lambda1 = -p / 2 + Math.sqrt(p * p / 4 - q);
		double lambda2 = -p / 2 - Math.sqrt(p * p / 4 - q);
		double lambda = (lambda1 > 0) ? ((lambda2 > 0) ? Math.min(lambda1,
				lambda2) : lambda1) : ((lambda2 > 0) ? lambda2 : 0);
		if (lambda != 0) {
			Vector3 intersection = ray.getPoint().add(
					ray.getDirection().multiply(lambda));
			IntersectionResult result = new IntersectionResult();
			result.point = intersection;
			result.object = this;
			Vector3 normal = intersection.subtract(center);
			normal.normalize();
			result.normal = normal;
			result.colour = colour;
			result.reflectionLevel = reflectionLevel;
			return result;
		}
		return null;
	}
}