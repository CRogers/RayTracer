package raytracer;

import java.util.*;

import processing.core.PVectorD;

public class ShaderPipeline implements Shader {

	public final List<Shader> shaders = new ArrayList<Shader>();
	
	public ShaderPipeline(Shader... shaders){
		this.shaders.addAll(Arrays.asList(shaders));
	}
	
	@Override
	public double computeIntensity(List<LightSource> lightSources, PVectorD point, Item item) {
		
		double totalIntensity = 0;
		for(Shader shader : shaders){
			totalIntensity += shader.computeIntensity(lightSources, point, item);
		}
		
		return totalIntensity;
	}

}
