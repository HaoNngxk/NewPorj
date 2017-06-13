package com.xkyb.jf.ui.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xkyb.jf.R;
import com.xkyb.jf.model.FindPassDataModel;

import net.anumbrella.customedittext.FloatLabelView;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * author：Anumbrella
 * Date：16/5/29 上午9:03
 * <p>
 * 短信验证码等待时间*/


public class FindPasswordActivity extends BaseThemeSettingActivity {


    private int time = 60;

    private String phone;

    private String code;

    private String password;

    private boolean flag = true;

    private ProgressDialog mDialog;

    @BindView(R.id.contact_us_toolbar)
    Toolbar toolbar;


    @BindView(R.id.find_pass_phone)
    FloatLabelView passPhone;


    @BindView(R.id.find_pass_code)
    FloatLabelView passCode;


    @BindView(R.id.send_code)
    Button sendCode;


    @BindView(R.id.find_pass_new)
    FloatLabelView passNew;


    @BindView(R.id.find_pass_upadate_pass)
    Button updatePass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
//        PushAgent.getInstance(this).onAppStart();
        passNew.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("请稍等");
        mDialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回按键取消
        mDialog.setCancelable(false);
        setToolbar(toolbar);

    }

    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("忘记密码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({R.id.find_pass_upadate_pass, R.id.send_code})
    public void clickBtn(View view) {
        phone = passPhone.getEditText().getText().toString().trim();
        code = passCode.getEditText().getText().toString().trim();
        password = passNew.getEditText().getText().toString().trim();
        switch (view.getId()) {
            case R.id.find_pass_upadate_pass:
                if (!TextUtils.isEmpty(phone)) {
                    if (TextUtils.isEmpty(code)) {
                        Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        if (code.length() == 4) {
                            if (TextUtils.isEmpty(password)) {
                                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                            } else {
                                //对验证码进行校验
                                flag = false;
                            }
                        } else {
                            Toast.makeText(this, "验证码格式不正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.send_code:
                //发送验证码
                if (!TextUtils.isEmpty(phone)) {
                    if (checkPhoneNumber(phone)) {
                        checkPhoneExist();
                    } else {
                        Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void checkPhoneExist() {
        FindPassDataModel.checkPhoneExist(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String resultResponse = response.body().string().toString();
                    if (resultResponse.equals("0200")) {
                        handlerText.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        Toast.makeText(FindPasswordActivity.this, "该手机号还没注册", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(FindPasswordActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
            }
        }, phone);
    }


    Handler handlerText = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (time > 0) {
                    sendCode.setClickable(false);
                    sendCode.setText("还剩" + time + "秒");
                    sendCode.setBackground(getResources().getDrawable(R.drawable.send_code_button_click_bg));
                    sendCode.setTextColor(getResources().getColor(R.color.milk_white));
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                } else {
                    sendCode.setText("发送验证码");
                    sendCode.setClickable(true);
                    sendCode.setBackground(getResources().getDrawable(R.drawable.button_register));
                    sendCode.setTextColor(getResources().getColor(R.color.login_btn_text_color));
                    time = 60;
                    flag = false;
                }
            } else if (msg.what == 2) {
                mDialog.show();
                updatePassword();
            }
        }

    };

    private void updatePassword() {
        FindPassDataModel.updatePassword(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String resultResponse = response.body().string().toString();
                    if (resultResponse.equals("0200")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FindPasswordActivity.this, "更改密码成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }, 1000);

                    } else {
                        Toast.makeText(FindPasswordActivity.this, "更改密码失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mDialog.hide();
                Toast.makeText(FindPasswordActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
            }
        }, phone, password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static boolean checkPhoneNumber(String mobiles) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(mobiles);
        b = m.matches();
        return b;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

