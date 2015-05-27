/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 5
 */
package aufgabe5;

import aufgabe1.TranslationNode;

import com.jogamp.opengl.GL2;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;
import computergraphics.scenegraph.SphereNode;

/**
 * This is a {@link Node}, that uses a {@link Curve} as its object to draw. The
 * curve's control points, the polygon and the tangent are also being drawn.
 */
public class CurveNode extends Node {

	/**
	 * The curve to be drawn.
	 */
	private Curve curve;

	/**
	 * Constructor.
	 * 
	 * @param curve
	 *            the curve
	 */
	public CurveNode(Curve curve) {
		this.curve = curve;
	}

	@Override
	public void drawGl(GL2 gl) {

		// the control points
		gl.glColor3d(0.0, 1.0, 0.0);
		for (Vector3 c : curve.getControlPoints()) {
			SphereNode sphereNode = new SphereNode(0.1, 20);
			TranslationNode transNode = new TranslationNode(c);
			transNode.addChild(sphereNode);
			transNode.drawGl(gl);
		}

		// the polygon
		gl.glLineWidth(3.0f);
		gl.glBegin(GL2.GL_LINE_STRIP);
		gl.glColor3d(0.0, 1.0, 0.0);
		for (Vector3 c : curve.getControlPoints()) {
			gl.glVertex3d(c.get(0), c.get(1), c.get(2));
		}
		gl.glEnd();

		// the curve
		gl.glLineWidth(5.0f);
		gl.glBegin(GL2.GL_LINE_STRIP);
		gl.glColor3d(1.0, 0.0, 0.0);
		for (double t = 0.0; t < 1.01; t += 0.05) {
			Vector3 v = curve.getVertexForParameter(t);
			gl.glVertex3d(v.get(0), v.get(1), v.get(2));
		}
		gl.glEnd();

		// the tangent
		double t = 0.5;
		gl.glLineWidth(3.0f);
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3d(0.0, 0.0, 1.0);
		Vector3 tan1 = curve.getVertexForParameter(t);
		Vector3 tan2 = curve.getTangent(t).add(tan1);
		gl.glVertex3d(tan1.get(0), tan1.get(1), tan1.get(2));
		gl.glVertex3d(tan2.get(0), tan2.get(1), tan2.get(2));
		gl.glEnd();
		SphereNode sphereNode = new SphereNode(0.1, 20);
		TranslationNode transNode = new TranslationNode(tan1);
		transNode.addChild(sphereNode);
		transNode.drawGl(gl);
	}

}
