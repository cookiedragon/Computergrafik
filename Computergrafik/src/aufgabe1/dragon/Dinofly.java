/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1.dragon;

import aufgabe1.RotationNode;
import aufgabe1.TranslationNode;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.GroupNode;

/**
 * This {@link GroupNode} represents a dinofly, a species of dragon. It is a
 * complex object consisting of several primitive parts. Some of which (the
 * wings) are animated.
 */
public class Dinofly extends GroupNode {

	/**
	 * The constructor.
	 */
	public Dinofly() {

		// the nodes to represent the dragon parts
		LegNode legNode = new LegNode(0.5f);

		TailNode tailNode = new TailNode(0.1f, 0.0f, 5.5f, 0.4f, 0.0f, 5.5f,
				0.25f, 3.0f, 5.5f);

		HeadNode headNode1 = new HeadNode(0.4f);
		HeadNode headNode2 = new HeadNode(0.25f);

		BodyNode bodyNode = new BodyNode(0.5f);

		EyeNode eyeNode = new EyeNode();

		float wing1 = 5.5f;
		float wing2 = 5.5f;

		WingNode wingNode1 = new WingNode(0.35f, -2.0f, 5.5f, 0.35f, 0.0f,
				5.5f, 4.0f, -1.0f, wing1);
		WingNode wingNode2 = new WingNode(0.15f, -2.0f, 5.5f, 0.15f, 0.0f,
				5.5f, -3.5f, -1.0f, wing2);

		// the dragon needs to be hoisted upright
		RotationNode rotationNode = new RotationNode(
				new Vector3(1.0, 0.0, 0.0), 270.0f);

		// the legs need to be positioned
		TranslationNode legTransNode1 = new TranslationNode(new Vector3(0.0,
				-2.0, 5.0));
		TranslationNode legTransNode2 = new TranslationNode(new Vector3(0.5,
				-2.0, 5.0));
		TranslationNode legTransNode3 = new TranslationNode(new Vector3(0.0,
				0.0, 5.0));
		TranslationNode legTransNode4 = new TranslationNode(new Vector3(0.5,
				0.0, 5.0));

		// the body need to be positioned
		TranslationNode bodyTransNode = new TranslationNode(new Vector3(0.25,
				-2.1, 5.8));
		RotationNode bodyRotationNode = new RotationNode(new Vector3(1.0, 0.0,
				0.0), 270.0f);

		// the head need to be positioned
		TranslationNode headTransNode1 = new TranslationNode(new Vector3(0.25,
				-2.25, 6.5));
		TranslationNode headTransNode2 = new TranslationNode(new Vector3(0.25,
				-2.8, 6.4));

		// the eyes need to be positioned
		TranslationNode eyeTransNode1 = new TranslationNode(new Vector3(0.0,
				-2.5, 6.7));
		TranslationNode eyeTransNode2 = new TranslationNode(new Vector3(0.55,
				-2.5, 6.7));

		// assembling the dragon parts
		this.addChild(rotationNode);
		GroupNode dragonNode = new GroupNode();
		rotationNode.addChild(dragonNode);
		dragonNode.addChild(tailNode);
		dragonNode.addChild(wingNode1);
		dragonNode.addChild(wingNode2);

		dragonNode.addChild(legTransNode1);
		dragonNode.addChild(legTransNode2);
		dragonNode.addChild(legTransNode3);
		dragonNode.addChild(legTransNode4);
		dragonNode.addChild(bodyTransNode);
		dragonNode.addChild(headTransNode1);
		dragonNode.addChild(headTransNode2);
		dragonNode.addChild(eyeTransNode1);
		dragonNode.addChild(eyeTransNode2);

		legTransNode1.addChild(legNode);
		legTransNode2.addChild(legNode);
		legTransNode3.addChild(legNode);
		legTransNode4.addChild(legNode);
		bodyTransNode.addChild(bodyRotationNode);
		bodyRotationNode.addChild(bodyNode);
		headTransNode1.addChild(headNode1);
		headTransNode2.addChild(headNode2);
		eyeTransNode1.addChild(eyeNode);
		eyeTransNode2.addChild(eyeNode);
	}

}
