/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 5
 */
package aufgabe5;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.scenegraph.ShaderNode;

public class Aufgabe5 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Constructor.
	 */
	public Aufgabe5(int timerInverval) {
		super(timerInverval);
		ShaderNode colorNode = new ShaderNode();
		getRoot().addChild(colorNode);

		colorNode.addChild(new CurveNode(new BezierCurve()));
		colorNode.addChild(new CurveNode(new MonomCurve()));
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
		new Aufgabe5(1000);
	}
}
