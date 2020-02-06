<template xmlns:v>
    <div>
        <el-input v-model="input" placeholder="请输入项目名"></el-input>
        <el-button round @click="finditem">查找</el-button>
        <el-button round @click="add">新增</el-button>
        <el-table
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column type="index"></el-table-column>
            <el-table-column
                    prop="id"
                    label="编号"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="补丁名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="remarks"
                    label="补丁说明"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="type"
                    label="补丁类型"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="createtime"
                    label="创建时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="createuser"
                    label="创建人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="updatetime"
                    label="修改时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="updateuser"
                    label="修改人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="ischeck"
                    label="是否通过校验"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="change"
                    label="操作">

                <template slot-scope="page">
                    <el-button round @click="updateuser(page.row.id)">修改</el-button>
                    <el-button round @click="deletepatchentry(page.row.id)">删除</el-button>
                </template>


            </el-table-column>

        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                :total="total"
                :current-page="this.page"
                v-on:current-change="changePage">
        </el-pagination>
        <el-button type="primary" round @click="createpatch">生成补丁</el-button>
        <el-button type="primary" round @click="returntop">返回</el-button>
    </div>

</template>
<script>
    import {getRequest} from '../utils/api'

    export default {
        methods: {
            finditem() {
                getRequest("/item/getitembyname/?name=" + this.input).then(resp => {
                    if (resp.status == 200) {
                        // eslint-disable-next-line no-debugger,no-undef
                        this.tableData = resp.data.data.content
                    }

                });
            },
            add() {
                this.$router.push({
                    path: `/addpatchentry/` + this.$route.params.id,
                });
            },
            changePage(page) {
                this.page = page
                getRequest("/user//getuser/" + this.page + "/10").then(resp => {
                    if (resp.status == 200) {
                        // eslint-disable-next-line no-debugger
                        debugger;
                        this.tableData = resp.data.data.content
                    }

                });
            },
            createpatch() {
                getRequest("/patch/ceratepatch/" + this.$route.params.id).then(resp => {
                    if (resp.status == 200) {

                        // eslint-disable-next-line no-debugger
                        if (resp.data) {
                            alert("生成成功")
                        }


                    }

                });
            },
            deletepatchentry(id) {
                this.id = id;
                this.$confirm('是否确认删除该信息', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // eslint-disable-next-line no-console
                    console.log(id)
                    getRequest("/patchentry/deletepatchentry/" + this.id).then(resp => {
                        if (resp.status == 200) {
                            // eslint-disable-next-line no-debugger
                            debugger;
                            this.$router.go(0);
                        }

                    });


                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });// eslint-disable-next-line no-console
            },
            returntop() {
                this.$router.push({
                    path: `/patch/`+this.aid,
                });
            }


        },
        mounted() {
            getRequest("/patchentry/getpatchentry/" + this.page + "/10/" + this.$route.params.id).then(resp => {
                if (resp.status == 200) {
                    // eslint-disable-next-line no-debugger
                    debugger;

                    this.tableData = resp.data.data.content

                    this.total = resp.data.data.totalElements
                }

            });
            getRequest("/patchentry/getpatchid/" + this.$route.params.id).then(resp => {

                // eslint-disable-next-line no-debugger
                debugger;
                this.aid=resp.data.data.id

            }, function (msg) {
                console(msg)
            });
        },
        created() {

        },
        data() {
            return {
                formatBoolean: function (row, column, cellValue) {
                    var ret = ''  //你想在页面展示的值
                    if (cellValue) {
                        ret = "是"  //根据自己的需求设定
                    } else {
                        ret = "否"
                    }
                    return ret;
                },
                total: 0,
                aid:"",
                page: 1,
                id: "",
                tableData: [],
                input: ''
            }
        }
    }
</script>

