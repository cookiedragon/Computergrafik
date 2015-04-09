package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;


public class ShaderNode extends Node {

	/**
	 * Shader representation.
	 */
	private CgGlslShader shader;

	/**
	 * Constructor
	 */
	public ShaderNode() {

		// Use a Phong shader
		shader = new CgGlslShader("shader/vertex_shader_phong_shading.glsl",
				"shader/fragment_shader_phong_shading.glsl");
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
