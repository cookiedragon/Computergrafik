/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 5
 */
package aufgabe5;

import java.util.List;

import aufgabe1.TranslationNode;

import computergraphics.math.Vector3;
import computergraphics.scenegraph.GroupNode;
import computergraphics.scenegraph.SphereNode;

public class CurveNode extends GroupNode {

	/**
	 * Constructor.
	 * 
	 * @param curve
	 *            the curve
	 */
	public CurveNode(Curve curve) {

		// the control points
		List<Vector3> controlPoints = curve.getControlPoints();
		for (Vector3 point : controlPoints) {
			SphereNode sphere = new SphereNode(0.5, 20);
			TranslationNode transNode = new TranslationNode(point);
			transNode.addChild(sphere);
			this.addChild(transNode);
		}

		// the curve
		Vector3 prev = curve.getVertexForParameter(0);
		for (double i = 0.05; i < 1.01; i += 0.05) {
			Vector3 next = curve.getVertexForParameter(i);
			ArrowNode tangent = new ArrowNode(prev, next);
			this.addChild(tangent);
			prev = next;
		}

		// the tangent
		ArrowNode tangent = new ArrowNode(curve.getVertexForParameter(0.5),
				curve.getTangent(0.5));
		this.addChild(tangent);
	}
}
