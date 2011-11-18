package raytracer;

import java.awt.Color;
import java.util.*;

import processing.core.*;


public class RayTracer extends PApplet {
	
	public static PApplet pa;
	
	public RayTracer(){
		pa = this;
	}
	
	
	private List<Item> items = new ArrayList<Item>();
	
	public void setup() {
		size(600,600,JAVA2D);
		smooth();
		noLoop();
		
		items.add(new Sphere(new PVectorD(0.5,0,3), 0.7, Color.BLUE));
		items.add(new Sphere(new PVectorD(-0.5,0,3), 0.6, Color.RED));
		items.add(new Sphere(new PVectorD(0,0.5,2), 0.3, Color.GREEN));
	}

	public void draw() {
		background(0);
		castRays();
	}
	
	private void castRays(){
		
		double dh = (double)height;
		double dw = (double)width;
		
		double[] zbuffer = new double[width*height];
		Arrays.fill(zbuffer, Double.MAX_VALUE);
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				Line ray = new Line(PVectorD.zero(), new PVectorD((x-width/2)/dw, (y-height/2)/dh, 1));
				
				for(Item item : items){
					PVectorD[] ips = item.intersectionPoints(ray, true);
					
					if(ips.length > 0){
						PVectorD ip = ips[0];
						
						if(zbuffer[y*width+x] > ip.z){
							zbuffer[y*width+x] = ip.z;
							set(x,y,item.color.getRGB());
						}
					}
				}
				
			}
		}
		
	}
}
