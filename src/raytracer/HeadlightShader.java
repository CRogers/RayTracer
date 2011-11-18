package raytracer;

import java.awt.Color;
import java.util.List;

import processing.core.PVectorD;

public class HeadlightShader implements Shader {

	@Override
	public double computeIntensity(List<LightSource> lightSources, PVectorD point, Item item) {
		
		PVectorD normal = item.normalAtPoint(point);
		return Math.tanh(Math.abs(normal.z));
		
	}

}
