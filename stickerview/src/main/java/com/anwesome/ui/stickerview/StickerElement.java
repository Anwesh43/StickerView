package com.anwesome.ui.stickerview;

import android.graphics.*;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerElement {
    private Bitmap bitmap;
    private float x,y,size;
    public OnTapListener onTapListener;
    public StickerElement(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }
    public void setDimension(float x,float y,float size ){
        this.x = x;
        this.y = y;
        this.size = size;
        this.bitmap = Bitmap.createScaledBitmap(bitmap,(int)size,(int)size,true);
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.restore();
    }
    public boolean handleTap(float x,float y) {
        boolean condtion =  x>=this.x && x<=this.x+size && y>=this.y && y<=this.y+size;
        if(condtion && onTapListener != null) {
            onTapListener.onTap();
        }
        return condtion;
    }
    public int hashCode() {
        return bitmap.hashCode()+(int)(x+y);
    }
    public interface OnTapListener {
        void onTap();
    }
}
