package com.manle.saitamall.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.manle.saitamall.R;
import com.manle.saitamall.bean.User;
import com.manle.saitamall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @Bind(R.id.et_register_pwd_re)
    EditText etRegisterPwdRe;
    @Bind(R.id.et_register_phone)
    EditText etRegisterPhone;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.tv_alert_error)
    TextView tvAlertError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        btnRegister.setOnClickListener(this);
        etRegisterPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    isRepeat();
                }
            }
        });
    }

    private void isRepeat() {
        if (!etRegisterPwdRe.getText().equals(etRegisterPwd.getText())){
            tvAlertError.setVisibility(View.VISIBLE);
            tvAlertError.setText("与输入密码不一致");
        }else {
            tvAlertError.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                register();
        }

    }

    private void register() {
       User user = new User(etRegisterName.getText().toString(),etRegisterPhone.getText().toString(),etRegisterPwd.getText().toString(),null,0f);

       OkHttpUtils.post().tag(this).url(Constants.CLIENT_USER).addParams("userName",user.getUserName()).addParams("phone",user.getPhone()).addParams("password",user.getPassword()).build().execute(new StringCallback() {
           @Override
           public void onBefore(Request request, int id) {
               super.onBefore(request, id);
               ProgressDialog.show(RegisterActivity.this,"","注册中...");
           }

           @Override
           public void onAfter(int id) {
               super.onAfter(id);
           }

           @Override
           public void onError(Call call, Exception e, int id) {
               Log.d(TAG, "onError: =============="+id);
           }

           @Override
           public void onResponse(String response, int id) {
               Log.d(TAG, "onResponse:===============register "+id);
           }
       });

    }

}
