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

import org.parallax3d.parallax.graphics.core.Geometry;
import org.parallax3d.parallax.graphics.materials.LineBasicMaterial;
import org.parallax3d.parallax.graphics.materials.Material;
import org.parallax3d.parallax.graphics.objects.LineSegments;
import org.parallax3d.parallax.math.Color;
import org.parallax3d.parallax.math.Vector3;
import org.parallax3d.parallax.system.ThreejsObject;

import java.util.Arrays;

// port from three.js r70 GridHelper.js
/**
 * 
 * @author bartolomiew 
 *
 */
@ThreejsObject("THREE.GridHelper")
public class GridHelper extends LineSegments {

    static Color color1 = new Color( 0x444444 ); // colorCenterLine
    static Color color2 = new Color( 0x888888 ); // colorGrid

    public GridHelper(double size, int step)
    {
        super(intDefaultGeometry(size, step), new LineBasicMaterial().setVertexColors(Material.COLORS.VERTEX));
    }

    private static Geometry intDefaultGeometry(double size, int step) {

        Geometry geometry = new Geometry();

        for (int i = (int) - size; i <= size; i += step ) {

            geometry.getVertices().addAll(Arrays.asList(
                    new Vector3( - size, 0, i ), new Vector3( size, 0, i ),
                    new Vector3( i, 0, - size ), new Vector3( i, 0, size )
            ));

            Color color = i == 0 ? color1 : color2;

            geometry.getColors().addAll(Arrays.asList( color, color, color, color ));

        }

        return geometry;
    }
}