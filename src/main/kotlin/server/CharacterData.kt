package server

import kotlinx.serialization.Serializable

@Serializable
data class CharacterData(
        val chInfo: ChInform,
        val chAttr: ChAttr,
        val attr: Attr
) {
    @Serializable
    data class ChAttr(
            val str: Int,
            val con: Int,
            val siz: Int,
            val dex: Int,
            val app: Int,
            val edu: Int,
            val int: Int,
            val pow: Int
    )

    @Serializable
    data class ChInform(

            val plName: String,
            val pcName: String,
            val sex: String,
            val job: String,
            val age: Int,
            val center: String,
            val addr: String,
            val home: String
    )

    @Serializable
    data class Attr(
            val hp: Int,
            val hpm: Int,
            val mp: Int,
            val mpm: Int,
            val san: Int,
            val luck: Int
    )

}

