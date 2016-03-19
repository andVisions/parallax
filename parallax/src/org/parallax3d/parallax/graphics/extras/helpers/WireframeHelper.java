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

package org.parallax3d.parallax.graphics.extras.helpers;

import org.parallax3d.parallax.graphics.core.GeometryObject;
import org.parallax3d.parallax.graphics.extras.geometries.WireframeGeometry;
import org.parallax3d.parallax.graphics.materials.LineBasicMaterial;
import org.parallax3d.parallax.graphics.objects.LineSegments;
import org.parallax3d.parallax.math.Color;
import org.parallax3d.parallax.system.ThreejsObject;

@ThreejsObject("THREE.WireframeHelper")
public class WireframeHelper extends LineSegments
{
	public WireframeHelper(GeometryObject object)
	{
		this(object, new Color(0xffffff));
	}

	public WireframeHelper(GeometryObject object, Color color)
	{
		super( new WireframeGeometry(object.getGeometry()), new LineBasicMaterial().setColor(color) );

		this.setMatrix(object.getMatrixWorld());
		this.setMatrixAutoUpdate(false);
	}

}
