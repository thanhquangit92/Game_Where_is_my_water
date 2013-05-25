package com.group14.wheresmywater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import com.group14.wheresmywater.SceneManager.SceneType;

import android.os.Environment;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener{   
	
	private MainMenuSceneResource resource = ResourcesManager.getInstance()._mainMenuSceneResource;
	private Sprite background; 
	private Sprite logo;   
	private Sprite radio; 
	private MenuScene childScene; 

	@Override
	public void createScene() { 
		createBackground();
		createLogo();
		createRadio();
		createMenu();
		createMusic();
		//createCasau();
	} 
	
	private void createBackground() {   
		background = new Sprite(0, 0, Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT, resource.bg_region, _vbom);
		setBackground(new SpriteBackground(background));  
	}

	private void createLogo() { 
		logo = new Sprite(144, 0, resource.logo_region, _vbom);
		this.attachChild(logo); 
	}

	private void createRadio() { 
		radio = new Radio(137, 500, 155, 200, resource.radio_region, _vbom);
		this.attachChild(radio);
	}
	
//	private void createCasau() {
//		// TODO Auto-generated method stub
//		BitmapTextureAtlas bmpWaitWater = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA); 
//		TiledTextureRegion casauWaitRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bmpWaitWater, ((BaseGameActivity)_activity).getAssets(), "gfx/casauwait.png", 0, 0, 4, 4);  
//		bmpWaitWater.load();  
//		
//		addCasau(casauWaitRegion);
//	}

	private void addCasau(TiledTextureRegion region) {
		AnimatedSprite _spriteCasau = new AnimatedSprite(320, 740, 400, 400, region, _vbom);
		_spriteCasau.setZIndex(3);
		_spriteCasau.animate(300);
		this.attachChild(_spriteCasau);
	}
	
	private final int MENU_PLAY = 0; 

	private void createMenu()
	{
	    childScene = new MenuScene(this._camera);
	    childScene.setPosition(0, 0);
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, 274, 82, resource.btnPlay_region, _vbom), 1.1f, 1);
	     
	    childScene.addMenuItem(playMenuItem);  
	    childScene.buildAnimations();
	    childScene.setBackgroundEnabled(false); 
	    childScene.setOnMenuItemClickListener(this);
	    
	    playMenuItem.setPosition(265, 400);
	    
	    setChildScene(childScene);
	}
	
	private void createMusic(){ 
		resource.music.setLooping(true);
		resource.music.play();
	} 
	
	@Override
	public void onBackKeyPressed() {
		 System.exit(0);
	}

	@Override
	public void disposeScene() {
		background.detachSelf();
		background.dispose();
		
		logo.detachSelf();
		logo.dispose(); 
		
		radio.detachSelf();
		radio.dispose();
		
		this.detachSelf();
		this.dispose(); 
		System.out.println("Dispose");
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}
	

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		resource.music.stop();
		
		int id = pMenuItem.getID();
		switch (id) {
		case MENU_PLAY:
//			if (isPlayFirst()) {
//				BaseScene select = new SelectLevel(_sceneManager); 
//				_sceneManager.setScene(select);
//			} else {
//				BaseScene game = new Level01(_sceneManager);
//				_sceneManager.setScene(game);
//			}
			break;
		default:
			break;
		}
		
		return true;
	}

	private boolean isPlayFirst() {
		// TODO Auto-generated method stub
		try {
			File root = Environment.getExternalStorageDirectory(); 
			FileReader filereader = new FileReader(new File(root, "wmw.txt"));   
			filereader.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return true;
	} 
	
	
}
