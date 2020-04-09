var patchAdd = function () {

    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        } else {
            return null;
        }
    };
    var patchAdd = function () {
        layui.use(['form', 'laydate'], function () {
            var form = layui.form,
                layer = layui.layer,
                laydate = layui.laydate,
                $ = layui.$;
            //监听提交
            form.on('submit(saveBtn)', function (data) {
                $.ajax({
                    url: "/patchcheck/patch/addpatch?itemid=" + GetQueryString("id"),
                    type: "post",
                    data: data.field,
                    success: function (result) {

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
                })

                return false;
            });
        })
    };
    return {
        init: function () {
            patchAdd();
        }
    };
}();