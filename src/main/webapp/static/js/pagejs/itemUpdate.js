var itemUpdate = function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        } else {
            return null;
        }
    };
    var itemUpdate = function () {
        layui.use(['form', 'laydate'], function () {
            var form = layui.form,
                layer = layui.layer,
                laydate = layui.laydate,
                $ = layui.$;
            //自定义格式
            laydate.render({
                elem: '#endtime'
                , position: 'fixed'

            });
            //给表单赋值
            form.val("item", {
                "name": GetQueryString("name"),
                "remarks": GetQueryString("remarks"),
                "endtime": GetQueryString("endtime"),
                "id": GetQueryString("id")
            });
            //监听提交
            form.on('submit(saveBtn)', function (data) {
                debugger
                $.ajax({
                    url: "/patchcheck/item/updateitem",
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
            itemUpdate();
        }
    };
}();