<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>大茂抽奖</title>

<script type="text/javascript" src="/static/js/jquery-3.1.1.min.js"></script>
<#--<script type="text/javascript" src="/static/js/jquery-1.8.3.min.js"></script>-->

<style>
*{margin:0;padding:0;}
img{display:block;}
i{font-style:normal;}
.vetically{justify-content:center;align-items:center;display:-webkit-flex;}
.prize_con{position: absolute;width: 100%;height: 100%;background: url(/static/img/award/firstp_bg.jpg) no-repeat left top / 100% 100%;overflow: hidden;}
.prize_grade{font-size:98px;color: #ffe9af;text-align: center;margin: 60px auto 0;}
.prize_list{width:55%;height:400px;margin: 20px auto 55px;text-align: center;overflow: hidden;}
.prize_list ul{width:100%;font-size:0;}
.prize_list li{display:inline-block;font-size:45px;color:#f1bf90;text-align: center;width:20%;line-height:100px;font-family:Arial;font-weight: bold}
.start{width: 250px;height: 90px;margin:200px auto;cursor:pointer;}
.prize_set{position: absolute;right: 60px;bottom:140px;font-size: 16px;color: #f7f3e8;line-height: 30px;}
.prize_set li{display: inline-block;margin-left: 20px;}
.set_grade select,.set_people input, .set_money input{background: #fff;width:150px;height:36px;border:1px solid #8f0000;margin-left: .1rem;color: #000;padding-left:10px;}
.set_dept select,.set_people input, .set_money input{background: #fff;width:110px;height:36px;border:1px solid #8f0000;margin-left: .1rem;color: #000;padding-left:10px;}
</style>

</head>
<body>

<div class="wrap">
  <div class="prize_con">
      <p class="prize_grade"><span>开始抽奖</span></p>
      <#--<p class="prize_grade"><span>开始抽奖</span> <i>0</i>元</p>-->
        <div class="prize_list vetically">
          <ul id="ul">
          </ul>
        </div>
      <p class="start"><img src="/static/img/award/prize_start.png" alt=""></p>
      <ul class="prize_set">
          <li class="set_dept">部门
              <select id="set_dept">
                  <option>选择部门</option>
                  <#list departments as department>
                      <option>${department}</option>
                  </#list>
              </select>
          </li>
          <li class="set_grade">奖等
            <select id="set_grade">
              <option>选择奖等</option>
              <#--<#list awards as award>-->
                  <#--<option id="${award.totalNum}">${award.level}</option>-->
              <#--</#list>-->
            </select>
          </li>
          <li class="set_people">人数<input type="tel" id="prizeCount" readonly></li>
          <#--<li class="set_money">金额<input type="tel" placeholder="输入中奖金额" id="prizeMoney"></li>-->
      </ul>
  </div>
</div>
<input type="hidden" value="0" id="prize_btn">
<script>
  var myNumber;
  var arr = [];
  var peopleArr=[];
  var showPeopleArr = [];
  var ndArr = [];
  //数组删除元素
  function arrayRemoveItem(arr, delVal) {
      if (arr instanceof Array) {
          var index = arr.indexOf(delVal);
          if (index > -1) {
              arr.splice(index, 1);
          }
      }
  }
  //生成固定范围随机数
  function randomNum(minNum,maxNum){
      switch(arguments.length){
          case 1:
              return parseInt(Math.random()*minNum+1,10);
              break;
          case 2:
              return parseInt(Math.random()*(maxNum-minNum)+minNum,10);
              break;
          default:
              return 0;
              break;
      }
  }

  /*随机所有的code并且不重复*/
  function showRandomNum(num) {
    for(var i = 0; i < showPeopleArr.length; i++){
      arr[i] = i;
    }
    //打乱 总人员排序 用于显示
    arr.sort(function(){
      return 0.5 - Math.random();
    });

      var li = "";

      for(var i = 0; i < num; i++){
          var index = arr[i];
          li += '<li>'+
                  // '<p>'+info[0]+" "+info[1]+" "+info[2]+'</p>'+
                  '<p>'+showPeopleArr[index]+'</p>'+
                  '</li>';

      }

    $(".prize_list ul").html(li);
  }



  $(function () {

      $("#set_dept").change(function () {
          //获取奖励等级数量
          $.ajax({
              url: "/award/getAwardLevel?department="+$("#set_dept").val(),
              type: 'GET',
              contentType: "application/json;charset=utf-8",
              dataType: "json",
              success: function (r) {
                  if (r.code === 0) {
                      var obj = r.obj;
                      peopleArr = r.obj2;
                      showPeopleArr = r.obj3;
                      // console.log(peopleArr);
                      var option = "";
                      option += "<option>选择奖等</option>";
                      for(var i=0;i<obj.length;i++){
                          option += "<option value='"+obj[i].deptNum+"'>"+obj[i].level+"-"+obj[i].levelDesc+"</option>";
                      }
                      $("#set_grade").empty();
                      $("#set_grade").html(option);
                  }
              }
          })
      })

      $("#set_grade").change(function () {
        //获取奖励等级数量
          $.ajax({
              url: "/award/getAwardNum",
              type: 'POST',
              data: JSON.stringify({
                  dept:$("#set_dept").val(),
                  award:$("#set_grade").find("option:selected").text()
              }),
              contentType: "application/json;charset=utf-8",
              dataType: "json",
              success: function (r) {
                  if (r.code === 0) {
                      var awardInfo = r.obj;
                      $(".prize_grade span").text(r.obj2);
                      if(null!=awardInfo&&""!=awardInfo){
                          ndArr = awardInfo.split("|");
                      }else{
                          ndArr = [];
                      }

                  }
              }
          })
          $("#prizeCount").val($(this).find("option:checked").attr("value"));
      })


    $(".start").click(function(){
      if($("#prize_btn").val() == 0){
        if($("#set_grade").val() == "选择奖等") {
            alert("请选择奖等");
            return;
        }
        else{
          $("#prize_btn").val(1);
          // var num = $("#prizeCount").val();
          $(this).find("img").attr("src","/static/img/award/prize_stop.png");

          myNumber = setInterval(function(){
            showRandomNum($("#prizeCount").val());
          }, 30);
        }        
      }else{
          //按下停止按钮
        $("#prize_btn").val(0);
        clearInterval(myNumber);
        $(this).find("img").attr("src","/static/img/award/prize_start.png");


        //如果ndArr 为空
          var ndSize = 0;
          if(ndArr.length>0){
              ndSize = ndArr.length;
          }

        var randomArr = [];
        var randomSize = $("#prizeCount").val();

        while (randomArr.length<randomSize-ndSize){
            var random = randomNum(0,peopleArr.length);
            if(randomArr.indexOf(random)>-1){
                continue;
            }else{
                randomArr.push(random);
            }

        }
        console.log("peopleArr="+peopleArr);
        console.log("randomArr="+randomArr);
        // 修改屏幕显示信息
          var li = "";

          for(var i = 0; i < randomArr.length; i++){
              var index = randomArr[i];
              li += '<li>'+
                      '<p>'+peopleArr[index]+'</p>'+
                      '</li>';

          }
          if(ndArr.length>0){
              for(var i = 0; i < ndArr.length; i++){
                  li += '<li>'+
                          '<p>'+ndArr[i]+'</p>'+
                          '</li>';

              }
          }
          // ndArr=[];

        $(".prize_list ul").html(li);

        var lisArr = [];
        $("#ul li").each(function () {
            var li = this.innerHTML;
            li = li.replace("<p>","");
            li = li.replace("</p>","");
            lisArr.push(li);
        });

        console.log("抽中人数："+lisArr);
        //将抽中人员移出数组
          for(var i=0;i<lisArr.length;i++){
              arrayRemoveItem(peopleArr,lisArr[i]);
          }

        //修改抽中人员状态
          $.ajax({
              url: "/award/updateWinPeople",
              type: 'POST',
              data: JSON.stringify({
                  names:lisArr.join("|"),
                  dept:$("#set_dept").val(),
                  award:$("#set_grade").find("option:selected").text()
              }),
              contentType: "application/json;charset=utf-8",
              dataType: "json",
              success: function (r) {

              }
          })
      }
    });

    //回车键控制开始和停止
    $(document).keydown(function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            $(".start").click();
        }
    });

    // $("#set_grade").change(function(){
    //   $(".prize_grade span").text($(this).val());
    // });

    $("#prizeMoney").keyup(function(){
      $(".prize_grade i").text($(this).val());
    });
}); 
</script>

</body>
</html>
