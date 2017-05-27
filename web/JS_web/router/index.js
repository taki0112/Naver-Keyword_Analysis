var express = require('express')
var router = express.Router()
var app = express();
// var worCloud = require('wordcloud');
var tagCloud = require('tag-cloud');


var fs = require("fs");





// routing 
router.get('/',function(req, res, next) {
  console.log('get /');
  res.redirect('/naver')
});


router.get('/naver',function(req, res, next) {
  console.log('get /naver/index.ejs');
  res.render('../view/naver/index.ejs');
});


var check = 1
var vec_list = [];

router.post('/get_word2vec', function (req, res, next) {
  console.log('post /get_word2vec');      
    vec_list = [];
    var univ = req.body['univ']
    var keyword = req.body['keyword']
      // excute java application

  var dir = 'router\\vector.jar'
  var exec = require('child_process').exec;
  exec('java -Dfile.encoding=UTF-8 -jar '+dir+' '+univ+' '+keyword, function(error, stdout, stderr) {

  console.log('stderr: ' + stderr);

  var f_dir = 'vector.txt'
  var file_data = fs.readFileSync(f_dir);
  vec_list.push(file_data.toString())

  res.send(vec_list)


    if (error !== null) {
        console.log('exec error: ' + error);
    }
  });

});

router.post('/get_keyword', function (req, res, next) {
  console.log('post /get_keyword');      
    _list = [];
    var univ = req.body['univ']
      // excute java application

  var dir = 'router\\keyword.jar'
  var exec = require('child_process').exec;
  exec('java -Dfile.encoding=UTF-8 -jar '+dir+' '+univ, function(error, stdout, stderr) {

    console.log('stderr: ' + stderr);
    var f_dir = 'tf_idf.txt'
    var file_data = fs.readFileSync(f_dir);
    _list.push(file_data.toString())

    res.send(_list)


    if (error !== null) {
        console.log('exec error: ' + error);
    }
  });

});




// use error check
app.use(function(err, req, res, next) {
  console.log("error check");
  console.log(err);
})

module.exports = router