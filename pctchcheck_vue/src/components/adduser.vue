<template>
    <el-form ref="user" status-icon :rules="rules" :model="user" label-width="70px">
        <el-form-item label="名称" prop="name">
            <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
            <el-input v-model="user.phone"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="department">
            <el-select v-model="user.department" placeholder="请选择所属部门">
                <el-option label="开发部" value="0"></el-option>
                <el-option label="实施部" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="权限" prop="power">
            <el-select v-model="user.power" placeholder="请选择权限">
                <el-option label="普通用户" value="0"></el-option>
                <el-option label="管理员" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit('user')">立即创建</el-button>
            <el-button @click="qx">取消</el-button>
        </el-form-item>
    </el-form>

</template>
<script>
    import {postRequest} from '../utils/api'

    export default {
        data() {
            return {
                user: {
                    name: '',
                    password: '',
                    phone: '',
                    item: '',
                    department: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                    phone: [
                        {required: true, message: '请输入手机号码', trigger: 'blur'}
                    ],
                    department: [
                        {required: true, message: '请选择部门', trigger: 'blur'}
                    ],
                    power: [
                        { required: true, message: '请选择权限', trigger: 'blur'}
                    ]

                }
            }
        },
        methods: {

            onSubmit(user) {
                this.$refs[user].validate((valid) => {
                    if (valid) {
                        var _this = this;
                        postRequest('/user/adduser', _this.user).then(resp => {
                            // eslint-disable-next-line no-debugger
                            _this.loading = false;
                            var data = resp.data;
                            if (resp.status == 200) {
                                _this.$alert('新增成功!', '成功!');
                                var code = data.data;
                                if (code) {
                                    _this.user={}
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
                    path: `/home`,
                });

            }
        }
    }
</script>