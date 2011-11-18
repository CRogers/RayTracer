package raytracer;

import java.awt.Color;
import java.util.List;

import processing.core.*;

public interface Shader {

	public double computeIntensity(List<LightSource> lightSources, PVectorD point, Item item); 
	
}
