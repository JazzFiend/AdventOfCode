function Container(capacity) {
  this.capacity = capacity;
}

Container.prototype = {
  constructor: Container,

  getCapacity: function() {
    return this.capacity;
  }
}

module.exports = Container;
