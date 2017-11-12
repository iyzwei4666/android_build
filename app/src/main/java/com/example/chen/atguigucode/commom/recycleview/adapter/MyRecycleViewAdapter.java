package com.example.chen.atguigucode.commom.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/15.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {


    private List<Trailer.TrailersBean> datas;
    private Context mContext;


    /**
     * 相当于创建 listview 适配器中创建 viewholder的过程
     *
     * @param datas
     * @param mContext
     */
    public MyRecycleViewAdapter(List<Trailer.TrailersBean> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }


    /**
     * 相当于创建 listview 适配器中创建 viewholder的过程
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_recycleview, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    /**
     * 相当于绑定视图对象
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Trailer.TrailersBean bean = datas.get(position);

        //1. 设置名称
//        holder.tvName.setText(+position+":"+bean.getMovieName());
        holder.tvName.setText("position:" + position);


        //2. 使用picasso显示图片
        Picasso.with(mContext)
                .load(bean.getCoverImg())
                .into(holder.image);


    }

    /**
     * 返回数据条目总数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_item_add)
        TextView tvItemAdd;
        @BindView(R.id.tv_item_del)
        TextView tvItemDel;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            /*方式一*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mContext, "id:"+getLayoutPosition()+"被点击了", Toast.LENGTH_SHORT).show();

                    if (onItemClickListener != null) {
                        int position = getLayoutPosition();
                        Trailer.TrailersBean bean = datas.get(position);
                        onItemClickListener.onItemClick(view, bean.getMovieName(), position);

                    }
                }
            });


            //2. 为单独的图片设置点击事件
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (onItemClickListener != null) {

                        int position = getLayoutPosition();
                        Trailer.TrailersBean bean = datas.get(position);

                        onItemClickListener.onItemImageClick(view, bean, position);

                    }
                }
            });


            //3. 为单独的标题设置点击事件
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {

                        int position = getLayoutPosition();
                        String name = datas.get(position).getMovieName();
                        onItemClickListener.onItemTextClick(view, name, position);
                    }

                }
            });



            //4. 设置可以增加和删除
            tvItemAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null) {
                        int position = getLayoutPosition();
                        Trailer.TrailersBean bean = datas.get(position);
                        addItemData(position,bean);
                    }


                }
            });
            tvItemDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null) {
                        int position = getLayoutPosition();
                        Trailer.TrailersBean bean = datas.get(position);
                        removeItemData(position);
                        Toast.makeText(mContext, bean.toString(), Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }

    /**
     * 设置监听
     * 方式一: 在holder 中直接设置监听:不对外提供接口
     *
     * 方式二: 定义一个接口,
     *
     *
     */


    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener {

        /**
         * 当RecyclerView某个被点击的时候回调
         *
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view, String data, int position);


        public void onItemImageClick(View view, Trailer.TrailersBean data, int position);//图片点击事件

        public void onItemTextClick(View view, String data, int position);//文本点击事件


    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    /**
     * adapter 向recyclewiew 添加数据
     *
     * @param position
     * @param bean
     */
    public void addItemData(int position, Trailer.TrailersBean bean) {

        if (bean == null) {
            bean = datas.get(1);

        }
        datas.add(position, bean);
        //notifyItemInserted(position);

        notifyDataSetChanged();
    }

    /**
     * 删除数据
     *
     * @param position
     */
    public void removeItemData(int position) {

        datas.remove(position);
       // notifyItemRemoved(position);

        notifyDataSetChanged();
    }


}
