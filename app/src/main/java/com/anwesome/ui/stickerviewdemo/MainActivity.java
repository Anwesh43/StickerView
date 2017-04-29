package com.anwesome.ui.stickerviewdemo;

import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.stickerview.StickerLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Bitmap> bitmaps = new ArrayList<>();
    private int images[] = {R.drawable.sml1,R.drawable.sml2,R.drawable.sml3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StickerLayout stickerLayout = new StickerLayout(this);
        if(bitmaps.size() == 0) {
            for(int image:images) {
                bitmaps.add(BitmapFactory.decodeResource(getResources(),image));
            }
        }
        stickerLayout.setStickers(bitmaps);
        stickerLayout.setMainImage(BitmapFactory.decodeResource(getResources(),R.drawable.back3));
        stickerLayout.show();
    }
}
