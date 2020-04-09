var normalItemList = function () {
    var handler = function () {
        layui.use(['form', 'table'], function () {
                var $ = layui.jquery,
                    form = layui.form,
                    table = layui.table;
                //表格渲染
                table.render({
                    elem: '#currentTableId',
                    url: '/patchcheck/normal/getitme',
                    id: "tableInfo",
                    method: "get",
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
                        {title: '操作', templet: '#currentTableBar', align: "center"}
                    ]],
                  /*  limits: [3,10, 20, 50, 100],
                    page: true*/
                });

                // 监听搜索操作
                form.on('submit(data-search-btn)', function (data) {
                    //执行搜索重载
                    table.reload('tableInfo', {
                        url: "/patchcheck/normal/getitembyname",
                        page: {
                            curr: 1
                        },
                        where: {
                            name: data.field.name
                        }
                    }, 'data');

                    return false;
                });


                table.on('tool(currentTableFilter)', function (obj) {
                        var data = obj.data;
                        if (obj.event === 'add') {

                            var index = layer.open({
                                title: '添加补丁',
                                type: 2,
                                shade: 0.2,
                                maxmin: true,
                                shadeClose: true,
                                area: ['100%', '100%'],
                                content: '../page/patchList.html?id=' + data.id,
                            });
                            return false;

                        }
                    }
                );

            }
        )
        ;
    }
    return {
        init: function () {
            handler();
        }
    };
}
();