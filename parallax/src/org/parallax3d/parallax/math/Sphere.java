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

package org.parallax3d.parallax.math;

import org.parallax3d.parallax.system.ThreejsObject;

import java.util.List;

@ThreejsObject("THREE.Sphere")
public class Sphere 
{
	Vector3 center;
	double radius;

	// Temporary variables
	static Box3 _box = new Box3();

	public Sphere()
	{
		this(new Vector3(), 0);
	}

	public Sphere(double radius)
	{
		this(new Vector3(), radius);
	}

	public Sphere(Vector3 center, double radius)
	{
		this.center = center;
		this.radius = radius;
	}

	public Vector3 getCenter() {
		return center;
	}

	public void setCenter(Vector3 center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Sphere set( Vector3 center, double radius )
	{
		this.center.copy( center );
		this.radius = radius;

		return this;
	}

	public Sphere setFromPoints(List<Vector3> points, Vector3 optionalCenter)
	{
		return setFromPoints(points.toArray(new Vector3[points.size()]), optionalCenter);
	}

	public Sphere setFromPoints(Vector3[] points, Vector3 optionalCenter)
	{
		if ( optionalCenter != null )
		{
			center.copy( optionalCenter );
		}
		else
		{
			_box.setFromPoints( points ).center( center );
		}

		double maxRadiusSq = 0;

		for ( int i = 0, il = points.length; i < il; i ++ )
		{
			maxRadiusSq = Math.max( maxRadiusSq, center.distanceToSquared( points[ i ] ) );
		}

		this.radius = Math.sqrt( maxRadiusSq );

		return this;

	}

	public Sphere copy( Sphere sphere )
	{
		this.center.copy( sphere.center );
		this.radius = sphere.radius;

		return this;
	}

	public boolean isEmpty()
	{
		return ( this.radius <= 0 );
	}

	public boolean containsPoint(Vector3 point )
	{
		return ( point.distanceToSquared( this.center ) <= ( this.radius * this.radius ) );
	}

	public double distanceToPoint( Vector3 point )
	{
		return ( point.distanceTo( this.center ) - this.radius );

	}

	public boolean intersectsSphere(Sphere sphere )
	{
		double radiusSum = this.radius + sphere.radius;

		return sphere.center.distanceToSquared( this.center ) <= ( radiusSum * radiusSum );
	}

	public boolean intersectsBox( Box3 box ) {

		return box.intersectsSphere( this );

	}


	// We use the following equation to compute the signed distance from
	// the center of the sphere to the plane.
	//
	// distance = q * n - d
	//
	// If this distance is greater than the radius of the sphere,
	// then there is no intersection.
	public boolean intersectsPlane( Plane plane ) {

		return Math.abs( this.center.dot( plane.getNormal() ) - plane.getConstant() ) <= this.radius;

	}

	public Vector3 clampPoint( Vector3 point)
	{
		return clampPoint(point, new Vector3());
	}

	public Vector3 clampPoint( Vector3 point, Vector3 optionalTarget )
	{
		double deltaLengthSq = this.center.distanceToSquared( point );

		optionalTarget.copy( point );

		if ( deltaLengthSq > ( this.radius * this.radius ) )
		{
			optionalTarget.sub( this.center ).normalize();
			optionalTarget.multiply( this.radius ).add( this.center );
		}

		return optionalTarget;
	}

	public Box3 getBoundingBox()
	{
		return getBoundingBox(new Box3());
	}

	public Box3 getBoundingBox( Box3 optionalTarget )
	{
		optionalTarget.set( this.center, this.center );
		optionalTarget.expandByScalar( this.radius );

		return optionalTarget;
	}

	@Deprecated
	public Sphere applyMatrix4( Matrix4 matrix )
	{
		return apply( matrix );
	}

	public Sphere apply( Matrix4 matrix )
	{
		this.center.apply( matrix );
		this.radius = this.radius * matrix.getMaxScaleOnAxis();

		return this;
	}

	public Sphere translate( Vector3 offset )
	{
		this.center.add( offset );

		return this;
	}

	public boolean equals( Sphere sphere )
	{
		return sphere.center.equals( this.center ) && ( sphere.radius == this.radius );
	}

	public Sphere clone()
	{
		return new Sphere().copy( this );
	}
}
