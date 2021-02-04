package de.slgaachen.firstlittleapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;

public class RandomNameArrayAdapter extends ArrayAdapter<RandomName> {

    private Context mContext;
    private List<RandomName> mRandomNameList;
    private LayoutInflater mLayoutInflater;

    private Resources mResources;
    private String mPackagename;
    private Map<String, Drawable> mRandomNameImageDrawables = new HashMap<>();

    public RandomNameArrayAdapter(Context context, List<RandomName> randomNameList){
        super(context, R.layout.list_row, randomNameList);

        mContext = context;
        mRandomNameList = randomNameList;
        mLayoutInflater = LayoutInflater.from(context);

        mResources = context.getResources();
        mPackagename = context.getPackageName();
        createRandomNameImageDrawables();
    }

    private void createRandomNameImageDrawables(){
        int imageId;
        String[] randomNameImages = mResources.getStringArray(R.array.randomName_array);

        for(String image: randomNameImages){
            imageId = mResources.getIdentifier(image, "drawable", mPackagename);
            if (imageId > 0){
                mRandomNameImageDrawables.put(image, ContextCompat.getDrawable(mContext, imageId));
            }
        }
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent){
        View rowView;
        if (converView == null){
            rowView = mLayoutInflater.inflate(R.layout.list_row, parent, false);
        } else {
            rowView = converView;
        }

        RandomName currentRandomName = mRandomNameList.get(position);

        TextView tvRandomNameText = rowView.findViewById(R.id.randomName_TextView);
        ImageView ivRandomNameImage = rowView.findViewById(R.id.randomName_ImageView);

        tvRandomNameText.setText(currentRandomName.getRandomName());

        Drawable randomNameImageDrawable = mRandomNameImageDrawables.get(currentRandomName.getImageId());
        if (randomNameImageDrawable != null){
            ivRandomNameImage.setImageDrawable(randomNameImageDrawable);
        }

        return rowView;
    }
}
