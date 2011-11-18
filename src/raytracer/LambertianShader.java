package raytracer;

import java.util.List;

import processing.core.PVectorD;

public class LambertianShader implements Shader {

	@Override
	public double computeIntensity(List<LightSource> lightSources, PVectorD point, Item item) {
		
		PVectorD normal = item.normalAtPoint(point);
		
		double totalLight = 0;
		for(LightSource ls : lightSources){
			double ndotl = normal.dot(ls.getLightVector(point));
			// Make sure we only light up one side
			if(ndotl > 0)
				totalLight += ls.intensityAt(point) * ndotl;
		}
		
		return totalLight;
		
	}

}
