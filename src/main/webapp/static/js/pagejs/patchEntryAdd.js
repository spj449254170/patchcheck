var patchEntryAdd = function () {

    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        } else {
            return null;
        }
    };
    var patchEntryAdd = function () {
        layui.use(['form', 'upload'], function () {
            var form = layui.form,
                layer = layui.layer,
                upload = layui.upload,
                $ = layui.$;
            //初始化下拉
            $.get('/patchcheck/patchtype/typelist', function (res) {
                if (res.code == 0) {
                    var datas = res.data;
                    var options = "<option value='' >请选择</option>";
                    $.each(datas, function (index, element) {
                        options += "<option value='" + element.id + "'>" + element.name + "</option>";
                    });
                    $("#type").html(options);
                    form.render('select');
                }
            })


            //监听下拉框
            var type = '';
            form.on('select(type)', function (data) {
                type = data.value
            });

            //监听指定开关
            var delivery = true;
            form.on('switch(delivery)', function (data) {
                delivery = this.checked ? 'true' : 'false'
            });

            //选完文件后不自动上传
            upload.render({
                elem: '#upload'
                , url: '/patchcheck/file/fileUpload'//改成您自己的上传接口
                , auto: false
                , accept: 'file'
                , bindAction: '#save'
                , before: function () {
                    this.data = {
                        name: $("#name").val(),
                        id: GetQueryString("id"),
                        type: type,
                        remarks: $("#remarks").val(),
                        delivery: JSON.parse(delivery)
                    }
                }
                , done: function (result) {
                    if (result.code == 0) {
                        layer.alert('添加成功', {icon: 1}, function () {
                            // 关闭弹出层
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                            parent.location.reload();
                        });

                    } else {
                        layer.alert('添加失败', {icon: 5});
                    }
                }
            });
        })
    };
    return {
        init: function () {
            patchEntryAdd();
        }
    };
}();