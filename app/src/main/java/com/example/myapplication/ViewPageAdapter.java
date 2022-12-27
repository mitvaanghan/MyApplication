package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPageAdapter extends PagerAdapter {

    Context context;
    int images[]={
      R.drawable.choosedestination,
      R.drawable.fastdelivery,
      R.drawable.payments
    };

    int heading[] = {
      R.string.heading_0ne,
      R.string.heading_two,
      R.string.heading_Three
    };

    int description[] = {
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three
    };

    public  ViewPageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {

        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public  Object instantiateItem(@NonNull ViewGroup container , int position){
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.titleimage);
        TextView slideheading = (TextView) view.findViewById(R.id.texttitle);



    }
}
