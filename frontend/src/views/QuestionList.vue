<template>
  <div class="questionlist">
    <el-table
        :data="tableData"
        style="width: 100%"
        align="center"
        header-align="center"
    >
      <el-table-column
          label=""
          width="45">
        <template slot-scope="scope">
          <el-tag v-rong='scope.row.state'>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="题目编号" width="100">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="题目名称" width="400">
        <template slot-scope="scope">
          <el-link target="_blank" @click.prevent="test1(scope.row.date)" >{{scope.row.name}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="上次提交时间" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.time }}</span>
        </template>
      </el-table-column>
      <el-table-column label="通过率" width="100">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.ratio }}</span>
        </template>
      </el-table-column>
      <el-table-column label="提交记录" width="100">
        <template slot-scope="scope">
          <el-link  target="_blank" type="info" >{{scope.row.subHistory}}</el-link>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="100">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  props:{
    userid:String
  },
  data() {
    return {
      uid:'',
      curPage:1,
      tableData: [
      ]
    }
  },

  methods: {

    test1(row_number){
      this.$router.push({
        path: '/testquestion',
        name: 'QuestionPage',
        params: {
          pid:row_number,
          userid:this.uid
        }
      })

    },
    async handleCurrentChange(val) {
      //alert(`当前页: ${val}`);
      //刷新到新页面
      this.tableData=[]
      this.curPage=val
      let url="/problem/page/"+(val-1)*7+"/7"
      var outcome=[];
      console.log(url)
      this.$axios.get(url).then((response) => {
        outcome=response.data;
        console.log(outcome);
        for(var i=0;i<outcome.length;i++){
          console.log(outcome[i].pb_title)
          let temp={
                date: outcome[i].pb_file_id,
                name: outcome[i].pb_title,
                url:'#/testquestion1',
                time: '2021-04-20',
                ratio: '43%',
                historyUrl: '#/history',
                subHistory: '提交记录',
                state:true
          }
          this.tableData.push(temp)
        }
      })
      // for(var i=0;i<outcome.length;i++){
      //   console.log(outcome[i])
      // }
    },
  async  autotest(){
      this.tableData=[]
      let url="/problem/page/"+(this.curPage-1)+"/7"
      console.log(url)
      var outcome;
      this.$axios.get(url).then((response) => {
        outcome=response.data;
        console.log(outcome);
        for(var i=0;i<outcome.length;i++){
          console.log(outcome[i].pb_title)
          let temp={
            date: outcome[i].pb_file_id,
            name: outcome[i].pb_title,
            url:'#/testquestion1',
            time: '2021-04-20',
            ratio: '43%',
            historyUrl: '#/history',
            subHistory: '提交记录',
            state:true
          }
          this.tableData.push(temp)
        }
      })
    },
    pro1(){
      this.uid=this.userid
      console.log("list uid"+this.uid)
    }
  },
    mounted() {
    console.log('list create')
      this.pro1()
      //自动触发写入的函数
      this.autotest();
  },

  directives:{
    rong:{ //自定义指令的名字
      inserted(el,binding){ /*用inserted方法来进行的指令的操作，el为当前元素为了方便DOM的操作，
                              binding是一个对象，有传来的值等一些基本信息*/
        if(binding.value===true){
          el.className="el-icon-check"
        }
        else{
          el.style="display:none"
        }
      }
    }
  }
}
</script>

<style scoped>

</style>