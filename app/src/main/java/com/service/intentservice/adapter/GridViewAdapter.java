package com.service.intentservice.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.service.intentservice.R;
import com.service.intentservice.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by CIPL0233 on 9/29/2016.
 */

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<String> filePathList;
    private Context mContext;

    public GridViewAdapter(ArrayList<String> fileList, Context context) {
        this.filePathList = fileList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return filePathList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.girdview, null);

        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.dynamic_image);
            Bitmap bitmap = BitmapFactory.decodeFile(filePathList.get(position));
            imageView.setImageBitmap(bitmap);
        }


        return view;
    }
}
