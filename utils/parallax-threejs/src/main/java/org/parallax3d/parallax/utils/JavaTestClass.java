/*
 * Copyright 2016 Alex Usachev, thothbot@gmail.com
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
package org.parallax3d.parallax.utils;

import org.junit.Test;
import org.parallax3d.parallax.system.ThreejsTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JavaTestClass extends JavaFile {

    public JavaTestClass(Class cls) {
        super(cls);
    }

    public String getThreeTestId()
    {
        return ((ThreejsTest)cls.getAnnotation(ThreejsTest.class)).value();
    }

    public List<String> getTestCaseNames() {

        List<String> names = new ArrayList<>();
        for(Method method: JavaReflections.getMethodsAnnotatedWith(cls, Test.class))
        {
            names.add(method.getName());
        }

        return names;
    }
}