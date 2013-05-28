package com.group14.wheresmywater;

import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import android.content.Context;
import android.content.res.AssetManager;

public class ResourcesManager {
	 //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    
    private static final ResourcesManager INSTANCE = new ResourcesManager();
    
    public Engine _engine;
    public GameActivity _activity;
    public Camera _camera;
    public VertexBufferObjectManager _vbom;
    
    //---------------------------------------------
    // CLASS MANAGER RESOURCE SCENE
    //---------------------------------------------
    public SplashSceneResource _splashSceneResource;
    public MainMenuSceneResource _mainMenuSceneResource;
    public LoadingSceneResource _loadingSceneResource;
    public ScoreSceneResource _scoreSceneResource;
    public Level01Resource _level01Resource; 
    
    //---------------------------------------------
    // LOAD & UNLOAD RESOURCE
    //---------------------------------------------
    public void loadSplashScreen()
    {
    	_splashSceneResource = new SplashSceneResource();
    	_splashSceneResource.load();
    }
    
    public void unloadSplashScreen()
 {
		if (_splashSceneResource != null) {
			_splashSceneResource.unload();
			_splashSceneResource = null;
		}
	}

    public void loadMainMenuScreen()
    {
    	_mainMenuSceneResource = new MainMenuSceneResource();
    	_mainMenuSceneResource.load();
    }
    
    public void unloadMainMenuScreen()
    {
		if (_mainMenuSceneResource != null) {
			_mainMenuSceneResource.unload();
			_mainMenuSceneResource = null;
		}
    }
    
    public void loadLoadingScreen()
    {
    	_loadingSceneResource = new LoadingSceneResource();
    	_loadingSceneResource.load();
    }
    
    public void unloadLoadingScreen()
    {
		if (_loadingSceneResource != null) {
			_loadingSceneResource.unload();
			_loadingSceneResource = null;
		}
    }
     
    public void loadLevel01Screen()
    {
    	_level01Resource = new Level01Resource();
    	_level01Resource.load();
    }
    
    public void unloadLevel01Screen()
    {
		if (_level01Resource != null) {
			_level01Resource.unload();
			_level01Resource = null;
		}
    }
    
    public void loadScoreScreen()
    {
    	_scoreSceneResource = new ScoreSceneResource();
    	_scoreSceneResource.load();
    }
    
    public void unloadScoreScreen()
    {
		if (_scoreSceneResource != null) {
			_scoreSceneResource.unload();
			_scoreSceneResource = null;
		}
    }
    
    public void loadGameResources()
    {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }
    
    private void loadMenuGraphics()
    {
        
    }
    
    private void loadMenuAudio()
    {
        
    }

    private void loadGameGraphics()
    {
        
    }
    
    private void loadGameFonts()
    {
        
    }
    
    private void loadGameAudio()
    {
        
    }
    
    
    
    /**
     * @param engine
     * @param activity
     * @param camera
     * @param vbom
     * <br><br>
     * We use this method at beginning of game loading, to prepare Resources Manager properly,
     * setting all needed parameters, so we can latter access them from different classes (eg. scenes)
     */
    public static void prepareManager(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom)
    {
        getInstance()._engine = engine;
        getInstance()._activity = activity;
        getInstance()._camera = camera;
        getInstance()._vbom = vbom;
    }
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    public static ResourcesManager getInstance()
    {
        return INSTANCE;
    } 
    
    //---------------------------------------------
    // GET MANAGERS
    //--------------------------------------------- 
    public FontManager getFontManager()
	{
		return _engine.getFontManager();
	}
	
	public MusicManager getMusicManager()
	{
		return _engine.getMusicManager();
	}
	
	public TextureManager getTextureManager()
	{
		return _engine.getTextureManager();
	} 
	
	public SoundManager getSoundManager()
	{
		return _engine.getSoundManager();
	} 
	
	public AssetManager getAssets()
	{
		return ((BaseGameActivity)_activity).getAssets();
	} 
	
	public Context getApplicationContext()
	{
		return _activity.getApplicationContext();
	} 
}
