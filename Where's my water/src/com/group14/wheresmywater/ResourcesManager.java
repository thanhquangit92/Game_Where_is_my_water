package com.group14.wheresmywater;

import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

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
    	_splashSceneResource.unload();
    	_splashSceneResource = null;
    }
    
    public void loadMainMenuScreen()
    {
    	_mainMenuSceneResource = new MainMenuSceneResource();
    	_mainMenuSceneResource.load();
    }
    
    public void unloadMainMenuScreen()
    {
    	_mainMenuSceneResource.unload();
    	_mainMenuSceneResource = null;
    }
    
    public void loadMenuResources()
    {
        loadMenuGraphics();
        loadMenuAudio();
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
    protected FontManager getFontManager()
	{
		return _engine.getFontManager();
	}
	
	protected MusicManager getMusicManager()
	{
		return _engine.getMusicManager();
	}
	
	protected TextureManager getTextureManager()
	{
		return _engine.getTextureManager();
	} 
	
	protected SoundManager getSoundManager()
	{
		return _engine.getSoundManager();
	} 
	
	protected AssetManager getAssets()
	{
		return ((BaseGameActivity)_activity).getAssets();
	} 
}
