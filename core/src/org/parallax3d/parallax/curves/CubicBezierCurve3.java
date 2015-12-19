/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file is part of Parallax project.
 * 
 * Parallax is free software: you can redistribute it and/or modify it 
 * under the terms of the Creative Commons Attribution 3.0 Unported License.
 * 
 * Parallax is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the Creative Commons Attribution 
 * 3.0 Unported License. for more details.
 * 
 * You should have received a copy of the the Creative Commons Attribution 
 * 3.0 Unported License along with Parallax. 
 * If not, see http://creativecommons.org/licenses/by/3.0/.
 */

package org.parallax3d.parallax.curves;

import org.parallax3d.parallax.ThreeJsObject;
import org.parallax3d.parallax.math.Vector3;
import org.parallax3d.parallax.utils.ShapeUtils;

@ThreeJsObject("THREE.CubicBezierCurve3")
public class CubicBezierCurve3 extends Curve
{

	private Vector3 v0;
	private Vector3 v1;
	private Vector3 v2;
	private Vector3 v3;

	public CubicBezierCurve3(Vector3 v0, Vector3 v1, Vector3 v2, Vector3 v3) 
	{
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	@Override
	public Vector3 getPoint(double t)
	{
		double tx = ShapeUtils.b3(t, this.v0.getX(), this.v1.getX(), this.v2.getX(), this.v3.getX());
		double ty = ShapeUtils.b3(t, this.v0.getY(), this.v1.getY(), this.v2.getY(), this.v3.getY());
		double tz = ShapeUtils.b3(t, this.v0.getZ(), this.v1.getZ(), this.v2.getZ(), this.v3.getZ());

		return new Vector3(tx, ty, tz);
	}

}
