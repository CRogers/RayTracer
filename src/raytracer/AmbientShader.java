package raytracer;

import java.util.List;

import processing.core.PVectorD;

public class AmbientShader implements Shader {

	@Override
	public double computeIntensity(List<LightSource> lightSources, PVectorD point, Item item) {
		return 1;
	}

}
