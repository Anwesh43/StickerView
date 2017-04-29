package com.anwesome.ui.stickerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerView extends View {
    private Bitmap currBitmap = null;
    public void setCurrBitmap(Bitmap bitmap) {
        this.currBitmap = bitmap;
        postInvalidate();
    }
    public StickerView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
