const ageConfirm = new Vue({
    el: "#ageConfirm",
    data: {
        isShow: false,
        bigger15: false,
        showConfirm: false,
        change: [0, 0, 0],
        changeAttrs: [
            {
                id: 0,
                name: "力量",
                before: 0,
                value: 1,
                show: true
            },
            {
                id: 1,
                name: "体质",
                before: 0,
                value: 1,
                show: true
            },
            {
                id: 3,
                name: "敏捷",
                before: 0,
                value: 1,
                show: true
            }
        ]
    },
    computed: {
        show15: function () {
            const age = information.infors[4].value;
            let showDEX = age > 19;
            this.changeAttrs[2].show = showDEX;
            return !showDEX;
        },
        sum: function () {
            return this.changeAttrs[0].value + this.changeAttrs[1].value + this.changeAttrs[2].value;
        },
        confirm: function () {
            const luckBool = Attribute.luckyTimes > 0;
            return !((this.change[0] > 0) || (this.sum < this.change[1]) || luckBool);
        }
    },
    methods: {
        add: function (attr) {
            attr.value += 1;
            const index = attr.id;
            const attribute = Attribute.attrs[index];
            attribute.value = attr.before - attr.value;
        },
        sub: function (attr) {
            attr.value -= attr.value <= 0 ? 0 : 1;
            const index = attr.id;
            const attribute = Attribute.attrs[index];
            attribute.value = attr.before - attr.value;
        },
        upperEDU: function () {
            if (this.change[0] > 0) {
                Vue.set(this.change, 0, this.change[0] - 1);
                const edu = Attribute.attrs[7];
                const upper = rollDice(1, 100, 0);
                const plus = rollDice(1, 10, 0);
                let finalValue = edu.value;
                finalValue += edu.value < upper ? 0 : plus;
                edu.value = finalValue > 99 ? 99 : finalValue;
            }
        },
        submit: function () {
            Attribute.submit();
            location.href = "./skill.html";
        }
    }
});
