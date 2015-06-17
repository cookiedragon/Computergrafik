/**
 * Praktikum AI WP Computergrafik, SS 2015
 * Gruppe:	Corinna Klaukin (corinna.klaukin@haw-hamburg.de)
 * 			Anna Steinhauer (annachristin.steinhauer@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 7
 */
package aufgabe6und7;

import java.awt.Image;

import computergraphics.framework.Camera;
import computergraphics.math.Vector3;
import computergraphics.projects.ImageViewer;
import computergraphics.projects.Raytracer;
import computergraphics.scenegraph.GroupNode;
import computergraphics.scenegraph.SphereNode;

public class Aufgabe7 {

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		GroupNode rootNode = new GroupNode();

		// red
		SphereNode sphere1 = new SphereNode(0.5, 20, new Vector3(0, 0.5, -2),
				new Vector3(1, 0, 0));
		// blue
		SphereNode sphere2 = new SphereNode(0.5, 20, new Vector3(2, -1, -2),
				new Vector3(0, 0, 1));
		// green
		SphereNode sphere3 = new SphereNode(0.5, 20, new Vector3(-2, -0.5, -2),
				new Vector3(0, 1, 0));
		Vector3 normal = new Vector3(0, 1, -0.1);
		PlaneNode plane = new PlaneNode(new Vector3(0, -2, 0),
				normal.getNormalized(), new Vector3(1, 0, 1));

		rootNode.addChild(sphere1);
		rootNode.addChild(sphere2);
		rootNode.addChild(plane);
		rootNode.addChild(sphere3);

		Raytracer raytracer = new Raytracer(new Camera(), rootNode);
		Image image = raytracer.render(640, 480);
		new ImageViewer(image);
	}
}