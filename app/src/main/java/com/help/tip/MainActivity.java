package com.help.tip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.transform.sax.TemplatesHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.tv_login_fingerprint)
    TextView tvLoginFingerPrint;


    private FingerprintCore mFingerprintCore;
    private KeyguardLockScreenManager mKeyguardLockScreenManager;

    private Toast mToast;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private ImageView mFingerGuideImg;
    private TextView mFingerGuideTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvLogin.setOnClickListener(this);
        tvLoginFingerPrint.setOnClickListener(this);

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
                if ("123".equals(accout) && "456".equals(pwd)){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "账号或密码失败", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_login_fingerprint:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                   startFingerprintRecognition();
                }

                break;
            default:
                break;
        }
    }

    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
           // toastTipMsg(R.string.fingerprint_recognition_success);
           // resetGuideViewState();
            Toast.makeText(MainActivity.this, "---onAuthenticateSuccess---", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            Toast.makeText(MainActivity.this, "---onAuthenticateFailed---", Toast.LENGTH_LONG).show();
            //  toastTipMsg(R.string.fingerprint_recognition_failed);
          //  mFingerGuideTxt.setText(R.string.fingerprint_recognition_failed);
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            Toast.makeText(MainActivity.this, "---onAuthenticateError---", Toast.LENGTH_LONG).show();
            // resetGuideViewState();
           // toastTipMsg(R.string.fingerprint_recognition_error);
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {
            Toast.makeText(MainActivity.this, "---onStartAuthenticateResult---", Toast.LENGTH_LONG).show();
        }
    };



    /**
     * 开始指纹识别
     */
    private void startFingerprintRecognition() {
        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHasEnrolledFingerprints()) {
                //toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                FingerprintUtil.openFingerPrintSettingPage(this);
                return;
            }
            //toastTipMsg(R.string.fingerprint_recognition_tip);
           // mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
           // mFingerGuideImg.setBackgroundResource(R.drawable.fingerprint_guide);
            if (mFingerprintCore.isAuthenticating()) {
                //toastTipMsg(R.string.fingerprint_recognition_authenticating);
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
            //toastTipMsg(R.string.fingerprint_recognition_not_support);
           // mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
        }
    }
}