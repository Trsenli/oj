<template>
  <div class="firstdemo">
    <el-form ref="form" :model="form" label-width="100px">
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label-width="70px">
            <span><font color="pink" size="3">注册登录页面</font></span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="账户：" prop="name">
            <el-input v-model="form.name" size="small"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="密码：" prop="password">
            <el-input v-model="form.password" size="small"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item>
            <el-button type="primary" @click="submit">登录</el-button>
            <router-link to='/register'>
              <el-button @click="register">注册</el-button>
            </router-link>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

  </div>

</template>

<script>
export default {
  name: 'firstdemo',
  data () {
    return {
      form:{
        name:'',
        password:'',
      },

    }
  },
  methods:{
    /*提交进行判断的函数 */
    submit:function(){
      if(this.form.name==="张三"&&this.form.password==="123456")
        alert("登陆成功")
      else
        alert("账号或密码错误")
    },
    register:function(){},
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
