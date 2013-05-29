package com.group14.wheresmywater;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

public class MainMenuSceneResource {
	private ResourcesManager resourceManager = ResourcesManager.getInstance();

	public ITextureRegion bg_region;
	private BitmapTextureAtlas bgTextureAtlas;
	
	public TextureRegion btnOptions_region;
	private BitmapTextureAtlas btnOptionsTextureAtlas;
	
	public TextureRegion btnPlay_region;
	private BitmapTextureAtlas btnPlayTextureAtlas; 
	 
	public TiledTextureRegion cranky_region; 
	private BitmapTextureAtlas crankyTextureAtlas;
	
	public TextureRegion radio_region; 
	private BitmapTextureAtlas radioTextureAtlas;  
	
	public Music music; 
	
	public void load() {
		// TODO Auto-generated method stub
		loadGraphic(); 
		loadAudio();
	} 
	
	private void loadGraphic() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		bgTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		bg_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bgTextureAtlas, resourceManager._activity, "bg_main_menu.png", 0, 0);
		bgTextureAtlas.load(); 
		
		btnPlayTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 512, 256, TextureOptions.BILINEAR);
		btnPlay_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(btnPlayTextureAtlas, resourceManager._activity, "btnplay.png", 0, 0);
		btnPlayTextureAtlas.load(); 
		
		btnOptionsTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		btnOptions_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(btnOptionsTextureAtlas, resourceManager._activity, "splash.png", 0, 0);
		btnOptionsTextureAtlas.load(); 
		
		radioTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		radio_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(radioTextureAtlas, resourceManager._activity, "radio.png", 0, 0);
		radioTextureAtlas.load();  
		 
		crankyTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		cranky_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(crankyTextureAtlas, resourceManager._activity, "cranky_waitwater.png", 0, 0, 4, 4);
		crankyTextureAtlas.load();  
		  
	}

	private void loadAudio() {
		// TODO Auto-generated method stub 
		MusicFactory.setAssetBasePath("sfx/");
		try
		{
		    music = MusicFactory.createMusicFromAsset(resourceManager.getMusicManager(), resourceManager._activity, "start_game_music.mp3");
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		}
	}
	
	public void unload(){
		// TODO Auto-generated method stub
		bgTextureAtlas.unload();
		bg_region = null;
		
		btnPlayTextureAtlas.unload();
		btnPlay_region = null;
		
		btnOptionsTextureAtlas.unload();
		btnOptions_region = null;
		
		radioTextureAtlas.unload();
		radio_region = null;
		
//		btnOptionsTextureAtlas.unload();
//		btnOptions_region = null; 
		music = null;
	}
}
