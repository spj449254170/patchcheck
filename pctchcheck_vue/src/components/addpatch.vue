<template>
    <el-form ref="item" status-icon :rules="rules" :model="item" label-width="90px">
        <el-form-item label="补丁名称" prop="name">
            <el-input v-model="item.name"></el-input>
        </el-form-item>
        <el-form-item label="补丁描述" prop="remarks">
            <el-input type="textarea" v-model="item.remarks"></el-input>
        </el-form-item>
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
    import {postRequest} from '../utils/api'

    export default {
        data() {
            return {
                pickerOptions0: {
                    disabledDate(time) {
                        return time.getTime() < Date.now() - 8.64e7;
                    }
                },
                itemid:"",
                item: {
                    name: '',
                    remarks: '',
                    endtime: '',
                },
                rules: {
                    name: [
                        {required: true, message: '请输入补丁名称', trigger: 'blur'},
                    ],
                    remarks: [
                        {required: true, message: '请输入描述', trigger: 'blur'}
                    ],
                    /*endtime: [
                        {required: true, message: '请输入结束日期', trigger: 'blur'}
                    ],*/

                }
            }
        },
        methods: {

            onSubmit(item) {
                this.$refs[item].validate((valid) => {
                    if (valid) {
                        var _this = this;
                        postRequest('/patch/addpatch?itemid='+this.$route.params.id, _this.item).then(resp => {
                            // eslint-disable-next-line no-debugger
                            _this.loading = false;
                            var data = resp.data;

                            if (resp.status == 200) {

                                var code = data.data;
                                if (code) {
                                    _this.$alert('新增成功!', '成功!');
                                    _this.item = {}
                                } else {
                                    _this.$alert('新增失败!', '失败!');
                                }
                            } else {
                                //失败
                                _this.$alert('新增失败!', '失败!');
                            }
                        });
                    } else {

                        return false;
                    }
                });

            },
            qx() {
                this.$router.push({
                    path: `/patch/`+this.$route.params.id,
                });

            }
        }
    }
</script>