var stackElements;

var initialize = function() {
  stackElements = [];
  return this;
}

var push = function(element) {
  stackElements.push(element);
}

var pop = function() {
  if(stackElements.length === 0) {
    return undefined;
  } else {
    return stackElements.pop();
  }
}

var peek = function() {
  return stackElements[stackElements.length - 1];
}

var isEmpty = function() {
  return (stackElements.length === 0);
}

var display = function() {
  console.log("Stack: ", stackElements);
}

module.exports = {
  initialize: initialize,
  push: push,
  pop: pop,
  peek: peek,
  isEmpty: isEmpty,
  display: display
};
