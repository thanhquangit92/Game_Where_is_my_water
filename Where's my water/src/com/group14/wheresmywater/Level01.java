package com.group14.wheresmywater; 

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import android.graphics.Point;
import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.group14.wheresmywater.SceneManager.SceneType;

public class Level01 extends BaseScene implements IOnSceneTouchListener, IOnMenuItemClickListener { 
	private Level01Resource _resource;
	
	private Sprite _spriteRock; 
	private Sprite _spriteWall;  
	private AnimatedSprite  _spriteCranky;  
	
	private ArrayList<SoilArea> listSoil; 
	private int nRow;
	private int nCol;
	private Point pos;
	private float wCell;
	private float hCell;
	private PhysicsWorld mPhysicsWorld;
	
	private ArrayList<Sprite> listWater;
	private ArrayList<Body> listBody;
	private ArrayList<Sprite> listDuckyEmpty;
	private ArrayList<Sprite> listDucky;
	private Rectangle rectPipe;
	
	private MenuScene childScene;
	private final int RESTART_MENU = 0;
	
	private int nWaterIntoRoom = 0;   
	
	public int nDuckyHaveWater = 0;
	private long timeStart; 
	private boolean isGameWin = false; 
	private boolean isGameOver = false;

	@Override
	public void createScene() {
		// TODO Auto-generated method stub  
		_resource = ResourcesManager.getInstance()._level01Resource;
//		_sceneManager.setScene(new SceneLoadReplay(_sceneManager, this));
		
		// get time start Level
		timeStart = System.currentTimeMillis();
		
		// create PhysicWorld
		mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		this.registerUpdateHandler(mPhysicsWorld);
		
		// create Object
		createWall();
		createSoil();
		createRock();
		createWater(390, 100, 80);
		createRectPipe();
		createMenuRestart();
		createDuckyEmpty();  
		createCranky();
		playMusic();
		
		this.setOnSceneTouchListener(this); 
	}   

	public Level01(){
		
	}
	
	public Level01(int unused){
		super(unused);
	}
	 
	private void createCranky() {
		// TODO Auto-generated method stub 
		addCranky(_resource.crankyWait_Region);
	}

	private void addCranky(TiledTextureRegion region) {
		_spriteCranky = new AnimatedSprite(340, 990, 200, 200, region, _vbom);
		_spriteCranky.setZIndex(3);
		_spriteCranky.animate(200);
		this.attachChild(_spriteCranky);
	}

	private void createDuckyEmpty() {
		// TODO Auto-generated method stub 
		int nDucky = 3;
		float topEmpty = 290;
		float leftEmpty = 368;
		float top = 10;
		float left = 10;
		listDuckyEmpty = new ArrayList<Sprite>();
		listDucky = new ArrayList<Sprite>();
		for (int i = 0; i < nDucky; i++) {
			Sprite duckyEmpty = new Sprite(leftEmpty, topEmpty + i * 150, _resource.listduckywater_region.get(0), _vbom); 
			duckyEmpty.setTag(0);
			listDuckyEmpty.add(duckyEmpty);
			duckyEmpty.setZIndex(0);
			touch(leftEmpty + 32, topEmpty + i * 150 + 32);
			
			Sprite ducky = new Sprite(left + i * 70, top, _resource.listduckywater_region.get(0), _vbom);  
			listDucky.add(ducky);
			duckyEmpty.setZIndex(4); 
			
			this.attachChild(duckyEmpty);
			this.attachChild(ducky);
		}
	}

	private void createMenuRestart() {
		// TODO Auto-generated method stub
		childScene = new MenuScene(this._camera);
	    childScene.setPosition(0, 0); 
	   
	    final IMenuItem restartMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(RESTART_MENU, 75, 75, _resource.btnRePlay_Region, _vbom), 1.1f, 1);
	     
	    childScene.addMenuItem(restartMenuItem);  
	    childScene.buildAnimations();
	    childScene.setBackgroundEnabled(false); 
	    childScene.setOnMenuItemClickListener(this);
	    
	    restartMenuItem.setPosition(720, 10);
	    setChildScene(childScene);
	}

	private void playMusic() {
		// TODO Auto-generated method stub 
		_resource.music.setLooping(true);
		_resource.music.play();
	}
	
	private void createRectPipe() {
		// TODO Auto-generated method stub
		rectPipe = new Rectangle(378, 862, 48, 1, _vbom);
	}

	private void createWall() {
		// TODO Auto-generated method stub  
		_spriteWall = new Sprite(0, 0, Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT, _resource.wall_Region, _vbom);
		_spriteWall.setZIndex(0);
		this.attachChild(_spriteWall);
	}  

	private void createSoil() {
		// TODO Auto-generated method stub
		listSoil = new ArrayList<SoilArea>();   
		float WidthSoil = 800.0f;
		float HeightSoil = 700.0f;
		pos = new Point(0, 150);
		nRow = 10;
		nCol = 10;
		wCell = WidthSoil/nCol;
		hCell = HeightSoil/nRow;
		
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				SoilArea soil = new SoilArea();
				soil.addPoint(0, 0);
				soil.addPoint(wCell, 0);
				soil.addPoint(wCell, hCell); 
				soil.addPoint(0, hCell);
		 
				soil.setTouchable(true);
				soil.setPosition(j*wCell + pos.x, i*hCell + pos.y);
				listSoil.add(soil);
			}
		} 
		
		touch(400, 150);
		touch(400, 170);
		touch(400, 190);
		touch(400, 210);
		
		touch(300, 150);
		touch(330, 165);
		touch(360, 180);
		touch(390, 195);
		
		touch(500, 150);
		touch(470, 165);
		touch(440, 180);
		touch(410, 195);  
		
		for (SoilArea soil : listSoil) {
			soil.attachToScene(this, this._engine, mPhysicsWorld, _vbom); 
		}  
	}
 
	private void createRock() {
		// TODO Auto-generated method stub 
		_spriteRock = new Sprite(0, 480, 800, 800, _resource.rock_Region, _vbom);
		_spriteRock.setZIndex(3);
		this.attachChild(_spriteRock);
		 
		createBody();
	}
	 
	private void createBody() {
		// TODO Auto-generated method stub
		FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(0f, 0f, 0f);
		BodyProvider.generateBodies("body/body_level01.xml", mPhysicsWorld, FIXTURE_DEF);
	}
   
	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
//		if (!isGameWin) {
//			_engine.stop();
//			_activity.showDialog(1);
//		} 
		System.exit(0);
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		ResourcesManager.getInstance().unloadLevel01Screen();
	}

	@Override
	public SceneType getSceneType() {
		 return SceneType.SCENE_GAME;
	}
	
	protected void createWater(float pX, float pY, int count) { 
		listWater = new ArrayList<Sprite>();
		listBody = new ArrayList<Body>();  
		for (int i = 0; i < count; i++) {
			Sprite drop = new Sprite(pX, pY, 100, 100, _resource.water_Region, _vbom);
			drop.setZIndex(10);
			Rectangle rect = new Rectangle(pX + 42.5f, pY + 80, 10, 10, _vbom);
			
			final FixtureDef fixtureDef = PhysicsFactory.createFixtureDef(1f, 0f, 0f); 
			Body body = PhysicsFactory.createCircleBody(mPhysicsWorld, rect, BodyType.DynamicBody, fixtureDef);
			
			mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(drop, body));
			this.attachChild(drop);
			listWater.add(drop);
			listBody.add(body);
		}
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		final float x = pSceneTouchEvent.getX();
		final float y = pSceneTouchEvent.getY(); 
		touch(x, y); 

		return true;
	}

	private void touch(final float x, final float y) {
		int col = (int)((x - pos.x) / wCell);
		int row = (int)((y - pos.y)/ hCell);
		 
		for (int i = -1; i < 2; i++) { 
			int newRow = row + i;
			for (int j = -1; j < 2; j++) {
				int newCol = col + j;
				if(newRow < 0 || newRow >= nRow || newCol < 0 || newCol >= nCol)
					continue;
				final int index = newRow * nCol + newCol;
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub 
						listSoil.get(index).touch(x, y); 
					}
				}); 
				thread.start(); 
			}
		}
	} 
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		// TODO Auto-generated method stub
		super.onManagedUpdate(pSecondsElapsed);
		for (SoilArea soil : listSoil) {
			soil.Update(); 
		} 
		sortChildren();  
		updateWater(); 
		
		if(isGameWin == false && isGameOver == false && listWater.size() <= 0){
			isGameOver = true;
			gameOver();
		}
	}

	private void gameOver() {
		// TODO Auto-generated method stub
		if(_resource.music.isPlaying())
			_resource.music.stop();
		_resource.soundCrankyCry.play();
		((SmoothCamera)_camera).setCenter(400, 950);
		((SmoothCamera)_camera).setZoomFactor(2.0f); 
		Thread threadGameOver = new ThreadGameOver();
		threadGameOver.start(); 
	}

	class ThreadGameOver extends Thread{ 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {   
				Thread.sleep(2500);  
				((SmoothCamera)_camera).setCenter(400, 640);
				((SmoothCamera)_camera).setZoomFactor(1.0f); 
				Thread.sleep(2000);
				SceneManager.getInstance().loadSceneGameReplay(_engine);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	private void updateWater() {
		for (int i = 0; i < listWater.size(); i++) { 
			Sprite sprite = listWater.get(i);
			Rectangle rect1 = new Rectangle(sprite.getX() + 40f, sprite.getY() + 40.0f, 10, 10, _vbom);
			 
			for (int j = 0; j < listDuckyEmpty.size(); j++) {
				Sprite ducky = listDuckyEmpty.get(j);
				if (rect1.collidesWith(ducky)) {
					excuteCollidesWithDucky(j);
					this.detachChild(sprite);
					Body b = listBody.get(i);
					mPhysicsWorld.destroyBody(b);
					listBody.remove(i);
					listWater.remove(i); 
				} 
			}
			
			if(sprite.getX() < -sprite.getWidth() || sprite.getX() > Global.CAMERA_WIDTH
					|| sprite.getY() > Global.CAMERA_HEIGHT){
				this.detachChild(sprite);
				Body b = listBody.get(i);
				mPhysicsWorld.destroyBody(b);
				listBody.remove(i);
				listWater.remove(i);
				i--;
			}
		}

		for (int i = 0; i < listWater.size(); i++) {
			Sprite sprite = listWater.get(i);
			if (sprite.collidesWith(rectPipe)) {
				excuteCollidesWithPipe(i);
				i--;
			} 
		}
	}

	private void excuteCollidesWithDucky(int index) {
		// TODO Auto-generated method stub
		Sprite duckyEmpty = listDuckyEmpty.get(index);
		int count = duckyEmpty.getTag() + 1;
		if (count >= 5) {
			this.detachChild(duckyEmpty);
			listDuckyEmpty.remove(index);
			_resource.soundDucky.play(); 
			Sprite ducky = listDucky.get(nDuckyHaveWater);
			this.detachChild(ducky);
			ducky = new Sprite(ducky.getX(), ducky.getY(), _resource.listduckywater_region.get(5), _vbom);
			this.attachChild(ducky);
			nDuckyHaveWater++;
		} else { 
			this.detachChild(duckyEmpty);
			duckyEmpty = new Sprite(duckyEmpty.getX(), duckyEmpty.getY(), _resource.listduckywater_region.get(count), _vbom);
			duckyEmpty.setTag(count);
			listDuckyEmpty.remove(index);
			listDuckyEmpty.add(index, duckyEmpty);
			this.attachChild(duckyEmpty);  
		}
	}

	private void excuteCollidesWithPipe(int index) {
		Sprite sprite = listWater.get(index);
		
		// Kiểm tra thắng game
		testWingame(); 
		
		// Di chuyển giọt nước vào bồn
		mPhysicsWorld.destroyBody(listBody.get(index));
		listWater.remove(index);
		listBody.remove(index); 
		sprite.setPosition(300, 480 + 480); 
		Rectangle rect = new Rectangle(300 + 42.5f,480 + 480 + 80, 5, 5, _vbom);
		
		final FixtureDef fixtureDef = PhysicsFactory.createFixtureDef(1f, 0f, 5f); 
		Body body = PhysicsFactory.createCircleBody(mPhysicsWorld, rect, BodyType.DynamicBody, fixtureDef);
		sprite.setZIndex(4);
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(sprite, body));  
		index--;
		
		if(isGameWin == false)
			_resource.soundWaterDrop.play(); 
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		int id = pMenuItem.getID();
		switch (id) {
		case RESTART_MENU:
			if(isGameWin && isGameOver == false)
				return true; 
			if(_resource.music.isPlaying())
				_resource.music.stop();
			SceneManager.getInstance().loadSceneGameReplay(_engine);
			break;

		default:
			break;
		}
		return false;
	}

	private void testWingame() {
		if(isGameWin == true)
			return;
		nWaterIntoRoom += 1;
		if (nWaterIntoRoom >= 30) { 
			isGameWin = true;
			this.detachChild(_spriteCranky);
			addCranky(_resource.crankyHaveWater_Region);
			Thread threadWin = new ThreadWinGame();
			threadWin.start(); 
			((SmoothCamera)_camera).setCenter(400, 950);
			((SmoothCamera)_camera).setZoomFactor(2.0f); 
		}
	}
	
	class ThreadWinGame extends Thread{ 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {  
				Global.TimePlayGame = System.currentTimeMillis() - timeStart;
				Global.IDScene = 1;
				Global.nDuckyHaveWater = nDuckyHaveWater;
				_resource.soundGameWin.play();
				_resource.soundCrankyLaugh.play();
				if(_resource.music.isPlaying())
					_resource.music.stop();
				Thread.sleep(4500);  
				SceneManager.getInstance().loadScoreScene(_engine); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	@Override
	public BaseScene clone() {
		// TODO Auto-generated method stub
		return new Level01();
	}


	@Override
	public void load() {
		// TODO Auto-generated method stub
		ResourcesManager.getInstance().loadLevel01Screen();
	} 
}