var login = function () {
    var login = function () {
        layui.use(['form', 'layer'], function () {
            var form = layui.form,
                layer = layui.layer;

            // 登录过期的时候，跳出ifram框架
            if (top.location != self.location) top.location = self.location;

            // 粒子线条背景
            $(document).ready(function () {
                $('.layui-container').particleground({
                    dotColor: '#5cbdaa',
                    lineColor: '#5cbdaa'
                });
            });

            // 进行登录操作
            form.on('submit(login)', function (data) {
                data = data.field;
                if (data.username == '') {
                    layer.msg('用户名不能为空');
                    return false;
                }
                if (data.password == '') {
                    layer.msg('密码不能为空');
                    return false;
                }
                /*   if (data.captcha == '') {
                       layer.msg('验证码不能为空');
                       return false;
                   }*/
                $.ajax({
                    url: "/patchcheck/user/login"
                    , type: "post"
                    , data: {name: data.username, password: data.password}
                    , success: function (result) {
                        if (result.code == 0) {
                            window.location.href = "/patchcheck/static/index.jsp"
                        } else {
                            layer.msg("登录失败！");
                        }
                    }
                })
                return false
            });
        });
    }
    return {
        init: function () {
            login();
        }
    };
}();