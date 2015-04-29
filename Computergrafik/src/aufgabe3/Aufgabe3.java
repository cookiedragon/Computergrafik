/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3
 */
package aufgabe3;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ShaderNode;

public class Aufgabe3 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Constructor.
	 */
	public Aufgabe3(int timerInverval) {
		super(timerInverval);
		ShaderNode colorNode = new ShaderNode();
		getRoot().addChild(colorNode);

		// get a sphere
		Vector3 center = new Vector3(0.0, 0.0, 0.0);
		double radius = 1.0;
		ImplicitSphere sphere = new ImplicitSphere(radius, center);
		
		// get a torus
		double innerRadius = 0.5;
		double outerRadius = 1.0;
		ImplicitTorus torus = new ImplicitTorus(innerRadius, outerRadius, center);
		
		ImplicitNode implicitNode = new ImplicitNode(sphere);
		colorNode.addChild(implicitNode);
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
		new Aufgabe3(1000);
	}
}
