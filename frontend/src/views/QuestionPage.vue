<template>
  <el-container>
    <el-aside width="700px">
      <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="题目描述" name="QInfo">
          <h1>{{QId}}. {{QTitle}}
               <el-button type="warning" v-on:click="collect" icon="el-icon-star-off" size="mini" circle></el-button></h1>
          <h1>问题描述：</h1>
          <p v-html="QInfo"></p>
          <h1>输入描述：</h1>
          <p v-html="QInput"></p>
          <h1>输出描述：</h1>
          <p v-html="QOutput"></p>
          <h1>资源限制：</h1>
          <p v-html="QLimit"></p>
        </el-tab-pane>

<!--s-->

        <el-tab-pane label="提交记录" name="subRecord">
          <el-table :data="subData" style="width: 100%">
            <el-table-column label="提交结果" width="200">
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{ scope.row.res }}</span>
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
            <el-table-column label="提交时间" width="200">
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{ scope.row.subTime }}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-aside>
    <el-container>
      <el-main>
        <template>
          <el-select id="language_select" v-model="value" placeholder="请选择语言">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </template>
        <p>请在以下区域输入你的代码内容</p>
        <el-input
            type="textarea"
            :rows="20"
            placeholder="请输入内容"
            resize="none"
            v-model="textarea">
        </el-input>
        <p>                                      </p>
<!--        <el-button type="primary">提交代码</el-button>-->
<!--        <p>                                      </p>-->
<!--        <p>执行结果:</p>-->
<!--        <p>                                      </p>-->
<!--        <p id="outcome">传来的结果</p>-->
      </el-main>
      <el-footer>
        <el-button v-on:click="submitcode" type="primary">提交代码</el-button>
        <el-button id="but1" @click="drawer = true"  type="primary" style="margin-right: 16px;">
          代码执行结果
        </el-button>
        <el-drawer
            title="代码执行结果"
            :visible.sync="drawer"
            :modal=false
            direction="btt">
          <span v-if="subState==='unsub'">请先提交代码</span>
          <span v-if="subState==='doing'">运行中……</span>
          <span v-if="subState==='done'">
                     {{errInfo}}
          </span>
          <span v-if="subState==='err'">{{errInfo}}</span>
        </el-drawer>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    mounted() {
      window.Vue = this;
      this.subState='unsub'
      this.uid=this.$route.params.userid
      this.pid=this.$route.params.pid
      console.log(this.uid+"<-uid pid-> "+this.pid)
      if (window.history && window.history.pushState) {
        // 向历史记录中插入了当前页
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', this.goBack, false);
      }
      let url="/problem/fid/"+(this.pid)
      console.log(url)
      var outcome;
      this.$axios.get(url).then((response) => {
        outcome=response.data;

        let pb_intput_des=outcome[0].pb_input_description
        pb_intput_des=pb_intput_des.substring(pb_intput_des.indexOf('value')+9,pb_intput_des.length-3)
        let pb_output_des=outcome[0].pb_output_description
        pb_output_des=pb_output_des.substring(pb_output_des.indexOf('value')+9,pb_output_des.length-3)
        let pb_des=outcome[0].pb_description
        pb_des=pb_des.substring(pb_des.indexOf('value')+9,pb_des.length-3)
        let pb_hint1=outcome[0].pb_hint
        pb_hint1=pb_hint1.substring(pb_hint1.indexOf('value')+9,pb_hint1.length-3)
        this.QId=outcome[0].pb_file_id.replace(/u3000/g,' ');
        this.QTitle=outcome[0].pb_title.replace(/u3000/g,' ');
        pb_des=pb_des.replace(/u3000/g,' ')
        pb_des=pb_des.slice(0,pb_des.indexOf('src=')+5)+'http://lx.lanqiao.cn'+pb_des.slice(pb_des.indexOf('src=')+5)
        console.log(pb_des)
        this.QInfo=pb_des
        this.QInput=pb_intput_des.replace(/u3000/g,' ')
        this.QOutput=pb_output_des.replace(/u3000/g,' ')
        this.QLimit=pb_hint1.replace(/u3000/g,' ')
      })
    },
    data() {
      return {
        uid:'',
        pid:'',
        options: [{
          value: 'c',
          label: 'C'
        },
          {
            value: 'cpp',
            label: 'C++'
          },
          {
          value: 'py',
          label: 'Python'
        }, {
          value: 'java',
          label: 'Java'
        }],
        value: '',
        textarea: '',
        drawer: false,
        subState: "err",
        errInfo: "Line 14: Char 17: error: expected ';' after return statement\n" +
            "        return 0\n" +
            "                ^\n" +
            "                ;\n" +
            "1 error generated.",
        activeName: 'first',
        QId: "old1149",//"10-1",
        QTitle: "",//"长度最小的子数组",
        QInfo: "",
        QInput: " ",
        QOutput: "",
        QLimit: "",
        subData: [
        ]
      }
    },

    methods: {
      test:function (){
        alert(this.value);
      },
      collect(){
        console.log(this.uid)
        let url="/favor/add/"+(this.uid)+'/'+this.pid
        console.log(url)
        var outcome;
        this.$axios.get(url).then((response) => {
          outcome=response.data;
          console.log(outcome);
          if(outcome==='success'){
            alert("收藏成功！")
          }
          else{
            alert("收藏失败！")
          }

        })
      },
      destroyed () {
        window.removeEventListener('popstate', this.goBack, false);
      },
      goBack () {
        // console.log("点击了浏览器的返回按钮");
        let condition = this.uid
        localStorage.setItem('condition', condition)
      },
      submitcode(){
        document.getElementById('but1').click()

        this.subState='doing'
        let temp={
          uid:this.uid,
          fid:this.pid,
          codeCont:this.textarea,
          codeType:'.'+this.value,
        }
        console.log(temp)
        var outcome
        this.$axios.post("/run",temp).then((response) => {
          this.subState='done'
          outcome=response.data;
          this.errInfo=outcome.execRes+": "+outcome.errCont
          console.log(outcome);
          var myDate = new Date();
          // myDate.toLocaleDateString();                //获取当前日期
          // var mytime=myDate.toLocaleTimeString();     //获取当前时间           //获取日期与时间
          let temp={
            res: outcome.execRes,
            execTime: outcome.execTime,
            execMem: outcome.execMem,
            execLan: "C",
            subTime: myDate.toLocaleString(),
          }
          this.subData.push(temp)
        })
      }

    },
  }
</script>

<style scoped>
.el-footer {
}
.el-aside {
  padding-left: 30px;
}
.el-input {
  height: 700px;
}

.el-tabs {
  height: calc(100vh - 70px);
}

/*.el-tabs__content {*/
/*  flex-grow: 1;*/
/*  overflow-y: scroll;*/
/*}*/

.el-main {
  /*background-color: #E9EEF3;*/
  /*color: #333;*/
}

.el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.el-drawer__body {
  text-align: center
}
 /*html,body,#app,.el-container{*/
 /*  !*设置内部填充为0，几个布局元素之间没有间距*!*/
 /*  padding: 0px;*/
 /*  !*外部间距也是如此设置*!*/
 /*  margin: 0px;*/
 /*  !*统一设置高度为100%*!*/
 /*  height: 100%;*/
 /*}*/
</style>