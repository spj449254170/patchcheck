var patchEntryList = function () {

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
        layui.use(['form', 'table', 'element'], function () {
            var $ = layui.jquery,
                form = layui.form,
                element = layui.element,
                table = layui.table;
            //表格渲染
            table.render({
                elem: '#currentTableId',
                url: '/patchcheck/patchentry/getpatchentry?patch=' + GetQueryString("id"),
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
                    {type: "checkbox", fixed: "left"},
                    {field: 'id', title: '编码', sort: true},
                    {field: 'name', title: '补丁名称'},
                    {field: 'remarks', title: '补丁说明'},
                    {field: 'type', title: '补丁类型'},
                    {field: 'createtime', title: '创建时间'},
                    {field: 'createuser', title: '创建人'},
                    {field: 'updatetime', title: '修改时间'},
                    {field: 'updateuser', title: '修改人'},
                    {
                        field: 'ischeck', title: '是否通过校验', templet: function (res) {
                            if (res.ischeck == 1) {
                                return "是"
                            } else {
                                return "否"
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
                        title: '新增',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/patchEntryAdd.html?id=' + GetQueryString("id"),
                    });
                } else if (obj.event === 'create') {
                    layer.confirm('确定生成补丁？', function (index) {
                        $.ajax({
                            url: "/patchcheck/patch/ceratepatch/" + GetQueryString('id'),
                            type: "get",
                            success: function (result) {
                                if (result.code == 0) {
                                    layer.msg('生成补丁成功');
                                } else {
                                    layer.msg("生成补丁失败")
                                }
                            }
                        })
                        layer.close(index);
                    });
                }
            });


            table.on('tool(currentTableFilter)', function (obj) {
                var data = obj.data;
                if (obj.event === 'edit') {
                    /*     var index = layer.open({
                             title: '编辑用户',
                             type: 2,
                             shade: 0.2,
                             maxmin: true,
                             shadeClose: true,
                             area: ['100%', '100%'],
                             content: '../page/userUpdate.html?id=' + data.id + '&name=' + data.name + '&password=' + data.password + '&power=' + data.power + '&department=' + data.department + '&phone=' + data.phone,
                         });
                         return false;*/
                } else if (obj.event === 'delete') {
                    layer.confirm('确定删除？', function (index) {
                        $.ajax({
                            url: "/patchcheck/patchentry/deletepatchentry/" + data.id,
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