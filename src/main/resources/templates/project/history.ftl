<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8" />
    <title>数据列表页面</title>
    <!-- layui.css -->
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <style>
        tr td:not(:nth-child(0)),
        tr th:not(:nth-child(0)) {
            text-align: center;
        }
        /*可选*/
        .layui-laypage > * {
            float: left;
        }
        .layui-field-title .layui-field-box{
            padding: 10px 20px 10px 30px;
        }
        .layui-table-cell{
            padding-top: 4px;
            height: 45px;
        }
        .star-so{
            text-align: center;
            margin-bottom: 10px;
            margin-top: 40px;
        }
        .star-so input.layui-input{
            width: 200px;
            display: inline-block;
        }

    </style>
</head>
<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">历史项目</legend>

    <button class="layui-btn" style="position: relative;float: right;right: 100px;" onclick="javascript:location.replace(location.href)">
        <i class="layui-icon">&#x1002;</i>
    </button>
    <div class="layui-row">
        <div class="layui-form layui-col-md12 star-so">
            <input class="layui-input" placeholder="请输入关键字" name="keyword">

            <button class="layui-btn" id="search" "><i class="layui-icon">&#xe615;</i></button>
        </div>
    </div>


    <div class="layui-field-box">
        <div id="dataContent" class="">
            <table class="layui-hide" id="history" lay-filter="table"></table>
            <script type="text/html" id="operator">
                <a class="layui-btn layui-btn-normal" lay-event="detail">查看详情</a>
            </script>
        </div>
    </div>
</fieldset>

<!-- layui.js -->
<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.define([ 'layer',  'table'], function (exports) {
        var $ = layui.jquery,
                table  = layui.table ;
        table.render({
            elem: '#history'
            ,height: 'full-200'
            ,method:'GET'
            ,url: '/project/all' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type: 'checkbox', align:'center',unresize:true}
                ,{field: 'name', align:'center', title: '项目名',unresize:true}
                ,{field: 'proResource', align:'center', title: '项目来源',unresize:true}
                ,{field: 'moneyResource', align:'center', title: '经费来源',unresize:true}
                ,{field: 'desc', align:'center', title: '项目描述',unresize:true}
                ,{field: 'teacher', title: '指导老师',unresize:true,templet: '<div>{{d.teacher.name}}</div>'}
                ,{fixed: 'right',  title:'操作',align:'center', toolbar: '#operator',unresize:true}
            ]]
        });

        //监听工具条
        table.on('tool(table)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                if($("#detail-view-"+data.id).length > 0){
                    $("#detail-view-"+data.id).hide();
                    $("#detail-view-"+data.id).remove();
                }else{
                    createHtml(obj);
                    $("#detail-view-"+data.id).show();
                }
            }
        });

        function createHtml(obj) {
            var packageType;
            var data = obj.data;
            var name = data.name;
            var arr = name.split("-");
            if(arr[0] == "StarTVUpgrade"){
                packageType = "智能电视升级包";
            }
            var detailHtml = '';
            detailHtml += '<tr class="detail-view" style="display: none" id="detail-view-'+data.id+'">';
            detailHtml += '<td colspan="8"><blockquote class="layui-elem-quote" style="line-height: 30px">';
            detailHtml += '<div class="lay ui-inline layui-word-aux" style="width: 115px">包类型:</div>'+packageType+'</br>';
            detailHtml += '<div class="layui-inline layui-word-aux" style="width: 115px">芯片名称/型号:</div>'+arr[1]+'</br>';
            detailHtml += '<div class="layui-inline layui-word-aux" style="width: 115px">产品型号:</div>'+arr[2]+"-"+arr[3]+'</br>';
            detailHtml += '<div class="layui-inline layui-word-aux" style="width: 115px">屏型号:</div>'+arr[4]+'</br>';
            detailHtml += '<div class="layui-inline layui-word-aux" style="width: 115px">屏参:</div>'+arr[5]+'</br>';
            detailHtml += '<div class="layui-inline layui-word-aux" style="width: 115px">flash大小:</div>'+arr[6]+'</br>';
            detailHtml += '<div class="layui-inline layui-word-aux" style="width: 115px">描述:</div>'+data.description+'</br>';
            detailHtml += '</blockquote></td></tr>';
            obj.tr.after(detailHtml);
        }
        //搜索
        $('#search').click(function () {

            table.reload('app', {
                url: "/appinfo/app/search"
                ,where: {keyword:keyword} //设定异步数据接口的额外参数
                //,height: 300
            });
        });

    });
</script>
</body>
</html>