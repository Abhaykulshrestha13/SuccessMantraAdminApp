package com.kumarsidhanta13.successmantraadmin.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kumarsidhanta13.successmantraadmin.R

class ViewHolderDaily (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgEditView: ImageView = itemView.findViewById(R.id.img_edit_dmq)
    var btnDelete: Button = itemView.findViewById(R.id.btn_delete_dmq)
}