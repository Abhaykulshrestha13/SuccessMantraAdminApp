package com.kumarsidhanta13.successmantraadmin.view

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.kumarsidhanta13.successmantraadmin.R

class ViewHolderVideo(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titleVideo:TextView = itemView.findViewById(R.id.tv_title_video_edit)
    var vvEditView:VideoView = itemView.findViewById(R.id.vv_edit_video)
    var btnDelete:Button = itemView.findViewById(R.id.btn_delete_video)
    var progressBarVideo:ProgressBar = itemView.findViewById(R.id.progressbarVideo)
}