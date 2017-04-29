package com.anwesome.ui.stickerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerContainerView extends View {
    private int time = 0,h,initH;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private StickerView stickerView;
    private StickerContainerButton stickerContainerButton;
    private AnimationHandler animationHandler;
    private List<StickerElement> stickers = new ArrayList<>();
    public StickerContainerView(Context context,StickerView stickerView,List<StickerElement> stickers) {
        super(context);
        this.stickerView = stickerView;
        this.stickers = stickers;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            int w = canvas.getWidth();
            h = canvas.getHeight();
            initH = h/10;
            animationHandler = new AnimationHandler(this);
            stickerContainerButton = new StickerContainerButton(w/2,h/20,h/20);
            int gap = w/7,x = 3*gap/2,y = 3*gap/2,i = 0;
            for(StickerElement sticker:stickers) {
                final StickerElement currSticker = sticker;
                sticker.setDimension(x,y,gap);
                i++;
                x+=2*gap;
                if(i == 3) {
                    x = 3*gap/2;
                    y += 2*gap;
                    i = 0;
                }
                sticker.setOnTapListener(new StickerElement.OnTapListener() {
                    @Override
                    public void onTap() {
                        animationHandler.end(new AnimationHandler.OnEndListener() {
                            @Override
                            public void onEnd() {
                                stickerView.setCurrSticker(currSticker);
                            }
                        });
                    }
                });
            }
        }
        for(StickerElement sticker:stickers) {
            sticker.draw(canvas,paint);
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
