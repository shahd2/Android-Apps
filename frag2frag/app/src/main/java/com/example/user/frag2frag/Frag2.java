package com.example.user.frag2frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Rakesh on 6/28/2017.
 */

public class Frag2 extends Fragment {
    @Nullable
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.frag2,container,false);
        textView = (TextView) view1.findViewById(R.id.textView);
        return view1;
    }
    public void textUpdate(String message){
        textView.setText(message);
    }
}
