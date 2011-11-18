package raytracer;

import java.awt.Color;
import processing.core.*;

public abstract class LightSource {

	public Color color;
	public PVectorD position;

	public LightSource(PVectorD position, Color color) {
		this.color = color;
		this.position = position;
	}
	
	public PVectorD getLightVector(PVectorD point){
		PVectorD ret = PVectorD.sub(point, position);
		ret.normalize();
		return ret;
	}
	
	public abstract double intensityAt(PVectorD point);
}
