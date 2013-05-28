package com.group14.wheresmywater;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.group14.wheresmywater.SceneManager.SceneType;

public class SplashScene extends BaseScene{
	private static SplashSceneResource _resource = ResourcesManager.getInstance()._splashSceneResource;
	
	private Sprite splash; 
	
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		splash = new Sprite(0, 0, _resource.splash_region, _vbom)
		{ 
		    @Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				// TODO Auto-generated method stub
		    	 super.preDraw(pGLState, pCamera);
			       pGLState.enableDither();
			} 
		};
		        
		splash.setScale(1.5f);
		splash.setPosition(400, 240);
		attachChild(splash);
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		splash.detachSelf();
	    splash.dispose();
	    this.detachSelf();
	    this.dispose();
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_SPLASH;
	}
	

	@Override
	public BaseScene clone() {
		// TODO Auto-generated method stub
		return new SplashScene();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		ResourcesManager.getInstance().loadSplashScreen();
	}

}
