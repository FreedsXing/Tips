package com.help.tips.util;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.freeds.tool.util.UnitTurnUtil;


/**
 * 针对TextView 特殊位置文本字号、颜色的处理
 */
public class MultTextStyleTool {


    public Context mContext;
    public TextView mTextView;
    public String mString;

    public SpannableStringBuilder mSpannable;


    public int startIndex = 0;
    public int endIndex = 1;
    public int textSize = 13; //dp
    public int textColor = 0xFF000000;;

    public MultTextStyleTool(Context context, TextView textView, String str) {

        this.mContext = context;
        this.mTextView = textView;
        this.mString = str;

        mSpannable = new SpannableStringBuilder(mString);
    }


    public void  apply(){

        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(UnitTurnUtil.dip2px(mContext, textSize));
        mSpannable.setSpan(absoluteSizeSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);


        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(mContext.getResources().getColor(textColor));
        mSpannable.setSpan(foregroundColorSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        mTextView.setText(mSpannable);
    }
}
