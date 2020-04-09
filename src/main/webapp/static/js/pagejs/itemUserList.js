var itemUserList = function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        } else {
            return null;
        }
    };
    var handler = function () {
        layui.use(['form', 'table'], function () {
            var $ = layui.jquery,
                form = layui.form,
                table = layui.table;
            //表格渲染
            table.render({
                elem: '#currentTableId',
                url: '/patchcheck/item/userlist?id=' + GetQueryString("id"),
                id: "tableInfo",
                method: "get",
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
            });


            //表格渲染
            table.render({
                elem: '#addTableId',
                url: '/patchcheck/item/getusernoitem?id=' + GetQueryString("id"),
                id: "addTableInfo",
                toolbar: '#toolbarDemo',
                method: "get",
                parseData: function (res) {
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.data.totalElements, //解析数据长度
                        "data": res.data.content //解析数据列表
                    }
                },
                cols: [[
                    {type: "checkbox", fixed: "left"},
                    {field: 'id', title: '编码', sort: true},
                    {field: 'name', title: '用户名'},
                    {
                        field: 'department', title: '部门', templet: function (res) {
                            if (res.department == 0) {
                                return "开发部"
                            } else {
                                return "实施部"
                            }
                        }, sort: true
                    },
                ]],
            });

            /**
             * toolbar监听事件
             */
            table.on('toolbar(addTableFilter)', function (obj) {
                if (obj.event === 'add') {  // 监听添加操作

                    var checkStatus = table.checkStatus('addTableInfo')
                        , data = checkStatus.data;
                    if (data.length == 0) {
                        layer.msg('请选择要添加的人员');
                        return;
                    }

                    $.ajax({
                        url: "/patchcheck/item/adduser",
                        type: "post",
                        data: {user: JSON.stringify(data), itemid: GetQueryString("id")},
                        success: function (result) {
                            if (result.code == 0) {
                                layer.msg('添加成功');
                                table.reload('tableInfo');
                                table.reload('addTableInfo');
                            } else {
                                layer.msg("添加失败")
                            }
                        }
                    })
                }
            });


            table.on('tool(currentTableFilter)', function (obj) {
                var data = obj.data;
                if (obj.event === 'delete') {
                    layer.confirm('确定删除？', function (index) {
                        $.ajax({
                            url: "/patchcheck/item/deleteuser?itemid=" + GetQueryString("id") + "&userid=" + data.id,
                            type: "get",
                            success: function (result) {
                                if (result.code == 0) {
                                    layer.msg('删除成功');
                                    table.reload('tableInfo');
                                    table.reload('addTableInfo');
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