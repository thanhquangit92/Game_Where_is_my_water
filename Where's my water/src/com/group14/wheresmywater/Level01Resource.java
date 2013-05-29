package com.group14.wheresmywater;

import java.io.IOException;
import java.util.ArrayList;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

public class Level01Resource {
	private ResourcesManager resourceManager = ResourcesManager.getInstance();
	
	public TextureRegion wall_Region; 
	private BitmapTextureAtlas wallTextureAtlas; 
	
	public TextureRegion rock_Region; 
	private BitmapTextureAtlas rockTextureAtlas; 
	
	public TextureRegion water_Region; 
	private BitmapTextureAtlas waterTextureAtlas; 
	
	public TextureRegion btnRePlay_Region; 
	private BitmapTextureAtlas btnRePlayTextureAtlas; 
	
	public TiledTextureRegion crankyWait_Region; 
	private BitmapTextureAtlas crankyWaitTextureAtlas; 
	
	public TiledTextureRegion crankyHaveWater_Region; 
	private BitmapTextureAtlas crankyHaveWaterTextureAtlas; 
	
	public ArrayList<TextureRegion> listduckywater_region;
	private ArrayList<BitmapTextureAtlas> listduckywaterTextureAtlas;
	
	public Sound soundGameWin;
	public Sound soundWaterDrop;
	public Sound soundDucky;
	public Sound soundCrankyCry;
	public Sound soundCrankyLaugh;
	public Music music;
	
	public void load(){
		loadGraphic();
		loadAudio();
	}
	
	private void loadGraphic() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		rockTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 800, 800, TextureOptions.BILINEAR);
		rock_Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(rockTextureAtlas, resourceManager._activity, "rock_level01.png", 0, 0);
		rockTextureAtlas.load(); 
		
		wallTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 800, 1280, TextureOptions.BILINEAR);
		wall_Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(wallTextureAtlas, resourceManager._activity, "wall.png", 0, 0);
		wallTextureAtlas.load(); 
		
		waterTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		water_Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(waterTextureAtlas, resourceManager._activity, "drop.png", 0, 0);
		waterTextureAtlas.load(); 
		
		crankyWaitTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA); 
		crankyWait_Region= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(crankyWaitTextureAtlas, resourceManager.getAssets(), "cranky_waitwater.png", 0, 0, 4, 4);  
		crankyWaitTextureAtlas.load(); 
		
		crankyHaveWaterTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA); 
		crankyHaveWater_Region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(crankyHaveWaterTextureAtlas, resourceManager.getAssets(), "cranky_havewater.png", 0, 0, 4, 4);  
		crankyHaveWaterTextureAtlas.load();  
		
		btnRePlayTextureAtlas = new BitmapTextureAtlas(resourceManager.getTextureManager() ,512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		btnRePlay_Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(btnRePlayTextureAtlas, resourceManager.getAssets(), "btnrestart.png", 0, 0);
		btnRePlayTextureAtlas.load();
		
		int nducky = 6;
		listduckywater_region = new ArrayList<TextureRegion>();
		listduckywaterTextureAtlas = new ArrayList<BitmapTextureAtlas>();
		for (int i = 0; i < nducky; i++) {
			BitmapTextureAtlas bmp = new BitmapTextureAtlas(resourceManager.getTextureManager(), 64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA); 
			TextureRegion region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bmp, resourceManager.getAssets(), "ducky_duck_water" + String.valueOf(i) + ".png", 0, 0);  
			bmp.load(); 
			
			listduckywater_region.add(region);
			listduckywaterTextureAtlas.add(bmp);
		} 
	}

	private void loadAudio() {
		// TODO Auto-generated method stub
		SoundFactory.setAssetBasePath("sfx/");
		MusicFactory.setAssetBasePath("sfx/");
		try {
			soundGameWin = SoundFactory.createSoundFromAsset(resourceManager.getSoundManager(), resourceManager.getApplicationContext(), "gamewin.ogg");
			soundWaterDrop = SoundFactory.createSoundFromAsset(resourceManager.getSoundManager(), resourceManager.getApplicationContext(), "waterdrop.ogg");
			soundDucky = SoundFactory.createSoundFromAsset(resourceManager.getSoundManager(), resourceManager.getApplicationContext(), "ducky01.ogg");
			soundCrankyCry = SoundFactory.createSoundFromAsset(resourceManager.getSoundManager(), resourceManager.getApplicationContext(), "cranky_cry.ogg");
			soundCrankyLaugh = SoundFactory.createSoundFromAsset(resourceManager.getSoundManager(), resourceManager.getApplicationContext(), "cranky_laugh.ogg");
			music = MusicFactory.createMusicFromAsset(resourceManager.getMusicManager(), resourceManager.getApplicationContext(), "level_music.mp3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unload(){
		
	}
}
