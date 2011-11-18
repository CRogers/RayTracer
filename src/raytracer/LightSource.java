package raytracer;

import java.awt.Color;
import processing.core.*;

public abstract class LightSource {

	public Color color;

	public LightSource(Color color) {
		this.color = color;
	}
	
	public abstract double intensityAt(PVectorD point);
}
