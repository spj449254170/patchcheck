var userAdd = function () {
    var addUser = function () {
        layui.use(['form'], function () {
            var form = layui.form,
                layer = layui.layer,
                $ = layui.$;

            //监听提交
            form.on('submit(saveBtn)', function (data) {
                $.ajax({
                    url: "/patchcheck/user/adduser",
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
            addUser();
        }
    };
}();