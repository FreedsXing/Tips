package com.help.tips.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freeds.toolutil.LogUtils;
import com.help.tips.R;
import com.help.tips.fingerprint.FingerPrintDialog;
import com.help.tips.fingerprint.FingerprintCore;
import com.help.tips.fingerprint.FingerprintUtil;
import com.help.tips.fingerprint.KeyguardLockScreenManager;
import com.help.tips.util.SharedPreferencesUtil;

import butterknife.BindView;

/**
 * @author Administrator
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_account)
    public EditText etAccount;
    @BindView(R.id.et_pwd)
    public EditText etPwd;
    @BindView(R.id.tv_login)
    public TextView tvLogin;

    @BindView(R.id.tv_login_fingerprint)
    TextView tvLoginFingerPrint;

    private FingerprintCore mFingerprintCore;
    private KeyguardLockScreenManager mKeyguardLockScreenManager;

    private Toast mToast;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private ImageView mFingerGuideImg;
    private TextView mFingerGuideTxt;

    private FingerPrintDialog fingerPrintDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFingerGuideImg = (ImageView) findViewById(R.id.fingerprint_guide);
        mFingerGuideTxt = (TextView) findViewById(R.id.fingerprint_guide_tip);

        tvLogin.setOnClickListener(this);
        tvLoginFingerPrint.setOnClickListener(this);

        findViewById(R.id.fingerprint_recognition_sys_unlock).setOnClickListener(this);
        findViewById(R.id.fingerprint_recognition_sys_setting).setOnClickListener(this);

        String phone = SharedPreferencesUtil.getString(this, "phone", "");
        etAccount.setText(phone);

        String pwd = SharedPreferencesUtil.getString(this, "password", "");

        boolean login = SharedPreferencesUtil.getBoolean(this, "login", false);
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd) && login){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        initFingerprintCore();
    }


    private void initFingerprintCore() {
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mResultListener);
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                String accout = etAccount.getText().toString();
                String pwd = etPwd.getText().toString();
                String phone = SharedPreferencesUtil.getString(this, "phone", "");
                if (TextUtils.isEmpty(phone)){
                    SharedPreferencesUtil.putString(this, "phone", etAccount.getText().toString());
                    SharedPreferencesUtil.putString(this, "password", etPwd.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Toast.makeText(LoginActivity.this, "您已注册成功", Toast.LENGTH_LONG).show();
                    SharedPreferencesUtil.putBoolean(this, "login", true);
                    startActivity(intent);
                }else {
                    String pwdSave = SharedPreferencesUtil.getString(this, "password", "");
                    if (pwdSave.equals(pwd) && accout.equals(SharedPreferencesUtil.getString(this, "phone", ""))){
                        SharedPreferencesUtil.putString(this, "password", etPwd.getText().toString());
                        SharedPreferencesUtil.putBoolean(this, "login", true);

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Toast.makeText(LoginActivity.this, "您已登录成功", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "账号或密码失败", Toast.LENGTH_LONG).show();
                    }
                }

                break;

            case R.id.tv_login_fingerprint:
                mFingerGuideImg.setVisibility(View.VISIBLE);
                mFingerGuideTxt.setVisibility(View.VISIBLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                   startFingerprintRecognition();
                }

                break;
            case R.id.fingerprint_guide:

            break;
            // set
            case R.id.fingerprint_recognition_sys_unlock:
                startFingerprintRecognitionUnlockScreen();
                break;
            case R.id.fingerprint_recognition_sys_setting:
                enterSysFingerprintSettingPage();
                break;
            default:
                break;
        }
    }

    private void showFingerDialog() {
        fingerPrintDialog = new FingerPrintDialog(this);
        FingerPrintDialog.Builder builder = new FingerPrintDialog.Builder(this);
        fingerPrintDialog = builder.setNegativeButton(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 LogUtils.e("TAG", "----------setNegativeButton-------------");
                 mFingerGuideImg.setVisibility(View.INVISIBLE);
                 mFingerGuideTxt.setVisibility(View.INVISIBLE);

                 if (mFingerprintCore.isAuthenticating()) {

                     mFingerprintCore.cancelAuthenticate();
                     resetGuideViewState();
                 }
                 fingerPrintDialog.dismiss();
             }
         }).create();
        fingerPrintDialog.show();
    }

    private void enterSysFingerprintSettingPage() {
        FingerprintUtil.openFingerPrintSettingPage(this);
    }


    private void startFingerprintRecognitionUnlockScreen() {
        if (mKeyguardLockScreenManager == null) {
            return;
        }
        if (!mKeyguardLockScreenManager.isOpenLockScreenPwd()) {
            toastTipMsg(R.string.fingerprint_not_set_unlock_screen_pws);
            FingerprintUtil.openFingerPrintSettingPage(this);
            return;
        }
        mKeyguardLockScreenManager.showAuthenticationScreen(this);
    }

    /**
     * 开始指纹识别
     */
    private void startFingerprintRecognition() {
        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHasEnrolledFingerprints()) {
                toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                FingerprintUtil.openFingerPrintSettingPage(this);
                return;
            }
            toastTipMsg(R.string.fingerprint_recognition_tip);
            mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
            mFingerGuideImg.setBackgroundResource(R.drawable.fingerprint_guide);
            if (mFingerprintCore.isAuthenticating()) {
                toastTipMsg(R.string.fingerprint_recognition_authenticating);
            } else {
                mFingerprintCore.startAuthenticate();
            }

            showFingerDialog();
        } else {
            toastTipMsg(R.string.fingerprint_recognition_not_support);
            mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
        }
    }

    private void resetGuideViewState() {
        mFingerGuideTxt.setText(R.string.fingerprint_recognition_guide_tip);
        mFingerGuideImg.setBackgroundResource(R.drawable.fingerprint_normal);
    }

    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            toastTipMsg(R.string.fingerprint_recognition_success);
           resetGuideViewState();
            Toast.makeText(LoginActivity.this, "---onAuthenticateSuccess---", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            Toast.makeText(LoginActivity.this, "---onAuthenticateFailed---", Toast.LENGTH_LONG).show();
              toastTipMsg(R.string.fingerprint_recognition_failed);
            mFingerGuideTxt.setText(R.string.fingerprint_recognition_failed);
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            Toast.makeText(LoginActivity.this, "---onAuthenticateError---", Toast.LENGTH_LONG).show();
             resetGuideViewState();
           toastTipMsg(R.string.fingerprint_recognition_error);
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {
            Toast.makeText(LoginActivity.this, "---onStartAuthenticateResult---", Toast.LENGTH_LONG).show();
        }
    };


    private void toastTipMsg(int messageId) {
        if (mToast == null) {
            mToast = Toast.makeText(this, messageId, Toast.LENGTH_LONG);
        }
        mToast.setText(messageId);
        mToast.cancel();
        mHandler.removeCallbacks(mShowToastRunnable);
        mHandler.postDelayed(mShowToastRunnable, 0);
    }

    private void toastTipMsg(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        }
        mToast.setText(message);
        mToast.cancel();
        mHandler.removeCallbacks(mShowToastRunnable);
        mHandler.postDelayed(mShowToastRunnable, 200);
    }
    private Runnable mShowToastRunnable = new Runnable() {
        @Override
        public void run() {
            mToast.show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KeyguardLockScreenManager.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                toastTipMsg(R.string.sys_pwd_recognition_success);
            } else {
                toastTipMsg(R.string.sys_pwd_recognition_failed);
            }
        }
    }


    @Override
    protected void onDestroy() {
        if (mFingerprintCore != null) {
            mFingerprintCore.onDestroy();
            mFingerprintCore = null;
        }
        if (mKeyguardLockScreenManager != null) {
            mKeyguardLockScreenManager.onDestroy();
            mKeyguardLockScreenManager = null;
        }
        mResultListener = null;
        mShowToastRunnable = null;
        mToast = null;
        super.onDestroy();
    }
}