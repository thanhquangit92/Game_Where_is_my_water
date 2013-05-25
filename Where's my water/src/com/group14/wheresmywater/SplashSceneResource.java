package com.group14.wheresmywater;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

public class SplashSceneResource{
	private ResourcesManager resourceManager = ResourcesManager.getInstance();
	
	public ITextureRegion splash_region;
	private BitmapTextureAtlas splashTextureAtlas;

	public void load() {
		// TODO Auto-generated method stub
		loadGraphic(); 
	}

	private void loadGraphic() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, resourceManager._activity, "splash.png", 0, 0);
		splashTextureAtlas.load(); 
	}

	public void unload(){
		// TODO Auto-generated method stub
		splashTextureAtlas.unload();
		splash_region = null;
	}
}
