
var tool = require('cloneextend')
    , conf = {};
    conf.defaults = {
        server : {
            port : 3000
        }
    };

exports.get = function get(env, obj){
   var settings = tool.cloneextend(conf.defaults, conf[env]);
   return ('object' === typeof obj) ? tool.cloneextend(settings, obj) : settings;
}
