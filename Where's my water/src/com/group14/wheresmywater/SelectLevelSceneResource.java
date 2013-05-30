package com.group14.wheresmywater;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.color.Color;

import android.graphics.Typeface;

public class SelectLevelSceneResource {
	private ResourcesManager _resourceManager = ResourcesManager.getInstance();
	
	public TextureRegion btnBack_Region; 
	private BitmapTextureAtlas btnBackTextureAtlas; 
	
	public TextureRegion bg_Region; 
	private BitmapTextureAtlas bgTextureAtlas; 
	
	public TiledTextureRegion menu_Region; 
	private BitmapTextureAtlas menuTextureAtlas; 
	
	public Font mFont;
	public Music music; 
	
	public void load(){
		loaGraphic();
		loadAudio();
	}
	
	private void loaGraphic() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		btnBackTextureAtlas = new BitmapTextureAtlas(_resourceManager.getTextureManager() ,128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		btnBack_Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(btnBackTextureAtlas, _resourceManager.getAssets(), "btnback.png", 0, 0);
		btnBackTextureAtlas.load();
		
		menuTextureAtlas = new BitmapTextureAtlas(_resourceManager.getTextureManager() ,320, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		menu_Region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, _resourceManager.getAssets(), "spriteimenu.png", 0, 0, 5, 1);  
		menuTextureAtlas.load();
		
		bgTextureAtlas = new BitmapTextureAtlas(_resourceManager.getTextureManager(), 640, 812, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		bg_Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bgTextureAtlas, _resourceManager.getAssets(), "bg_seleclevel.jpg", 0, 0);
		bgTextureAtlas.load();
		
		mFont = FontFactory.create(_resourceManager.getFontManager(), _resourceManager.getTextureManager(), 256, 256, TextureOptions.BILINEAR, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 60, Color.WHITE_ABGR_PACKED_INT);
		mFont.load();
	}

	private void loadAudio() {
		// TODO Auto-generated method stub
		MusicFactory.setAssetBasePath("sfx/");
		try
		{
		    music = MusicFactory.createMusicFromAsset(_resourceManager.getMusicManager(), _resourceManager.getApplicationContext(), "select_level_music.mp3");
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		}
	}

	public void unload(){ 
		btnBackTextureAtlas.unload(); 
		btnBack_Region = null; 
		
		bgTextureAtlas.unload();
		bg_Region = null; 
		 
		menuTextureAtlas.unload(); 
		menu_Region = null;  
	}
}
