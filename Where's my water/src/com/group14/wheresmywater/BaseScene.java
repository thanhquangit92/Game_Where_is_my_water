package com.group14.wheresmywater;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;

import com.group14.wheresmywater.SceneManager.SceneType;
 
public abstract class BaseScene extends Scene { 

	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	protected Engine _engine;
	protected Activity _activity;
	protected ResourcesManager _resourcesManager;
	protected VertexBufferObjectManager _vbom;
	protected Camera _camera;
	
	// ---------------------------------------
	// CONTRUCTOR
	// ---------------------------------------
	public BaseScene() { 
		this._resourcesManager = ResourcesManager.getInstance();
        this._engine   = _resourcesManager._engine;
        this._activity = _resourcesManager._activity;
        this._vbom     = _resourcesManager._vbom;
        this._camera   = _resourcesManager._camera;
        createScene();
	}  
	
	public BaseScene(int unused) { 
		 
	}  

	// ---------------------------------------
	// METHOD
	// ---------------------------------------
	public abstract void createScene(); 
	public abstract void onBackKeyPressed(); 
	public abstract void disposeScene();
	public abstract SceneType getSceneType();
	public abstract BaseScene clone();
	public abstract void load();
}
