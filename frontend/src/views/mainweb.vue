<template>
  <el-container>
    <el-aside style="width:180px;">
      <el-menu>
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-user"></i>个人信息</template>
          <el-menu-item index="1-1" v-on:click="onBasic()">基础信息</el-menu-item>
          <el-submenu index="1-4">
            <template slot="title">做题信息</template>
            <el-menu-item index="1-4-1" v-on:click="onQuestionCollection">收藏夹</el-menu-item>
            <el-menu-item index="1-4-2" v-on:click="onQRecordInfo()">提交记录</el-menu-item>
          </el-submenu>
        </el-submenu>
<!--        <el-submenu index="2">-->
<!--          <template slot="title"><i class="el-icon-folder"></i>题目列表</template>-->
<!--          <el-menu-item index="2-1" v-on:click="onQuestionList()">题目列表</el-menu-item>-->
<!--        </el-submenu>-->
        <el-menu-item index="2" v-on:click="onQuestionList()">
          <i class="el-icon-menu" ></i>
          <span slot="title" >题目列表</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
<!--      <el-header class="el-icon-star-on">系统主页</el-header>-->
      <el-main id="name1">
        <curpage></curpage>
        <component :is="currentView" :userid="this.uid" :userpwd="this.pwd"></component>
      </el-main>
<!--      <el-footer>版权所有</el-footer>-->
    </el-container>
  </el-container>
</template>
<script>
import BasicInfo from "@/views/PersonInfo/BasicInfo";
import whiterpage from "@/views/whitepage";
import QRecord from "@/views/PersonInfo/QRecord";
import QuestionList from "@/views/QuestionList";
import QCollection from "@/views/PersonInfo/QCollection";
export default {
  data(){
    return {
      uid:'',
      pwd:'123',
      index:4,
      arr:[QuestionList,QRecord,QCollection,BasicInfo,whiterpage]
    }
  },

  methods:{
    onQRecordInfo(){
      // this.$refs.name.innerHTML="1233456";
      this.index=1
    },
    onQuestionList(){
      // this.$refs.name.innerHTML="1233456";
      this.index=0
    },
    onQuestionCollection(){
      this.index=2
    },
    onBasic(){
      this.index=3
    }
  },
  computed:{
    currentView(){
      return this.arr[this.index];
    }
  },
  mounted() {
    this.uid=this.$route.params.userid
    this.pwd=this.$route.params.userpwd
    let condition = localStorage.getItem('condition')
    console.log('conuid:'+condition)
    console.log('this.uid:'+this.uid)
    if(this.uid===undefined||this.uid==='')this.uid=condition
    // if(this.uid===undefined){
    //   alert("请去首页登录！！！")
    //   window.opener = null;
    //   window.open("about:blank", "_top").close()
    //
    // }
    console.log(this.uid+"  "+this.pwd)
  }

};
</script>


<style scoped>
.el-container {
  height: 100%;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  /*background-color: #e9eef3;*/
  /*color: #333;*/
  text-align: center;
  line-height: 60px;
}
.el-menu {
  background-color: #d3dce6;
}
</style>