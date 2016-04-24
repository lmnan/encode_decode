package com.qianfeng.encode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


/**
 * A simple {@link Fragment} subclass.
 */
public class DESFragment extends Fragment implements View.OnClickListener {


    private EditText content;
    private TextView txt_encode;
    private TextView txt_deocde;

    private static final String privateKey = "13289231305895920850759621609516803792672616030773748226361430996947051152483017449144521331706386440609786491905811654851817719020610636593971109796298273178376476974468442724954071289959337858761365053935643183532212159956303857667155214467182262526117461341270682353500931373140197649762490795810708827541189842451870114055157843207878070142025039891416634823515109084217902821223984718606491378435223312504140169905041790629161129485579428171197024453197347008246900882517743217026119452265133163785953529928109017524437789696113819791121287891589058281817960279884253154499477421849579163511611418339298216669441";

    private static final String publicKey = "65537";

    private static final String mod = "24541714159561005545430379886804068140227266591772405813543989609076839815720229783718003114180608886334636421326397047566038600412921530952972402578899907836205454561421842055492419131257470842246494069538442496594640112912992727680690692361804664652112265101523239106215919167084342126140790106093508352980671822186697123200123160383938761256746172848853349045220919126106984425818095777658560969309655758707096146051784019094216480401756320602716676145714808645849862153575678888492996160322752188569430871753192243400503776859724077617204265499540375939357916269031038133532163387420740093910060848553485025496911";

    public DESFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_de, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content = ((EditText) view.findViewById(R.id.des_content));
        Button encode = (Button) view.findViewById(R.id.des_encode);
        encode.setOnClickListener(this);
        txt_encode = ((TextView) view.findViewById(R.id.encode_txt));
        Button decode = (Button) view.findViewById(R.id.des_decode);
        decode.setOnClickListener(this);
        txt_deocde = ((TextView) view.findViewById(R.id.deocode_txt));


        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //私钥
            String privateKey = ((RSAPrivateKey) keyPair.getPrivate()).getPrivateExponent().toString();
            //公钥
            String publicKey = ((RSAPublicKey) keyPair.getPublic()).getPublicExponent().toString();
            //系数
            String mod = ((RSAPrivateKey) keyPair.getPrivate()).getModulus().toString();

            Log.d("wyz", "onViewCreated: privateKey = "+privateKey+"\n publicKey = "+publicKey+"\n mod = "+mod);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
/*
    //DES 加解密
    @Override
    public void onClick(View v) {
        try {
        String string = content.getText().toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        byte[] contents = string.getBytes("UTF-8");
        //1 指定秘钥数组
        byte[] bytes = new byte[32];
            //给密钥设置内容
        System.arraycopy(contents, 0, bytes, 0, Math.min(contents.length, bytes.length));
        //2、获取key
        SecretKeySpec keySpec = new SecretKeySpec(bytes, "AES");// DES 对应数组是8位  DESede 24位  AES 32位
        //3、获取加解密工具

            Cipher cipher = Cipher.getInstance("AES");
            switch (v.getId()) {
                case R.id.des_encode:
                    //4 设置工具模式 加密
                    cipher.init(Cipher.ENCRYPT_MODE,keySpec);
                    byte[] doFinal = cipher.doFinal(contents);
                    String s = Base64.encodeToString(doFinal, Base64.DEFAULT);
                    txt_encode.setText(s);
                    break;
                case R.id.des_decode:
                    //4、解密工具
                    cipher.init(Cipher.DECRYPT_MODE,keySpec);
                    byte[] aFinal = cipher.doFinal(Base64.decode(txt_encode.getText().toString(), Base64.DEFAULT));
                    String s1 = new String(aFinal);
                    txt_deocde.setText(s1);

                    break;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.des_encode :
                if (content.getText().toString() != null){
                    String encode = RSAUtils.encode(mod, publicKey, content.getText().toString());
                    txt_encode.setText(encode);
                }
                break;
            case R.id.des_decode:

                if (txt_encode.getText().toString() != null){
                    String encode = RSAUtils.deocde(mod, privateKey, txt_encode.getText().toString());
                    txt_deocde.setText(encode);
                }
                break;
        }
    }
}
