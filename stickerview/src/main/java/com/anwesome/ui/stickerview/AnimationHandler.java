package com.anwesome.ui.stickerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
    private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
    {{
        startAnim.setDuration(500);
        endAnim.setDuration(500);
        startAnim.addUpdateListener(this);
        endAnim.addUpdateListener(this);
    }}
    private StickerContainerView stickerContainerView;
    public AnimationHandler(StickerContainerView stickerContainerView) {
        this.stickerContainerView = stickerContainerView;
    }
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        stickerContainerView.update((float)(valueAnimator.getAnimatedValue()));
    }
    public void start() {
        startAnim.start();
    }
    public void end(OnEndListener onEndListener) {
        endAnim.start();
        this.onEndListener = onEndListener;
    }
    public void onAnimationEnd(Animator animator) {
        if(this.onEndListener != null) {
            onEndListener.onEnd();
        }
    }
    private OnEndListener onEndListener;

    public interface OnEndListener {
        void onEnd();
    }
}
