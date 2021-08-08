package com.kumarsidhanta13.successmantraadmin.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kumarsidhanta13.successmantraadmin.R
import com.kumarsidhanta13.successmantraadmin.model.ModelFriends
import com.kumarsidhanta13.successmantraadmin.model.ModelLife
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderAttitude
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderFriends
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderLife

class MyAdapterLife(mContext: Context, mList:ArrayList<ModelLife>) : RecyclerView.Adapter<ViewHolderLife>()   {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLife {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_lifeq,parent,false)
        return ViewHolderLife(view)
    }

    override fun onBindViewHolder(holder: ViewHolderLife, position: Int) {
        val modelAtt = mList[position]
        val imageUri = modelAtt.imageUri
        setImageUri(modelAtt,holder,position)
    }

    private fun setImageUri(modelLife: ModelLife, holder: ViewHolderLife, position: Int) {
        val bussUrl: String? = modelLife.imageUri
        val bussUri = Uri.parse(bussUrl)
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImageLifeQuotes")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("LifeQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}