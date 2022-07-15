<template>
  <div class="page">
    <el-container>
      <el-header>
        <TheHeader></TheHeader>
      </el-header>
      <el-container>
        <el-aside width="250px">
          <el-menu class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
            <router-link to="/area">
              <el-menu-item index="1">
                <i class="el-icon-menu"></i>
                <span slot="title">area</span>
              </el-menu-item>
            </router-link>
            <router-link to="/flightCrew">
              <el-menu-item index="2">
                <i class="el-icon-setting"></i>
                <span slot="title">flightCrew</span>
              </el-menu-item>
            </router-link>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import TheHeader from '@/views/TheHeader.vue'
export default {
  name: 'ContainerPage',
  components: {
    TheHeader
  },
  data () {
    return {

    }
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style>
/*去除未被访问的a标签的下划线*/
a:link {
  text-decoration: none;
}

/*去除已经被访问过的a标签的下划线*/
a:visited {
  text-decoration: none;
}

/*去除鼠标悬停时的a标签的下划线*/
a:hover {
  text-decoration: none;
}

/*去除正在点击的a标签的下划线（鼠标按下，尚未松开）*/
a:active {
  text-decoration: none;
}

/*去除获得焦点的a标签的下划线（被鼠标点击过）*/
a:focus {
  text-decoration: none;
}
</style>
