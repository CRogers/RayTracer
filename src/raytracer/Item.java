package raytracer;

import java.awt.Color;
import java.util.*;
import processing.core.*;

public abstract class Item {
	
	protected Color color;
	
	public Item(Color color){
		this.color = color;
	}

	public abstract boolean isIntersectionPoint(PVectorD p);
	public abstract PVectorD[] intersectionPoints(Line l);
	public abstract PVectorD normalAtPoint(PVectorD p);
	
}
