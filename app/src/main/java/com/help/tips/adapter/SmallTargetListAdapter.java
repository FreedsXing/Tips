package com.help.tips.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freeds.tool.LogUtils;
import com.help.tips.R;
import com.help.tips.activity.SignInActivity;
import com.help.tips.bean.SmallTargetBean;
import com.help.tips.util.MultTextStyleTool;

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

        holder.ivIcon.setBackgroundResource(bean.getImgUrl());


        String str = "已经完成目标" + bean.getFinishDay() + "天";
        MultTextStyleTool multText = new MultTextStyleTool(mContext, holder.tvContent, str);
        multText.startIndex = 6;
        multText.endIndex = 7;
        multText.textSize = 18;
        multText.textColor = R.color.colorMain;
        multText.apply();


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
