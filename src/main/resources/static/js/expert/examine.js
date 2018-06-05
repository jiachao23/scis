layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#project'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/expert/project/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'name', align:'center', title: '项目名',unresize:true}
            ,{field: 'genre', align:'center', title: '团队性质'}
            ,{field: 'type', align:'center', title: '项目类别',unresize:true,templet: '<div>{{d.type.name}}</div>'}
            ,{field: 'proResource', align:'center', title: '项目来源',unresize:true}
            ,{field: 'moneyResource', align:'center', title: '经费来源',unresize:true}
            ,{title: '项目状态',templet: '#status',unresize:true,width:250}
            ,{fixed: 'right',  title:'操作',align:'center', toolbar: '#operator',unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            del(data.id);
        }else if(obj.event === 'pass'){
            changeStatus(data.id);
        }else if(obj.event === 'recall'){
            changeStatus(data.id);
        }else if(obj.event === 'detail'){
            if($("#detail-view-"+data.id).length > 0){
                $("#detail-view-"+data.id).hide();
                $("#detail-view-"+data.id).remove();
            }else{
                createHtml(obj);
                $("#detail-view-"+data.id).show();
            }
        }else if(obj.event === 'reject'){
            reject(data.id);
        }
    });

    function reject(id) {
        layer.prompt('请输入意见',function(val, index){
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "/project/reject/" + id + "?advise="+val,
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/teacher/examine";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }

    function del(id) {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                type: "DELETE",
                dataType: "json",
                url: "/project/" + id + "/del",
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/expert/examine";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }

    function changeStatus(id) {
        layer.prompt('请输入意见',function(val, index){
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "/project/change/" + id + "?advise="+val,
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/expert/examine";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }

    function createHtml(obj) {
        var data = obj.data;
        var expert = data.expert;
        var groups = data.groups;
        var ereason,group;
        if(expert == null){
            ereason = '此项目还未分配专家审核';
        }else{
            ereason = data.ereason;
        }
        if(groups ==="个人赛无团队"){
            group = "此项目为个人赛";
        }else{
            group ="组员学号："+groups;

        }
        var detailHtml = '';
        detailHtml += '<tr class="detail-view" style="display: none" id="detail-view-'+data.id+'">';
        detailHtml += '<td colspan="10"><blockquote class="layui-elem-quote" style="line-height: 30px;text-align:left;padding-left: 30px;">';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">项目描述:</div>'+data.desc+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">团队性质：</div>'+group+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">项目任务书下载地址:</div><a href="'+data.book.downloadUrl+'">'+data.book.downloadUrl+'</a></br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">展示视频下载地址:</div><a href="'+data.video.downloadUrl+'">'+data.video.downloadUrl+'</a></br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导教师工号:</div>'+data.teacher.num+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导教师姓名:</div>'+data.teacher.name+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导教师意见:</div>'+data.teacher.address+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">专家意见:</div>'+ereason+'</br>';
        detailHtml += '</blockquote></td></tr>';
        obj.tr.after(detailHtml);
    }

    exports('expert/examine');
});