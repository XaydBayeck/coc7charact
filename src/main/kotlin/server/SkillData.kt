package server

import kotlinx.serialization.Serializable

@Serializable
data class SkillData(
        val name: String,
        val kind: String,
        val probability: Int,
        val description: String,
        val levelDescription: String,
        val normalLevel: String,
        val hardLevel: String,
        val allEgg: String,
        val allEggBreak: String,
        val madEggBreak: String
)