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
import com.kumarsidhanta13.successmantraadmin.model.ModelMoney
import com.kumarsidhanta13.successmantraadmin.model.ModelPositive
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderAttitude
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderMoney
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderPositive

class MyAdapterPositive(mContext: Context, mList:ArrayList<ModelPositive>) : RecyclerView.Adapter<ViewHolderPositive>()   {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPositive {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_positiveq,parent,false)
        return ViewHolderPositive(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPositive, position: Int) {
        val modelAtt = mList[position]
        val imageUri = modelAtt.imageUri
        setImageUri(modelAtt,holder,position)
    }

    private fun setImageUri(modelAtt: ModelPositive, holder: ViewHolderPositive, position: Int) {
        val bussUrl: String? = modelAtt.imageUri
        val bussUri = Uri.parse(bussUrl)
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImagePositiveQuotes")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("PositiveQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
       return mList.size
    }

}