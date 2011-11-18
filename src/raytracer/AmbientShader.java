package raytracer;

import java.util.List;

import processing.core.PVectorD;

public class AmbientShader implements Shader {

	double intensity;
	
	public AmbientShader(double intensity){
		this.intensity = intensity;
	}
	
	@Override
	public double computeIntensity(List<LightSource> lightSources, PVectorD point, Item item) {
		return intensity;
	}

}
