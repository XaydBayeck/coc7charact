
function confirm(enable) {
    Attribute.showButton = enable;
    information.showButton = enable;
    const decButton = age.children[1].children[0];
    const addButton = age.children[1].children[2];
    decButton.style.display = enable ? "" : "none";
    addButton.style.display = enable ? "" : "none";
    ageConfirm.isShow = !enable;
}

function configChange() {
    const age = information.infors[4].value;
    let changes;
    if (age >= 15) {
        ageConfirm.bigger15 = true;
        if (age < 20) {
            changes = [0, 5, 5];
            const edu = Attribute.attrs[7];
            const changeNum = edu.value - changes[2];
            edu.value = changeNum > 0 ? changeNum : 0;
            Attribute.luckyTimes +=  1;
        } else {
            if (age < 40) {
                changes = [1, 0, 0];
            } else if (age < 50) {
                changes = [2, 5, 5];
            } else if (age < 60) {
                changes = [3, 10, 10];
            } else if (age < 70) {
                changes = [4, 20, 15];
            } else if (age < 80) {
                changes = [4, 40, 20];
            } else if (age < 90) {
                changes = [4, 80, 25];
            } else {
                ageConfirm.bigger15 = false;
                changes = [0, 0, 0];
            }
        }
        const app = Attribute.attrs[4];
        const changeNum = app.value - changes[2];
        app.value = changeNum > 0 ? changeNum : 0;
    } else {
        ageConfirm.bigger15 = false;
        changes = [0, 0, 0];
    }

    ageConfirm.change = changes;

    ageConfirm.changeAttrs[0].before = Attribute.attrs[0].value;
    ageConfirm.changeAttrs[1].before = Attribute.attrs[1].value;
    ageConfirm.changeAttrs[2].before = Attribute.attrs[3].value;

    ageConfirm.changeAttrs[0].value = 0;
    ageConfirm.changeAttrs[1].value = 0;
    ageConfirm.changeAttrs[2].value = 0;
}

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
        ],
        showButton: true
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

            confirm(false);
            configChange();
        },
        sub: function () {
            this.infors[4].value --;
        },
        add: function () {
            this.infors[4].value ++;
        },
        reEdit: function () {
            confirm(true);
            const age = information.infors[4].value;
            if (age<20) {
                Attribute.attrs[7].value += 5;
            } else {
                const changeApp = ageConfirm.change[2];
                Attribute.attrs[4].value += changeApp;
            }

            Attribute.attrs[0].value = ageConfirm.changeAttrs[0].before;
            Attribute.attrs[1].value = ageConfirm.changeAttrs[1].before;
            Attribute.attrs[3].value = ageConfirm.changeAttrs[2].before;

            Attribute.luckyTimes = 1;
            Attribute.attrs2[3].value = 0;
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
        },
        submit: function () {
            // console.log(jobVue.job);
            // console.log(jobVue.job.profsPoint);
            this.selectJob = { "profsPoint": jobVue.job.profsPoint};
            // console.log(this.selectJob);
            axios.post('/post/character/Job', this.selectJob)
                .then(function (response) {
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
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
