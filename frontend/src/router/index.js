import Vue from 'vue'
import Router from 'vue-router'

import mainweb from '../views/mainweb'
import QuestionPage from '../views/QuestionPage'
import AccountLogin from "@/views/AccountLogin";
import AccountRegister from "@/views/AccountRegister";

Vue.use(Router);

export default new Router({
    routes: [
        {
            // 首页
            path: '/mainweb',
            name: 'mainweb',
            component: mainweb
        },
        {
            // 登录
            path: '/',
            name: 'AccountLogin',
            component:AccountLogin
        },
        {
            // 注册
            path: '/register',
            name: 'AccountRegister',
            component:AccountRegister
        },
        {
            // coding页面
            path: '/testquestion',
            name: 'QuestionPage',
            component:QuestionPage
        },

    ]
});