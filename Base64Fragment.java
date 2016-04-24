package com.qianfeng.encode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Base64Fragment extends Fragment implements View.OnClickListener {


    private EditText content;
    private TextView result;
    private TextView decode_txt;

    public Base64Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base64, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        content = ((EditText) view.findViewById(R.id.base64_content));
        Button encode = (Button) view.findViewById(R.id.base_encode);

        encode.setOnClickListener(this);
        result = ((TextView) view.findViewById(R.id.base64_result));
        Button decode = (Button) view.findViewById(R.id.base_decode);
        decode.setOnClickListener(this);
        decode_txt = ((TextView) view.findViewById(R.id.base64_de_result));
        /**
         *
         *
         * URLCode 编码主要用于文字编码
         */


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_encode:
                String s = content.getText().toString();
                //Base64编码
//                if (!TextUtils.isEmpty(s)){
//                    String encodeToString = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
//                    result.setText(encodeToString);
//                }


                //URLEncoder编码

                if (s != null) {
                    try {
                        String encode = URLEncoder.encode(s, "UTF-8");
                        result.setText(encode);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.base_decode:
                String string = result.getText().toString();
                //Base64 解码
//                if (!TextUtils.isEmpty(string)) {
//                    byte[] decode = Base64.decode(string.getBytes(), Base64.DEFAULT);
//                    String s1 = new String(decode);
//                    decode_txt.setText(s1);
//                }

                //URLDecode 解码
                if (!TextUtils.isEmpty(string)) {
                    try {
                        String decode = URLDecoder.decode(string, "UTF-8");
                        decode_txt.setText(decode);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }



                break;
        }
    }
}
