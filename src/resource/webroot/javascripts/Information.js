const information = new Vue({
    el: "#information",
    data: {
        infors: [
            {
                name: "玩家",
                value: "小明"
            },
            {
                name: "角色姓名",
                value: "索拉尔"
            },
            {
                name: "性别",
                value: "猛男"
            },
            {
                name: "职业",
                value: "太阳骑士"
            },
            {
                name: "年龄",
                value: "24"
            },
            {
                name: "时代",
                value: "初次传火后"
            },
            {
                name: "住址",
                value: "传火祭祀场"
            },
            {
                name: "故乡",
                value: "阿斯特拉"
            }
        ]
    },
    methods: {
        submit: function () {
            const chInfo = {
                "plName": this.infors[0].value,
                "pcName": this.infors[1].value,
                "sex": this.infors[2].value,
                "job": jobVue.job,
                "age": this.infors[4].value,
                "center": this.infors[5].value,
                "addr": this.infors[6].value,
                "home": this.infors[7].value
            };
            console.log(chInfo);
            axios.post("/post/character/Information", chInfo)
                .then(function (response) {
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
            // TODO 添加下一个页面
            location.href = "#"
        },
        sub: function () {
            this.infors[4].value --;
        },
        add: function () {
            this.infors[4].value ++;
        }
    }
});

const defineSelf = {
    "name": "自定义",
    "description": "与KP商量自定义符合世界观背景的职业",
    "profsPoint": {
        "skill1": "教育",
        "mag1": 4,
        "skill2": "力量",
        "orSkill2": "力量",
        "mag2": 0
    },
    "skillPoint": "教育 X 4",
    "belief": {
        "max": 0,
        "min": 100
    },
    "relationShip": "无",
    "proSkill": [
        "暂无"
    ],
    "kind": "普通职业"
};

const jobVue = new Vue({
    el: "#job",
    data: {
        job: defineSelf
    }
});

const appDOM = document.getElementById("information");
const age = appDOM.children[4];
const ageHTML=age.children[1].childNodes[0];
age.removeChild(age.childNodes[1]);
const ageOutHTML = "<p class='name'>年龄</p><div class='input'><button class='dec' onclick='information.sub();'><span></span><div>-</div>" +
    "</button><label id='age'></label><button class='add' onclick='information.add();'><div>+</div><span></span></button></div>";
age.innerHTML = ageOutHTML;
age.children[1].childNodes[1].appendChild(ageHTML);
age.type = "number";
age.min = 0;

let job = appDOM.children[3];
job.removeChild(job.childNodes[1]);

const jobHTML = "<p class='name'>职业</p><label id='jobInput'><select v-model='selectJob' name='job' id='jobSelect' " +
    "@change='update()'><option :value='job.name' v-for='job in jobList'>{{ job.name }}</option></select></label>";
job.innerHTML = jobHTML;

const jobSelect = new Vue({
    el: "#jobSelect",
    data: {
        jobList: [defineSelf],
        selectJob: "自定义"
    },
    methods: {
        update: function () {
            const select = document.getElementById("jobSelect");
            const index = select.selectedIndex;
            jobVue.job = jobSelect.jobList[index];
        }
    },
    mounted() {
        axios
            .get("./json/joblist.json")
            .then(response => {
                this.jobList = response.data;
                // for (let i in this.jobList) {
                //     const aJob = this.jobList[i];
                //     aJob.skillPoint = aJob.profsPoint.skill1 + " X " + aJob.profsPoint.mag1;
                //     const skill2 = aJob.profsPoint.skill2;
                //     const orSkill2 = aJob.profsPoint.orSkill2;
                //     if (aJob.profsPoint.mag2 !== 0) {
                //         if (skill2 === orSkill2) {
                //             aJob.skillPoint += " + " + skill2 + " X " + aJob.profsPoint.mag2;
                //         } else {
                //             aJob.skillPoint += " + " + skill2 + "/" + orSkill2 + " X " + aJob.profsPoint.mag2;
                //         }
                //     }
                // }
            })
            .catch(function (error) {
                console.log(error);
            });
    }
});