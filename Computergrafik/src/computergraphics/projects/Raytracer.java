package computergraphics.projects;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import computergraphics.framework.Camera;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;

/**
 * Creates a raytraced image of the current scene.
 */
public class Raytracer {

	/**
	 * Reference to the current camera.
	 */
	private final Camera camera;

	/**
	 * Reference to the scene root node.
	 */
	private final Node rootNode;

	/**
	 * The Lightsource.
	 */
	private Vector3 light = new Vector3(0, 3, 0);

	/**
	 * Constructor.
	 * 
	 * @param camera
	 *            Scene camera.
	 * @param rootNode
	 *            Root node of the scenegraph.
	 */
	public Raytracer(Camera camera, Node rootNode) {
		this.camera = camera;
		this.rootNode = rootNode;
	}

	/**
	 * Creates a raytraced image for the current view with the provided
	 * resolution. The opening angle in x-direction is grabbed from the camera,
	 * the opening angle in y-direction is computed accordingly.
	 * 
	 * @param resolutionX
	 *            X-Resolution of the created image.
	 * 
	 * @param resolutionX
	 *            Y-Resolution of the created image.
	 */
	public Image render(int resolutionX, int resolutionY) {
		BufferedImage image = new BufferedImage(resolutionX, resolutionY,
				BufferedImage.TYPE_INT_RGB);

		Vector3 viewDirection = camera.getRef().subtract(camera.getEye())
				.getNormalized();
		Vector3 xDirection = viewDirection.cross(camera.getUp())
				.getNormalized();
		Vector3 yDirection = viewDirection.cross(xDirection).getNormalized();
		double openingAngleYScale = Math.sin(camera.getOpeningAngle() * Math.PI
				/ 180.0);
		double openingAngleXScale = openingAngleYScale * (double) resolutionX
				/ (double) resolutionY;

		for (int i = 0; i < resolutionX; i++) {
			double alpha = (double) i / (double) (resolutionX + 1) - 0.5;
			for (int j = 0; j < resolutionY; j++) {
				double beta = (double) j / (double) (resolutionY + 1) - 0.5;
				Vector3 rayDirection = viewDirection
						.add(xDirection.multiply(alpha * openingAngleXScale))
						.add(yDirection.multiply(beta * openingAngleYScale))
						.getNormalized();
				Ray3D ray = new Ray3D(camera.getEye(), rayDirection);

				Vector3 color = trace(ray, 0);

				// Adjust color boundaries
				for (int index = 0; index < 3; index++) {
					color.set(index, Math.max(0, Math.min(1, color.get(index))));
				}

				image.setRGB(i, j, new Color((int) (255 * color.get(0)),
						(int) (255 * color.get(1)), (int) (255 * color.get(2)))
						.getRGB());
			}
		}

		return image;
	}

	/**
	 * Compute a color from tracing the ray into the scene.
	 * 
	 * @param ray
	 *            Ray which needs to be traced.
	 * @param recursion
	 *            Current recursion depth. Initial recursion depth of the rays
	 *            through the image plane is 0. This parameter is used to abort
	 *            the recursion.
	 * 
	 * @return Color in RGB. All values are in [0,1];
	 */
	private Vector3 trace(Ray3D ray, int recursion) {

		List<IntersectionResult> results = new ArrayList<IntersectionResult>();

		for (int i = 0; i < rootNode.getNumberOfChildren(); i++) {
			Node node = rootNode.getChildNode(i);
			IntersectionResult intersection = node.intersection(ray);
			if (intersection != null) {
				results.add(intersection);
			}
		}
		Vector3 colour = new Vector3();
		if (!results.isEmpty()) {
			Comparator<IntersectionResult> com = new ResultComparator();
			Collections.sort(results, com);
			IntersectionResult result = results.get(0);

			// calc the colour at this point
			colour = light(result, ray);

			// get reflected colour
			if (result.reflectionLevel > 0 && recursion < 5) {
				Vector3 newDirection = result.point.subtract(result.normal
						.multiply(2 * result.point.multiply(result.normal)));
				// Vector3 newDirection = ray
				// .getDirection()
				// .multiply(-1)
				// .subtract(
				// result.normal.multiply(2 * ray.getDirection()
				// .multiply(result.normal)));
				Vector3 newPoint = result.point.add(newDirection.multiply(0.1));
				Vector3 tracedColour = trace(new Ray3D(newPoint, newDirection),
						recursion + 1);
				if (!tracedColour.equals(new Vector3())) {
					colour = colour.multiply(1 - result.reflectionLevel).add(
							tracedColour.multiply(result.reflectionLevel));
				}
			}

			// if in shadow, make it dark
			if (shadow(result)) {
				colour = colour.multiply(0.1);
			}
		}
		return colour;
	}

	/**
	 * 
	 * @param result
	 *            the intersection
	 * @return true, if the intersection is in the shadow
	 */
	private boolean shadow(IntersectionResult result) {
		boolean shadow = false;
		Ray3D ray = new Ray3D(result.point, light.subtract(result.point));
		for (int i = 0; i < rootNode.getNumberOfChildren(); i++) {
			Node node = rootNode.getChildNode(i);
			if (!node.equals(result.object)) {
				IntersectionResult intersection = node.intersection(ray);
				if (intersection != null) {
					shadow = true;
				}
			}
		}
		return shadow;
	}

	private Vector3 light(IntersectionResult result, Ray3D ray) {

		// Vector vom Schnittpunkt zur Lichtquelle
		Vector3 l = light.subtract(result.point).getNormalized();
		// Oberflaechennormale
		Vector3 n = result.normal;
		if (n.multiply(l) < 0) {
			return new Vector3();
		}

		// diffuser Anteil
		// (N*L)*Objectfarbe
		Vector3 colourDiff = result.colour.multiply(n.multiply(l));

		// spekulaerer Anteil
		Vector3 colourSpec = new Vector3();
		// R ist Reflektion vom Schnittpunkt zurueck zum Augpunkt
		Vector3 r = l.subtract((n.multiply(l.multiply(n) * 2))).getNormalized();
		if (r.multiply(ray.getDirection()) > 0) {
			// Material
			double m = 20;
			// (1,1,1)
			Vector3 one = new Vector3(1.0, 1.0, 1.0);
			// (R*(-Vs))^m * (1,1,1)
			colourSpec = one.multiply(Math.pow(
					r.multiply(ray.getDirection().multiply(-1.0)
							.getNormalized()), m));
		}
		// Summe des diffusen Lichts und dem spekularen Licht
		return colourDiff.add(colourSpec);
	}

	private final class ResultComparator implements
			Comparator<IntersectionResult> {
		@Override
		public int compare(IntersectionResult o1, IntersectionResult o2) {
			double z1 = o1.point.get(2);
			double z2 = o2.point.get(2);
			if (z1 < z2) {
				return 1;
			}
			if (z1 > z2) {
				return -1;
			}
			return 0;
		}
	}
}