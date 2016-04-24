package com.qianfeng.encode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A simple {@link Fragment} subclass.
 */
public class MD5Fragment extends Fragment implements View.OnClickListener {


    private EditText et;
    private TextView textView;

    public MD5Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_md5, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et = ((EditText) view.findViewById(R.id.md5_et));
        Button button = (Button) view.findViewById(R.id.md5_btn);
        textView = ((TextView) view.findViewById(R.id.md5_result));

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String string = et.getText().toString();
        if (!TextUtils.isEmpty(string)){

            try {
                MessageDigest digest =  MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(string.getBytes());
                /**
                 * 算法处理过的byte数组不能直接转String
                 *
                 *
                 */

                //String s = new String(bytes);
                StringBuilder s = new StringBuilder();
                for (byte b: bytes) {
                    //对byte里面的特殊字符过滤
                    s.append(String.format("%02x",b&0xff));
                }
                textView.setText(s);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(),"请输入内容",Toast.LENGTH_SHORT).show();
        }
    }
}
