

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
  data: {},
  methods: {
    submit: function () {}
  }
});

