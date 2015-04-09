/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 */
package aufgabe1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aufgabe1.dragon.Dinofly;
import aufgabe1.trees.LeafTreeNode;
import aufgabe1.trees.PineTreeNode;

import computergraphics.datastructures.Coordinates;
import computergraphics.datastructures.ObjIO;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.GroupNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class Aufgabe1 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	private RotationNode dragonFlyNode;
	Vector3 yAxis = new Vector3(0.0, 1.0, 0.0);
	private float angle = 0.0f;

	/**
	 * Constructor.
	 */
	public Aufgabe1(int timerInverval) {
		super(timerInverval);
		ShaderNode colorNode = new ShaderNode();
		getRoot().addChild(colorNode);

		// first exercises
		// ScaleNode scaleNode = new ScaleNode(new Vector3(2.0, 2.0, 2.0));
		// SingleTriangleNode triangleNode = new SingleTriangleNode();
		// colorNode.addChild(scaleNode);
		// scaleNode.addChild(triangleNode);

		// the nodes needed for the scene
		int squareWidth = 20;
		SquareNode squareNode = new SquareNode(squareWidth);
		PineTreeNode pineTreeNode = new PineTreeNode();
		LeafTreeNode leafTreeNode = new LeafTreeNode();
		ScaleNode scale = new ScaleNode(new Vector3(0.25, 0.25, 0.25));
		GroupNode trees = new GroupNode();

		// assembling the nodes
		colorNode.addChild(scale);
		scale.addChild(squareNode);
		scale.addChild(trees);

		// plant the trees randomly
		List<Coordinates> coordinates = new ArrayList<Coordinates>();
		for (int i = 0; i < 100; i++) {
			int x = (new Random()).nextInt(squareWidth - 1) - (squareWidth - 2)
					/ 2;
			int z = (new Random()).nextInt(squareWidth - 1) - (squareWidth - 2)
					/ 2;
			Coordinates c = new Coordinates(x, 0, z);
			if (!coordinates.contains(c)) {
				coordinates.add(c);
				// the new planting place
				TranslationNode translationNode = new TranslationNode(
						new Vector3(x, 0, z));
				trees.addChild(translationNode);
				// plant either type of tree
				if ((new Random()).nextInt(2) % 2 == 0) {
					translationNode.addChild(leafTreeNode);
				} else {
					translationNode.addChild(pineTreeNode);
				}
			}
		}

		// build the dinofly
		Dinofly dragon = new Dinofly();
		ScaleNode scaleDragon = new ScaleNode(new Vector3(0.2, 0.2, 0.2));
		dragonFlyNode = new RotationNode(yAxis, angle);
		TranslationNode tNode = new TranslationNode(new Vector3(-(squareWidth * 0.5) ,
				-2.0, 0.0));
		colorNode.addChild(scaleDragon);
		scaleDragon.addChild(dragonFlyNode);
		dragonFlyNode.addChild(tNode);
		tNode.addChild(dragon);

		// Debugging
		ObjIO reader = new ObjIO();
		reader.einlesen("meshes/cube.obj", null);
	}

	/*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		System.out.println("Tick");

		angle = angle + 30.0f;
		dragonFlyNode.setAngle(angle);
	}

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		new Aufgabe1(1000);
	}
}
