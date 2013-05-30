package com.group14.wheresmywater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import android.os.Environment;

import com.group14.wheresmywater.SceneManager.SceneType;

public class SelectLevelScene extends BaseScene implements IOnMenuItemClickListener { 
	private SelectLevelSceneResource _resource;
	ArrayList<IMenuItem> imenuList ;  
	private MenuScene childScene; 
	private int[] indexs;

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		_resource = ResourcesManager.getInstance()._selectLevelResource;
		
		imenuList = new ArrayList<IMenuItem>(); 
		createMenuItem(); 
		createBtnBack();
		createBackground(); 
		createMusic();
	} 
	
	private void createMusic(){ 
		_resource.music.setLooping(true);
		_resource.music.play();
	}

	private void createMenuItem() {
		// TODO Auto-generated method stub
		String[] s = getInfoGame();
		if(s == null)
			return; 
		 
		childScene = new MenuScene(this._camera);
		childScene.setPosition(0, 0);
		    
		int Col = 3;  
		indexs = new int[Global.nScene]; 
		int size = 200;
		boolean played = false;
		for(int i  =  0; i < Global.nScene; i ++){
			String [] info = s[i].split(",");
			indexs[i] = getIndexMenuItem(info);
			if(indexs[i] == 4 && ((i == 0) || (played == true)))
				indexs[i] = 0;
			IMenuItem menuitem = new ScaleMenuItemDecorator(new SpriteMenuItem(i + 1, size, size, _resource.menu_Region.getTextureRegion(indexs[i]), _vbom), 1.1f, 1);
			this.imenuList.add(menuitem); 
			childScene.addMenuItem(menuitem);  
			played = (Integer.parseInt(info[1]) != 0);
		} 
		
	    childScene.buildAnimations();
	    childScene.setBackgroundEnabled(false);  
	    childScene.setOnMenuItemClickListener(this);
	    
	    for(int i  =  0; i < Global.nScene; i ++){  
			int r = i / Col;
			int c = i % Col;
			IMenuItem item = this.imenuList.get(i); 
			item.setPosition( 50 + (size + 50 ) * c , 150 + (size + 30) * r);
			if(i < indexs.length && indexs[i] != 4){
				Text mText = new Text(size/2 - 15, 45, _resource.mFont, s[i].split(",")[0], 1000, new TextOptions(HorizontalAlign.LEFT), _vbom);
				item.attachChild(mText); 
			}
		}
	    
		setChildScene(childScene);
	}

	private int getIndexMenuItem(String[] info) {
		// TODO Auto-generated method stub
		if(Integer.parseInt(info[1]) == 0)
			return 4;
		int nDuck = Integer.parseInt(info[2]); 
		return nDuck;
	}

	private String[] getInfoGame() {
		// TODO Auto-generated method stub
		String[] s = new String[Global.nScene];
		 
		try {
			File root = Environment.getExternalStorageDirectory(); 
			FileReader filereader = new FileReader(new File(root, "wmw.txt"));  
			BufferedReader reader = new BufferedReader(filereader); 
			for (int i = 0; i < Global.nScene; i++) {
				s[i] = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return s;
	}

	private void createBtnBack() {
		// TODO Auto-generated method stub  
		IMenuItem btnBack = new ScaleMenuItemDecorator(new SpriteMenuItem(0, 100, 100, _resource.btnBack_Region, _vbom), 1.1f, 1);
		childScene.addMenuItem(btnBack);
		btnBack.setPosition(10, 10);
	}
	
	private void createBackground() {  
		Sprite background = new Sprite(0, 0, Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT, _resource.bg_Region, _vbom);
		this.setBackground(new SpriteBackground(background)); 
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub 
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		ResourcesManager.getInstance().unloadSelectLevelScreen();
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub 
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		int id = pMenuItem.getID(); 
		
		if(id == 0){ 
			SceneManager.getInstance().loadMenuScene(_engine); 
			stopMusic();
		}else{
			if(indexs[id - 1] != 4){
				SceneManager.getInstance().loadGameScene(id, _engine);
				stopMusic();
			}
		}
		
		return true;
	}
	  
	private void stopMusic(){
		if(_resource.music.isPlaying())
			_resource.music.stop();  
	}
	
	@Override
	public BaseScene clone() {
		// TODO Auto-generated method stub
		return new SelectLevelScene();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		ResourcesManager.getInstance();
	} 
}
