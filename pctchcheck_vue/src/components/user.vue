<template xmlns:v>
    <div>
        <el-input v-model="input" placeholder="请输入姓名"></el-input>
        <el-button round @click="finduser">查找</el-button>
        <el-button round @click="add">新增</el-button>
        <el-table
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column type="index"></el-table-column>
            <el-table-column
                    prop="id"
                    label="编号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="password"
                    label="密码"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="手机号"
                    width="180">
            </el-table-column>
            <el-table-column
                    :formatter="formatBoolean"
                    prop="power"
                    label="权限"
                    width="180">
            </el-table-column>
            <el-table-column
                    :formatter="formatBoolean1"
                    prop="department"
                    label="部门"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="change"
                    label="操作">

                <template slot-scope="page">
                    <el-button round @click="updateuser(page.row.id)">修改</el-button>
                    <el-button round @click="deleteuser(page.row.id)">删除</el-button>
                </template>
                <!--<el-button round>修改</el-button>-->


            </el-table-column>

        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                :total="total"
                :current-page="this.page"
                v-on:current-change="changePage">
        </el-pagination>

    </div>

</template>
<script>
    import {getRequest} from '../utils/api'

    export default {
        methods: {
            finduser(){
                getRequest("/user/getuserbyname/?name="+this.input).then(resp => {
                    if (resp.status == 200) {
                        // eslint-disable-next-line no-debugger,no-undef
                        this.tableData = resp.data.data.content
                    }

                });
            },
            add() {
                this.$router.push({
                    path: `/adduser`,
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
            deleteuser(id) {
                this.id = id;
                this.$confirm('是否确认删除该信息', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // eslint-disable-next-line no-console
                    console.log(id)
                    getRequest("/user/deleteuser/" + this.id).then(resp => {
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

            updateuser(id) {
                this.id = id;
                // eslint-disable-next-line no-debugger
                this.$router.push({
                    path: 'updateuser/'+this.id,
                });
                // eslint-disable-next-line no-console
                console.log(id)

            }// eslint-disable-next-line no-console
        },
        mounted() {
            getRequest("/user/getuser/" + this.page + "/10").then(resp => {
                if (resp.status == 200) {
                    // eslint-disable-next-line no-debugger
                    debugger;
                    this.tableData = resp.data.data.content
                    this.total = resp.data.data.totalElements
                }

            });
        },

        data() {
            return {
                formatBoolean: function (row, column, cellValue) {
                    var ret = ''  //你想在页面展示的值
                    if (cellValue == 0) {
                        ret = "开发部"  //根据自己的需求设定
                    } else {
                        ret = "实施部"
                    }
                    return ret;
                },
                formatBoolean1: function (row, column, cellValue) {
                    var ret = ''  //你想在页面展示的值
                    if (cellValue == 0) {
                        ret = "普通用户"  //根据自己的需求设定
                    } else {
                        ret = "管理员"
                    }
                    return ret;
                },
                total: 0,
                page: 1,
                id: "",
                tableData: [],
                input: ''
            }
        }
    }
</script>

