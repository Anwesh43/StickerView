package com.anwesome.ui.stickerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap currBitmap = null,mainBitmap = null;
    private int w,h;
    private float cbX,cbY;
    private int time = 0;
    public void setCurrBitmap(Bitmap bitmap) {
        this.currBitmap = Bitmap.createScaledBitmap(bitmap,w/6,w/6,true);
        postInvalidate();
    }
    public StickerView(Context context,Bitmap mainBitmap) {
        super(context);
        this.mainBitmap = mainBitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            mainBitmap = Bitmap.createScaledBitmap(mainBitmap,w,h,true);
            cbX = w/2-w/12;
            cbY = h/2-w/12;
        }
        canvas.drawBitmap(mainBitmap,0,0,paint);
        if(currBitmap != null) {
            canvas.drawBitmap(currBitmap,cbX,cbY,paint);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX() , y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
