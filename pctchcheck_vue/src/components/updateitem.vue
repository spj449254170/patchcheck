<template>
    <el-form ref="form" :model="item" label-width="70px">
        <el-form-item label="项目名称" prop="name">
            <el-input v-model="item.name"></el-input>
        </el-form-item>
        <el-form-item label="项目描述" prop="remarks">
            <el-input type="textarea" v-model="item.remarks"></el-input>
        </el-form-item>
        <el-form-item label="结束日期" prop="endtime">
            <el-date-picker type="date" placeholder="选择日期" v-model="item.endtime" format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd" :picker-options="pickerOptions0"></el-date-picker>
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
                item: {
                    name: '',
                    remarks: '',
                    endtime: '',

                },
                id:""
            }
        },
        created() {
          var _this = this;
          this.id = this.$route.params.id,
                  getRequest("/item/getitembyid/"+_this.id).then(function (msg) {
                    _this.item = msg.data.data;
                    // eslint-disable-next-line no-unused-vars
                  }, function (msg) {
                    _this.currentUserName = '游客';
                  });

        },
        methods: {
            onSubmit() {
                var _this = this;
                postRequest('/item/updateitem', _this.item).then(resp => {
                    // eslint-disable-next-line no-debugger
                    _this.loading = false;
                    var data = resp.data;
                    if (resp.status == 200) {
                        var code = data.data;
                        if (code) {
                          _this.$alert('修改成功!', '成功!');
                          this.$router.push({
                            path: `/itemlist`,
                          });
                        } else {
                            _this.$alert('修改失败!', '失败!');
                        }
                    } else {
                        //失败
                        _this.$alert('修改失败!', '失败!');
                    }
                });
            },
            qx() {
                this.$router.push({
                    path: `/itemlist`,
                });

            }
        }
    }
</script>