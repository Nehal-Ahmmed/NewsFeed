package com.nhbhuiyan.newsfeed.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,     //this can not be directly store by room db: only primitive type can be stored by room db
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) : Parcelable