//package com.xdpm14.whereismywater;
//
//import org.andengine.engine.Engine;
//import org.andengine.entity.scene.Scene;
//import org.andengine.entity.sprite.Sprite;
//import org.andengine.entity.text.Text;
//import org.andengine.opengl.font.Font;
//import org.andengine.opengl.font.FontFactory;
//import org.andengine.opengl.texture.TextureOptions;
//import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
//import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
//import org.andengine.opengl.texture.region.TextureRegion;
//import org.andengine.util.HorizontalAlign;
//import org.andengine.util.color.Color;
//
//import android.content.Context;
//
//public class Tile extends Sprite {
//    private static BitmapTextureAtlas mBitmapTextureAtlas;
//    private static TextureRegion mBoxTextureRegion;
//    private static BitmapTextureAtlas mFontTexture;
//    private static Font mFont;
//    String letter;
//    private Text mText;
//
//    public Tile(String letter, int score, float x, float y, float width,
//                    float height) {
//            super(x, y, width, height, mBoxTextureRegion);
//            this.letter = letter;
//    }
//
//    public static void loadTileResources(Context context, Engine mEngine) {
//            Tile.mFontTexture = new BitmapTextureAtlas(128, 128,
//                            TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//            Tile.mFont = FontFactory.createFromAsset(Tile.mFontTexture, context,
//                            "Arial Rounded MT.ttf", 32, true, Color.WHITE);
//
//            mEngine.getTextureManager().loadTexture(Tile.mFontTexture);
//            mEngine.getFontManager().loadFont(Tile.mFont);
//
//            Tile.mBitmapTextureAtlas = new BitmapTextureAtlas(128, 128,
//                            TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//
//            Tile.mBoxTextureRegion = BitmapTextureAtlasTextureRegionFactory
//                            .createFromAsset(Tile.mBitmapTextureAtlas, context,
//                                            "rect3761.png", 0, 0);
//
//            mEngine.getTextureManager().loadTexture(Tile.mBitmapTextureAtlas);
//
//    }
//
//    public void loadTileScene(Scene scene) {
//            if (letter != null) {
//                    this.mText = new Text(50, 50, Tile.mFont, letter,
//                                    HorizontalAlign.CENTER);
//                    this.attachChild(this.mText);
//                    scene.attachChild(this);
//
//            }
//    }
//
//}
