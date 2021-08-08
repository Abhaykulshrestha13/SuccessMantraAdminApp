package com.kumarsidhanta13.successmantraadmin.model

class ModelVideo {
    var title: String? = null
    var videoUri: String? = null
    var id:String? = null
    constructor(){

    }

    constructor(title: String?, videoUri: String?, id: String?) {
        this.title = title
        this.videoUri = videoUri
        this.id = id
    }

//    constructor(title: String?, timestamp: String?, videoUri: String?) {
//        this.title = title
//        this.videoUri = videoUri
//        this.id = id
//    }
}