var crypto = require('crypto');

var secretKey = "ckczppom";
var appendedKey = 0;

var correctHash = false;

while(!correctHash) {
  appendedKey = appendedKey + 1;
  var combinedKey = secretKey + appendedKey.toString();
  var md5Hash = crypto.createHash('md5').update(combinedKey).digest("hex");
  if(md5Hash[0] === '0' &&
    md5Hash[1] === '0' &&
    md5Hash[2] === '0' &&
    md5Hash[3] === '0' &&
    md5Hash[4] === '0' &&
    md5Hash[5] === '0') {
      correctHash = true;
    }
}

console.log(appendedKey);
