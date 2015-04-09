/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabe2;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;

/**
 * Application for the second exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class Aufgabe2 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Constructor.
	 */
	public Aufgabe2(int timerInverval) {
		super(timerInverval);
		ShaderNode colorNode = new ShaderNode();
		getRoot().addChild(colorNode);

		TriangleMeshNode triangleMeshNode = new TriangleMeshNode();
		double factor = 6.0;
		ScaleNode scale = new ScaleNode(new Vector3(factor, factor, factor));
		colorNode.addChild(scale);
		scale.addChild(triangleMeshNode);
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
