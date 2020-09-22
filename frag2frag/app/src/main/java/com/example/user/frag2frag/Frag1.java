package com.example.user.frag2frag;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class Frag1 extends Fragment{
    @Nullable
    EditText editText;
    Button button;
    OnTextSendListener onTextSendListener;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1,container,false);
        editText = (EditText) view.findViewById(R.id.editText);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                onTextSendListener.onTextSend(name);
            }
        });

        return view;
    }
    interface OnTextSendListener{
        public void onTextSend(String msg);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onTextSendListener = (OnTextSendListener) activity;
    }
}
