
const skillsheet = new Vue({
    el: "#skills",
    data: {
        showDescrip: false,
        descrip: {
            name: "名称",
            probability: "初始值",
            profession: "职业加点",
            inter: "兴趣加点",
            finalValue: "成功率"
        },
        skills: [],
        profesPoint: 114,
        interestPoint: 514,
        showPoint: false
    },
    methods: {
        sub: function (skill, value) {
            if (value === "profes") {
                skill.profession -= skill.profession > 0 ? 1 : 0;
            } else if (value === "inter") {
                skill.interest -= skill.interest >0 ? 1 : 0;
            }
        },
        add: function (skill, value) {
            if (value === "profes") {
                const bool = this.profesPoint > this.residualProfesPoint;
                skill.profession += bool ? 1 : 0;
            } else if (value === "inter") {
                const bool = this.interestPoint > this.residualInterestPoint;
                skill.interest += bool ? 1 : 0;
            }
        }
    },
    computed: {
        residualProfesPoint: function () {
            let usePoint = 0;
            for(let index in this.skills) {
                usePoint += this.skills[index].profession;
            }
            return usePoint;
        },
        residualInterestPoint: function () {
            let usePoint = 0;
            for(let index in this.skills) {
                usePoint += this.skills[index].interest;
            }
            return usePoint;
        }
    },
    mounted() {
        axios
            .get("./json/skill.json")
            .then(response => {
                const skillList = response.data;
                for(var index in skillList) {
                    skillList[index].finalValue = skillList[index].probability;
                    skillList[index].profession = 0;
                    skillList[index].interest = 0;
                }
                this.skills = skillList;
            })
            .catch(function (error) {
                console.log(error);
            });
    }
});

function setShowPoint(bool) {
  skillsheet.showPoint = bool;
}
