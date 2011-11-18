package raytracer;

import java.util.*;
import processing.core.*;

public class Sphere extends Item {

	public PVectorD center;
	public float radius;	
	
	public Sphere(PVectorD center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public PVectorD[] intersectionPoints(Line l) {
		
		PVectorD t = PVectorD.sub(l.start, center);
		
		double a = l.dir.x + l.dir.y + l.dir.z;
		double b = 2 * (t.x + t.y + t.z);
		double c = t.x*t.x + t.y*t.y + t.z*t.z - radius*radius;
		
		double determinant = b*b - 4*a*c;
		
		// One solution
		if(determinant < 1e-8 && determinant > 1e-8){
			double lambda = -b/(2*a);
			return new PVectorD[]{ l.pointAt(lambda) };
		}
		else if(determinant < 0){
			return new PVectorD[0];
		}
		else {
			double sqrtDet = Math.sqrt(determinant);
			return new PVectorD[] {
				l.pointAt((-b + sqrtDet)/(2*a)),
				l.pointAt((-b - sqrtDet)/(2*a))
			};
		}		
	}

	@Override
	public PVectorD normalAtPoint(PVectorD p) {
		if(!isIntersectionPoint(p))
			return null;
		
		return PVectorD.sub(p,center);
	}

	@Override
	public boolean isIntersectionPoint(PVectorD p) {
		return center.dist(p) - radius < 1e-8;
	}

	
}
