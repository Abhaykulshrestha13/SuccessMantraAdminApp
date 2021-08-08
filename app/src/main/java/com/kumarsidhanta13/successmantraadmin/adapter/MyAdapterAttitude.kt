package com.kumarsidhanta13.successmantraadmin.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kumarsidhanta13.successmantraadmin.R
import com.kumarsidhanta13.successmantraadmin.model.ModelAttitude
import com.kumarsidhanta13.successmantraadmin.model.ModelBuss
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderAttitude
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderBuss

class MyAdapterAttitude(mContext: Context, mList:ArrayList<ModelAttitude>) : RecyclerView.Adapter<ViewHolderAttitude>()  {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAttitude {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_att,parent,false)
        return ViewHolderAttitude(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAttitude, position: Int) {
        val modelAtt = mList[position]
        val imageUri = modelAtt.imageUri
        setImageUri(modelAtt,holder,position)
    }

    private fun setImageUri(modelAtt: ModelAttitude, holder: ViewHolderAttitude, position: Int) {
        val bussUrl: String? = modelAtt.imageUri
        val bussUri = Uri.parse(bussUrl)
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImageAttitudeQuotes")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("AttitudeQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}