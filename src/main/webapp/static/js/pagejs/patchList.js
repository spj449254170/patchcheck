var patchList = function () {
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
                table = layui.table,
                element = layui.element;
            //表格渲染
            table.render({
                elem: '#currentTableId',
                url: '/patchcheck/patch/getpatch?itemid=' + GetQueryString("id"),
                toolbar: '#toolbarDemo',
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
                    {field: 'name', title: '补丁名称'},
                    {field: 'remarks', title: '补丁描述'},
                    {field: 'username', title: '提交人', sort: true},
                    {field: 'ishaszip', title: '是否已有压缩文件', sort: true},
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
                        title: '添加',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/patchAdd.html?id=' + GetQueryString("id"),
                    });
                }
            });


            table.on('tool(currentTableFilter)', function (obj) {
                var data = obj.data;
                if (obj.event === 'update') {  // 监听添加操作
                    var index = layer.open({
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/patchEntryList.html?id=' + data.id,
                    });
                } else if (obj.event === 'download') {
                    layer.confirm('确定下载？', function (index) {
                        $.ajax({
                            url: "/patchcheck/patch/downloadpatch/" + data.id,
                            type: "get",
                            success: function (result) {
                                if (result.code == 0) {
                                    layer.msg('下载成功');
                                } else {
                                    layer.msg("下载失败,请先生成压缩包")
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