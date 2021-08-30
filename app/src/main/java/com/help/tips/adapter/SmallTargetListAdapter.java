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
import com.help.tips.bean.SmallTargetItemBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SmallTargetListAdapter extends RecyclerView.Adapter<SmallTargetListAdapter.SmallTargetViewHolder> {

    private Activity mContext;
    private ArrayList<SmallTargetItemBean> mSmallTargetList;

    public SmallTargetListAdapter(Activity activity, ArrayList<SmallTargetItemBean> smallTargetList) {
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
        SmallTargetItemBean bean = mSmallTargetList.get(position);
        LogUtils.e("TAG", "---onBindViewHolder---" + bean.name);
        holder.tvName.setText(bean.name);
        holder.tvContent.setText(bean.name + bean.days);
        holder.ivIcon.setBackgroundResource(bean.imgUrl);

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignInActivity.class);
                intent.putExtra("name", bean.name);
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
