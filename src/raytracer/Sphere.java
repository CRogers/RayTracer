package raytracer;

import java.awt.Color;
import java.util.*;
import processing.core.*;

public class Sphere extends Item {

	public PVectorD center;
	public double radius;	
	
	public Sphere(PVectorD center, double radius, Color color) {
		super(color);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public PVectorD[] intersectionPoints(Line l, boolean halfLine) {
		
		PVectorD t = PVectorD.sub(l.start, center);
		
		double a = PVectorD.dot(l.dir, l.dir);
		double b = 2 * PVectorD.dot(t,  l.dir);
		double c = PVectorD.dot(t, t) - radius*radius;
		
		double determinant = b*b - 4*a*c;
		
		// One solution
		if(determinant < 1e-8 && determinant > -1e-8){
			double lambda = -b/(2*a);
			return (halfLine && lambda < 0) ? new PVectorD[0] : new PVectorD[]{ l.pointAt(lambda) };
		}
		else if(determinant < 0){
			return new PVectorD[0];
		}
		else {
			double sqrtDet = Math.sqrt(determinant);
			double l1 = (-b + sqrtDet)/(2*a);
			double l2 = (-b - sqrtDet)/(2*a);
			List<PVectorD> list = new ArrayList<PVectorD>(2);
			
			if(!halfLine || l1 > 0)
				list.add(l.pointAt(l1));
			
			if(!halfLine || l2 > 0)
				list.add(l.pointAt(l2));
			
			return list.toArray(new PVectorD[0]);
		}		
	}

	@Override
	public PVectorD normalAtPoint(PVectorD p) {
		if(!isIntersectionPoint(p))
			return null;
		
		PVectorD ret = PVectorD.sub(p,center);
		ret.normalize();
		return ret;
	}

	@Override
	public boolean isIntersectionPoint(PVectorD p) {
		return center.dist(p) - radius < 1e-8;
	}

	
}
