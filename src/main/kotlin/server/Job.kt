package server

import kotlinx.serialization.Serializable

@Serializable
data class Job(
        val name: String,
        val description: String,
        val profsPoint: ProfsPoint,
        val belief: Belief,
        val relationShip: String,
        val proSkill: ArrayList<String>,
        val kind:String,
        val skillPoint:String
) {

    @Serializable
    data class ProfsPoint(
            val skill1: String,
            val mag1: Int,
            val skill2: String,
            val orSkill2: String,
            val mag2: Int
    )

    @Serializable
    data class Belief(
            val max: Int,
            val min: Int
    )

    override fun toString(): String {
        var str = "=========== 职业信息=============\n"
        str += "职业名称: $name\n"
        str += "职业技能点: $skillPoint"
//        str += "职业技能点: ${profsPoint.skill1}x${profsPoint.mag1}"
//        if (profsPoint.mag2 != 0) {
//            str += if (profsPoint.skill2 == profsPoint.orSkill2) {
//                "，${profsPoint.skill2}x${profsPoint.mag2}"
//            } else {
//                "，${profsPoint.skill2}或${profsPoint.orSkill2}x${profsPoint.mag2}"
//            }
//        }
        str += "\n信用范围: ${belief.min}-${belief.max}\n"
        str += "推荐关系人: $relationShip\n"
        str += "职业技能: "
        for (skill in proSkill) {
            str += "$skill "
        }
        str += "\n=========== 职业信息============="
        return str
    }
}