/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3
 */
package aufgabe3;

import aufgabe1.TranslationNode;
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
		ImplicitTorus torus = new ImplicitTorus(innerRadius, outerRadius,
				center);

		// get a superquadric
		ImplicitSuperquadric superquadric = new ImplicitSuperquadric(center);
		
		// get a heart
		ImplicitHeart heart = new ImplicitHeart(center);
		
		// get a genus
		ImplicitGenus genus = new ImplicitGenus(center);

		TranslationNode tNode1 = new TranslationNode(
				new Vector3(-3.0, 0.0, 0.0));
		TranslationNode tNode3 = new TranslationNode(new Vector3(3.0, 0.0, 0.0));

		ImplicitNode implicitNode1 = new ImplicitNode(torus);
		ImplicitNode implicitNode2 = new ImplicitNode(superquadric);
		ImplicitNode implicitNode3 = new ImplicitNode(genus);

		tNode1.addChild(implicitNode1);
		colorNode.addChild(tNode1);
		colorNode.addChild(implicitNode2);
		tNode3.addChild(implicitNode3);
		colorNode.addChild(tNode3);
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
