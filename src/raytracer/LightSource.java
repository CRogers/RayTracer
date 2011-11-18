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
		return PVectorD.sub(point, position);
	}
	
	public abstract double intensityAt(PVectorD point);
}
