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
                    label="补丁描述"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="提交人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="ishaszip"
                    label="是否已有压缩文件"
                    width="140">
            </el-table-column>

            <!--<el-table-column
                    prop="e"
                    label="结束时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    :formatter="formatBoolean"
                    prop="closetype"
                    label="是否关闭"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="userid"
                    label="创建人"
                    width="120">
            </el-table-column>-->
            <el-table-column
                    prop="change"
                    label="操作">

                <template slot-scope="page">
                   <!-- <el-button round @click="selectpatchentry(page.row.id)">查看</el-button>-->
                    <el-button round @click="updatepatchentry(page.row.id)">更新</el-button>
                    <!--<a href="ftp://localhost/test.zip" download="filename">下载</a>-->
                    <el-button round @click="downloadpatch(page.row.id)">下载</el-button>
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
        <el-button round @click="returntop">返回</el-button>
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
                    path: `/addpatch/`+this.id,
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
            returntop(){
                this.$router.push({
                    path: `/normalitem`,
                });
            },
            downloadpatch(id) {

                this.id = id;
                getRequest("/patch/downloadpatch/"+this.id).then(resp => {
                    if (resp.status == 200) {
                        // eslint-disable-next-line no-debugger,no-undef
                        debugger
                        if(resp.data.code == 0){
                            window.location.href = resp.data.data
                        }else{
                            alert("请先生成压缩包")
                        }

                    }

                });
            },
            updatepatchentry(id) {
                this.id = id;
                // eslint-disable-next-line no-debugger
                this.$router.push({
                    path: '/patchentry/'+this.id,
                });
                // eslint-disable-next-line no-console
                console.log(id)
            },
        },
        mounted() {
            this.id = this.id = this.$route.params.id,
            getRequest("/patch/getpatch/" + this.page + "/10/"+this.$route.params.id).then(resp => {
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
                tableData: [

                ],
                input: ''
            }
        }
    }
</script>

