package com.group14.wheresmywater;

import static org.andengine.extension.physics.box2d.util.constants.PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

import java.util.Vector;

import org.andengine.engine.Engine;
import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.seisw.util.geom.Poly;
import com.seisw.util.geom.PolyDefault;

public class SoilArea {
	private Poly mBasePoly; 
	private float mPositionX;
	private float mPositionY;

	private Color mFillColor;
	private Color mHolePolyColor;
	private Color mLineColor;
	private float mLineWidth;

	private boolean mTouchable;

	private int mTouchRadius;
	private Vector<Polygon> mCurrPolygons;
	private Vector<Body> mCurrPolygonBodies;

	private Scene mScene;
	private VertexBufferObjectManager mVertexBuffObjManager;
	private Engine mEngine;
	private PhysicsWorld mPhysicsWorld;

	private final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(0f, 0f, 0.0f);

	public float getX() {
		return mPositionX;
	}

	public float getY() {
		return mPositionY;
	}

	public void setPosition(float pX, float pY) {
		mPositionX = pX;
		mPositionY = pY;
	}

	public boolean touchable() {
		return mTouchable;
	}

	public void setTouchable(boolean touchable) {
		mTouchable = touchable;
	}

	public Color getFillColor() {
		return mFillColor;
	}

	public void setFillColor(Color fillColor) {
		mFillColor = fillColor;
	}

	public Color getLineColor() {
		return mLineColor;
	}

	public void setLineColor(Color lineColor) {
		mLineColor = lineColor;
	}

	public float getLineWidth() {
		return mLineWidth;
	}

	public void setLineWidth(float lineWidth) {
		mLineWidth = lineWidth;
	}

	public void setTouchRadius(int touchRadius) {
		if (touchRadius <= 0)
			touchRadius = 0;

		mTouchRadius = touchRadius;
	}

	public int getTouchRadius() {
		return mTouchRadius;
	}

	// ================================================
	// Constructor
	// ================================================

	public SoilArea() {
		mBasePoly = new PolyDefault();

		mFillColor = new Color(208 / 255f, 105 / 255f, 21 / 255f);
		mHolePolyColor = Color.WHITE;
		mLineColor = new Color(114 / 255f, 60 / 255f, 16 / 255f);

		mTouchable = true;

		mTouchRadius = 40;
		mLineWidth = 3.5f;

		mCurrPolygons = new Vector<Polygon>();
		mCurrPolygonBodies = new Vector<Body>(); 
	}

	public void addPoint(float pX, float pY) {
		mBasePoly.add(pX, pY);
		attachAllPolygonsToScene(mBasePoly);
	}

	public synchronized void touch(float touchPositionX, float touchPositionY) {
		if (mTouchable == true) {
			Poly touchPoly = createTouchPoly(touchPositionX - mPositionX,
					touchPositionY - mPositionY, mTouchRadius);
			// TODO: xá»­ lÃ½ pháº§n Ä‘á»¥ng khÃ´ng trÃºng?
			difference(touchPoly);
		}
	}

	public void difference(Poly poly) {
		if (mBasePoly.isEmpty() == false) {
			mBasePoly = mBasePoly.difference(poly);
			bChange = true;
		}
	}

	Boolean bChange = false;

	public void Update() {
		if (bChange) {
			attachAllPolygonsToScene(mBasePoly);
			bChange = false;
		} 
	}

	public void attachToScene(Scene scene, Engine engine,
			PhysicsWorld physicsWorld,
			VertexBufferObjectManager vertexBuffObjMngr) {
		mScene = scene;
		mEngine = engine;
		mPhysicsWorld = physicsWorld;
		mVertexBuffObjManager = vertexBuffObjMngr;

		// Láº¥y tá»�a Ä‘á»™ cÃ¡c Ä‘á»‰nh
		int numPoints = mBasePoly.getNumPoints();

		if (numPoints < 3) {
			return;
		}

		float[] pVertexX = new float[numPoints];
		float[] pVertexY = new float[numPoints];

		for (int i = 0; i < numPoints; i++) {
			pVertexX[i] = (float) mBasePoly.getX(i);
			pVertexY[i] = (float) mBasePoly.getY(i);
		}

		attachAllPolygonsToScene(mBasePoly); 
	}

	private void attachAllPolygonsToScene(Poly poly) {
		if ((mScene == null) || (mVertexBuffObjManager == null)
				|| (mEngine == null)) {
			return;
		} 

		// xÃ³a nhá»¯ng polygon cÅ© ra khá»�i mScene
		detachAllPolygonsInScene(); 

		if(poly == null || poly.isEmpty())
			return;
		
		// thÃªm nhá»¯ng polygon má»›i vÃ o mScene
		int numInnerPoly = poly.getNumInnerPoly();

		for (int i = 0; i < numInnerPoly; i++) {
			attachPolygonToScene(poly.getInnerPoly(i));
		}
	}

	private void attachPolygonToScene(Poly polyToAttach) {
		if ((polyToAttach == null) || (mScene == null)) {
			return;
		}

		// Láº¥y tá»�a Ä‘á»™ cÃ¡c Ä‘á»‰nh
		int numPoints = polyToAttach.getNumPoints();

		if (numPoints < 3) {
			return;
		}

		float[] pVertexX = new float[numPoints];
		float[] pVertexY = new float[numPoints];

		for (int i = 0; i < numPoints; i++) {
			pVertexX[i] = (float) polyToAttach.getX(i);
			pVertexY[i] = (float) polyToAttach.getY(i);
		}

		// Táº¡o polygon Ä‘á»ƒ thÃªm vÃ o mScene
		Polygon polygonToAttach = new Polygon(mPositionX, mPositionY, pVertexX,
				pVertexY, mVertexBuffObjManager);

		if (polyToAttach.isHole())
			polygonToAttach.setColor(mHolePolyColor);
		else
			polygonToAttach.setColor(mFillColor);

		polygonToAttach.setZIndex(1);  
		mCurrPolygons.add(polygonToAttach); // lÆ°u láº¡i Ä‘á»ƒ sau nÃ y detach
		mScene.attachChild(polygonToAttach); 
		// Táº¡o body
		if (polyToAttach.isHole() == false) {
			generateBodies(numPoints, pVertexX, pVertexY);
			//generateLines(numPoints, pVertexX, pVertexY);
		}
	}

	private void generateBodies(int numPoints, float[] pVertexX,
			float[] pVertexY) {
		Vector2[] vertices = new Vector2[numPoints];

		for (int i = 0; i < numPoints; i++) {
			vertices[i] = new Vector2((pVertexX[i] + mPositionX)
					/ PIXEL_TO_METER_RATIO_DEFAULT, (pVertexY[i] + mPositionY - 20)
					/ PIXEL_TO_METER_RATIO_DEFAULT);
		}

		Body body = PhysicsFactory.createChainBody(mPhysicsWorld, vertices,
				BodyType.StaticBody, FIXTURE_DEF, PIXEL_TO_METER_RATIO_DEFAULT);
		mCurrPolygonBodies.add(body);

		if (numPoints > 1) {
			Body lineBody = PhysicsFactory.createEdgeBody(mPhysicsWorld,
					vertices[0], vertices[numPoints - 1], BodyType.StaticBody,
					FIXTURE_DEF, PIXEL_TO_METER_RATIO_DEFAULT);

			mCurrPolygonBodies.add(lineBody);
		}
	}

	public void detachAllPolygonsInScene() {
		if ((mEngine == null) || (mScene == null)) {
			return;
		}

		final EngineLock engineLock = mEngine.getEngineLock();
		engineLock.lock();

		// xÃ³a body
		for (Body polygonBody : mCurrPolygonBodies) {
			polygonBody.setActive(false); // safe remove
			mPhysicsWorld.destroyBody(polygonBody);
		}
		mCurrPolygonBodies.clear();

		// xÃ³a polygon
		for (Polygon attachedPoly : mCurrPolygons) {
			mScene.detachChild(attachedPoly); 
		} 
		mCurrPolygons.clear(); 

		engineLock.unlock();
	}

	public static Poly createTouchPoly(float touchPositionX,
			float touchPositionY, float touchRadius) {
		Poly touchPoly = new PolyDefault(true); // isHole = true

		float x = touchPositionX;
		float y = touchPositionY;
		float r = touchRadius;

		touchPoly.add(x - r, y);
		touchPoly.add(x - r * 0.866, y - r * 0.5);
		touchPoly.add(x - r * 0.5, y - r * 0.866);
		touchPoly.add(x, y - r);
		touchPoly.add(x + r * 0.5, y - r * 0.866);
		touchPoly.add(x + r * 0.866, y - r * 0.5);
		touchPoly.add(x + r, y);
		touchPoly.add(x + r * 0.866, y + r * 0.5);
		touchPoly.add(x + r * 0.5, y + r * 0.866);
		touchPoly.add(x, y + r);
		touchPoly.add(x - r * 0.5, y + r * 0.866);
		touchPoly.add(x - r * 0.866, y + r * 0.5);

		return touchPoly;
	}
}