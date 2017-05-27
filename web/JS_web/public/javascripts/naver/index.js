

function draw_word_cloud(_frequency_list,_id){
        var color = d3.scale.linear()
           // .domain([0,1,2,3,4,5,6,10,15,20,100])
            .domain([1000,20,15,10,6,5,4,3,2,1,0])
            .range(["#ddd", "#ccc", "#bbb", "#d1cba5", "#999", "#c9d899", "#acefcd", "#bce4e5", "#809cdd", "#764ace", "#9e197a", "#841120"]);
           // .range(["#ddd", "#ccc", "#bbb", "#aaa", "#999", "#888", "#777", "#666", "#555", "#444", "#333", "#222"]);

    d3.layout.cloud().size([800, 300])
            .words(_frequency_list)
            .rotate(0)
            .fontSize(function(d) { return d.size; })
            .on("end", draw)
            .start();
            
    function draw(words) {
        d3.select(_id).append("svg")
                .attr("width", 1500)
                .attr("height", 500)
                .append("g")
                .attr("transform", "translate(320,200)")
                .selectAll("text")
                .data(words)
                .enter().append("text")
                .style("font-size", function(d) { return d.size + "px"; })
                .attr("id", function(d) { return d.text;})
                .style("fill", function(d, i) { return color(i); })
                .attr("transform", function(d) {
                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                })
                .text(function(d) { return d.text; })
    }
}




console.log("index.js included")

var word2vec = []
var cur_univ='';
var tf_idf = []
var vec_list = []
function run_word2vec(univ,keyword){
    console.log(univ+"," + keyword)
   // alert(univ+" / "+keyword)

        $.ajax({
        type: 'POST',     
        url: '/get_word2vec',
        dataType:'text',
        data: { 
            'univ':univ,
            'keyword':keyword
        },
        success: function (result) {
            if (result) {
                vec_list = result;
                alert("finish!")
                show_vector()
            }
            else{
                alert("success");
            }
        } // success:result
    });//ajax 


}
function run_TF_IDF(univ){
 
   // alert(univ+" clicked!")

        $.ajax({
        type: 'POST',     
        url: '/get_keyword',
        dataType:'text',
        data: { 
            'univ':univ
        },
        success: function (result) {
            if (result) {
                tf_idf = result;
                cur_univ = univ;
                show_ranking()
            }
            else{
                alert("success");
            }
        } // success:result
    });//ajax 

}
function show_vector(){
    $('#place_2').empty()
    var keyword,score;
    var result=[];
    var elem = vec_list.split("\\n");
    for(var i = 0; i < elem.length-1; i++){
        if(i==0){
           keyword = vec_list.split("\\n")[0].split("[\"")[1].split(":")[0]
           score = vec_list.split("\\n")[0].split("[\"")[1].split(":")[1]
        }
        else{
           keyword= vec_list.split("\\n")[i].split(":")[0]
           score= vec_list.split("\\n")[i].split(":")[1]

        }
        var k = i==0? 1 : i;
        k++;
        score =  Number(parseFloat(score)*100)
        var input = Number(score)* (Math.log(3)/Math.log(k))
        var temp = {"text":keyword,"size":input}
        result.push(temp)


        
        var para = $('<div id = "'+keyword+'" onclick=console.log("'+cur_univ+'","'+keyword+'")> '+keyword+' , ' +score+ ' </div>')
        para.appendTo('#place_2')
    }
    
    $('#vector_cloud').empty();
    draw_word_cloud(result,"#vector_cloud")
}


function show_ranking(){
    $('#place_1').empty()
    $('#place_2').empty()
    var keyword,score;
    var result=[];
    for(var i = 0; i < tf_idf.length; i++){
        if(i==10) break
        if(i==0){
           keyword = tf_idf.split("\\n")[0].split("[\"")[1].split(":")[0]
           score = tf_idf.split("\\n")[0].split("[\"")[1].split(":")[1]
        }
        else{
           keyword= tf_idf.split("\\n")[i].split(":")[0]
           score= tf_idf.split("\\n")[i].split(":")[1]

        }
        var k = i==0? 1 : i;
        k++;
        var input = Number(score)* (Math.log(3)/Math.log(k))/5
        var temp = {"text":keyword,"size":input}
        result.push(temp)


        
        var para = $('<div id = "'+keyword+'" onclick=run_word2vec("'+cur_univ+'","'+keyword+'")> '+keyword+' , ' +score+ ' </div>')
        para.appendTo('#place_1')
    }
    
    $('#word_cloud').empty();
    $('#vector_cloud').empty();
    draw_word_cloud(result,"#word_cloud")
    
   
}
