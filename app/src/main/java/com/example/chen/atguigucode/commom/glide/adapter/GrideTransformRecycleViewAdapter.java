package com.example.chen.atguigucode.commom.glide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by chen on 2017/8/16.
 */

public class GrideTransformRecycleViewAdapter extends RecyclerView.Adapter<GrideTransformRecycleViewAdapter.ViewHolder> {


    private List<Trailer.TrailersBean> list;
    private Context mContext;

    public GrideTransformRecycleViewAdapter(List<Trailer.TrailersBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_glide_transform_recycleview,null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        //图片转换
        //R.drawable.demo
        //R.drawable.check

        String url = list.get(position).getCoverImg();
        int uri = R.drawable.demo;

        switchTransform(position,holder,uri);




    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image)
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();

                    Toast.makeText(mContext, "id:"+position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    //设置监听








    public  void switchTransform(int position,ViewHolder holder,int url){

        switch (position) {
            case 1: {
                int width = Utils.dip2px(mContext, 133.33f);
                int height = Utils.dip2px(mContext, 126.33f);
                Glide.with(mContext)
                        .load(url)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.mask_starfish))
                        .into(holder.image);
                break;
            }
            case 2: {
                int width = Utils.dip2px(mContext, 150.0f);
                int height = Utils.dip2px(mContext, 100.0f);
                Glide.with(mContext)
                        .load(url)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.mask_chat_right))
                        .into(holder.image);
                break;
            }
            case 3:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.TOP))
                        .into(holder.image);
                break;
            case 4:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new CropTransformation(mContext, 300, 100))
                        .into(holder.image);
                break;
            case 5:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.BOTTOM))
                        .into(holder.image);

                break;
            case 6:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new CropSquareTransformation(mContext))
                        .into(holder.image);
                break;
            case 7:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new CropCircleTransformation(mContext))
                        .into(holder.image);
                break;
            case 8:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0)))
                        .into(holder.image);
                break;
            case 9:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new GrayscaleTransformation(mContext))
                        .into(holder.image);
                break;
            case 10:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM))
                        .into(holder.image);
                break;
            case 11:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new BlurTransformation(mContext, 25))
                        .into(holder.image);
                break;
            case 12:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new ToonFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 13:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new SepiaFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 14:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new ContrastFilterTransformation(mContext, 2.0f))
                        .into(holder.image);
                break;
            case 15:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new InvertFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 16:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new PixelationFilterTransformation(mContext, 20))
                        .into(holder.image);
                break;
            case 17:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new SketchFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 18:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(
                                new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(holder.image);
                break;
            case 19:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new BrightnessFilterTransformation(mContext, 0.5f))
                        .into(holder.image);
                break;
            case 20:
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new KuwaharaFilterTransformation(mContext, 25))
                        .into(holder.image);
                break;
            case 21:
                Glide.with(mContext)
                    .load(url)
                    .bitmapTransform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
                            new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f))
                    .into(holder.image);
            break;
            default: //默认
                Glide.with(mContext)
                        .load(url)
                        .placeholder(R.drawable.loading2)
                        .centerCrop()
                        .into(holder.image);


                break;
        }
    }


}
