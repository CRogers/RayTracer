package raytracer;

import java.util.*;
import processing.core.*;

public abstract class Item {

	public abstract boolean isIntersectionPoint(PVector p);
	public abstract List<PVector> intersectionPoints(Line l);
	public abstract PVector normalAtPoint(PVector p);
	
}
