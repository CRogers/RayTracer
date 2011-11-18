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
	private List<LightSource> lightSources = new ArrayList<LightSource>();
	
	Shader shader = new LambertianShader();
	
	public void setup() {
		size(800,800,JAVA2D);
		smooth();
		noLoop();
		
		items.add(new Sphere(new PVectorD(0.7,0,3), 0.7, Color.BLUE));
		items.add(new Sphere(new PVectorD(-0.5,0,3), 0.5, Color.RED));
		items.add(new Sphere(new PVectorD(0,0.5,2), 0.3, Color.GREEN));
		
		lightSources.add(new PointLight(new PVectorD(1,1,0), Color.WHITE, 1));
	}

	public void draw() {
		background(0);
		castRays();
	}
	
	private void castRays(){
		
		double max = (double)Math.max(width, height);
		
		double[] zbuffer = new double[width*height];
		Arrays.fill(zbuffer, Double.MAX_VALUE);
		
		double maxIntensity = 0;
		Color[] outputColors = new Color[width*height];
		double[] intensities = new double[width*height];
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				Line ray = new Line(PVectorD.zero(), new PVectorD((x-width/2)/max, (y-height/2)/max, 1));
				
				for(Item item : items){
					PVectorD[] ips = item.intersectionPoints(ray, true);
					
					if(ips.length > 0){
						PVectorD ip = ips[0];
						int i = y*width+x;
						
						if(zbuffer[i] > ip.z){
							zbuffer[i] = ip.z;
							
							double intensity = shader.computeIntensity(lightSources, ip, item);
							maxIntensity = Math.max(maxIntensity, intensity);
							
							outputColors[i] = item.color;
							intensities[i] = intensity;
						}
					}
				}				
			}
		}
		
		loadPixels();
		for(int i = 0; i < width*height; i++){
			if(outputColors[i] != null){
				pixels[i] = scaleColor(outputColors[i], intensities[i]/maxIntensity).getRGB();
				//if(intensities[i]/maxIntensity > 1)
			}
		}
		updatePixels();
		
	}

	private Color scaleColor(Color color, double s) {
		return new Color((int)(color.getRed()*s), (int)(color.getGreen()*s), (int)(color.getBlue()*s));
	}
	
	
	public void keyPressed(){
		switch(key){
			case 'a': shader = new AmbientShader(); break;
			case 'h': shader = new HeadlightShader(); break;
			case 'l': shader = new LambertianShader(); break;
		}
		
		redraw();
	}
}
