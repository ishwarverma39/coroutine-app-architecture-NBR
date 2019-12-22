package com.livetech.demo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livetech.demo.R
import com.livetech.demo.core.models.Photo

class PhotosAdapter(val photos : ArrayList<Photo>) : RecyclerView.Adapter<PhotoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo, parent, false))
    }

    override fun getItemCount(): Int {
       return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindData(photos[position])
    }

}