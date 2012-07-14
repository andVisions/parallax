/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file based on the JavaScript source file of the THREE.JS project, 
 * licensed under MIT License.
 * 
 * This file is part of Squirrel project.
 * 
 * Squirrel is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 * 
 * Squirrel is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * Squirrel. If not, see http://www.gnu.org/licenses/.
 */

package thothbot.squirrel.core.client.textures;

import thothbot.squirrel.core.client.gl2.WebGLRenderingContext;
import thothbot.squirrel.core.client.gl2.WebGLTexture;
import thothbot.squirrel.core.client.gl2.enums.DataType;
import thothbot.squirrel.core.client.gl2.enums.GLenum;
import thothbot.squirrel.core.client.gl2.enums.PixelFormat;
import thothbot.squirrel.core.client.gl2.enums.TextureMagFilter;
import thothbot.squirrel.core.client.gl2.enums.TextureMinFilter;
import thothbot.squirrel.core.client.gl2.enums.TextureWrapMode;
import thothbot.squirrel.core.shared.core.Vector2f;

import com.google.gwt.dom.client.Element;

public class Texture
{
	private static int TextureCount = 0;
	
	public static enum OPERATIONS 
	{
		MULTIPLY(0), // MultiplyOperation
		MIX(1); // MixOperation

		private final int value;
		private OPERATIONS(int value) { this.value = value; }
		public int getValue() { return value; }
	};

	// Mapping modes
	public static enum MAPPING_MODE 
	{
		UV, // UVMapping = function () {};

		CUBE_REFLECTION, // CubeReflectionMapping = function () {};
		CUBE_REFRACTION, // CubeRefractionMapping = function () {};

		SPHERICAL_REFLECTION, // SphericalReflectionMapping = function () {};
		SPHERICAL_REFRACTION // SphericalRefractionMapping = function () {};
	};

	private int id;

	private Element image;

	private Vector2f offset;
	private Vector2f repeat;

	private Texture.MAPPING_MODE mapping = Texture.MAPPING_MODE.UV;

	private TextureWrapMode wrapS = TextureWrapMode.CLAMP_TO_EDGE;
	private TextureWrapMode wrapT = TextureWrapMode.CLAMP_TO_EDGE;

	private TextureMagFilter magFilter = TextureMagFilter.LINEAR;
	private TextureMinFilter minFilter = TextureMinFilter.LINEAR_MIPMAP_LINEAR;

	private PixelFormat format = PixelFormat.RGBA;
	private DataType type = DataType.UNSIGNED_BYTE;

	private boolean isGenerateMipmaps = true;
	private boolean isPremultiplyAlpha = false;

	private boolean isNeedsUpdate = false;
	
	public boolean __webglInit;
	public WebGLTexture __webglTexture;

	public Texture() 
	{
		this.id = Texture.TextureCount++;
		this.offset = new Vector2f(0, 0);
		this.repeat = new Vector2f(1, 1);
	}

	public Texture(Element image) 
	{
		this();
		this.image = image;
	}

	public Texture(Element image, Texture.MAPPING_MODE mapping)
	{
		this(image);
		this.mapping = mapping;
	}

	public Texture(Element image, Texture.MAPPING_MODE mapping, TextureWrapMode wrapS,
			TextureWrapMode wrapT, TextureMagFilter magFilter, TextureMinFilter minFilter,
			PixelFormat format, DataType type) 
	{	
		this(image, mapping);

		this.wrapS = wrapS;
		this.wrapT = wrapT;

		this.magFilter = magFilter;
		this.minFilter = minFilter;

		this.format = format;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public Texture.MAPPING_MODE getMapping() {
		return this.mapping;
	}

	public void setWrapS(TextureWrapMode wrapS)	{
		this.wrapS = wrapS;
	}

	public TextureWrapMode getWrapS(){
		return this.wrapS;
	}

	public void setWrapT(TextureWrapMode wrapT) {
		this.wrapT = wrapT;
	}
	
	public TextureWrapMode getWrapT() {
		return this.wrapT;
	}

	public TextureMagFilter getMagFilter() {
		return this.magFilter;
	}

	public TextureMinFilter getMinFilter() {
		return this.minFilter;
	}
	
	public Boolean isNeedsUpdate()	{
		return this.isNeedsUpdate;
	}
	
	public void setNeedsUpdate(Boolean needsUpdate) {
		this.isNeedsUpdate = needsUpdate;
	}
	
	public Element getImage() {
		return this.image;
	}

	public Vector2f getOffset() {
		return offset;
	}

	public void setOffset(Vector2f offset) {
		this.offset = offset;
	}

	public Vector2f getRepeat() {
		return repeat;
	}

	public void setRepeat(Vector2f repeat) {
		this.repeat = repeat;
	}

	public PixelFormat getFormat() {
		return format;
	}

	public void setFormat(PixelFormat format) {
		this.format = format;
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

	public boolean isGenerateMipmaps() {
		return isGenerateMipmaps;
	}

	public void setGenerateMipmaps(boolean generateMipmaps) {
		this.isGenerateMipmaps = generateMipmaps;
	}

	public boolean isPremultiplyAlpha() {
		return isPremultiplyAlpha;
	}

	public void setPremultiplyAlpha(boolean premultiplyAlpha) {
		this.isPremultiplyAlpha = premultiplyAlpha;
	}

	public void setMapping(Texture.MAPPING_MODE mapping) {
		this.mapping = mapping;
	}

	public void setMagFilter(TextureMagFilter magFilter) {
		this.magFilter = magFilter;
	}

	public void setMinFilter(TextureMinFilter minFilter) {
		this.minFilter = minFilter;
	}

	public void setNeedsUpdate(boolean needsUpdate) {
		this.isNeedsUpdate = needsUpdate;
	}

	public Texture clone()
	{
		Texture clonedTexture = new Texture(this.image, this.mapping, this.wrapS, this.wrapT,
				this.magFilter, this.minFilter, this.format, this.type);

		clonedTexture.offset.copy(this.offset);
		clonedTexture.repeat.copy(this.repeat);

		return clonedTexture;
	}

	public void setTextureParameters (WebGLRenderingContext gl, int textureType, boolean isImagePowerOfTwo ) 
	{	
		if ( isImagePowerOfTwo ) 
		{
			gl.texParameteri( textureType, GLenum.TEXTURE_WRAP_S.getValue(), this.wrapS.getValue() );
			gl.texParameteri( textureType, GLenum.TEXTURE_WRAP_T.getValue(), this.wrapT.getValue() );
			gl.texParameteri( textureType, GLenum.TEXTURE_MAG_FILTER.getValue(), this.magFilter.getValue() );
			gl.texParameteri( textureType, GLenum.TEXTURE_MIN_FILTER.getValue(), this.minFilter.getValue() );
		} 
		else 
		{
			gl.texParameteri( textureType, GLenum.TEXTURE_WRAP_S.getValue(), GLenum.CLAMP_TO_EDGE.getValue() );
			gl.texParameteri( textureType, GLenum.TEXTURE_WRAP_T.getValue(), GLenum.CLAMP_TO_EDGE.getValue() );
			gl.texParameteri( textureType, GLenum.TEXTURE_MAG_FILTER.getValue(), filterFallback( this.magFilter.getEnum() ) );
			gl.texParameteri( textureType, GLenum.TEXTURE_MIN_FILTER.getValue(), filterFallback( this.minFilter.getEnum() ) );
		}
	}
	
	/**
	 * Fallback filters for non-power-of-2 textures.
	 * 
	 * @param f
	 * @return
	 */
	private int filterFallback ( GLenum f ) 
	{
		switch ( f ) {

		case NEAREST:
		case NEAREST_MIPMAP_NEAREST:
		case NEAREST_MIPMAP_LINEAR: 
			return GLenum.NEAREST.getValue();

		case LINEAR:
		case LINEAR_MIPMAP_NEAREST:
		case LINEAR_MIPMAP_LINEAR:
		default:
			return GLenum.LINEAR.getValue();

		}
	}
}