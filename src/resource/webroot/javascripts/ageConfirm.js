const ageConfirm = new Vue({
    el: "#ageConfirm",
    data: {
        isShow: true,
        show15: true,
        showConfirm: false,
        change: [0, -5, -5],
        changeAttrs:[
            {
                name:"力量",
                value:0
            },
            {
                name:"体质",
                value:0
            },
            {
                name:"敏捷",
                value:0
            }
        ],
        EDURaise:this.change[0],
        decNum:this.change[1]
    },
    computed:{
        showEDURaise:function () {
           return this.EDURaise > 0;
        }
    }
});