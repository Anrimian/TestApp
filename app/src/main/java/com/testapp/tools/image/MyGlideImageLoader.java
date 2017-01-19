package com.testapp.tools.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

/**
 * Created on 18.01.2017.
 */

public class MyGlideImageLoader {

    private static final int PREVIEW_IMAGE_HEIGHT = 350;

    public static void displayImage(ImageView imageView,String imageLink) {
        imageView.setMinimumHeight(PREVIEW_IMAGE_HEIGHT);
        Glide.with(imageView.getContext())
                .load(imageLink)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(imageView);
    }
}
