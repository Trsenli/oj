<template>
  <el-container>
    <el-container>
      <el-main>
  <el-table
      :data="tableData"
      align="center"
      header-align="center"
      style="width: 100%">
    <el-table-column
        label="日期"
        width="200">
      <template slot-scope="scope">
        <i class="el-icon-time"></i>
        <span style="margin-left: 10px">{{ scope.row.date }}</span>
      </template>
    </el-table-column>
    <el-table-column label="编号" width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.number}}</span>
      </template>
    </el-table-column>
    <el-table-column
        label="题目名称"
        width="300">
      <template slot-scope="scope">
        <el-link  @click.prevent="test1(scope.row.number)" target="_blank" class="buttonText" >{{scope.row.name}}</el-link>
      </template>
    </el-table-column>
    <el-table-column label="提交情况" width="100" style="color: aquamarine">
      <template slot-scope="scope" style="color: aquamarine">
        <span v-if="scope.row.res==='ac'||scope.row.res==='Accepted'" style="color: green">ACCEPT</span>
        <span v-else-if="scope.row.is!='ac'||scope.row.is!='Accepted'" style="color: red">{{scope.row.res}}</span>
      </template>
    </el-table-column>
    <el-table-column label="执行用时" width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.execTime }}</span>
      </template>
    </el-table-column>
    <el-table-column label="内存消耗" width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.execMem }}</span>
      </template>
    </el-table-column>
    <el-table-column label="语言" width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.execLan }}</span>
      </template>
    </el-table-column>
  </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  props:{
    userid:String
  },
  data() {
    return {
      uid:'',
      otherData:{
        tag_state:'success',
      },
      tableData: [
      ]
    }
  },
  mounted(){
    this.testprop1()
    let url="/log/"+(this.uid)
    console.log(url)
    var outcome;
    this.$axios.get(url).then((response) => {
      outcome=response.data;
      console.log(outcome);
      for(var i=0;i<outcome.length;i++){
        let temp={
          number:outcome[i].pfid,
          date: outcome[i].time,
          name: outcome[i].title,
          url:'#/testquestion1',
          res: outcome[i].exec_res,
          execTime: outcome[i].use_time,
          execMem: outcome[i].use_memery,
          execLan: 'C'
        }
        this.tableData.push(temp)
      }
      alert("加载完成！")
    })
  },
  methods: {
    testprop1(){
      this.uid=this.userid
    },
    test1(row_number){
      console.log(row_number)
      this.$router.push({
        path: '/testquestion',
        name: 'QuestionPage',
        params: {
          pid:row_number,
          userid:this.uid
        }
        /*query: {
            key: 'key',
            msgKey: this.msg
        }*/
      })
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    }
  },
  directives:{
    rong:{ //自定义指令的名字
      inserted(el,binding){ /*用inserted方法来进行的指令的操作，el为当前元素为了方便DOM的操作，
                              binding是一个对象，有传来的值等一些基本信息*/
        if(binding.value==true){
          el.innerHTML='通过';
        }
        else if(binding.value==false){
          el.innerHTML='失败';
        }
        else{
          el.innerHTML='系统错误！';
        }
      }
    }
  }
}
</script>

<style scoped>
.el-container {
  height: 100%;
}

.el-header{
  background-color: #b3c0d1;
  text-align: center;
  line-height: 60px;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
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
  /* text-align: center; */
  line-height: 10px !important;
}

.el-menu {
  background-color: #d3dce6;
}
</style>