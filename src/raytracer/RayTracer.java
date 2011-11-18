package raytracer;

import java.util.*;

import processing.core.*;


public class RayTracer extends PApplet {
	
	private List<Item> items = new ArrayList<Item>();
	
	public void setup() {
		size(600,600,JAVA2D);
		smooth();
		noLoop();
		
		items.add(new Sphere(new PVectorD(0,0,3), 1));
	}

	public void draw() {
		background(0);
		castRays();
	}
	
	private void castRays(){
		
		double dh = (double)height;
		double dw = (double)width;
		
		for(int x = -width/2; x < width/2; x++){
			for(int y = -height/2; y < height/2; y++){
				Line ray = new Line(PVectorD.zero(), new PVectorD(x/dw, y/dh, 1));
				
				for(Item item : items){
					PVectorD[] ips = item.intersectionPoints(ray);
					
					if(ips.length > 0){
						set(width/2+x,height/2+y,color(255));
					}
				}
				
			}
		}
		
	}
}
