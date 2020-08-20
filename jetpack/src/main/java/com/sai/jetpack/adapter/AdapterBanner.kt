package com.sai.jetpack.adapter

import com.bumptech.glide.Glide
import com.sai.jetpack.bean.ResBannerItem
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class AdapterBanner(data: List<ResBannerItem>) :
    BannerImageAdapter<ResBannerItem>(data) {
    override fun onBindView(
        holder: BannerImageHolder,
        data: ResBannerItem,
        position: Int,
        size: Int
    ) {
        Glide.with(holder.itemView)
            .load(data.imagePath)
//            .apply(RequestOptions.bitmapTransform( RoundedCorners (30)))
            .into(holder.imageView);
    }

}


//
//class AdapterBanner(data: List<ResBannerItem>) :
//    BannerAdapter<ResBannerItem, AdapterBanner.ImageHolder>(data) {
//
//    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageHolder {
//
//        return ImageHolder.create(parent)
//    }
//
//    override fun onBindView(holder: ImageHolder, data: ResBannerItem, position: Int, size: Int) {
//        Glide.with(holder.itemView.context)
//            .load(data.imagePath)
//            .into(holder.imageView)
//    }
//
//
//    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imageView: ImageView = view.findViewById(R.id.view_item_banner_im)
//
//        companion object {
//            fun create(parent: ViewGroup): ImageHolder {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.view_item_banner, parent, false)
//                return ImageHolder(view)
//            }
//        }
//
//    }
//}

//class AdapterBanner(imageUrls: List<ResBannerItem>) :
//    BannerAdapter<ResBannerItem, AdapterBanner.ImageHolder>(imageUrls) {
//
//
//    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageHolder {
//        val imageView = ImageView(parent!!.context)
//        val params = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//        imageView.layoutParams = params
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        //通过裁剪实现圆角
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            BannerUtils.setBannerRound(imageView, 20f)
//        }
//        return ImageHolder(imageView)
//    }
//
//    override fun onBindView(holder: ImageHolder?, data: ResBannerItem, position: Int, size: Int) {
//        Glide.with(holder!!.itemView)
//            .load(data.imagePath)
//            .into(holder.imageView)
//    }
//
//
//    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var imageView: ImageView
//
//        init {
//            imageView = view as ImageView
//        }
//    }
//}
