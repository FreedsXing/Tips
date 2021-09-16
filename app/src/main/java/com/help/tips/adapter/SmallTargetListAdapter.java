package com.help.tips.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freeds.tool.LogUtils;
import com.freeds.tool.util.UnitTurnUtil;
import com.help.tips.R;
import com.help.tips.activity.SignInActivity;
import com.help.tips.bean.SmallTargetBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SmallTargetListAdapter extends RecyclerView.Adapter<SmallTargetListAdapter.SmallTargetViewHolder> {

    private Activity mContext;
    private List<SmallTargetBean> mSmallTargetList;

    public SmallTargetListAdapter(Activity activity, List<SmallTargetBean> smallTargetList) {
        mContext = activity;
        mSmallTargetList = smallTargetList;
    }

    @NonNull
    @Override
    public SmallTargetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SmallTargetViewHolder(View.inflate(mContext, R.layout.item_small_target_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SmallTargetViewHolder holder, int position) {
        SmallTargetBean bean = mSmallTargetList.get(position);
        LogUtils.e("TAG", "---onBindViewHolder---" + bean.getTargetName());
        holder.tvName.setText(bean.getTargetName());


        String str = "已经完成目标" + bean.getFinishDay() + "天";
        SpannableStringBuilder mSpannable = new SpannableStringBuilder(str);
        ////mSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mSpannable.setSpan(new ForegroundColorSpan(Color.RED), 6, 7, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mSpannable.setSpan(new AbsoluteSizeSpan(UnitTurnUtil.dip2px(mContext, 14)), 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.tvContent.setText(mSpannable);

        holder.ivIcon.setBackgroundResource(bean.getImgUrl());

        boolean isLike = bean.isLike();
        if (isLike){
            holder.ivToDetail.setBackgroundResource(R.drawable.target_to_signin_select);
        }else {
            holder.ivToDetail.setBackgroundResource(R.drawable.target_to_signin);
        }

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignInActivity.class);
                intent.putExtra("name", bean.getTargetName());
                intent.putExtra("id", bean.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSmallTargetList.size();
    }


    public class SmallTargetViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl;
        ImageView ivIcon;
        TextView tvName;
        TextView tvContent;
        ImageView ivToDetail;

        public SmallTargetViewHolder(@NonNull View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.rl);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);
            ivToDetail = itemView.findViewById(R.id.iv_to_detail);
        }
    }
}
