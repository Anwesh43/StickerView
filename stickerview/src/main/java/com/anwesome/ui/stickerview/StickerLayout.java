package com.anwesome.ui.stickerview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 29/04/17.
 */
public class StickerLayout {
    private RelativeLayout relativeLayout;
    private Activity activity;
    private List<StickerElement> stickers = new ArrayList<>();
    private StickerView stickerView;
    private int w,h;
    private StickerContainerView stickerContainerView;
    private Bitmap mainBitmap;
    public StickerLayout(Activity activity){
        this.activity = activity;
        initDimension(activity);
    }
    private void initDimension(Activity activity) {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display == null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void setStickers(List<Bitmap> bitmaps) {
        if(relativeLayout == null) {
            for (Bitmap bitmap : bitmaps) {
                stickers.add(new StickerElement(bitmap));
            }
        }
    }
    public void setMainImage(Bitmap mainBitmap) {
        if(relativeLayout == null) {
            this.mainBitmap = mainBitmap;
        }
    }
    public void show() {
        if(relativeLayout == null && mainBitmap!=null && stickers.size() != 0) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            relativeLayout = new RelativeLayout(activity);
            stickerView = new StickerView(activity,mainBitmap);
            stickerContainerView = new StickerContainerView(activity,stickerView,stickers);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if(activity instanceof AppCompatActivity) {
                ActionBar actionBar = ((AppCompatActivity)activity).getSupportActionBar();
                if(actionBar != null) {
                    actionBar.hide();
                }
            }
            else {
                android.app.ActionBar actionBar = activity.getActionBar();
                if(actionBar != null) {
                    actionBar.hide();
                }
            }
            stickerView.setY(0);
            relativeLayout.addView(stickerView,new WindowManager.LayoutParams(w,h));
            relativeLayout.addView(stickerContainerView,new WindowManager.LayoutParams(w,h));
            stickerContainerView.setY((9*h)/10);
            activity.setContentView(relativeLayout);
        }
    }
}
