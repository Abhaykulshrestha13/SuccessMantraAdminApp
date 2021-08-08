package com.kumarsidhanta13.successmantraadmin.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.kumarsidhanta13.successmantraadmin.R

class ViewHolderLoveQuotes(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgEditView: ImageView = itemView.findViewById(R.id.img_edit_lq)
    var btnDelete: Button = itemView.findViewById(R.id.btn_delete_lq)
    var progressBarLq: ProgressBar = itemView.findViewById(R.id.progressbarLq)
}