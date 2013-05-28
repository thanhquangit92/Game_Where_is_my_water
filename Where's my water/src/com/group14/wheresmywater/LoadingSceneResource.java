package com.group14.wheresmywater;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.util.color.Color;

public class LoadingSceneResource {
	private ResourcesManager resourceManager = ResourcesManager.getInstance();
	public Font _font;
	public void load(){
		loadFont();
	}
	
	private void loadFont() {
		// TODO Auto-generated method stub
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(resourceManager.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		_font = FontFactory.createStrokeFromAsset(resourceManager.getFontManager(), mainFontTexture, resourceManager.getAssets(), "font.ttf", 50, true, Color.WHITE_ARGB_PACKED_INT, 2, Color.BLACK_ARGB_PACKED_INT);
		_font.load();
	}

	public void unload(){
		
	}
}
