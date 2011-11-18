package raytracer;

import java.util.*;
import processing.core.*;

public abstract class Item {

	public abstract boolean isIntersectionPoint(PVectorD p);
	public abstract PVectorD[] intersectionPoints(Line l);
	public abstract PVectorD normalAtPoint(PVectorD p);
	
}
