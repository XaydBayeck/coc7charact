const skillSheet = new Vue({
    el: "#skills",
    data: {
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
        sub: function(skill, value, num) {
            if (value === "profes") {
                skill.profession -= skill.profession > 0 ? num : 0;
            } else if (value === "inter") {
                skill.interest -= skill.interest > 0 ? num : 0;
            }
            skill.finalValue = skill.probability + skill.profession + skill.interest;
        },
        add: function(skill, value, num) {
            if (value === "profes") {
                const bool = this.profesPoint > this.residualProfesPoint;
                skill.profession += bool ? num : 0;
            } else if (value === "inter") {
                const bool = this.interestPoint > this.residualInterestPoint;
                skill.interest += bool ? num : 0;
            }
            skill.finalValue = skill.probability + skill.profession + skill.interest;
        }
    },
    computed: {
        residualProfesPoint: function() {
            let usePoint = 0;
            for (let index in this.skills) {
                usePoint += this.skills[index].profession;
            }
            return usePoint;
        },
        residualInterestPoint: function() {
            let usePoint = 0;
            for (let index in this.skills) {
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
                for (var index in skillList) {
                    skillList[index].finalValue = skillList[index].probability;
                    skillList[index].profession = 0;
                    skillList[index].interest = 0;
                    skillList[index].showDescrip = false;
                }
                this.skills = skillList;
            })
            .catch(function(error) {
                console.log(error);
            });
        axios
            .get("/character/skillPoint")
            .then(response => {
                const skillPoint = response.data;
                this.profesPoint = skillPoint.profsPoint;
                this.interestPoint = skillPoint.interestPoint;
            })
            .catch(function(error) {
                console.log(error);
            });
    }
});

function setShowPoint(bool) {
    skillSheet.showPoint = bool;
}
