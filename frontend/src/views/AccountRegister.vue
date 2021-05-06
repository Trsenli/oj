<template>
  <div class="Aregister">
    <el-form ref="form" :model="form" label-width="100px">
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label-width="70px">
            <span><font color="pink" size="3"></font></span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="用户账号：" prop="name">
            <el-input v-model="form.name" size="small" maxlength="15" show-word-limit></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="邮箱地址：" prop="address">
            <el-input v-model="form.address" size="small"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="用户名称" prop="phone">
            <el-input v-model="form.phone" size="small" maxlength="11"  show-word-limit></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="密码" prop="password">
            <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="确认密码" prop="password">
            <el-input type="password" placeholder="确认密码" v-model="form.secpassword"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item>
            <el-button type="primary" @click="register">注册</el-button>
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
        secpassword:'',
        address:'',
        phone:'',
      },

    }
  },
  methods:{
    /*提交进行判断的函数 */
    register:function(){
      const reg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
      let str = this.form.address;
      if(this.form.password!=this.form.secpassword)alert("两次密码不一致，请确认！");
      else if(!reg.test(str))alert("邮箱格式不正确！");
      else{
        console.log(this.form.name)
        console.log(this.form.password)
        console.log(this.form.address)
        console.log(this.form.phone)
        let info={
            'user_id':this.form.name,
            'user_pwd':this.form.password,
            'user_nick_name':this.form.phone,
            'user_email':this.form.address
        }
        console.log(info)
        this.$axios.post("/user/register",info).then((response) => {
          var temp=response.data;
          console.log(temp)
          if(temp==false)alert("注册失败")
          else{
            alert("注册完毕！");
            this.$router.push("/");
          }
        })
      }
    },
  },
}
</script>

<style scoped>

</style>