package com.example.chen.atguigucode.commom.glide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.utils.UiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/16.
 */

public class GrideRecycleViewAdapter extends RecyclerView.Adapter<GrideRecycleViewAdapter.MyViewHolder> {



    private List<Trailer.TrailersBean> datas;
    private Context mContext;

    public GrideRecycleViewAdapter(List<Trailer.TrailersBean> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_glide_recycleview, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

        width = UiUtils.px2dp(width,mContext);
        height = UiUtils.px2dp(height,mContext);


        Trailer.TrailersBean bean = datas.get(position);

        holder.tvName.setText("ID:"+position);
        Glide.with(mContext)
                .load(bean.getCoverImg())
                .placeholder(R.drawable.loading) //占位图
                .error(R.drawable.atguigu_logo)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_item_add)
        TextView tvItemAdd;
        @BindView(R.id.tv_item_del)
        TextView tvItemDel;
        @BindView(R.id.image)
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


            //text 点击监听
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        int position = getLayoutPosition();
                        String name = datas.get(position).getMovieName();
//                        Toast.makeText(mContext, "id:"+position+" name:"+name, Toast.LENGTH_SHORT).show();

                    //方式二. 使用接口
                    if(onItemClickListener!=null) {
                        onItemClickListener.onItemTextClickListener(position,name,view);
                    }

                    }

            });

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null) {
                        int position = getLayoutPosition();
                        Trailer.TrailersBean bean = datas.get(position);

                        onItemClickListener.onItemImageClickListener(position,bean,view);
                    }
                }
            });



            /**
             * 点击add 添加当前条目
             */
            tvItemAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        addItem(getLayoutPosition());
                }
            });


            tvItemDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        removeItem(getLayoutPosition());
                }
            });
        }
    }

    /**
     * 删除当前条目
     * @param position
     */
    private void removeItem(int position) {
        if(datas!=null) {
            datas.remove(position);
            notifyDataSetChanged();
        }

    }

    /**
     * 添加条目
     * @param layoutPosition
     */
    public void addItem(int layoutPosition) {

        if(datas!=null) {
            Trailer.TrailersBean bean = datas.get(layoutPosition);
            datas.add(layoutPosition,bean);
            notifyDataSetChanged();
        }

    }


    /**
     * 定义点击监听接口
     */
    public interface OnItemClickListener{
          void onItemTextClickListener(int position,String name,View view);
          void onItemImageClickListener(int position, Trailer.TrailersBean bean,View view);

    }
    private  OnItemClickListener onItemClickListener;

    public  void  setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }






}
