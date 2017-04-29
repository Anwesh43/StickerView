package com.anwesome.ui.stickerview;

import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class AnimationHandler implements ValueAnimator.AnimatorUpdateListener{
    private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
    {{
        startAnim.setDuration(500);
        endAnim.setDuration(500);
        startAnim.addUpdateListener(this);
        endAnim.addUpdateListener(this);
    }}
    private StickerView stickerView;
    public AnimationHandler(StickerView stickerView) {
        this.stickerView = stickerView;
    }
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        
    }
    public void start() {
        startAnim.start();
    }
    public void end() {
        endAnim.start();
    }
}
