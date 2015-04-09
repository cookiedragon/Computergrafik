/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1;

import com.jogamp.opengl.GL2;

import computergraphics.math.Vector3;
import computergraphics.scenegraph.Node;

/**
 * Scene graph {@link Node} which translates all its child nodes to a new
 * position.
 *
 */
public class TranslationNode extends Node {

	/**
	 * Translation factors in x-, y- and z-direction.
	 */
	private Vector3 trans = new Vector3(1, 1, 1);

	/**
	 * Scene graph {@link Node} which translates all its child nodes according
	 * to the given {@link Vector3} to a new position.
	 * 
	 * @param trans
	 *            the translation {@link Vector3}
	 */
	public TranslationNode(Vector3 trans) {
		this.trans.copy(trans);
	}

	@Override
	public void drawGl(GL2 gl) {

		// Remember current state of the render system
		gl.glPushMatrix();

		// Apply translation
		gl.glTranslatef((float) trans.get(0), (float) trans.get(1),
				(float) trans.get(2));

		// Draw all children
		for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}

		// Restore original state
		gl.glPopMatrix();
	}

	/**
	 * Returns the translation {@link Vector3}.
	 * 
	 * @return the translation {@link Vector3}
	 */
	public Vector3 getTrans() {
		return trans;
	}

	/**
	 * Sets the new translation {@link Vector3}. To be used in animations.
	 * 
	 * @param trans
	 *            the new translation {@link Vector3}
	 */
	public void setTrans(Vector3 trans) {
		this.trans = trans;
	}

}
