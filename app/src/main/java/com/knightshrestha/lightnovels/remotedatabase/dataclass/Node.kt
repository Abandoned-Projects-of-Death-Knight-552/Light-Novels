package com.knightshrestha.lightnovels.remotedatabase.dataclass

data class Node(
    val alias: List<String>,
    val author: String?,
    val download: String,
    val genres: List<String>,
    val seriesID: Int,
    val synopsis: String,
    val thumbnail: String,
    val title: String
)