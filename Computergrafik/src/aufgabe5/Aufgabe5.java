/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 5
 */
package aufgabe5;

import java.util.ArrayList;
import java.util.List;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
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

		List<Vector3> pes1 = new ArrayList<Vector3>();
		pes1.add(new Vector3(0, 0, 0));
		pes1.add(new Vector3(-0.5, 0.5, -0.9));
		pes1.add(new Vector3(0.7, -0.2, 0.2));

		List<Vector3> pes2 = new ArrayList<Vector3>();
		pes2.add(new Vector3(0.0, 0.0, 0.0));
		pes2.add(new Vector3(1.0, 1.0, 0.0));
		pes2.add(new Vector3(0.0, 3.0, 4.0));

		List<Vector3> ces = new ArrayList<Vector3>();
		ces.add(new Vector3(0.0, 0.0, 0.0));
		ces.add(new Vector3(1.0, 1.0, 0.0));
		ces.add(new Vector3(0.0, 3.0, 4.0));
		// bez.add(new Vector3(1.0, 2.0, 3.0));

		// colorNode.addChild(new CurveNode(new BezierCurve(ces)));
		colorNode.addChild(new CurveNode(new MonomCurve(pes2, 0.5)));
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
