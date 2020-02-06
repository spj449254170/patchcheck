<template>
    <el-form ref="form" :model="user" label-width="70px">
        <el-form-item label="名称">
            <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
            <el-input v-model="user.phone"></el-input>
        </el-form-item>
        <el-form-item label="部门">
            <el-select v-model="user.department" placeholder="请选择所属部门">
                <el-option label="开发部" value="0"></el-option>
                <el-option label="实施部" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="权限">
            <el-select v-model="user.power" placeholder="请选择权限">
                <el-option label="普通用户" value="0"></el-option>
                <el-option label="管理员" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即修改</el-button>
            <el-button @click="qx">取消</el-button>
        </el-form-item>
    </el-form>

</template>
<script>
    import {postRequest} from '../utils/api'
    import {getRequest} from '../utils/api'
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
                id:""
            }
        },
        created() {
          var _this = this;
          this.id = this.$route.params.id,
                  getRequest("/user/getuserbyid/"+_this.id).then(function (msg) {
                    _this.user = msg.data.data;
                    // eslint-disable-next-line no-unused-vars
                  }, function (msg) {
                    _this.currentUserName = '游客';
                  });

        },
        methods: {
            onSubmit() {
                var _this = this;
                postRequest('/user/updateuser', _this.user).then(resp => {
                    // eslint-disable-next-line no-debugger
                    _this.loading = false;
                    var data = resp.data;
                    if (resp.status == 200) {
                        var code = data.data;
                        if (code) {
                          _this.$alert('修改成功!', '成功!');
                          this.$router.push({
                            path: `/userlist`,
                          });
                        } else {
                            _this.$alert('新增失败!', '失败!');
                        }
                    } else {
                        //失败
                        _this.$alert('新增失败!', '失败!');
                    }
                });
            },
            qx() {
                this.$router.push({
                    path: `/userlist`,
                });

            }
        }
    }
</script>