package com.group14.wheresmywater;

import static org.andengine.extension.physics.box2d.util.constants.PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

import java.util.Vector;

import org.andengine.engine.Engine;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Polygon;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.seisw.util.geom.Poly;
import com.seisw.util.geom.PolyDefault;

public class RockArea {
//  private Poly mBasePoly;

//	private Color mLineColor;
//	private float mLineWidth;
	private Vector<Body> mCurrPolygonBodies;
//	private Vector<Line> mCurrLines;
	private float[] pVertexX0;
	private float[] pVertexY0;
	private int numPoints0;
	
	private float[] pVertexX1;
	private float[] pVertexY1;
	private int numPoints1;
	
	private float[] pVertexX2;
	private float[] pVertexY2;
	private int numPoints2;
	private Scene mScene;
	private VertexBufferObjectManager mVertexBuffObjManager;
	private Engine mEngine;
	private PhysicsWorld mPhysicsWorld;
	
	private final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(0f, 0f, 0.5f);
	public RockArea() {

		
		mCurrPolygonBodies = new Vector<Body>();
		
		
		
		
		/*
		 * Tao body cho mang da thu nhat
		 */
		numPoints0 = 31;		
		pVertexX0 = new float[numPoints0];
		pVertexY0 = new float[numPoints0];
		
		//Tao body
		pVertexX0[0] = 0;
		pVertexY0[0] = 306;
		
		pVertexX0[1] = 49;
		pVertexY0[1] = 309;
		
		pVertexX0[2] = 64;
		pVertexY0[2] = 320;
		
		pVertexX0[3] = 64;
		pVertexY0[3] = 390;
		
		pVertexX0[4] = 148;
		pVertexY0[4] = 393;
		
//		163, 407
		pVertexX0[5] = 168;
		pVertexY0[5] = 407;
		
//		164, 454
		pVertexX0[6] = 164;
		pVertexY0[6] = 454;
		
//		377, 454
		pVertexX0[7] = 377;
		pVertexY0[7] = 457;
		
//		388, 464
		pVertexX0[8] = 388;
		pVertexY0[8] = 464;
		
//		458, 467
		pVertexX0[9] = 458;
		pVertexY0[9] = 467;
		
//		470, 477
		pVertexX0[10] = 470;
		pVertexY0[10] = 477;
		
//		506, 476
		pVertexX0[11] = 506;
		pVertexY0[11] = 480;
		
//		519, 487
		pVertexX0[12] = 519;
		pVertexY0[12] = 487;
		
//		519, 494
		pVertexX0[13] = 519;
		pVertexY0[13] = 494;
		
//		534, 494
		pVertexX0[14] = 534;
		pVertexY0[14] = 497;
		
//		547, 506
		pVertexX0[15] = 547;
		pVertexY0[15] = 506;
		
//		547, 531
		pVertexX0[16] = 547;
		pVertexY0[16] = 531;
		
//		551, 534
		pVertexX0[17] = 551;
		pVertexY0[17] = 534;
		
//		551, 584
		pVertexX0[18] = 551;
		pVertexY0[18] = 584;
		
//		537, 599
		pVertexX0[19] = 537;
		pVertexY0[19] = 599;
		
//		219, 599
		pVertexX0[20] = 219;
		pVertexY0[20] = 599;
		
//		211, 606
		pVertexX0[21] = 211;
		pVertexY0[21] = 606;
		
//		211, 627
		pVertexX0[22] = 211;
		pVertexY0[22] = 627;
		
//		199, 641
		pVertexX0[23] = 199;
		pVertexY0[23] = 641;
		
//		111, 641
		pVertexX0[24] = 111;
		pVertexY0[24] = 641;
		
//		103, 649
		pVertexX0[25] = 103;
		pVertexY0[25] = 649;
		
//		103, 680
		pVertexX0[26] = 103;
		pVertexY0[26] = 680;
		
//		088, 695
		pVertexX0[27] = 88;
		pVertexY0[27] = 695;
		
//		006, 695
		pVertexX0[28] = 6;
		pVertexY0[28] = 695;
		
//		6, 732
		pVertexX0[29] = 6;
		pVertexY0[29] = 732;
		
//		0, 740
		pVertexX0[30] = 0;
		pVertexY0[30] = 740;
		
		/*
		 * Tao body thu hai
		 */
		numPoints1 = 30;		
		pVertexX1 = new float[numPoints1];
		pVertexY1 = new float[numPoints1];
		
		
		/**/
//		799, 562
		pVertexX1[0] = 799;
		pVertexY1[0] = 562;
		
//		793, 571
		pVertexX1[1] = 793;
		pVertexY1[1] = 571;
		
//		722, 571
		pVertexX1[2] = 722;
		pVertexY1[2] = 574;
		
//		708, 587
		pVertexX1[3] = 708;
		pVertexY1[3] = 587;
		
//		708, 631
		pVertexX1[4] = 708;
		pVertexY1[4] = 631;
		
//		654, 636
		pVertexX1[5] = 654;
		pVertexY1[5] = 636;
		
//		642, 645
		pVertexX1[6] = 642;
		pVertexY1[6] = 645;
		
//		584, 646
		pVertexX1[7] = 584;
		pVertexY1[7] = 648;
		
//		571, 657
		pVertexX1[8] = 571;
		pVertexY1[8] = 657;
		
//		493, 659
		pVertexX1[9] = 493;
		pVertexY1[9] = 660;
		
//		482, 668
		pVertexX1[10] = 482;
		pVertexY1[10] = 668;
		
//		413, 668
		pVertexX1[11] = 413;
		pVertexY1[11] = 671;
		
//		403, 677
		pVertexX1[12] = 403;
		pVertexY1[12] = 677;
		
//		374, 677
		pVertexX1[13] = 374;
		pVertexY1[13] = 680;
		
//		360, 689
		pVertexX1[14] = 360;
		pVertexY1[14] = 689;
		
//		323, 690
		pVertexX1[15] = 323;
		pVertexY1[15] = 692;
		
		
		
		//doc dung
//		307, 705
		pVertexX1[16] = 307;
		pVertexY1[16] = 705;
		
//		307, 786
		pVertexX1[17] = 307;
		pVertexY1[17] = 786;
		
//		321, 800
		pVertexX1[18] = 321;
		pVertexY1[18] = 800;
		
//		677, 800
		pVertexX1[19] = 677;
		pVertexY1[19] = 800;
		
//		686, 806
		pVertexX1[20] = 686;
		pVertexY1[20] = 806;
		
//		686, 830
		pVertexX1[21] = 686;
		pVertexY1[21] = 830;
		
//		701, 844
		pVertexX1[22] = 701;
		pVertexY1[22] = 844;
		
//		740, 844
		pVertexX1[23] = 740;
		pVertexY1[23] = 844;
		
//		746, 851
		pVertexX1[24] = 746;
		pVertexY1[24] = 851;
		
//		746, 914
		pVertexX1[25] = 746;
		pVertexY1[25] = 914;
		
//		761, 929
		pVertexX1[26] = 761;
		pVertexY1[26] = 929;
		
//		791, 929
		pVertexX1[27] = 791;
		pVertexY1[27] = 929;
		
//		791, 967
		pVertexX1[28] = 791;
		pVertexY1[28] = 967;
		
//		799, 974
		pVertexX1[29] = 799;
		pVertexY1[29] = 974;
		
		
		
		/*
		 * Body thu 3
		 */
		numPoints2 = 26;		
		pVertexX2 = new float[numPoints2];
		pVertexY2 = new float[numPoints2];
		
		//Body thu 3
		
//		0, 761
		pVertexX2[0] = 0;
		pVertexY2[0] = 761;
		
//		23, 761
		pVertexX2[1] = 23;
		pVertexY2[1] = 764;
		
//		33, 769
		pVertexX2[2] = 33;
		pVertexY2[2] = 769;
		
//		33, 822
		pVertexX2[3] = 33;
		pVertexY2[3] = 822;
		
//		39, 827
		pVertexX2[4] = 39;
		pVertexY2[4] = 827;
		
//		117, 827
		pVertexX2[5] = 117;
		pVertexY2[5] = 830;
		
//		131, 852
		pVertexX2[6] = 131;
		pVertexY2[6] = 852;
		
//		131, 864
		pVertexX2[7] = 131;
		pVertexY2[7] = 864;
		
//		137, 870
		pVertexX2[8] = 137;
		pVertexY2[8] = 870;
		
//		354, 870
		pVertexX2[9] = 354;
		pVertexY2[9] = 873;
		
//		359, 874
		pVertexX2[10] = 359;
		pVertexY2[10] = 874;
		
//		359, 882
		pVertexX2[11] = 359;
		pVertexY2[11] = 882;
		
//		377, 900
		pVertexX2[12] = 377;
		pVertexY2[12] = 900;
		
//		377, 923
		pVertexX2[13] = 377;
		pVertexY2[13] = 923;
		
//		382, 927
		pVertexX2[14] = 382;
		pVertexY2[14] = 927;
		
//		383, 1013
		pVertexX2[15] = 383;
		pVertexY2[15] = 1013;
		
//		389, 1020
		pVertexX2[16] = 389;
		pVertexY2[16] = 1020;
		
//		408, 1020
//		pVertexX2[17] = 408;
//		pVertexY2[17] = 1023;
				
//		412, 1016
//		pVertexX2[18] = 412;
//		pVertexY2[18] = 1016;
//		
////		454, 1016
//		pVertexX2[19] = 454;
//		pVertexY2[19] = 1016;
//		
////		458, 1019
//		pVertexX2[20] = 458;
//		pVertexY2[20] = 1019;
//		
//		475, 1019
		pVertexX2[17] = 475;
		pVertexY2[17] = 1023;
		
//		492, 1035
		pVertexX2[18] = 492;
		pVertexY2[18] = 1035;
		
//		492, 1125
		pVertexX2[19] = 493;
		pVertexY2[19] = 1125;
		
//		566, 1125
		pVertexX2[20] = 566;
		pVertexY2[20] = 1128;
		
//		582, 1142
		pVertexX2[21] = 582;
		pVertexY2[21] = 1142;
		
//		582, 1272
		pVertexX2[22] = 582;
		pVertexY2[22] = 1272;
		
//		660, 1272
		pVertexX2[23] = 660;
		pVertexY2[23] = 1275;
		
//		666, 1279
		pVertexX2[24] = 666;
		pVertexY2[24] = 1279;
		
//		0, 1279
		pVertexX2[25] = 0;
		pVertexY2[25] = 1279;
	}
	
	
	
	
	private void generateBodies(int numPoints, float[] pVertexX, float[] pVertexY) {
		Vector2[] vertices = new Vector2[numPoints];
		
		for (int i = 0; i < numPoints; i++)
			vertices[i] = new Vector2(
					(pVertexX[i]) / PIXEL_TO_METER_RATIO_DEFAULT,
					(pVertexY[i]) / PIXEL_TO_METER_RATIO_DEFAULT);
		
		
		// vÃ¬ táº¡o polygon body chá»‰ tá»‘i Ä‘a 8 Ä‘á»‰nh nÃªn pháº£i dÃ¹ng Ä‘Æ°á»�ng tháº³ng
		
		for (int i = 0; i < numPoints - 1; i++) {
			Body lineBody = PhysicsFactory.createEdgeBody(
					mPhysicsWorld,
					vertices[i],
					vertices[i+1],
					BodyType.StaticBody,
					FIXTURE_DEF,
					PIXEL_TO_METER_RATIO_DEFAULT);
			
			mCurrPolygonBodies.add(lineBody);
		}
		
		if (numPoints > 1) {
			Body lineBody = PhysicsFactory.createEdgeBody(
					mPhysicsWorld,
					vertices[0],
					vertices[numPoints - 1],
					BodyType.StaticBody,
					FIXTURE_DEF,
					PIXEL_TO_METER_RATIO_DEFAULT);
			
			//mCurrPolygonBodies.add(lineBody);
		}
	}
	public void attachToScene(Scene scene, Engine engine, PhysicsWorld physicsWorld, VertexBufferObjectManager vertexBuffObjMngr) {
		mScene = scene;
		mEngine = engine;
		mPhysicsWorld = physicsWorld;
		mVertexBuffObjManager = vertexBuffObjMngr;
		generateBodies(numPoints0, pVertexX0, pVertexY0);
		generateBodies(numPoints1, pVertexX1, pVertexY1);
		generateBodies(numPoints2, pVertexX2, pVertexY2);
	}
}
