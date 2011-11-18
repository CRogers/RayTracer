package raytracer;

import processing.core.*;

public class Line {

	public final PVectorD start;
	public final PVectorD dir;
	
	public Line(PVectorD start, PVectorD dir) {
		this.start = start;
		this.dir = dir;
	}
	
	public PVectorD pointAt(double lambda){
		return PVectorD.add(start, PVectorD.mult(dir, lambda));
	}
}
