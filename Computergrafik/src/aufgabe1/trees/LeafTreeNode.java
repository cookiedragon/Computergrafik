/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1.trees;

import aufgabe1.RotationNode;
import aufgabe1.TranslationNode;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.GroupNode;

/**
 * This is a scene graph {@link GroupNode} which represents a leaf tree
 * consisting of a {@link LeafTreeCrownNode} and a {@link TreeTrunkNode}.
 */
public class LeafTreeNode extends GroupNode {

	/**
	 * Constructor.
	 */
	public LeafTreeNode() {

		// the nodes to represent the tree parts
		LeafTreeCrownNode crownNode = new LeafTreeCrownNode(0.5f, 24);
		TreeTrunkNode trunkNode = new TreeTrunkNode(0.6f);
		
		// the crown needs to be stacked
		TranslationNode translationNode = new TranslationNode(new Vector3(0.0,
				1.0, 0.0));
		
		// the trunk needs to be rotated upright
		RotationNode rotationNode = new RotationNode(
				new Vector3(1.0, 0.0, 0.0), 270.0f);

		// assembling the tree parts
		this.addChild(rotationNode);
		rotationNode.addChild(trunkNode);
		this.addChild(translationNode);
		translationNode.addChild(crownNode);
	}

}
