/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 4
 */
package aufgabe2und4;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.scenegraph.ShaderNode;

public class Aufgabe4 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Path to the .obj that stores a model of a cube.
	 */
	private String cubePath = "meshes/cube.obj";

	/**
	 * Constructor.
	 */
	public Aufgabe4(int timerInverval) {
		super(timerInverval);
		ShaderNode colorNode = new ShaderNode();
		getRoot().addChild(colorNode);

		ITriangleMesh triangleMesh = new TriangleMesh();
		ObjIO objIO = new ObjIO();
		objIO.einlesen(cubePath, triangleMesh);

		// build the cube
		TriangleMeshNode triangleMeshNode = new TriangleMeshNode(triangleMesh);
		colorNode.addChild(triangleMeshNode);
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
		new Aufgabe4(1000);
	}
}
