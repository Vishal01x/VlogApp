package com.exa.android.vlogassignment.data.mappers

import com.exa.android.vlogassignment.data.local.VlogEntity
import com.exa.android.vlogassignment.data.remote.dto.VlogDto
import com.exa.android.vlogassignment.data.remote.dto.VlogItems
import com.exa.android.vlogassignment.domain.Vlog

fun VlogDto.toVlogEntity() : VlogEntity{
    return VlogEntity(
        appId = id,
        author = author,
        date = date,
        imageUrl = jetpack_featured_media_url,
        vlogLink = link,
        title = title.rendered,
        type = type
    )
}

fun VlogEntity.toVlog() : Vlog{
    return Vlog(
        author = author,
        date = date,
        imageUrl = imageUrl,
        vlogLink = vlogLink,
        title = title
    )
}