function Reindeer(reindeerName, speed, speedTime, restTime) {
  this.reindeerName = reindeerName;
  this.speed = Number(speed);
  this.speedTime = Number(speedTime);
  this.restTime = Number(restTime);
  this.state = 'FLYING';
  this.actionTime = 0;
  this.distanceTraveled = 0;
  this.points = 0;
}

Reindeer.prototype = {
  construtor: Reindeer,

  rest: function() {
    this.actionTime++;
    if(this.actionTime === this.restTime) {
      this.state = 'FLYING';
      this.actionTime = 0;
    }
  },

  fly: function() {
    this.actionTime++;
    this.distanceTraveled += this.speed;
    if(this.actionTime === this.speedTime) {
      this.state = 'RESTING';
      this.actionTime = 0;
    }
  },

  advanceSecond: function() {
    if(this.state === 'FLYING') {
      this.fly();
    } else if(this.state === 'RESTING') {
      this.rest();
    } else {
      throw("Invalid State");
    }
  },

  givePoint: function() {
    this.points++;
  },

  display: function() {
    console.log("Name: ", this.reindeerName, "Distance Traveled: ", this.distanceTraveled,
                "State: ", this.state, "Action Time: ", this.actionTime,
                "Points: ", this.points);
  },

  getDistance: function() {
    return this.distanceTraveled;
  },

  getPoints: function() {
    return this.points;
  }
}

module.exports = Reindeer;
