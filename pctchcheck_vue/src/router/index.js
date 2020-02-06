import Vue from 'vue'
import Router from 'vue-router'
import login from '../components/login.vue'
import home from '../components/home.vue'
import chome from '../components/chome.vue'
import user from '../components/user.vue'
import adduser from '../components/adduser.vue'
import updateuser from '../components/updateuser.vue'
import item from '../components/item.vue'
import patch from '../components/patch.vue'
import patchentry from '../components/patchentry.vue'
import additem from '../components/additem.vue'
import addpatch from '../components/addpatch.vue'
import addpatchentry from '../components/addpatchentry.vue'
import updateitem from '../components/updateitem.vue'
import itemuserlist from '../components/itemuserlist.vue'
import additemuser from '../components/additemuser.vue'
import normalitem from '../components/normalitem.vue'
import patchtype from '../components/patchtype.vue'
import addpatchtype from '../components/addpatchtype.vue'
Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: '登录',
            hidden: true,
            component: login,


        },
        {
            path: '/home',
            name: '用户管理',
            hidden: false,
            component: home,
            admin:true,
            children: [
                {
                    path: '/userlist',
                    name: '用户列表',
                    component: user,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/adduser',
                    name: '新增用户',
                    component: adduser,
                    meta: {
                        keepAlive: true
                    }
                },
                {
                    path: '/updateuser/:id',
                    name: '修改用户',
                    component: updateuser,
                    hidden: true,
                    meta: {
                        keepAlive: true
                    }
                }
            ]

        },
        {
            path: '/home',
            name: '项目管理',
            hidden: false,
            component: home,
            admin:true,
            children: [
                {
                    path: '/itemlist',
                    name: '项目列表',
                    component: item,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/additem',
                    name: '新增项目',
                    component: additem,
                    meta: {
                        keepAlive: true
                    }
                },
                {
                    path: '/updateitem/:id',
                    name: '修改项目',
                    component: updateitem,
                    hidden: true,
                    meta: {
                        keepAlive: true
                    }
                },
                {
                    path: '/itemuserlist/:id',
                    name: '人员列表',
                    component: itemuserlist,
                    hidden: true,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/additemuser/:id',
                    name: '添加人员',
                    component: additemuser,
                    hidden: true,
                    meta: {
                        keepAlive: false
                    }
                },

            ]

        },
        {
            path: '/home',
            name: '配置管理',
            hidden: false,
            component: home,
            admin:true,
            children: [
                {
                    path: '/patchtype',
                    name: '补丁类型列表',
                    component: patchtype,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/addpatchtype',
                    name: '新增补丁类型',
                    component: addpatchtype,
                    meta: {
                        keepAlive: true
                    }
                },


            ]

        },
        {
            path: '/chome',
            name: '补丁管理',
            hidden: false,
            component: chome,
            children: [
                {
                    path: '/normalitem',
                    name: '项目列表',
                    component: normalitem,
                    hidden: false,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/patch/:id',
                    name: '补丁列表',
                    component: patch,
                    hidden: true,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/addpatch/:id',
                    name: '添加补丁',
                    component: addpatch,
                    hidden: true,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/patchentry/:id',
                    name: '补丁详细信息',
                    component: patchentry,
                    hidden: true,
                    meta: {
                        keepAlive: false
                    }
                },
                {
                    path: '/addpatchentry/:id',
                    name: '新增补丁组件',
                    component: addpatchentry,
                    hidden: true,
                    meta: {
                        keepAlive: false
                    }
                },
            ]
        }
    ]
})
