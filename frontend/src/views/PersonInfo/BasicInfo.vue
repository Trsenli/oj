<template>
  <el-row>
    用户名称：{{info.name}}
    <el-input v-model="info.name" placeholder="请输入名称"></el-input>
    用户邮箱：{{info.age}}
    <el-input v-model="info.add" placeholder="请输入邮箱"></el-input>
    <el-button type="primary" v-on:click="onSubmit">保存</el-button>

  </el-row>
</template>

<script>
export default {
  props:{
    userid:String,
    userpwd:String
  },
  name: "NewContact",
  data(){
    return {
      info: {
        pwd:'',
        uid:'',
        name: '',
        add: '',
      },
    }
    },
  mounted() {
    this.info.uid=this.userid
    this.info.pwd=this.userpwd
    let url='/user/'+this.info.uid

    console.log("basicuid"+this.info.uid)
    this.$axios.get(url).then((response) => {
      var temp=response.data;
      console.log(temp)
      this.info.name=temp.user_nick_name
      this.info.add=temp.user_email
    })
  },
    methods: {
    onSubmit() {
      console.log('submit!');
      let info={
        'user_id':this.info.uid,
        'user_pwd':this.info.pwd,
        'user_nick_name':this.info.name,
        'user_email':this.info.add
      }
      console.log(info)
      this.$axios.post("/user/update",info).then((response) => {
        var temp=response.data;
        console.log(temp)
        if(temp==='success')alert("保存成功！")
        else{
          alert("保存失败！")
        }
      })
    },
  }
}
</script>

<style scoped>

</style>