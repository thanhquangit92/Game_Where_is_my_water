package com.group14.wheresmywater;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.ui.activity.BaseGameActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

public class GameActivity extends BaseGameActivity {

	//---------------------------------------------
    // VARIABLES
    //--------------------------------------------- 
	public Font mFont;
	public SmoothCamera mCamera;
	public Scene mCurrentScene;
	public SceneManager mSceneManager;
	private ResourcesManager mResourcesManager;
	private static BaseGameActivity INSTANCE; 
	
	//---------------------------------------------
    // 
    //--------------------------------------------- 
	@Override
	protected synchronized void onCreateGame() {
		super.onCreateGame();
		INSTANCE = this;
	}

	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		return new LimitedFPSEngine(pEngineOptions, 60);
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		this.mCamera = new SmoothCamera(0, 0, Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT, 10, 400, 1.0f);
		EngineOptions engOps = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT), this.mCamera);
		engOps.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		engOps.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engOps;
	}

	@Override
	public final void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback)throws Exception {
		ResourcesManager.prepareManager(mEngine, this, mCamera, getVertexBufferObjectManager());
		mResourcesManager = ResourcesManager.getInstance();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public final void onCreateScene(final OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
		pOnCreateSceneCallback.onCreateSceneFinished(mSceneManager.getCurrentScene());
	}

	@Override
	public final void onPopulateScene(final Scene pScene, final OnPopulateSceneCallback pOnPopulateSceneCallback)throws Exception {
		 mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
		    {
		        public void onTimePassed(final TimerHandler pTimerHandler) 
		        {
		            mEngine.unregisterUpdateHandler(pTimerHandler);
		            SceneManager.getInstance().createMenuScene();
		        }
		    }));
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (this.isGameLoaded()) { 
			System.exit(0);
		}
	}

	public static Context getContext() {
		return INSTANCE;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub 
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
	    {
	        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    }
	    return false; 
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_launcher);

			final AlertDialog alert = builder.create();

			alert.setButton("Main Menu", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					mEngine.start();
					//mSceneManager.setScene(new MainMenuScene(mSceneManager));
					alert.dismiss();
				}
			});
			alert.setButton2("Resume", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					mEngine.start();
					alert.dismiss();
				}
			});
			return alert;
		default:
			return null;
		}
	}
}
