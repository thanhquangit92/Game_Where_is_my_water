package com.group14.wheresmywater;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.ui.activity.BaseGameActivity; 

public class SceneManager {
 
	// ---------------------------------------------
	// SCENES
	// ---------------------------------------------
	private BaseScene _splashScene;
	private BaseScene _menuScene;
	private BaseScene _loadScene;
	private BaseScene _gameScene;  
	
	// ---------------------------------------------
	// TYPE SCENES
	// ---------------------------------------------
	public enum SceneType
    {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }
	
	 //---------------------------------------------
    // VARIABLES
    //--------------------------------------------- 
    private static final SceneManager INSTANCE = new SceneManager(); 
    private SceneType _currentSceneType = SceneType.SCENE_SPLASH; 
    private BaseScene _currentScene; 
    private Engine _engine = ResourcesManager.getInstance()._engine; 
	 

	public void setScene(BaseScene scene)
    {  
        _engine.setScene(scene);
        _currentScene = scene;  
        
        addScene(scene);
    } 
	
	 public void addScene(BaseScene scene)
	 {
		 switch (scene.getSceneType()) {
	        case SCENE_SPLASH:
				_splashScene = scene;
				break;
			case SCENE_GAME:
				_gameScene = scene;
				break;
			case SCENE_LOADING:
				_loadScene = scene;
				break;
			case SCENE_MENU:
				_menuScene = scene;
				break;
			default:
				_splashScene = scene;
				break;
			}
	 }

	public void setScene(SceneType type) {
		switch (type) {
		case SCENE_SPLASH:
			_currentScene = _splashScene;
			break;
		case SCENE_GAME:
			_currentScene = _gameScene;
			break;
		case SCENE_LOADING:
			_currentScene = _loadScene;
			break;
		case SCENE_MENU:
			_currentScene = _menuScene;
			break;
		default:
			break;
		}
		_engine.setScene(_currentScene);
	}
	
	public SceneType getCurrentSceneType()
    {
        return _currentScene.getSceneType();
    }
    
    public BaseScene getCurrentScene()
    {
        return _currentScene;
    }
    
    public BaseScene setNextScene(int idCurrent)
    { 
         BaseScene scene = getScene(idCurrent + 1);
         this.setScene(scene); 
         return scene;
    }	
    
    public BaseScene getScene(int id){
    	BaseScene scene = null;
        switch (id) {
		case 1:
			//scene = new Level01(this);
			this.setScene(scene);
			break;
		case 2:
			//scene = new Level02(this);
			this.setScene(scene);
			break; 
		default:
			break;
		}
        
       return scene;
    } 
    
    public static SceneManager getInstance(){
    	return INSTANCE;
    }
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
    {
        ResourcesManager.getInstance().loadSplashScreen();
        _splashScene = new SplashScene();
        _currentScene = _splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(_splashScene);
    }
    
    private void disposeSplashScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        _splashScene.disposeScene();
        _splashScene = null;
    }
}
