<template xmlns:v>
    <div>
        <!--<el-input v-model="input" placeholder="请输入项目名"></el-input>
        <el-button round @click="finditem">查找</el-button>-->
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
                    label="名称"
                    width="180">
            </el-table-column>
            <el-table-column
                    :formatter="formatBoolean"
                    prop="department"
                    label="职位"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="change"
                    label="操作">
                <template slot-scope="page">
                    <!--<el-button round @click="updateuser(page.row.id)">修改</el-button>-->
                    <el-button round @click="deleteuser(page.row.id)">删除</el-button>
                    <!--<el-button round @click="adduser(page.row.id)">添加人员</el-button>-->
                    <!--<el-button v-if="closetype" round @click="closetype(page.row.id)">开/关项目</el-button>-->
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
        created(){
            this.id=this.id = this.$route.params.id
        },
        methods: {
            finditem(){
                getRequest("/item/getitembyname/?name="+this.input).then(resp => {
                    if (resp.status == 200) {
                        // eslint-disable-next-line no-debugger,no-undef
                        this.tableData = resp.data.data.content
                    }

                });
            },
            add() {
                this.$router.push({
                    path: `/additemuser/`+this.id,
                });
            },
            changePage(page) {
                this.page = page
                getRequest("/user/getuser/" + this.page + "/10").then(resp => {
                    if (resp.status == 200) {
                        // eslint-disable-next-line no-debugger
                        debugger;
                        this.tableData = resp.data.data.content
                    }

                });
            },
            deleteuser(id) {

                this.$confirm('是否确认删除该信息', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // eslint-disable-next-line no-console
                    console.log(id)
                    getRequest("/item/deleteuser/?itemid=" + this.id+"&userid="+id).then(resp => {
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
            adduser(id){
                this.id = id;
                // eslint-disable-next-line no-debugger
                this.$router.push({
                    path: 'itemadduser/'+this.id,
                });
                // eslint-disable-next-line no-console
                console.log(id)
            },
        },
        mounted() {
            getRequest("/item/userlist/?id="+this.id).then(resp => {
                if (resp.status == 200) {
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
                total: 0,
                page: 1,
                id: "",
                tableData: [],
                input: ''
            }
        }
    }
</script>

