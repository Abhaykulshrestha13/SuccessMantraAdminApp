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
import com.kumarsidhanta13.successmantraadmin.model.ModelGreatPerson
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderBuss
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderGreatPerson

class MyAdapterGreatPerson(mContext:Context,mList:ArrayList<ModelGreatPerson>):
    RecyclerView.Adapter<ViewHolderGreatPerson>() {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGreatPerson {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_gpq,parent,false)
        return ViewHolderGreatPerson(view)
    }

    override fun onBindViewHolder(holder: ViewHolderGreatPerson, position: Int) {
        val modelGpq = mList[position]
        val imageUri = modelGpq.imageUri
        setImageUri(modelGpq,holder,position)
    }

    private fun setImageUri(
        modelGpq: ModelGreatPerson,
        holder: ViewHolderGreatPerson,
        position: Int
    ) {
        holder.progressBarGpq.visibility = View.GONE
        val bussUrl: String? = modelGpq.imageUri
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImageGreatPersonQuotes")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("GreatPersonQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}