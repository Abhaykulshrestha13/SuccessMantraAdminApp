package com.kumarsidhanta13.successmantraadmin.view

import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.kumarsidhanta13.successmantraadmin.R

class ViewHolderBuss(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgEditView: ImageView = itemView.findViewById(R.id.img_edit_buss)
    var btnDelete: Button = itemView.findViewById(R.id.btn_delete_buss)
    var progressBarBuss: ProgressBar = itemView.findViewById(R.id.progressbarBuss)
}