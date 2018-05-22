layui.define(['element', 'layer', 'form','laydate','upload'], function (exports) {
    var form = layui.form,
        laydate = layui.laydate,
        upload = layui.upload,
        $ = layui.jquery;

    //自定义验证
    form.verify({
        name: function (value) {
            if (value.length <= 0 || value.length > 20) {
                return "名称必须1到00位"
            }
        },
        description:function (value) {
            if (value.length <= 1 || value.length > 100) {
                return "描述必须2到00位"
            }
        }

    });

    //上传文件设置
    upload.render({
        elem: '#upload' ,//绑定元素
        url: '/upload',
        accept: 'file',
        size:102400,
        before: function(input) {
            console.log($(input));
            box = $("#upload").parent('.layui-input-block');
            console.log(box);
            if (box.next('div').length > 0) {
                box.next('div').html('<div class="imgbox"><p>上传中...</p></div>');
            } else {
                box.after('<div class="layui-input-block"><div class="imgbox"><p>上传中...</p></div></div>');
            }
        },
        done: function(res) {
            if (res.isOk) {
                box.next('div').find('div.imgbox').html('<p>下载地址：<a href="' + res.book.downloadUrl + '">' + res.book.name + '</a></p>');
                box.find('input[type=hidden]').val(res.book.id);
            } else {
                box.next('div').find('p').html('上传失败...')
            }
        }
    });

//上传视频设置
    upload.render({
        elem: '#uploadVideo' ,//绑定元素
        url: '/upload',
        accept: 'video',
        size:102400,
        before: function(input) {
            console.log($(input));
            box = $("#uploadVideo").parent('.layui-input-block');
            console.log(box);
            if (box.next('div').length > 0) {
                box.next('div').html('<div class="imgbox"><p>上传中...</p></div>');
            } else {
                box.after('<div class="layui-input-block"><div class="imgbox"><p>上传中...</p></div></div>');
            }
        },
        done: function(res) {
            if (res.isOk) {
                console.log(res);
                box.next('div').find('div.imgbox').html('<p>下载地址：<a href="' + res.book.downloadUrl + '">' + res.book.name + '</a></p>');
                box.find('input[type=hidden]').val(res.book.id);
            } else {
                box.next('div').find('p').html('上传失败...')
            }
        }
    });

    form.on('submit(add)', function (data) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/student/save",
            data: data.field,
            success: function(ret){
                if(ret.isOk){
                    layer.msg("操作成功", {time: 2000},function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index);
                        window.location.href = "/student/form";
                    });
                }else{
                    layer.msg(ret.msg, {time: 2000});
                }
            }
        });
        return false;
    });

    exports('student/form', {});
});

