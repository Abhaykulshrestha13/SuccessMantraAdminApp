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
import com.kumarsidhanta13.successmantraadmin.model.ModelDaily
import com.kumarsidhanta13.successmantraadmin.model.ModelFriends
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderAttitude
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderDaily
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderFriends

class MyAdapterFriends(mContext: Context, mList:ArrayList<ModelFriends>) : RecyclerView.Adapter<ViewHolderFriends>()   {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFriends {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_friend,parent,false)
        return ViewHolderFriends(view)

    }

    override fun onBindViewHolder(holder: ViewHolderFriends, position: Int) {
        val modelF = mList[position]
        val imageUri = modelF.imageUri
        setImageUri(modelF,holder,position)
    }

    private fun setImageUri(modelF: ModelFriends, holder: ViewHolderFriends, position: Int) {
        val bussUrl: String? = modelF.imageUri
        val bussUri = Uri.parse(bussUrl)
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImageFriendQuotes")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("FriendQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}