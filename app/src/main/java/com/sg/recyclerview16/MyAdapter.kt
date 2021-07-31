package com.sg.recyclerview16

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sg.recyclerview16.model.Profile
import okhttp3.HttpUrl

class MyAdapter(val profileArrayList: ArrayList<Profile>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val profile=profileArrayList[position]
        holder.photo.load(HttpUrl.get(profile.photoUrl)){size(150,150)}
        holder.author.text=profile.auothour
    }

    override fun getItemCount()=profileArrayList.size


    class MyViewHolder(  itemView:View):RecyclerView.ViewHolder(itemView) {
        var photo:ImageView=itemView.findViewById(R.id.image_photo)
        var author:TextView=itemView.findViewById(R.id.txt_author)

    }


}