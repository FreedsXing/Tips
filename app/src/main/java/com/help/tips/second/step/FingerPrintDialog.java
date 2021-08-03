package com.help.tips.second.step;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.help.tips.BaseDialog;
import com.help.tips.R;

/**
 * Created by fengtao
 * on 2018/8/7.
 */

public class FingerPrintDialog extends BaseDialog {

    public FingerPrintDialog(@NonNull Context context) {
        super(context);
    }

    public static final class Builder {

        private View view;
        private FingerPrintDialog dialog;
        private View.OnClickListener negativeButtonClickListener;


        public Builder(Context context) {
            dialog = new FingerPrintDialog(context);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_dialog_finger_print_login, null);
            dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        public Builder setNegativeButton(View.OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }


        public FingerPrintDialog create() {
            view.findViewById(R.id.tv_cancel).setOnClickListener(negativeButtonClickListener);
            dialog.setContentView(view);
            dialog.setCancelable(true);//用户可以点击手机Back键不能取消对话框显示
            dialog.setCanceledOnTouchOutside(false);//用户不能通过点击对话框之外的地方取消对话框显示
            return dialog;
        }
    }
}
