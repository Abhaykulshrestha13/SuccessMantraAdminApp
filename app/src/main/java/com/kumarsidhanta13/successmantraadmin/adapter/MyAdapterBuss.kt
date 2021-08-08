package com.kumarsidhanta13.successmantraadmin.adapter

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kumarsidhanta13.successmantraadmin.R
import com.kumarsidhanta13.successmantraadmin.model.ModelBuss
import com.kumarsidhanta13.successmantraadmin.model.ModelVideo
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderBuss
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderVideo

class MyAdapterBuss(mContext: Context, mList:ArrayList<ModelBuss>) : RecyclerView.Adapter<ViewHolderBuss>() {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBuss {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_business,parent,false)
        return ViewHolderBuss(view)
    }

    override fun onBindViewHolder(holder: ViewHolderBuss, position: Int) {
        val modelBuss = mList[position]
        val imageUri = modelBuss.imageUri
        setImageUri(modelBuss,holder,position)

    }

    private fun setImageUri(modelBuss: ModelBuss, holder: ViewHolderBuss, position: Int) {
        holder.progressBarBuss.visibility = View.GONE
        val bussUrl: String? = modelBuss.imageUri
        val bussUri = Uri.parse(bussUrl)
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImageBuss")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("BusinessQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}