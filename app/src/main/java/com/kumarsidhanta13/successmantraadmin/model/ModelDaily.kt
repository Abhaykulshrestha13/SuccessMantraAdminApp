package com.kumarsidhanta13.successmantraadmin.model

class ModelDaily {
    var imageUri: String? = null
    var id:String? = null
    constructor(){

    }

    constructor(imageUri: String?, id: String?) {
        this.imageUri = imageUri
        this.id = id
    }
}