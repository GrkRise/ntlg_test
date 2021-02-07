package com.rise.grk.kotlin.ntlg_test

import android.graphics.Color

data class Data(
        var data : List<CourseInfo>
)

data class CourseInfo(
    var groups: List<Group>? = null,
    var direction: Direction? = null
)

data class Group(
        var id : String? = null,
        var link : String? = null,
        var badge: Badge? = null,
        var items: List<Item>? = null,
        var title: String? = null
)

data class Badge(
        var text : String? = null,
        var color: String? = null,
        var bgColor: String? = null
)

data class Item(
        var id : String? = null,
        var link : String? = null,
        var badge: Badge? = null,
        var title: String? = null

)

data class Direction(
        var id: String? = null,
        var link: String? = null,
        var badge: Badge? = null,
        var title: String? = null
)

