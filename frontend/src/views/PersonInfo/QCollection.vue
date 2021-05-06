<template>
        <el-table
            :data="this.tableData"
            align="center"
            header-align="center"
            style="width: 100%"
            >
          <el-table-column
              prop="number"
              label="编号"
              width="100">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.number}}</span>
            </template>
          </el-table-column>
          <el-table-column
              prop="name"
              label="题目名称"
              width="300">
            <template slot-scope="scope">
              <el-link  v-on:click.prevent="test2(scope.row.number)" target="_blank" class="buttonText">{{scope.row.name}}</el-link>
            </template>
          </el-table-column>
          <el-table-column
              prop="tag"
              label="标签"
              width="100">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.tag}}</span>
            </template>
          </el-table-column>
          <el-table-column
              label="操作"
              width="100">
            <template slot-scope="scope">
              <el-button
                  @click.native.prevent="deleteRow(scope.$index, tableData,scope.row.number)"
                  type="text"
                  size="small">
                移除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
</template>

<script>
export default {
  props:{
    userid:String
},
  data() {
    return {
      tableData: [],
      uid:'',
    }
  },
  methods:{
    deleteRow(index, rows,row_number) {
      rows.splice(index, 1);
      let url="/favor/delete/"+(this.uid)+'/'+row_number
      console.log(url)
      var outcome;
      this.$axios.get(url).then((response) => {
        outcome=response.data;
        console.log(outcome);
        if(outcome==='success'){
          alert("移除完成！")
        }
        else{
          alert("移除失败！")
        }
      })
    },
    testprop(){
      this.uid=this.userid
    },
    test2(row_number){
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
    onadditem(){
      this.tableData.push({
        number: '#1001',
        name: '字符串匹配字符串匹配字符串匹配',
        url:'#/testquestion',
        tag:'动态规划 动态规划'
      })
    },
    onaddcollection(){

    }
  },
  mounted() {
    this.testprop()
    console.log(this.uid)
    let url="/favor/"+(this.uid)
    console.log(url)
    var outcome;
    this.$axios.get(url).then((response) => {
      outcome=response.data;
      console.log(outcome);
      for(var i=0;i<outcome.length;i++){
        let temp={
          number:outcome[i].fid,
          name:outcome[i].title,
          url:'#/testquestion1',
          tag:outcome[i].tag
        }
        this.tableData.push(temp)
      }
      alert("加载完成！")
    })
  }

}
</script>

<style scoped>
.el-main {
  background-color: #e9eef3;
  color: #333;
   text-align: center;
  line-height: 10px !important;
}
</style>