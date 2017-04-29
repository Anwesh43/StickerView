package com.anwesome.ui.stickerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mainBitmap = null;
    private int w,h;
    private float cbX,cbY;
    private int time = 0;
    private ConcurrentLinkedQueue<StickerElement> stickerElements = new ConcurrentLinkedQueue<>();
    private StickerMotionHandler stickerMotionHandler = new StickerMotionHandler();
    public void setCurrSticker(StickerElement sticker) {
        StickerElement newSticker = new StickerElement(sticker.getBitmap());
        newSticker.setDimension(cbX,cbY,w/6);
        stickerElements.add(newSticker);
        stickerMotionHandler.setSticker(newSticker);
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
        for(StickerElement sticker:stickerElements) {
            sticker.draw(canvas,paint);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        stickerMotionHandler.handleMotion(this,event);
        return true;
    }
}
