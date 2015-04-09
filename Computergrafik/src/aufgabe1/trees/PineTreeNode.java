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
 * This is a scene graph {@link GroupNode} which represents a pine tree
 * consisting of three {@link ConeNode}s and a {@link TreeTrunkNode}.
 */
public class PineTreeNode extends GroupNode {

	/**
	 * Constructor.
	 */
	public PineTreeNode() {

		// the nodes to represent the tree parts
		TreeTrunkNode trunkNode = new TreeTrunkNode(0.5f);
		ConeNode coneNode1 = new ConeNode(0.5f, 0.5f);
		ConeNode coneNode2 = new ConeNode(0.4f, 0.5f);
		ConeNode coneNode3 = new ConeNode(0.3f, 0.5f);

		// the tree needs to be hoisted upright
		RotationNode rotationNode = new RotationNode(
				new Vector3(1.0, 0.0, 0.0), 270.0f);

		// the cones need to be stacked
		TranslationNode coneTransNode1 = new TranslationNode(new Vector3(0.0,
				0.0, 0.5));
		TranslationNode coneTransNode2 = new TranslationNode(new Vector3(0.0,
				0.0, 1.0));
		TranslationNode coneTransNode3 = new TranslationNode(new Vector3(0.0,
				0.0, 1.5));

		// assembling the tree parts
		this.addChild(rotationNode);
		rotationNode.addChild(trunkNode);
		rotationNode.addChild(coneTransNode1);
		rotationNode.addChild(coneTransNode2);
		rotationNode.addChild(coneTransNode3);
		coneTransNode1.addChild(coneNode1);
		coneTransNode2.addChild(coneNode2);
		coneTransNode3.addChild(coneNode3);
	}

}
