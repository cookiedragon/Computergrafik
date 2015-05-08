/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */
package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

/**
 * This node provides a Shader which can be used to nicely render content (child
 * nodes).
 * 
 * @author Philipp Jenke
 */
public class ShaderNode extends Node {

	/**
	 * Shader representation.
	 */
	private CgGlslShader shader;

	/**
	 * These shader types are currently supported. PHONG = simple lighting model
	 * (default), TEXTURE = Use a texture.
	 */
	public enum ShaderType {
		PHONG, TEXTURE
	}

	/**
	 * Constructor
	 */
	public ShaderNode() {
		this(false);
	}

	/**
	 * Constructor, which allows to set the shader type.
	 */
	public ShaderNode(boolean texture) {

		if (texture) {
			// Use a texture shader
			shader = new CgGlslShader(
					"shader/vertex_shader_texture.glsl",
					"shader/fragment_shader_texture.glsl");
		} else {
			shader = new CgGlslShader(
					"shader/vertex_shader_phong_shading.glsl",
					"shader/fragment_shader_phong_shading.glsl");
		}
	}

	@Override
	public void drawGl(GL2 gl) {
		// Setup the shader
		JoglShader.use(shader, gl);

		for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}

	}

}
