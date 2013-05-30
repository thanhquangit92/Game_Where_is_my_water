package com.group14.wheresmywater;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {

	// ---------------------------------------------
	// LIST SCENES
	// ---------------------------------------------
	private ArrayList<BaseScene> listGameScene = null;
	
	// ---------------------------------------------
	// SCENES
	// ---------------------------------------------
	private BaseScene _splashScene;
	private BaseScene _menuScene;
	private BaseScene _loadScene;
	private BaseScene _gameScene;  
	private BaseScene _scoreScene;
	
	// ---------------------------------------------
	// TYPE SCENES
	// ---------------------------------------------
	public enum SceneType
    {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING, 
        SCENE_SCORE,
    }
	
	 //---------------------------------------------
    // VARIABLES
    //--------------------------------------------- 
    private static final SceneManager INSTANCE = new SceneManager(); 
    private BaseScene _currentScene; 
    private Engine _engine = ResourcesManager.getInstance()._engine; 
 

	public void setScene(BaseScene scene)
    {  
        _engine.setScene(scene);
        _currentScene = scene;   
        //addScene(scene);
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
    
    public static void prepareManager(){
    	getInstance().listGameScene = new ArrayList<BaseScene>();
    	getInstance().listGameScene.add(new Level01(1));
    	getInstance().listGameScene.add(new Level02(1));
    	getInstance().listGameScene.add(new Level03(1));
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
    
    public void createMenuScene()
    {
        ResourcesManager.getInstance().loadMainMenuScreen();
        ResourcesManager.getInstance().loadLoadingScreen();
        _menuScene = new MainMenuScene();
        _loadScene = new LoadingScene();
        setScene(_menuScene);
        disposeSplashScene();
    }
    
    public void loadLevel01Scene(final Engine mEngine)
    {
        setScene(_loadScene);
        ResourcesManager.getInstance().unloadMainMenuScreen();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadLevel01Screen();
                _gameScene = new Level01();
                setScene(_gameScene);
            }
        }));
    }
    
    public void loadScoreScene(final Engine mEngine)
    {
        //setScene(_loadScene); 
    	final BaseScene scene = _currentScene; 
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadScoreScreen();
                _scoreScene = new ScoreScene(); 
                setScene(_scoreScene);
                ((SmoothCamera)ResourcesManager.getInstance()._camera).setCenter(400, 640); 
				((SmoothCamera)ResourcesManager.getInstance()._camera).setZoomFactor(1.0f);  
                scene.disposeScene();
            }
        }));
    }
    
    public void loadGameSceneReplay(final Engine mEngine)
    {
    	 _currentScene.disposeScene();
        setScene(_loadScene); 
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                _gameScene.load();
                _gameScene = _gameScene.clone();
                setScene(_gameScene);
            }
        }));
    }
    
    public void loadGameScene(final int id, final Engine mEngine)
    {
    	final int index = id - 1;
    	 _currentScene.disposeScene();
        setScene(_loadScene); 
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler); 
                listGameScene.get(index).load();
                _gameScene = listGameScene.get(index).clone();
                setScene(_gameScene);
            }
        }));
    }
    
    public void loadSelectLevelScene(final Engine mEngine)
    {
    	 _currentScene.disposeScene();
        setScene(_loadScene); 
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler); 
                ResourcesManager.getInstance().loadSelectLevelScreen(); 
                setScene(new SelectLevelScene());
            }
        }));
    }
    
    public void loadMenuScene(final Engine mEngine)
    {
    	_currentScene.disposeScene(); 
    	setScene(_loadScene); 
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadMainMenuScreen();
                _menuScene = new MainMenuScene();
                setScene(_menuScene);  
            }
        })); 
    }
}
