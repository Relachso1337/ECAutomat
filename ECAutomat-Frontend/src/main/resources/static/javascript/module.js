export function writeHighscore(points) {
var obj = {
    table: []
  };
  
  obj.table.push({Highscore: points});
  var json = JSON.stringify(obj);
  var fs = require('fs');
  fs.writeFile('../../../../../../ECAutomat-Backend/src/main/resources/static/quiz.json', json, 'utf8', callback);
}