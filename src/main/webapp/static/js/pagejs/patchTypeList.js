var patchTypeList = function () {
    var handler = function () {
        layui.use(['form', 'table'], function () {
            var $ = layui.jquery,
                form = layui.form,
                table = layui.table;
            //表格渲染
            table.render({
                elem: '#currentTableId',
                url: '/patchcheck/patchtype/typelist',
                toolbar: '#toolbarDemo',
                id: "tableInfo",
                method: "get",
                defaultToolbar: ['filter', 'exports', 'print'],
                parseData: function (res) {
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "data": res.data //解析数据列表
                    }
                },
                cols: [[
                    //{type: "checkbox", fixed: "left"},
                    {field: 'id', title: '编码', sort: true},
                    {field: 'name', title: '补丁类型'},
                    {field: 'remarks', title: '补丁类型描述'},
                    {
                        field: 'istrue', title: '是否生效', templet: function (res) {
                            if (res.istrue == 1) {
                                return "是"
                            } else {
                                return "否"
                            }
                        }, sort: true
                    },
                    {field: 'ruler', title: '校验规则', sort: true},
                    {title: '操作', templet: '#currentTableBar', fixed: "right", align: "center"}
                ]]
            });


            /**
             * toolbar监听事件
             */
            table.on('toolbar(currentTableFilter)', function (obj) {
                if (obj.event === 'add') {  // 监听添加操作
                    var index = layer.open({
                        title: '新增',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/patchTypeAdd.html',
                    });
                    /*   $(window).on("resize", function () {
                           layer.full(index);
                       });*/
                }
            });


            table.on('tool(currentTableFilter)', function (obj) {
                var data = obj.data;
                if (obj.event === 'edit') {

                    var index = layer.open({
                        title: '编辑',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/patchTypeUpdate.html?id=' + data.id + '&name=' + data.name + '&remarks=' + data.remarks + '&Url=' + data.Url + '&ruler=' + data.ruler,
                    });
                    /*           $(window).on("resize", function () {
                                   layer.full(index);
                               });*/
                    return false;
                } else if (obj.event === 'delete') {
                    layer.confirm('确定删除？', function (index) {
                        $.ajax({
                            url: "/patchcheck/patchtype/deletepatchtype/" + data.id,
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