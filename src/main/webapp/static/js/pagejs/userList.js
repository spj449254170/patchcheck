var userList = function () {
    var handler = function () {
        layui.use(['form', 'table', 'laypage'], function () {
            var $ = layui.jquery,
                form = layui.form,
                table = layui.table;
            //表格渲染
            table.render({

                elem: '#currentTableId',
                url: '/patchcheck/user/getuserPage',
                toolbar: '#toolbarDemo',
                id: "tableInfo",
                method: "get",
                defaultToolbar: ['filter', 'exports', 'print'],
                request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    , limitName: 'size' //每页数据量的参数名，默认：limit
                },
                parseData: function (res) {
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.data.totalElements, //解析数据长度
                        "data": res.data.content //解析数据列表
                    }
                },
                cols: [[
                    //{type: "checkbox", fixed: "left"},
                    {field: 'id', title: '编码', sort: true},
                    {field: 'name', title: '用户名'},
                    {field: 'phone', title: '手机'},
                    {
                        field: 'power', title: '权限', templet: function (res) {
                            if (res.power == 0) {
                                return "普通用户"
                            } else {
                                return "管理员"
                            }
                        }, sort: true
                    },
                    {
                        field: 'department', title: '部门', templet: function (res) {
                            if (res.department == 0) {
                                return "开发部"
                            } else {
                                return "实施部"
                            }
                        }, sort: true
                    },
                    {title: '操作', templet: '#currentTableBar', fixed: "right", align: "center"}
                ]],
                limits: [10, 20, 50, 100],
                page: true
            });


            // 监听搜索操作
            form.on('submit(data-search-btn)', function (data) {
                //执行搜索重载
                table.reload('tableInfo', {
                    url: "/patchcheck/user/getuserbyname",
                    page: {
                        curr: 1
                    },
                    where: {
                        name: data.field.username
                    }
                }, 'data');

                return false;
            });

            /**
             * toolbar监听事件
             */
            table.on('toolbar(currentTableFilter)', function (obj) {
                if (obj.event === 'add') {  // 监听添加操作
                    var index = layer.open({
                        title: '添加用户',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/userAdd.html',
                    });
                    /*   $(window).on("resize", function () {
                           layer.full(index);
                       });*/
                }
            });

            //监听表格复选框选择
            table.on('checkbox(currentTableFilter)', function (obj) {
                console.log(obj)
            });

            table.on('tool(currentTableFilter)', function (obj) {
                var data = obj.data;
                if (obj.event === 'edit') {

                    var index = layer.open({
                        title: '编辑用户',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/userUpdate.html?id=' + data.id + '&name=' + data.name + '&password=' + data.password + '&power=' + data.power + '&department=' + data.department + '&phone=' + data.phone,
                    });
                    /*           $(window).on("resize", function () {
                                   layer.full(index);
                               });*/
                    return false;
                } else if (obj.event === 'delete') {
                    layer.confirm('确定删除？', function (index) {
                        $.ajax({
                            url: "/patchcheck/user/deleteuser/" + data.id,
                            type: "get",
                            success: function (result) {
                                if (result.code == 0) {
                                    layer.msg('删除成功');
                                    table.reload('tableInfo');
                                } else {
                                    layer.msg("删除失败")
                                }
                            }
                        })
                        layer.close(index);
                    });
                }
            });

        });
    }
    return {
        init: function () {
            handler();
        }
    };
}();