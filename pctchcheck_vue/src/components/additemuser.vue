<template xmlns:v>
    <div>
        <el-input v-model="input" placeholder="请输入姓名"></el-input>
        <el-button round @click="finduser">查找</el-button>
        <el-table
                @selection-change="changeFun"
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
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
                    prop="department"
                    label="部门"
                    width="180">
            </el-table-column>


        </el-table>
        <el-button round @click="adduseritem">添加进项目</el-button>
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
    import {postRequest} from '../utils/api'

    export default {
        created(){
            this.id = this.$route.params.id
        },
        methods: {
            changeFun(val){
                // eslint-disable-next-line no-debugger
                debugger;
                this.multipleSelection = val;
                // eslint-disable-next-line no-console
                console.log(this.multipleSelection)
            },
            finduser(){
                getRequest("/user/getuserbyname/?name="+this.input).then(resp => {
                    if (resp.status == 200) {
                        // eslint-disable-next-line no-debugger,no-undef
                        this.tableData = resp.data.data.content
                    }

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
            adduseritem(){
                var _this = this;
                this.loading = true;
                postRequest('/item/adduser', {

                    user: JSON.stringify(this.multipleSelection),
                    itemid:this.id,
                }).then(resp=> {
                    // eslint-disable-next-line no-debugger
                    _this.loading = false;
                    var data = resp.data;
                    if (resp.status == 200) {

                        var code = data.code;
                        if (code == 0) {
                            _this.$router.replace({path: '/itemuserlist/'+this.id});
                        } else {
                            _this.$alert('添加失败!', '失败!');
                        }
                    } else {
                        //失败
                        _this.$alert('添加失败!', '失败!');
                    }
                });
            },// eslint-disable-next-line no-console
        },
        mounted() {
            getRequest("/item/getusernoitem/?id=" + this.id).then(resp => {
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
                total: 0,
                page: 1,
                id: "",
                tableData: [],
                multipleSelection:[],
                input: ''
            }
        }
    }
</script>

