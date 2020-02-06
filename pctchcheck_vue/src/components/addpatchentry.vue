<template>
    <el-form ref="item" status-icon :rules="rules" :model="item" label-width="120px" v-loading="loading">
        <el-form-item label="补丁组件名称" prop="name">
            <el-input v-model="item.name"></el-input>
        </el-form-item>
        <el-form-item label="补丁组件描述" prop="remarks">
            <el-input type="textarea" v-model="item.remarks"></el-input>
        </el-form-item>
        <el-form-item label="是否打入补丁" prop="delivery">
            <el-switch v-model="item.delivery"></el-switch>
        </el-form-item>
        <el-form-item label="补丁组件类型" >
            <el-select v-model="item.type" placeholder="请选择组件类型" >
                <el-option v-for="(a) in this.type" :key="a.id" :label="a.name" :value="a.id" ></el-option>
            </el-select>
        </el-form-item>
        <el-upload
                :on-success="onloadsuccess"
                :data="patchentry"
                class="upload-demo"
                ref="upload"
                action="/patchcheck/file/fileUpload"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :file-list="fileList"
                :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <!--/<el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>-->
        </el-upload>
        <!--<el-form-item label="结束日期" prop="endtime">
            <el-date-picker type="date" placeholder="选择日期" v-model="item.endtime" format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd" :picker-options="pickerOptions0"></el-date-picker>
        </el-form-item>-->
        <el-form-item>
            <el-button type="primary" @click="onSubmit('item')">立即创建</el-button>
            <el-button @click="qx">取消</el-button>
        </el-form-item>
    </el-form>

</template>
<script>
    //import {postRequest} from '../utils/api'
    import {getRequest} from '../utils/api'
    export default {
        data() {
            return {
                fileList:[],
                pickerOptions0: {
                    disabledDate(time) {
                        return time.getTime() < Date.now() - 8.64e7;
                    }
                },
                loading: false,
                patchentry:{
                    name:"",
                    remarks:"",
                    type:"",
                    id:"",
                    delivery:true,
                },
                patchid:"",
                id:"",
                type:[],
                itemid:"",
                item: {
                    name: '',
                    remarks: '',
                    type: '',
                    delivery:true,
                },
                rules: {
                    name: [
                        {required: true, message: '请输入补丁名称', trigger: 'blur'},
                    ],
                    remarks: [
                        {required: true, message: '请输入描述', trigger: 'blur'}
                    ],
                    type: [
                        {required: true, message: '请选择类型', trigger: 'blur'}
                    ],

                }
            }
        },
        created(){
            this.id = this.$route.params.id


        },
        mounted(){
            this.loading= true;
            let _this = this;
            getRequest("/patchtype/typelist").then(function (msg) {
                _this.loading= false;
                _this.type = msg.data.data;
                // eslint-disable-next-line no-unused-vars
            }, function (msg) {
                _this.currentUserName = '游客';
            });
        },
        methods: {

            onloadsuccess(response){
               if(response.data){
                    alert("添加成功");
                   this.$router.push({
                       path: `/patchentry/`+this.id,
                   });
               }

            },
            handleRemove(file, fileList) {
                // eslint-disable-next-line no-console
                console.log(file, fileList);
            },
            handlePreview(file) {
                // eslint-disable-next-line no-console
                console.log(file);
            },
            onSubmit(item) {
                this.loading= true;
                this.$refs[item].validate((valid) => {
                    this.loading= false;
                    if (valid) {
                        this.patchentry.id=this.id,
                            this.patchentry.name=this.item.name,
                            this.patchentry.remarks=this.item.remarks,
                            this.patchentry.type=this.item.type,
                            this.patchentry.delivery=this.item.delivery,
                        this.$refs.upload.submit();
                    } else {
                        return false;
                    }
                });

            },
            qx() {
                this.$router.push({
                    path: `/patchentry/`+this.id,
                });

            }
        }
    }
</script>