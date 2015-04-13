/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabe2und4;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;

public class Aufgabe2 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Path to the .obj that stores a model of a cow.
	 */
	private String cowPath = "resources/cow.obj";

	/**
	 * Constructor.
	 */
	public Aufgabe2(int timerInverval) {
		super(timerInverval);
		ShaderNode colorNode = new ShaderNode();
		getRoot().addChild(colorNode);

		ITriangleMesh triangleMesh = new TriangleMesh();
		ObjIO objIO = new ObjIO();
		objIO.einlesen(cowPath, triangleMesh);

		// build the cow
		TriangleMeshNode triangleMeshNode = new TriangleMeshNode(triangleMesh);
		double factor = 6;
		ScaleNode cowScale = new ScaleNode(new Vector3(factor, factor, factor));
		colorNode.addChild(cowScale);
		cowScale.addChild(triangleMeshNode);

	}

	/*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		System.out.println("Tick");
	}

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		new Aufgabe2(1000);
	}
}
