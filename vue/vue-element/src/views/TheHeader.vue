<template>
  <div class="header">
    <div class="logo">
      <!--  -->
      <img src="../assets/logo.png" />
    </div>
    <div class="header-right">
      <div class="btn-fullscreen" @click.stop="handleFullScreen">
        <el-tooltip effect="light" :content="fullscreen ? '取消全屏' : '全屏'" placement="bottom">
          <i class="iconfont icon-suofang"></i>
        </el-tooltip>
      </div>
      <div class="btn-changelan" @click.stop="handleLanguage">
        <el-tooltip effect="light" :content="language == 'en' ? '中文' : 'English'" placement="bottom">
          <i class="iconfont icon-yingwen"></i>
          <!-- <span>{{language}}</span> -->
        </el-tooltip>
      </div>
      <div class="welcome">
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link">
            <span class="w-text">欢迎您：&ensp;{{ user }}</span><i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">个人中心</el-dropdown-item>
            <el-dropdown-item command="b">账号设置</el-dropdown-item>
            <el-dropdown-item command="c">螺蛳粉</el-dropdown-item>
            <el-dropdown-item command="d" disabled>双皮奶</el-dropdown-item>
            <el-dropdown-item command="e" divided>蚵仔煎</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div>
        <el-tooltip effect="light" content="退出系统" placement="bottom">
          <i class="iconfont icon-tuichu"></i>
        </el-tooltip>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NamePage',
  data () {
    return {
      fullscreen: false,
      language: 'en',
      user: '张三'
    }
  },
  components: {},
  mounted () { },
  methods: {
    handleFullScreen () {
      console.log('点击了放大缩小按钮')
    },
    handleLanguage () {
      console.log('切换语言')
    },
    handleCommand (command) {
      this.$message('click on item ' + command)
    }
  }
}
</script>

<style scoped>
.header {
  background-color: #ffffff;
  height: 70px;
}

.logo {
  width: 70px;
  height: 70px;
  float: left;
  margin-left: 30px;
}

.header-right {
  float: right;
  width: 300px;
  height: 70px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.iconfont {
  color: rgb(0, 0, 0);
  font-size: 22px;
}

.logo img {
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
}

.btn-changelan>span {
  color: white;
  font-size: 18px;
}

.w-text {
  font-size: 14px;
  color: rgb(0, 0, 0);
}

.el-dropdown-link {
  cursor: pointer;
  color: white;
}

.el-icon-arrow-down {
  font-size: 12px;
}
</style>
