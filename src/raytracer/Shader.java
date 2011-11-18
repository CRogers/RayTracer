package raytracer;

import java.awt.Color;
import processing.core.*;

public interface Shader {

	public Color compute(PVectorD point, Item item); 
	
}
