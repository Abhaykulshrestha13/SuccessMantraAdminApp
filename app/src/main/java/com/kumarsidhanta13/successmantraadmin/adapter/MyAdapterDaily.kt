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
import com.kumarsidhanta13.successmantraadmin.model.ModelAttitude
import com.kumarsidhanta13.successmantraadmin.model.ModelDaily
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderAttitude
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderDaily

class MyAdapterDaily(mContext: Context, mList:ArrayList<ModelDaily>) : RecyclerView.Adapter<ViewHolderDaily>()  {
    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDaily {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_dm,parent,false)
        return ViewHolderDaily(view)
    }

    override fun onBindViewHolder(holder: ViewHolderDaily, position: Int) {
        val modelDaily = mList[position]
        val imageUri = modelDaily.imageUri
        setImageUri(modelDaily,holder,position)
    }

    private fun setImageUri(modelDaily : ModelDaily, holder: ViewHolderDaily, position: Int) {
        val bussUrl: String? = modelDaily.imageUri
        val bussUri = Uri.parse(bussUrl)
        Glide.with(mContext).load(mList[position].imageUri).into(holder.imgEditView)
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("ImageDailyQuotes")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("DailyQuotes/image_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}