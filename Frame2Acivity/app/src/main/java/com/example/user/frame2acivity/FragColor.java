package com.example.user.frame2acivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class FragColor extends Fragment {

    @Nullable
    RadioGroup radioGroup;
    OnClickColorChangedListener onClickColorChangedListener;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragcolor,container,false);
        radioGroup = (RadioGroup) view.findViewById(R.id.rgColour);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.red:
                        onClickColorChangedListener.onColorChange("RED");
                        break;
                    case R.id.magenta:
                        onClickColorChangedListener.onColorChange("MAGENTA");
                        break;
                    case R.id.blue:
                        onClickColorChangedListener.onColorChange("BLUE");
                        break;
                    case R.id.CYAN:
                        onClickColorChangedListener.onColorChange("CYAN");
                        break;
                }
            }
        });
        return view;
    }
    interface OnClickColorChangedListener{
        public void onColorChange(String color);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onClickColorChangedListener = (OnClickColorChangedListener) activity;
    }
}
