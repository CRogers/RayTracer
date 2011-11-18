package raytracer.tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.*;

import raytracer.*;
import processing.core.*;

import org.junit.Test;

public class Intersections {

	
	private void testIntersects(Line l, Sphere s, PVectorD... expected){
		PVectorD[] intersects = s.intersectionPoints(l);
		
		assertTrue(intersects.length == expected.length);
		
		for(PVectorD pv : intersects)
			assertTrue(containsFloating(expected, pv));
	}
	
	private boolean containsFloating(PVectorD[] pvs, PVectorD t){
		for(PVectorD p : pvs){
			if(Math.abs(p.x - t.x) < 1e-4 && Math.abs(p.y - t.y) < 1e-4 && Math.abs(p.z - t.z) < 1e-4)
				return true;
		}
		
		return false;
	}
	
	@Test
	public void sphere() {
		
		Sphere unitSphere = new Sphere(new PVectorD(0,0,0), 1, Color.WHITE);
		
		testIntersects(new Line(new PVectorD(-2,0,0), new PVectorD(1,0,0)), 
					   unitSphere, 
					   new PVectorD(-1,0,0), new PVectorD(1,0,0));
		
		testIntersects(new Line(new PVectorD(2,2,2), new PVectorD(1,1,1)), 
				       unitSphere, 
				       new PVectorD(0.57735,0.57735,0.57735), new PVectorD(-0.57735,-0.57735,-0.57735));
		
		testIntersects(new Line(new PVectorD(1,0,-1), new PVectorD(0, 0, 1)),
					   unitSphere,
					   new PVectorD(1,0,0));
		
		testIntersects(new Line(new PVectorD(1000,0,0), new PVectorD(0,0,1)),
					   unitSphere);
				
	}

}
