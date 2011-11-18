package raytracer;

import java.awt.Color;

import processing.core.PVectorD;

public class PointLight extends LightSource {

	public double brightness;
	
	public PointLight(PVectorD position, Color color, double brightness) {
		super(position, color);
		this.brightness = brightness;
	}

	@Override
	public double intensityAt(PVectorD point) {
		double dist = PVectorD.dist(point, position);
		return brightness/dist*dist;
	}
	
		
	
}
