package com.livetech.demo.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livetech.demo.R
import com.livetech.demo.core.models.Photo

class PhotoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val imageView :ImageView
    val titleText : TextView

    init {
        imageView=itemView.findViewById(R.id.photo_image)
        titleText = itemView.findViewById(R.id.title_text)
    }
    fun bindData(photo: Photo){
        titleText.text=photo.title
        val url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
        Glide.with(imageView).load(url).into(imageView)
    }
}