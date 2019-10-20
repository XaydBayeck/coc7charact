package server

class Skill {
    var name: String = "技能名称"
    var kind: Kind = Kind.NORMAL
    var probability: Int = 0
    var description: String = "技能描述"
    var levelDescription: String = "难度描述"
    var normalLevel: String = "普通难度"
    var hardLevel: String = "困难难度"
    var allEgg: String = "孤注一掷"
    var allEggBreak: String = "孤注一掷失败"
    var madEggBreak: String = "一名疯狂的调查员孤注一掷失败时"

    enum class Kind {
        NORMAL, PROFESSION, IRREGULAR;
    }

    constructor()

    constructor(SkillData: SkillData) {
        name = SkillData.name
        kind = kindValueOf(SkillData.kind)
        probability = SkillData.probability
        levelDescription = SkillData.levelDescription
        normalLevel = SkillData.normalLevel
        hardLevel = SkillData.hardLevel
        allEgg = SkillData.allEgg
        allEggBreak = SkillData.allEggBreak
        madEggBreak = SkillData.madEggBreak
    }

    private fun kindValueOf(value: String): Kind {
        return when (value) {
            "普通技能" -> Kind.NORMAL
            "专业化" -> Kind.PROFESSION
            "非常规" -> Kind.IRREGULAR
            else -> Kind.NORMAL
        }
    }

    override fun toString(): String {
        var str = "-----------------------------------------------------------\n"
        str += "|技能名称:$name|初始成功率:$probability%|技能分类:$kind|\n"
        str += "----------------------------------------------------------"
        return str
    }
}