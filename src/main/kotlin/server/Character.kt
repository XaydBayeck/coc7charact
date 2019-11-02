package server

/**
 * server.Character
 */
class Character {

    private var chInfo: ChInform = ChInform()
        set(value) {
            field = value.copySelf()
        }


    private var chAttr: ChAttr = ChAttr()
        set(value) {
            field = value.copySelf()
        }


    private var attr: Attr = Attr()
        set(value) {
            field = value.copySelf()
        }

    constructor()

    constructor(chInfo: CharacterData.ChInform, chAttr: CharacterData.ChAttr, attr: CharacterData.Attr) {
        this.chInfo = ChInform(chInfo)
        this.chAttr = ChAttr(chAttr)
        this.attr = Attr(attr)
    }

    constructor(characterData: CharacterData) {
        chInfo = ChInform(characterData.chInfo)
        chAttr = ChAttr(characterData.chAttr)
        attr = Attr(characterData.attr)
    }

    override fun toString(): String {
        var str = "<--=== === ===角色卡信息=== === ===-->\n"
        str += chInfo.toString()
        str += chAttr.toString()
        str += attr.toString()
        str += "<--=== === ===角色卡信息=== === ===-->\n"
        return str
    }

    fun toHtml(): String {
        var str = "<h5><--=== === ===角色卡信息=== === ===--></h5>"
        str += chInfo.toHtml()
        str += chAttr.toHtml()
        str += attr.toHtml()
        str += "<h5><--=== === ===角色卡信息=== === ===--></h5>"
        return str
    }


    /**
     * Interface
     */
    interface Chem {

        /**
         *
         * @param num  传入参数
         * @param max  允许的最大值
         * @param min  允许的最小值
         * @param mode 模式1：闭区间，模式2：开区间，模式3：前闭后开区间，模式4：前开后闭区间
         * @return 返回被允许的参数
         */
        fun setWay(num: Int, max: Int, min: Int, mode: Int): Int {

            if (mode == 1) {
                if (num in min..max) {
                    return num
                }
            } else if (mode == 2) {
                if (num > max && num > min) {
                    return num
                }
            } else if (mode == 3) {
                if (num in min until max) {
                    return num
                }
            } else if (mode == 4) {
                if (num in max until min) {
                    return num
                }
            } else {
                throw IllegalArgumentException()
            }
            throw IllegalArgumentException()
        }

        fun copySelf(): Chem
    }

    class ChInform : Chem {


        private var plName: String = "角色名字"

        private var pcName: String = "玩家名字"

        private var sex: String = "性别"

        private var job: String = "职业"

        private var age: Int = 0
            set(value) {
                field = setWay(value, 99, 0, 1)
            }

        private var center: String = "时代"

        private var addr: String = "地址"

        private var home: String = "故乡"

        constructor()

        constructor(inform: CharacterData.ChInform) {
            plName = inform.plName
            pcName = inform.pcName
            sex = inform.sex
            job = inform.job
            age = inform.age
            center = inform.center
            addr = inform.addr
            home = inform.home
        }

        override fun copySelf(): ChInform {
            return ChInform(CharacterData.ChInform(plName, pcName, sex, job, age, center, addr,
                    home))
        }

        override fun toString(): String {
            var str = "<--<--<--角色信息-->-->-->\n"
            str += "  角色姓名：$plName\n"
            str += "  玩家姓名：$pcName\n"
            str += "  性别：$sex\n"
            str += "  职业：$job\n"
            str += "  年龄：$age\n"
            str += "  时代：$center\n"
            str += "  地址：$addr\n"
            str += "  故乡：$home\n"
            str += "<--<--<--角色信息-->-->-->\n"
            return str
        }

        fun toHtml(): String {
            var str = "<p><--<--<--角色信息-->-->--><br>"
            str += "  角色姓名：$plName<br>"
            str += "  玩家姓名：$pcName<br>"
            str += "  性别：$sex<br>"
            str += "  职业：$job<br>"
            str += "  年龄：$age<br>"
            str += "  时代：$center<br>"
            str += "  地址：$addr<br>"
            str += "  故乡：$home<br>"
            str += "<--<--<--角色信息-->-->--></p><br>"
            return str
        }

    }

    class ChAttr : Chem {


        private var str = 0
            set(sTR) {
                field = setWay(sTR, 90, 15, 1)
            }

        var con: Int = 0
            set(cON) {
                field = setWay(cON, 90, 15, 1)
            }

        var siz: Int = 0
            set(sIZ) {
                field = setWay(sIZ, 90, 40, 1)
            }

        private var dex: Int = 0
            set(dEX) {
                field = setWay(dEX, 90, 15, 1)
            }

        private var app: Int = 0
            set(aPP) {
                field = setWay(aPP, 90, 15, 1)
            }

        private var edu: Int = 0
            set(eDU) {
                field = setWay(eDU, 90, 40, 1)
            }

        private var int: Int = 0
            set(iNT) {
                field = setWay(iNT, 90, 40, 1)
            }

        var pow: Int = 0
            set(pOW) {
                field = setWay(pOW, 90, 15, 1)
            }

        constructor()

        constructor(attrs: IntArray) {

            if (attrs.size == 8) {
                for (i in 0..7) {
                    setByNum(i, attrs[i])
                }
            } else {
                throw IllegalArgumentException("only 8 attributes!")
            }
        }

        constructor(chAttr: CharacterData.ChAttr) {
            str = chAttr.str
            con = chAttr.con
            siz = chAttr.siz
            dex = chAttr.dex
            app = chAttr.app
            int = chAttr.int
            pow = chAttr.pow
            edu = chAttr.edu
        }

        override fun copySelf(): ChAttr {
            val attrs = intArrayOf(str, con, siz, dex, app, int, pow, edu)
            return ChAttr(attrs)
        }

        private fun setByNum(num: Int, attr: Int) {
            when (num) {
                0 -> str = attr
                1 -> con = attr
                2 -> siz = attr
                3 -> dex = attr
                4 -> app = attr
                5 -> int = attr
                6 -> pow = attr
                7 -> edu = attr

                else -> throw IllegalArgumentException("only 8 attributes!")
            }
        }

        override fun toString(): String {
            var str = "<--<--<--<--角色属性-->-->-->-->\n"
            str += "力量：${this.str}  "
            str += "体质：$con\n"
            str += "体型：$siz  "
            str += "敏捷：$dex\n"
            str += "外貌：$app  "
            str += "智力：$int\n"
            str += "意志：$pow  "
            str += "教育：$edu\n"
            str += "<--<--<--<--角色属性-->-->-->-->\n"
            return str
        }

        fun toHtml(): String {
            var str = "<p><--<--<--<--角色属性-->-->-->--><br>"
            str += "力量：${this.str}  "
            str += "体质：$con<br>"
            str += "体型：$siz  "
            str += "敏捷：$dex<br>"
            str += "外貌：$app  "
            str += "智力：$int<br>"
            str += "意志：$pow  "
            str += "教育：$edu<br>"
            str += "<--<--<--<--角色属性-->-->-->--></p><br>"
            return str
        }
    }

    class Attr : Chem {


        private var hp: Int = 0
            set(value) {
                field = setWay(value, hpm, 0, 1)
            }
        private var hpm = 90

        private var mp: Int = 0
            set(value) {
                field = setWay(value, mpm, 0, 1)
            }
        private var mpm = 90

        private var san: Int = 0
            set(value) {
                field = setWay(value, 99, 0, 1)
            }

        private var luck: Int = 0
            set(value) {
                field = setWay(value, 90, 15, 1)
            }

        constructor()

        constructor(attr: CharacterData.Attr) {
            hp = attr.hp
            hpm = attr.hpm
            mp = attr.mpm
            mpm = attr.mp
            san = attr.san
            luck = attr.luck
        }

        override fun copySelf(): Attr {
            return Attr(CharacterData.Attr(hp, hpm, mp, mpm, san, luck))
        }

        override fun toString(): String {
            var str = "<--<--<--属性-->-->-->\n"
            str += "HP：$hp/$hpm  "
            str += "MP：$mp/$mpm\n"
            str += "San：$san  "
            str += "Luck：$luck\n"
            str += "<--<--<--属性-->-->-->\n"
            return str
        }

        fun toHtml(): String {
            var str = "<p><--<--<--属性-->-->--><br>"
            str += "HP：$hp/$hpm  "
            str += "MP：$mp/$mpm<br>"
            str += "San：$san  "
            str += "Luck：$luck<br>"
            str += "<--<--<--属性-->-->--></p><br>"
            return str
        }
    }
}
