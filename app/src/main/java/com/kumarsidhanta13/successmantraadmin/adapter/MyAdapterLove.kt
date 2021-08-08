package com.kumarsidhanta13.successmantraadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kumarsidhanta13.successmantraadmin.R
import com.kumarsidhanta13.successmantraadmin.model.ModelGreatPerson
import com.kumarsidhanta13.successmantraadmin.model.ModelLove
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderGreatPerson
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderLoveQuotes

class MyAdapterLove(mContext:Context,mList:ArrayList<ModelLove>):
    RecyclerView.Adapter<ViewHolderLoveQuotes>() {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLoveQuotes {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_lq,parent,false)
        return ViewHolderLoveQuotes(view)
    }

    override fun onBindViewHolder(holder: ViewHolderLoveQuotes, position: Int) {
        val modelLq = mList[position]
        setImageUri(modelLq,holder,position)
    }

    private fun setImageUri(modelLq: ModelLove, holder: ViewHolderLoveQuotes, position: Int) {
        holder.progressBarLq.visibility = View.GONE
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImageLoveQuotes")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("LoveQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
       return mList.size
    }

}