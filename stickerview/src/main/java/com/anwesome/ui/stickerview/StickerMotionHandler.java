package com.anwesome.ui.stickerview;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerMotionHandler {
    private StickerElement sticker;
    private boolean isDown = false;
    public void setSticker(StickerElement sticker) {
        this.sticker = sticker;
        sticker.setOnTapListener(new StickerElement.OnTapListener() {
            @Override
            public void onTap() {
                if(!isDown) {
                    isDown = true;
                }
            }
        });
    }
    public void handleMotion(View view, MotionEvent event) {
        float x = event.getX() , y = event.getY(),size = sticker.getSize(),x1 = sticker.getX(),y1 = sticker.getY();
        if(sticker!=null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(isDown) {
                        sticker.setXY(event.getX(),event.getY());
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if(!isDown) {
                        isDown = true;
                    }
                    break;
            }
            view.postInvalidate();
        }
    }
}
