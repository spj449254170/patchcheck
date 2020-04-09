var userUpdate = function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        } else {
            return null;
        }
    };
    var updateUser = function () {
        layui.use(['form'], function () {
            var form = layui.form,
                layer = layui.layer,
                $ = layui.$;
            //给表单赋值
            form.val("user", { //user 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "name": GetQueryString("name"),
                "password": GetQueryString("password"),
                "phone": GetQueryString("phone"),
                "department": GetQueryString("department"),
                "power": GetQueryString("power"),
                "id": GetQueryString("id")
            });
            //监听提交
            form.on('submit(saveBtn)', function (data) {
                $.ajax({
                    url: "/patchcheck/user/updateuser",
                    type: "post",
                    data: data.field,
                    success: function (result) {
                        if (result.code == 0) {
                            layer.alert('修改成功', {icon: 1}, function () {
                                // 关闭弹出层
                                var iframeIndex = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(iframeIndex);
                                parent.location.reload();
                            });

                        } else {
                            layer.alert('修改失败', {icon: 5});
                        }
                    }
                })

                return false;
            });
        })
    };
    return {
        init: function () {
            updateUser();
        }
    };
}();