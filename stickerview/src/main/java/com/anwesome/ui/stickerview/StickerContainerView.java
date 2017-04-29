package com.anwesome.ui.stickerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerContainerView extends View {
    private int time = 0,h,initH;
    private StickerView stickerView;
    private StickerContainerButton stickerContainerButton;
    private AnimationHandler animationHandler;
    public StickerContainerView(Context context,StickerView stickerView) {
        super(context);
        this.stickerView = stickerView;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            int w = canvas.getWidth();
            h = canvas.getHeight();
            initH = h/10;
            animationHandler = new AnimationHandler(this);
            stickerContainerButton = new StickerContainerButton(w/2,h/20,h/20);
        }
        time++;
    }
    public void update(float factor) {
        setY(h/10+(9*h/10)*factor);
        stickerContainerButton.update(factor);

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class StickerContainerButton {
        private float x,y,size;
        private int dir = 1;
        private float deg = 0;
        public void update(float factor) {
            deg = 180*factor;
        }
        public StickerContainerButton(float x,float y,float size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
        public void draw(Canvas canvas, Paint paint) {
            paint.setColor(Color.WHITE);
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.scale((1-2*i),1);
                Path path = new Path();
                path.moveTo(0, 0);
                path.lineTo(size,size/2);
                canvas.drawPath(path,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y && y<=this.y+size/2;
            if(condition && animationHandler!=null) {
                if(dir == 1) {
                    animationHandler.start();
                }
                else {
                    animationHandler.end(null);
                }
                dir *= -1;
            }
            return condition;
        }
    }
}
