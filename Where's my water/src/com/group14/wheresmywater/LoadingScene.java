package com.group14.wheresmywater;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import com.group14.wheresmywater.SceneManager.SceneType;

public class LoadingScene extends BaseScene{
	private LoadingSceneResource _resource;
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		_resource = ResourcesManager.getInstance()._loadingSceneResource;
		 setBackground(new Background(Color.BLACK));
		 attachChild(new Text(280, 1000, _resource._font, "Loading...", _vbom));
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_LOADING;
	}

	@Override
	public BaseScene clone() {
		// TODO Auto-generated method stub
		return new LoadingScene();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		ResourcesManager.getInstance().loadLoadingScreen();
	} 
}
