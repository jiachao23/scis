layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#teacher'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/teacher/project/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'name', align:'center', title: '项目名',unresize:true}
            ,{field: 'proResource', align:'center', title: '项目来源',unresize:true}
            ,{field: 'moneyResource', align:'center', title: '经费来源',unresize:true}
            ,{field: 'desc', align:'center', title: '项目描述',unresize:true}
            ,{field: 'downloadUrl', align:'center', title: '下载地址',unresize:true,templet: '<div>{{d.book.downloadUrl}}</div>'}
            ,{field: 'teacher', title: '指导老师',unresize:true,templet: '<div>{{d.teacher.name}}</div>'}
            ,{title: '项目状态',templet: '#status',unresize:true}
            ,{fixed: 'right',  title:'操作',align:'center', toolbar: '#operator',unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            del(data.id);
        } else if(obj.event === 'pass'){
            changeStatus(data.id);
        } else if(obj.event === 'edit'){
            common.frame_show('编辑','/teacher/form?id='+data.id);
        }else if(obj.event === 'detail'){
            if($("#detail-view-"+data.id).length > 0){
                $("#detail-view-"+data.id).hide();
                $("#detail-view-"+data.id).remove();
            }else{
                createHtml(obj);
                $("#detail-view-"+data.id).show();
            }

        }
    });

    //添加数据
    $('#addProject').click(function () {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            common.frame_show('添加','/teacher/form');
            // layer.msg('打开添加窗口');
        }, 500);
    });


    //输出接口，主要是两个函数，一个删除一个编辑
    var datalist = {
        deleteData: function (id) {
            layer.confirm('确定删除？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                del(id);
            }, function () {

            });
        },
        editData: function (id) {
            common.frame_show('编辑','/teacher/form?id='+id);
        }
    };
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
                            window.location.href = "/teacher/index";
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
                            window.location.href = "/teacher/index";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }
    function createHtml(obj) {
        var packageType;
        var data = obj.data;
        var expert = data.expert;
        var groups = data.groups;
        var ephone,eaddress,eresume,ereason,group;
        if(expert == null){
            ephone ='此项目还未分配专家审核';
            eaddress = '此项目还未分配专家审核';
            eresume ='此项目还未分配专家审核';
            ereason = '此项目还未分配专家审核';
        }else{
            ephone = data.expert.phone;
            eaddress = data.expert.address;
            eresume = data.expert.resume;
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

        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导老师电话:</div>'+data.teacher.phone+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导老师地址:</div>'+data.teacher.address+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导老师简历:</div>'+data.teacher.resume+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">专家电话:</div>'+ephone+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">专家地址:</div>'+eaddress+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">专家简历:</div>'+eresume+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">专家意见:</div>'+ereason+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">项目类型：</div>'+groups+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">项目描述:</div>'+data.desc+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">视频下载地址:</div><a href="'+data.video.downloadUrl+'">'+data.video.downloadUrl+'</a></br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">创意说明书下载地址:</div><a href="'+data.book.downloadUrl+'">'+data.book.downloadUrl+'</a></br>';
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
    exports('teacher/index', datalist);
});