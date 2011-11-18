package processing.core;

/*
 Part of the Processing project - http://processing.org

 Copyright (c) 2008 Dan Shiffman
 Copyright (c) 2008-10 Ben Fry and Casey Reas

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License version 2.1 as published by the Free Software Foundation.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General
 Public License along with this library; if not, write to the
 Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 Boston, MA  02111-1307  USA
 */

import java.io.Serializable;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * A class to describe a two or three dimensional vector.
 * <p>
 * The result of all functions are applied to the vector itself, with the
 * exception of cross(), which returns a new PVector (or writes to a specified
 * 'target' PVector). That is, add() will add the contents of one vector to this
 * one. Using add() with additional parameters allows you to put the result into
 * a new PVector. Functions that act on multiple vectors also include static
 * versions. Because creating new objects can be computationally expensive, most
 * functions include an optional 'target' PVector, so that a new PVector object
 * is not created with each operation.
 * <p>
 * Initially based on the Vector3D class by <a
 * href="http://www.shiffman.net">Dan Shiffman</a>.
 */
public class PVectorD implements Serializable {

	/**
	 * Generated 2010-09-14 by jdf
	 */
	private static final long serialVersionUID = -6717872085945400694L;

	/** The x component of the vector. */
	public double x;

	/** The y component of the vector. */
	public double y;

	/** The z component of the vector. */
	public double z;

	/**
	 * Constructor for an empty vector: x, y, and z are set to 0.
	 */
	public PVectorD() {
	}
	
	/**
	 * Produces a new zero vector
	 * @return Zero vector
	 */
	public static PVectorD zero(){
		return new PVectorD(0,0,0);
	}

	/**
	 * Constructor for a 3D vector.
	 * 
	 * @param x
	 *            the x coordinate.
	 * @param y
	 *            the y coordinate.
	 * @param z
	 *            the y coordinate.
	 */
	public PVectorD(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor for a 2D vector: z coordinate is set to 0.
	 * 
	 * @param x
	 *            the x coordinate.
	 * @param y
	 *            the y coordinate.
	 */
	public PVectorD(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	/**
	 * Set x, y, and z coordinates.
	 * 
	 * @param x
	 *            the x coordinate.
	 * @param y
	 *            the y coordinate.
	 * @param z
	 *            the z coordinate.
	 */
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Set x, y, and z coordinates from a Vector3D object.
	 * 
	 * @param v
	 *            the PVector object to be copied
	 */
	public void set(PVectorD v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	/**
	 * Set the x, y (and maybe z) coordinates using a double[] array as the
	 * source.
	 * 
	 * @param source
	 *            array to copy from
	 */
	public void set(double[] source) {
		if (source.length >= 2) {
			x = source[0];
			y = source[1];
		}
		if (source.length >= 3) {
			z = source[2];
		}
	}

	/**
	 * Get a copy of this vector.
	 */
	public PVectorD get() {
		return new PVectorD(x, y, z);
	}

	public double[] get(double[] target) {
		if (target == null) {
			return new double[] { x, y, z };
		}
		if (target.length >= 2) {
			target[0] = x;
			target[1] = y;
		}
		if (target.length >= 3) {
			target[2] = z;
		}
		return target;
	}

	/**
	 * Calculate the magnitude (length) of the vector
	 * 
	 * @return the magnitude of the vector
	 */
	public double mag() {
		return (double) Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Calculate the squared magnitude of the vector Faster if the real length
	 * is not required in the case of comparing vectors, etc.
	 * 
	 * @return squared magnitude of the vector
	 */
	public double magSq() {
		return (x * x + y * y + z * z);
	}

	/**
	 * Add a vector to this vector
	 * 
	 * @param v
	 *            the vector to be added
	 */
	public void add(PVectorD v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}

	public void add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	/**
	 * Add two vectors
	 * 
	 * @param v1
	 *            a vector
	 * @param v2
	 *            another vector
	 * @return a new vector that is the sum of v1 and v2
	 */
	static public PVectorD add(PVectorD v1, PVectorD v2) {
		return add(v1, v2, null);
	}

	/**
	 * Add two vectors into a target vector
	 * 
	 * @param v1
	 *            a vector
	 * @param v2
	 *            another vector
	 * @param target
	 *            the target vector (if null, a new vector will be created)
	 * @return a new vector that is the sum of v1 and v2
	 */
	static public PVectorD add(PVectorD v1, PVectorD v2, PVectorD target) {
		if (target == null) {
			target = new PVectorD(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
		} else {
			target.set(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
		}
		return target;
	}

	/**
	 * Subtract a vector from this vector
	 * 
	 * @param v
	 *            the vector to be subtracted
	 */
	public void sub(PVectorD v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}

	public void sub(double x, double y, double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
	}

	/**
	 * Subtract one vector from another
	 * 
	 * @param v1
	 *            a vector
	 * @param v2
	 *            another vector
	 * @return a new vector that is v1 - v2
	 */
	static public PVectorD sub(PVectorD v1, PVectorD v2) {
		return sub(v1, v2, null);
	}

	static public PVectorD sub(PVectorD v1, PVectorD v2, PVectorD target) {
		if (target == null) {
			target = new PVectorD(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
		} else {
			target.set(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
		}
		return target;
	}

	/**
	 * Multiply this vector by a scalar
	 * 
	 * @param n
	 *            the value to multiply by
	 */
	public void mult(double n) {
		x *= n;
		y *= n;
		z *= n;
	}

	/**
	 * Multiply a vector by a scalar
	 * 
	 * @param v
	 *            a vector
	 * @param n
	 *            scalar
	 * @return a new vector that is v1 * n
	 */
	static public PVectorD mult(PVectorD v, double n) {
		return mult(v, n, null);
	}

	/**
	 * Multiply a vector by a scalar, and write the result into a target
	 * PVector.
	 * 
	 * @param v
	 *            a vector
	 * @param n
	 *            scalar
	 * @param target
	 *            PVector to store the result
	 * @return the target vector, now set to v1 * n
	 */
	static public PVectorD mult(PVectorD v, double n, PVectorD target) {
		if (target == null) {
			target = new PVectorD(v.x * n, v.y * n, v.z * n);
		} else {
			target.set(v.x * n, v.y * n, v.z * n);
		}
		return target;
	}

	/**
	 * Multiply each element of one vector by the elements of another vector.
	 * 
	 * @param v
	 *            the vector to multiply by
	 */
	public void mult(PVectorD v) {
		x *= v.x;
		y *= v.y;
		z *= v.z;
	}

	/**
	 * Multiply each element of one vector by the individual elements of another
	 * vector, and return the result as a new PVector.
	 */
	static public PVectorD mult(PVectorD v1, PVectorD v2) {
		return mult(v1, v2, null);
	}

	/**
	 * Multiply each element of one vector by the individual elements of another
	 * vector, and write the result into a target vector.
	 * 
	 * @param v1
	 *            the first vector
	 * @param v2
	 *            the second vector
	 * @param target
	 *            PVector to store the result
	 */
	static public PVectorD mult(PVectorD v1, PVectorD v2, PVectorD target) {
		if (target == null) {
			target = new PVectorD(v1.x * v2.x, v1.y * v2.y, v1.z * v2.z);
		} else {
			target.set(v1.x * v2.x, v1.y * v2.y, v1.z * v2.z);
		}
		return target;
	}

	/**
	 * Divide this vector by a scalar
	 * 
	 * @param n
	 *            the value to divide by
	 */
	public void div(double n) {
		x /= n;
		y /= n;
		z /= n;
	}

	/**
	 * Divide a vector by a scalar and return the result in a new vector.
	 * 
	 * @param v
	 *            a vector
	 * @param n
	 *            scalar
	 * @return a new vector that is v1 / n
	 */
	static public PVectorD div(PVectorD v, double n) {
		return div(v, n, null);
	}

	static public PVectorD div(PVectorD v, double n, PVectorD target) {
		if (target == null) {
			target = new PVectorD(v.x / n, v.y / n, v.z / n);
		} else {
			target.set(v.x / n, v.y / n, v.z / n);
		}
		return target;
	}

	/**
	 * Divide each element of one vector by the elements of another vector.
	 */
	public void div(PVectorD v) {
		x /= v.x;
		y /= v.y;
		z /= v.z;
	}

	/**
	 * Multiply each element of one vector by the individual elements of another
	 * vector, and return the result as a new PVector.
	 */
	static public PVectorD div(PVectorD v1, PVectorD v2) {
		return div(v1, v2, null);
	}

	/**
	 * Divide each element of one vector by the individual elements of another
	 * vector, and write the result into a target vector.
	 * 
	 * @param v1
	 *            the first vector
	 * @param v2
	 *            the second vector
	 * @param target
	 *            PVector to store the result
	 */
	static public PVectorD div(PVectorD v1, PVectorD v2, PVectorD target) {
		if (target == null) {
			target = new PVectorD(v1.x / v2.x, v1.y / v2.y, v1.z / v2.z);
		} else {
			target.set(v1.x / v2.x, v1.y / v2.y, v1.z / v2.z);
		}
		return target;
	}

	/**
	 * Calculate the Euclidean distance between two points (considering a point
	 * as a vector object)
	 * 
	 * @param v
	 *            another vector
	 * @return the Euclidean distance between
	 */
	public double dist(PVectorD v) {
		double dx = x - v.x;
		double dy = y - v.y;
		double dz = z - v.z;
		return (double) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	/**
	 * Calculate the Euclidean distance between two points (considering a point
	 * as a vector object)
	 * 
	 * @param v1
	 *            a vector
	 * @param v2
	 *            another vector
	 * @return the Euclidean distance between v1 and v2
	 */
	static public double dist(PVectorD v1, PVectorD v2) {
		double dx = v1.x - v2.x;
		double dy = v1.y - v2.y;
		double dz = v1.z - v2.z;
		return (double) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	/**
	 * Calculate the dot product with another vector
	 * 
	 * @return the dot product
	 */
	public double dot(PVectorD v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public double dot(double x, double y, double z) {
		return this.x * x + this.y * y + this.z * z;
	}

	static public double dot(PVectorD v1, PVectorD v2) {
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
	}

	/**
	 * Return a vector composed of the cross product between this and another.
	 */
	public PVectorD cross(PVectorD v) {
		return cross(v, null);
	}

	/**
	 * Perform cross product between this and another vector, and store the
	 * result in 'target'. If target is null, a new vector is created.
	 */
	public PVectorD cross(PVectorD v, PVectorD target) {
		double crossX = y * v.z - v.y * z;
		double crossY = z * v.x - v.z * x;
		double crossZ = x * v.y - v.x * y;

		if (target == null) {
			target = new PVectorD(crossX, crossY, crossZ);
		} else {
			target.set(crossX, crossY, crossZ);
		}
		return target;
	}

	static public PVectorD cross(PVectorD v1, PVectorD v2, PVectorD target) {
		double crossX = v1.y * v2.z - v2.y * v1.z;
		double crossY = v1.z * v2.x - v2.z * v1.x;
		double crossZ = v1.x * v2.y - v2.x * v1.y;

		if (target == null) {
			target = new PVectorD(crossX, crossY, crossZ);
		} else {
			target.set(crossX, crossY, crossZ);
		}
		return target;
	}

	/**
	 * Normalize the vector to length 1 (make it a unit vector)
	 */
	public void normalize() {
		double m = mag();
		if (m != 0 && m != 1) {
			div(m);
		}
	}

	/**
	 * Normalize this vector, storing the result in another vector.
	 * 
	 * @param target
	 *            Set to null to create a new vector
	 * @return a new vector (if target was null), or target
	 */
	public PVectorD normalize(PVectorD target) {
		if (target == null) {
			target = new PVectorD();
		}
		double m = mag();
		if (m > 0) {
			target.set(x / m, y / m, z / m);
		} else {
			target.set(x, y, z);
		}
		return target;
	}

	/**
	 * Limit the magnitude of this vector
	 * 
	 * @param max
	 *            the maximum length to limit this vector
	 */
	public void limit(double max) {
		if (mag() > max) {
			normalize();
			mult(max);
		}
	}

	/**
	 * Sets the magnitude of the vector to an arbitrary amount.
	 * 
	 * @param len
	 *            the new length for this vector
	 */
	public void setMag(double len) {
		normalize();
		mult(len);
	}

	/**
	 * Sets the magnitude of this vector, storing the result in another vector.
	 * 
	 * @param target
	 *            Set to null to create a new vector
	 * @param len
	 *            the new length for the new vector
	 * @return a new vector (if target was null), or target
	 */
	public PVectorD setMag(PVectorD target, double len) {
		target = normalize(target);
		target.mult(len);
		return target;
	}

	/**
	 * Calculate the angle of rotation for this vector (only 2D vectors)
	 * 
	 * @return the angle of rotation
	 */
	public double heading2D() {
		double angle = (double) Math.atan2(-y, x);
		return -1 * angle;
	}

	/**
	 * Rotate the vector by an angle (only 2D vectors), magnitude remains the
	 * same
	 * 
	 * @param theta
	 *            the angle of rotation
	 */
	public void rotate(double theta) {
		double xTemp = x;
		// Might need to check for rounding errors like with angleBetween
		// function?
		x = x * Math.cos(theta) - y * Math.sin(theta);
		y = xTemp * Math.sin(theta) + y * Math.cos(theta);
	}

	/**
	 * Linear interpolate the vector to another vector
	 * 
	 * @param PVector
	 *            the vector to lerp to
	 * @param amt
	 *            The amt parameter is the amount to interpolate between the two
	 *            vectors where 1.0 equal to the new vector 0.1 is very near the
	 *            new vector, 0.5 is half-way in between.
	 */
	public void lerp(PVectorD v, double amt) {
		x = PApplet.lerp((float) x, (float) v.x, (float) amt);
		y = PApplet.lerp((float) y, (float) v.y, (float) amt);
	}

	public void lerp(double x, double y, double z, double amt) {
		this.x = PApplet.lerp((float) this.x, (float) x, (float) amt);
		this.y = PApplet.lerp((float) this.y, (float) y, (float) amt);
	}

	/**
	 * Calculate the angle between two vectors, using the dot product
	 * 
	 * @param v1
	 *            a vector
	 * @param v2
	 *            another vector
	 * @return the angle between the vectors
	 */
	static public double angleBetween(PVectorD v1, PVectorD v2) {
		double dot = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
		double v1mag = Math.sqrt(v1.x * v1.x + v1.y * v1.y + v1.z * v1.z);
		double v2mag = Math.sqrt(v2.x * v2.x + v2.y * v2.y + v2.z * v2.z);
		// This should be a number between -1 and 1, since it's "normalized"
		double amt = dot / (v1mag * v2mag);
		// But if it's not due to rounding error, then we need to fix it
		// http://code.google.com/p/processing/issues/detail?id=340
		// Otherwise if outside the range, acos() will return NaN
		// http://www.cppreference.com/wiki/c/math/acos
		if (amt <= -1) {
			return PConstants.PI;
		} else if (amt >= 1) {
			// http://code.google.com/p/processing/issues/detail?id=435
			return 0;
		}
		return (double) Math.acos(amt);
	}

	public String toString() {
		return "[ " + x + ", " + y + ", " + z + " ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PVectorD))
			return false;
		final PVectorD p = (PVectorD) obj;
		return x == p.x && y == p.y && z == p.z;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + (int) Double.doubleToLongBits(x);
		result = 31 * result + (int) Double.doubleToLongBits(y);
		result = 31 * result + (int) Double.doubleToLongBits(z);
		return result;
	}
}