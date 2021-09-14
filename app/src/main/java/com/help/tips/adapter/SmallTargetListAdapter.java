package com.help.tips.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freeds.toolutil.LogUtils;
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
        LogUtils.e("TAG", "---onBindViewHolder---" + bean.getUserName());
        holder.tvName.setText(bean.getUserName());
        holder.tvContent.setText(bean.getId() + "");
        holder.ivIcon.setBackgroundResource(bean.getImgUrl());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignInActivity.class);
                intent.putExtra("name", bean.getUserName());
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
