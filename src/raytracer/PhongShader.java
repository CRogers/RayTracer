package raytracer;

import java.util.List;

import processing.core.PVectorD;

public class PhongShader implements Shader {

	public double phongTerm;
	public int exponent;
	
	public PhongShader(double phongTerm, int exponent) {
		this.phongTerm = phongTerm;
		this.exponent = exponent;
	}

	@Override
	public double computeIntensity(List<LightSource> lightSources,	PVectorD point, Item item) {
		
		PVectorD normal = item.normalAtPoint(point);
		PVectorD v = new PVectorD(0,0,1);
		
		double totalLight = 0; 
		for(LightSource ls : lightSources){
			PVectorD l = ls.getLightVector(point);
			PVectorD r = PVectorD.sub(PVectorD.mult(normal, 2 * normal.dot(l)), l);
			double rdotv = r.dot(v);
			
			if(rdotv > 0)
				totalLight += phongTerm * Math.pow(rdotv, exponent);			
		}
		
		return totalLight;
	}

}
