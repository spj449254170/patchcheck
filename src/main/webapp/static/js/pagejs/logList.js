var logList = function () {
    var handler = function () {
        layui.use(['form', 'table', 'laypage'], function () {
            var $ = layui.jquery,
                form = layui.form,
                table = layui.table;
            //表格渲染
            table.render({

                elem: '#currentTableId',
                url: '/patchcheck/log/getpatchlog',
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
                    {field: 'patchid', title: '补丁id'},
                    {field: 'userid', title: '用户id'},
                    {field: 'updateinfo', title: '操作说明'},
                    {field: 'createtime', title: '操作时间', sort: true},
                ]],
                limits: [10, 20, 50, 100],
                page: true
            });


        });
    }
    return {
        init: function () {
            handler();
        }
    };
}();