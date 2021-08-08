package com.kumarsidhanta13.successmantraadmin.adapter

import android.app.AlertDialog
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kumarsidhanta13.successmantraadmin.EditVideoActivity
import com.kumarsidhanta13.successmantraadmin.R
import com.kumarsidhanta13.successmantraadmin.model.ModelVideo
import com.kumarsidhanta13.successmantraadmin.view.ViewHolderVideo

class MyAdapterVideo(mContext:Context, mList:ArrayList<ModelVideo>) :RecyclerView.Adapter<ViewHolderVideo>() {

    var mContext = mContext
    var mList = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVideo {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_video,parent,false)
        return ViewHolderVideo(view)
    }
//
//    override fun onBindViewHolder(holder: ViewHolderVideo, position: Int) {
//
//
//    }

    private fun setVideoUri(modelVideo: ModelVideo, holder: ViewHolderVideo, position: Int) {
        holder.progressBarVideo.visibility = View.VISIBLE
        val videoUrl: String? = modelVideo.videoUri
        val mediaController = MediaController(mContext)
        mediaController.setAnchorView(holder.vvEditView)
        val videoUri = Uri.parse(videoUrl)
        holder.vvEditView.setMediaController(mediaController)
        holder.vvEditView.setVideoURI(videoUri)
        holder.vvEditView.requestFocus()
        holder.vvEditView.setOnPreparedListener{
            holder.progressBarVideo.visibility = View.GONE
            it.start()
        }
        holder.vvEditView.setOnInfoListener (  MediaPlayer.OnInfoListener{ _, what, _ ->
            when(what){
//                MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START->{
//                    holder.progressBarVideo.visibility = View.VISIBLE
//                    return@OnInfoListener true
//                }
                MediaPlayer.MEDIA_INFO_BUFFERING_START->{
                    holder.progressBarVideo.visibility = View.VISIBLE
                    return@OnInfoListener true
                }
                MediaPlayer.MEDIA_INFO_BUFFERING_END->{
                    holder.progressBarVideo.visibility = View.GONE
                    return@OnInfoListener true
                }
            }
            false
        } )
        holder.vvEditView.setOnCompletionListener {
            it.start()
        }
        holder.btnDelete.setOnClickListener {
            FirebaseDatabase.getInstance().getReference().child("Videos")
                .child(mList[position].id!!).removeValue()
            FirebaseStorage.getInstance().getReference("Video/video_"+mList[position].id.toString()).delete()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

//    override fun onBindViewHolder(holder: ViewHolderVideo, position: Int, model: ModelVideo) {
//
//    }

    override fun onBindViewHolder(holder: ViewHolderVideo, position: Int) {
        val modelVideo = mList[position]
        val title = modelVideo.title
        val videoUri = modelVideo.videoUri
        holder.titleVideo.text = title
        setVideoUri(modelVideo,holder,position)
    }
}