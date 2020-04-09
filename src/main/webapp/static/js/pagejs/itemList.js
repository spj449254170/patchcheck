var itemList = function () {
    var handler = function () {
        layui.use(['form', 'table'], function () {
            var $ = layui.jquery,
                form = layui.form,
                table = layui.table;
            //表格渲染
            table.render({
                elem: '#currentTableId',
                url: '/patchcheck//item/getitme',
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
                    // {type: "checkbox", fixed: "left"},
                    {field: 'id', title: '编码', sort: true},
                    {field: 'name', title: '项目名称'},
                    {field: 'remarks', title: '项目描述'},
                    {field: 'createtime', title: '开始时间', sort: true},
                    {field: 'endtime', title: '结束时间', sort: true},
                    {
                        field: 'closetype', title: '是否关闭', templet: function (res) {
                            if (res.closetype == false) {
                                return "否"
                            } else {
                                return "是"
                            }
                        }, sort: true
                    },
                    {title: '操作', templet: '#currentTableBar', fixed: "right", align: "center", minWidth: 300}
                ]],
                limits: [3,10, 20, 50, 100],
                page: true
            });

            // 监听搜索操作
            form.on('submit(data-search-btn)', function (data) {
                //执行搜索重载
                table.reload('tableInfo', {
                    url: "/patchcheck/item/getitembyname",
                    page: {
                        curr: 1
                    },
                    where: {
                        name: data.field.name
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
                        title: '添加项目',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/itemAdd.html',
                    });
                    /*       $(window).on("resize", function () {
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
                        title: '编辑项目',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/itemUpdate.html?id=' + data.id + '&name=' + data.name + '&remarks=' + data.remarks + '&endtime=' + data.endtime,
                    });
                    /*    $(window).on("resize", function () {
                            layer.full(index);
                        });*/
                    return false;
                } else if (obj.event === 'delete') {
                    layer.confirm('确定删除？', function (index) {
                        $.ajax({
                            url: "/patchcheck/item/deleteitem/" + data.id,
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
                } else if (obj.event === "closetype") {
                    layer.confirm('确定关闭该项目？', function (index) {
                        $.ajax({
                            url: "/patchcheck/item/closeitem/" + data.id,
                            type: "get",
                            success: function (result) {
                                if (result.code == 0) {
                                    layer.msg('操作成功');
                                    table.reload('tableInfo');
                                } else {
                                    layer.msg("操作失败")
                                }
                            }
                        })
                        layer.close(index);
                    });
                } else if (obj.event === "adduser") {
                    var index = layer.open({
                        title: '添加人员',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/itemUserList.html?id=' + data.id,
                    });
                    /*     $(window).on("resize", function () {
                             layer.full(index);
                         });*/
                    return false;
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