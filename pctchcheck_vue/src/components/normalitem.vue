<template xmlns:v>
    <div>
        <el-input v-model="input" placeholder="请输入项目名"></el-input>
        <el-button round @click="finditem">查找</el-button>
       <!-- <el-button round @click="add">新增</el-button>-->
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
                    label="项目名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="remarks"
                    label="项目描述"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="createtime"
                    label="开始时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="endtime"
                    label="结束时间"
                    width="120">
            </el-table-column>
            <!--<el-table-column
                    :formatter="formatBoolean"
                    prop="closetype"
                    label="是否关闭"
                    width="120">
            </el-table-column>-->
            <el-table-column
                    prop="userid"
                    label="创建人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="change"
                    label="操作">

                <template slot-scope="page">
                    <!--<el-button round @click="updateuser(page.row.id)">修改</el-button>
                    <el-button round @click="deleteuser(page.row.id)">删除</el-button>-->
                    <el-button round @click="addpatch(page.row.id)">添加补丁</el-button>
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
                    path: `/additem`,
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

            addpatch(id){
                this.id = id;
                // eslint-disable-next-line no-debugger
                this.$router.push({
                    path: 'patch/'+this.id,
                });
                // eslint-disable-next-line no-console
                console.log(id)
            },
            closetype(id) {
                this.id = id;
                this.$confirm('是否确认改变状态', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // eslint-disable-next-line no-console
                    console.log(id)
                    getRequest("/item/closeitem/" + this.id).then(resp => {
                        if (resp.status == 200) {
                            // eslint-disable-next-line no-debugger
                            debugger;
                            this.$router.go(0);
                        }

                    });


                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消改变'
                    });
                });// eslint-disable-next-line no-console
            },// eslint-disable-next-line no-console
        },
        mounted() {
            getRequest("/normal/getitme/" + this.page + "/10").then(resp => {
                if (resp.status == 200) {
                    // eslint-disable-next-line no-debugger
                    debugger;
                    if(resp.data.data.content.closetype){
                        resp.data.data.content.closetype ="未关闭"
                    }else{
                        resp.data.data.content.closetype ="以关闭"
                    }
                    this.tableData = resp.data.data.content
                    this.total = resp.data.data.totalElements
                }

            });
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
                page: 1,
                id: "",
                tableData: [],
                input: ''
            }
        }
    }
</script>

