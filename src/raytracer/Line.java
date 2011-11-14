package raytracer;

import processing.core.*;

public class Line {

	public final PVector start;
	public final PVector dir;
	
	public Line(PVector start, PVector dir) {
		this.start = start;
		this.dir = dir;
	}
	
	public PVector pointAt(float lambda){
		return PVector.add(start, PVector.mult(dir, lambda));
	}
}
