layui.define([ 'layer',  'table'], function (exports) {
    var $ = layui.jquery,
        table  = layui.table ;
    table.render({
        elem: '#student'
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
        ]]
    });

    exports('project/history', datalist);
});