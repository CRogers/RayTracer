package raytracer;

import java.util.*;
import processing.core.*;

public class Sphere extends Item {

	public PVector center;
	public float radius;	
	
	public Sphere(PVector center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public List<PVector> intersectionPoints(Line l) {
		
		List<PVector> points = new ArrayList<PVector>();
		
		PVector t = PVector.sub(l.start, center);
		
		float a = l.dir.x + l.dir.y + l.dir.z;
		float b = 2 * (t.x + t.y + t.z);
		float c = t.x*t.x + t.y*t.y + t.z*t.z - radius*radius;
		
		float determinant = b*b - 4*a*c;
		float detAbs = Math.abs(determinant);
		
		// One solutions
		if(detAbs - 1 < 1e-8){
			float lambda = -b/(2*a);
			points.add(l.pointAt(lambda));
		}
		else {
			float sqrtDet = PApplet.sqrt(determinant);
			points.add(l.pointAt((-b + sqrtDet)/(2*a)));
			points.add(l.pointAt((-b - sqrtDet)/(2*a)));
		}
		
		return points;
		
	}

	@Override
	public PVector normalAtPoint(PVector p) {
		if(!isIntersectionPoint(p))
			return null;
		
		return PVector.sub(p,center);
	}

	@Override
	public boolean isIntersectionPoint(PVector p) {
		return center.dist(p) - radius < 1e-8;
	}

	
}
