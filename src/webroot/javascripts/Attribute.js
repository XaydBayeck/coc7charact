

function levelCheck(nums, target) {
  var level = 0;
  nums = nums || 0;
  for (var i = 0; i < nums.length; i++) {
    if (target < nums[i]) {
      level = i;
      break;
    }
  }
  return level;
}

function ranger(attr) {
  var nums = attr.nums;
  return levelCheck(nums, attr.value)
}

function rollDice(num, dice, plus = 0) {
  var sum = plus;
  for (var i = 0; i < num; i++) {
    sum += parseInt(Math.random() * dice + 1, 10);
  }
  return sum;
}

var app = new Vue({
  el: "#app",
  data: {
    attrs: [
      {
        name: "力量(STR)",
        value: 0,
        nums: [0, 15, 50, 90, 99, 140, 200],
        text: [
          "衰弱：没法站起来甚至端起一杯茶｡ ",
          "弱者,虚弱｡",
          "普通人水平",
          "你见过的力气最大的人｡ ",
          "世界水平(奥赛举重冠军)｡人类极限｡",
          "超越人类之力(例如大猩猩或马)｡",
          "怪物之力(例如格拉基,见14章) "
        ],
        currentText: "衰弱：没法站起来甚至端起一杯茶｡ ",
        roll: function () {
          return rollDice(3, 6, 0) * 5;
        }
      },
      {
        name: "体质(CON)",
        value: 0,
        nums: [0, 1, 15, 50, 90, 99, 140, 200],
        text: [
          "死亡。",
          "病弱。卧床不起,没有协助就无法自已。",
          "健康堪忧,经常需躺下休息,常年疼痛缠身。",
          "普通人水平。",
          "不惧寒冷。强壮而精神。",
          "铁之刚体。可以承受住最强的疼痛。人类极限。",
          "超越人类之体格(大象)。",
          "怪物之体,免疫大部分地球疾病。"
        ],
        currentText: "死亡。",
        roll: function () {
          return rollDice(3, 6, 0) * 5;
        }
      },
      {
        name: "体型(SIZ)",
        value: 0,
        nums: [1, 15, 65, 80, 99, 150, 180, 200],
        text: [
          "一名婴儿。(1-12磅)",
          "孩童,或身短体瘦(矮人)(33磅/15kg)｡ ",
          "普通人类体型(中等身高和体重)(170磅/75kg)｡",
          "非常高,强健的体格或非常胖(240磅/110kg)｡",
          "某方面已经是超大号了(330磅/150kg)｡",
          "马或牛(960磅/436kg)｡",
          "记录中最重的人类(1400磅/634kg)｡",
          "1920磅/872kg(例如昌格纳·方庚)"
        ],
        currentText: "一名婴儿。(1-12磅)",
        roll: function () {
          return rollDice(2, 6, 6) * 5;
        }
      },
      {
        name: "敏捷(DEX)",
        value: 0,
        nums: [0, 15, 50, 90, 99, 120, 200],
        text: [
          "没有协助无法移动",
          "缓慢,笨拙,无法行动自如",
          "普通人水平｡",
          "高速而灵活,可以达成超凡的技艺(例如杂技演员,伟大的舞者)｡ ",
          "世界级运动员｡人类极限｡",
          "超越人类之速(例如虎)｡",
          "闪电之速,可以在人类反应过来之前完成一系列动作。"
        ],
        currentText: "没有协助无法移动",
        roll: function () {
          return rollDice(3, 6, 0) * 5;
        }
      },
      {
        name: "外貌(APP)",
        value: 0,
        nums: [0, 15, 50, 90, 99],
        text: [
          "如此的难看｡他人会对你报以恐惧､厌恶和怜悯｡",
          "挫｡估计是因为受伤事故或先天如此｡",
          "普通人水平｡ ",
          "你见过的最漂亮的人,有着天然的吸引力｡ ",
          "魅力和酷的巅峰(超级名模或世界影星)｡人类极限｡"
        ],
        currentText: "如此的难看｡他人会对你报以恐惧､厌恶和怜悯｡",
        roll: function () {
          return rollDice(3, 6, 0) * 5;
        }
      },
      {
        name: "智力(INT)",
        value: 0,
        nums: [0, 15, 50, 90, 99, 140, 210],
        text: [
          "没有智商,无法理解周遭的世界｡",
          "学得很慢,只能理解最常用的数字,或阅读学前教育级别的书｡ ",
          "普通人水平｡",
          "超凡之脑,可以理解多门语言或法则｡ ",
          "天才(爱因斯坦､达芬奇､特斯拉等等)｡人类极限｡ ",
          "超越人类之智(例如远古者)｡ ",
          "怪物之智,可以理解并操作多重次元(例如伟大的克苏鲁)。"
        ],
        currentText: "没有智商,无法理解周遭的世界｡",
        roll: function () {
          return rollDice(2, 6, 6) * 5;
        }
      },
      {
        name: "意志(POW)",
        value: 0,
        nums: [0, 15, 50, 90, 100, 140, 210],
        text: [
          "弱者的心,没有意志力,没有魔法潜能｡ ",
          "意志力弱,经常成为高智力或高意志人士的人偶或玩物。",
          "普通人｡ ",
          "坚强的心,对沟通不可视之物和魔法有着高潜质｡",
          "钢铁之心,与灵能领域和不可视世界有着强烈的链接｡ ",
          "超越人类,基本上是异界存在(例如依格,见14章)｡",
          "怪物的魔法潜质和力量,超越凡人之理解力(例如伟大之克苏鲁)｡",
          ""
        ],
        currentText: "弱者的心,没有意志力,没有魔法潜能｡ ",
        roll: function () {
          return rollDice(3, 6, 0) * 5;
        }
      },
      {
        name: "教育(EDU)",
        value: 0,
        nums: [0, 15, 60, 70, 80, 90, 96, 99],
        text: [
          "新生儿｡",
          "任何方面都没有受过教育｡",
          "高中毕业｡",
          "大学毕业(专科学位)｡",
          "研究生毕业(硕士学位)｡",
          "博士学位,教授｡",
          "某研究领域的世界级权威｡",
          "人类极限｡"
        ],
        currentText: "新生儿｡",
        roll: function () {
          return rollDice(2, 6, 6) * 5;
        }
      }
    ],
    attrs2: [
      {
        name: "HP",
        value: 0,
      },
      {
        name: "MP",
        value: 0
      },
      {
        name: "San",
        value: 0
      },
      {
        name: "幸运(luck)",
        value: 0,
        roll: function () {
          return rollDice(3, 6, 0) * 5;
        }
      }
    ]
  },
  methods: {
    update: function (attr) {
      attr.currentText = attr.text[ranger(attr)];
      this.attrs2[0].value = parseInt((this.attrs[1].value + this.attrs[2].value) / 10);
      this.attrs2[1].value = parseInt((this.attrs[6].value) / 5);
      this.attrs2[2].value = this.attrs[6].value;
    },
    sub: function (attr) {
      var index = this.attrs.indexOf(attr);
      if (this.attrs[index].value > 0) {
        this.attrs[index].value--;
      }
      this.update(attr);
    },
    add: function (attr) {
      var index = this.attrs.indexOf(attr);
      if (this.attrs[index].value < 99) {
        this.attrs[index].value++;
      }
      this.update(attr);
    },
    rollAttrs: function () {
      for (var i = 0; i < this.attrs.length; i++) {
        this.attrs[i].value = this.attrs[i].roll();
        this.update(this.attrs[i]);
      }
    },
    rollLucky: function () {
      this.attrs2[3].value = this.attrs2[3].roll();
    },
    submit: function () {
      var character = {
        "chAttr": {
          "str": this.attrs[0].value,
          "con": this.attrs[1].value,
          "siz": this.attrs[2].value,
          "dex": this.attrs[3].value,
          "app": this.attrs[4].value,
          "edu": this.attrs[5].value,
          "int": this.attrs[6].value,
          "pow": this.attrs[7].value
        },
        "attr": {
          "hp": this.attrs2[0].value,
          "hpm":this.attrs2[0].value,
          "mp": this.attrs2[1].value,
          "mpm":this.attrs2[1].value,
          "san":this.attrs2[2].value,
          "luck": this.attrs2[3].value
        }
      };
      console.log(character);
      axios.post('./Information.html',character)
        .then(function (response){
          console.log(response);
        })
        .catch(function(error){
          console.log(error);
        })
    }
  }
});

