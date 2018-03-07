package com.example.wangyulong.campuspass.Adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by wangyulong on 07/03/18.
 */

public class ImageDataBindingAdapter
{
    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource)
    {
        imageView.setImageResource(resource);
    }
}
