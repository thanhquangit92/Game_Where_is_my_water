package com.group14.wheresmywater;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Radio extends Sprite{
	float secondTimeTolal = 0;
	float secondLastRotate = 0;
	float delta = 0.6f;
	float angle = 0;
	
	public Radio(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager); 
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) { 
		secondTimeTolal += pSecondsElapsed;
		int t = (int) ((secondTimeTolal - secondLastRotate) / delta);
		if (t > 0) { 
			secondLastRotate = secondTimeTolal;
			angle = (angle == 0) ? 7 : 0;
		}
		this.setRotation(angle);
		super.onManagedUpdate(pSecondsElapsed);
	}
}
