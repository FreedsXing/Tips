package com.help.tips.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

import com.freeds.tool.LogUtils;
import com.help.tips.R;
import com.help.tips.StepCountService;
import com.help.tips.base.BaseFragment;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThirdFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "ThirdFragment";

    Intent intent;

    //结果集
    private EditText editText;

    //数字1-9
    private Button main_btn1;
    private Button main_btn2;
    private Button main_btn3;
    private Button main_btn4;
    private Button main_btn5;
    private Button main_btn6;
    private Button main_btn7;
    private Button main_btn8;
    private Button main_btn9;
    private  Button main_btn0;

    //运算符
    private  Button main_btn1a  ;// +
    private  Button main_btnj;  // -
    private  Button main_btnx;  // *
    private  Button main_btnc;  // /
    private  Button main_btnd;  //小数点
    private  Button main_btn1d;  //=

    //清除
    private  Button main_btndel;
    boolean clear_flag;//清空标识


    private StepCountService.ModelBinder modelBinder;

    private String mContentCurrent = "";

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            modelBinder  = (StepCountService.ModelBinder) service;
            modelBinder.start();
            modelBinder.end();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    private View mainView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_third, container, false);
        mUnbinder = ButterKnife.bind(this, mainView);


        LogUtils.e("TAG", TAG + "---onCreateView---");

        TextView tvTitle = mainView.findViewById(R.id.tv_title);
        tvTitle.setText("计算器");
        mainView.findViewById(R.id.iv_back_logo).setVisibility(View.GONE);

         intent = new Intent(getActivity(), StepCountService.class);

        //数字1-9
        View main_btn1 = mainView.findViewById(R.id.main_btn1);
        View main_btn2 = mainView.findViewById(R.id.main_btn2);
        View main_btn3= mainView.findViewById(R.id.main_btn3);
        View main_btn4 = mainView.findViewById(R.id.main_btn4);
        View main_btn5 = mainView.findViewById(R.id.main_btn5);
        View main_btn6 = mainView.findViewById(R.id.main_btn6);
        View main_btn7 = mainView.findViewById(R.id.main_btn7);
        View main_btn8 = mainView.findViewById(R.id.main_btn8);
        View main_btn9 = mainView.findViewById(R.id.main_btn9);
        View main_btn0 = mainView.findViewById(R.id.main_btn0);
        //运算符
        View main_btn1a = mainView.findViewById(R.id.main_btn1a);// +
        View main_btnj = mainView.findViewById(R.id.main_btnj);// -
        View main_btnx = mainView.findViewById(R.id.main_btnx);// *
        View main_btnc = mainView.findViewById(R.id.main_btnc); // /
        View main_btnd = mainView.findViewById(R.id.main_btnd);//小数点
        View main_btn1d = mainView.findViewById(R.id.main_btn1d);//=
        View main_btndel = mainView.findViewById(R.id.main_btndel);//清空


        editText = (EditText) mainView.findViewById(R.id.main_et_result);//结果集


        //添加点击事件
        main_btn0.setOnClickListener(this);
        main_btn1.setOnClickListener(this);
        main_btn2.setOnClickListener(this);
        main_btn3.setOnClickListener(this);
        main_btn4.setOnClickListener(this);
        main_btn5.setOnClickListener(this);
        main_btn6.setOnClickListener(this);
        main_btn7.setOnClickListener(this);
        main_btn8.setOnClickListener(this);
        main_btn9.setOnClickListener(this);

        main_btnd.setOnClickListener(this);
        main_btndel.setOnClickListener(this);

        main_btn1a.setOnClickListener(this);
        main_btnj.setOnClickListener(this);
        main_btnx.setOnClickListener(this);
        main_btnc.setOnClickListener(this);
        main_btn1d.setOnClickListener(this);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LogUtils.e("TAG" + "---addTextChangedListener---beforeTextChanged---s=" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                LogUtils.e("TAG" + "---addTextChangedListener---onTextChanged---s=" + s + "---" + start + "---" + before + "---" + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (!TextUtils.isEmpty(s) && s.length() > 1){
//                    String temp = s.toString().substring(0, s.length() - 2);
//                    if (temp.contains("+") || temp.contains("-") || temp.contains("*") || temp.contains("/") || temp.contains(".")){
//                        editText.setText(s.toString().substring(0, s.length()-2));
//                    }
//                }
                LogUtils.e("TAG" + "---addTextChangedListener---afterTextChanged---s=" + s);
            }
        });

        return mainView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mBoxStore.close();
    }



    @Override
    public void onClick(View view) {

        mContentCurrent = editText.getText().toString();
        //获取文本内容
        LogUtils.e("TAG", TAG + "---onClick---" + mContentCurrent);

        switch (view.getId()){
            case R.id.main_btn0:
            case R.id.main_btn1:
            case R.id.main_btn2:
            case R.id.main_btn3:
            case R.id.main_btn4:
            case R.id.main_btn5:
            case R.id.main_btn6:
            case R.id.main_btn7:
            case R.id.main_btn8:
            case R.id.main_btn9:
            case R.id.main_btnd:
                if(clear_flag){
                    clear_flag = false;
                    editText.setText("");//赋值为空
                }
                editText.setText(mContentCurrent + ((Button)view).getText());//结果集就为本身
                break;
            case R.id.main_btn1a:
            case R.id.main_btnj:
            case R.id.main_btnx:
            case R.id.main_btnc:
                if(clear_flag){
                    clear_flag = false;
                    mContentCurrent = "";
                    editText.setText("");
                }
                editText.setText(mContentCurrent + " " + ((Button)view).getText() + " ");
                break;
            case R.id.main_btndel:
                if(clear_flag){
                    clear_flag = false;
                    mContentCurrent = "";
                    editText.setText("");
                }else if(mContentCurrent != null || !mContentCurrent.equals("")) {//如果获取到的内容为空
                    editText.setText(mContentCurrent.substring(0, mContentCurrent.length() - 1));//结果集为空
                }
                break;
            case R.id.main_btn1d://运算结果  =
                getResult();//调用处理结果集的方法
                break;
        }
    }


    //运算结果的方法
    private void getResult(){
        String exp = editText.getText().toString();//获取文本框的内容
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        double result = 0;

        //进行截取
        //运算符前的数字
        String s1 = exp.substring(0,exp.indexOf(" "));
        //运算符
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //运算符后的数字
        String s2 = exp.substring(exp.indexOf(" ")+3);

        if (!isNumeric(s1)){
            Toast.makeText(getActivity(), "输入不合法", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isNumeric(s2)){
            Toast.makeText(getActivity(), "输入不合法", Toast.LENGTH_LONG).show();
            return;
        }


        if(!s1.equals("")&&!s2.equals("")) {//如果包含小数点的运算
            double d1 = Double.parseDouble(s1);//则数字都是double类型
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {//如果是 +
                result = d1 + d2;
            } else if (op.equals("-")) {
                result = d1 - d2;
            } else if (op.equals("*")) {
                result = d1 * d2;
            } else if (op.equals("/")) {
                if (d2 == 0) { //如果被除数是0
                    result = 0; //则结果是0
                }
                else {//否则执行正常是除法运算
                    result = d1 / d2;
                }
            }

            if (!s1.contains(".") && !s2.contains(".") && !op.equals("/")) {//如果是整数类型
                int r = (int) result; //都是整形
                editText.setText(r + "");
            } else{
                editText.setText(result + "");
            }
        }else if(!s1.equals("") && s2.equals("")){//如果是只输入运算符前的数
            editText.setText(exp);//直接返回当前文本框的内容
        }else if(s1.equals("") && !s2.equals("")){//如果是只输入运算符后面的数
            double d2 = Double.parseDouble(s2);

            //运算符前没有输入数字
            if (op.equals("+")) {
                result = 0 + d2;
            } else if (op.equals("-")) {
                result = 0 - d2;
            } else if (op.equals("*")) {
                result = 0;
            } else if (op.equals("/")) {
                result = 0;
            }

            if (!s1.contains(".") && !s2.contains(".")) {
                int r = (int) result;
                editText.setText(r + "");
            } else{
                editText.setText(result + "");
            }
        }else {
            editText.setText("");
        }

    }


    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}