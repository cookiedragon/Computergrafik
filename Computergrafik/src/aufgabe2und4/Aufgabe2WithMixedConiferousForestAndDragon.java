/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabe2und4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aufgabe1.RotationNode;
import aufgabe1.SquareNode;
import aufgabe1.TranslationNode;
import aufgabe1.dragon.Dinofly;
import aufgabe1.trees.LeafTreeNode;
import aufgabe1.trees.PineTreeNode;

import computergraphics.datastructures.Coordinates;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.GroupNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;

/**
 * Application for the second exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class Aufgabe2WithMixedConiferousForestAndDragon extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	private RotationNode dragonFlyNode;
	Vector3 yAxis = new Vector3(0.0, 1.0, 0.0);
	private float angle = 0.0f;

	/**
	 * Path to the .obj that stores a model of a cow.
	 */
	private String cowPath = "resources/cow.obj";

	/**
	 * Constructor.
	 */
	public Aufgabe2WithMixedConiferousForestAndDragon(int timerInverval) {
		super(timerInverval);
		ShaderNode colorNode = new ShaderNode();
		getRoot().addChild(colorNode);

		ITriangleMesh triangleMesh = new TriangleMesh();
		ObjIO objIO = new ObjIO();
		objIO.einlesen(cowPath, triangleMesh);

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

		List<Coordinates> coordinates = new ArrayList<Coordinates>();
		// build the cow
		TriangleMeshNode triangleMeshNode = new TriangleMeshNode(triangleMesh);
		double factor = 0.5;
		ScaleNode cowScale = new ScaleNode(new Vector3(factor, factor, factor));
		TranslationNode cowTrans = new TranslationNode(new Vector3(3, 0.25, 2));
		coordinates.add(new Coordinates(3, 0, 2));
		cowScale.addChild(cowTrans);
		cowTrans.addChild(triangleMeshNode);
		colorNode.addChild(cowScale);

		// plant the trees randomly
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
				if (new Random().nextInt(2) % 2 == 0) {
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
		TranslationNode tNode = new TranslationNode(new Vector3(
				-(squareWidth * 0.5), -2.0, 0.0));
		colorNode.addChild(scaleDragon);
		scaleDragon.addChild(dragonFlyNode);
		dragonFlyNode.addChild(tNode);
		tNode.addChild(dragon);
	}

	/*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		System.out.println("Tick");

		angle = angle + 1.0f;
		if (dragonFlyNode != null) {
			dragonFlyNode.setAngle(angle);
		}
	}

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		new Aufgabe2WithMixedConiferousForestAndDragon(100);
	}
}
