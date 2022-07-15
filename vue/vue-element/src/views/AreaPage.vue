<template>
  <div>
    <el-row>
      <el-button type="primary" @click="savebutton()">添加</el-button>
      <el-button type="danger" @click="deleteByIds" v-if="false">批量删除</el-button>
    </el-row>
    <!-- Form -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="form">
        <el-form-item label="区域编码" :label-width="formLabelWidth">
          <el-input v-model="form.areaCode" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="区域名称" :label-width="formLabelWidth">
          <el-input v-model="form.areaName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="insertOrUpdata()">确 定</el-button>
      </div>
    </el-dialog>

    <el-table :data="
      tableData.filter(
        (data) =>
          !search || data.name.toLowerCase().includes(search.toLowerCase())
      )
    " style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column label="ID" prop="id" v-if="false"> </el-table-column>
      <el-table-column label="区域编码" prop="areaCode"> </el-table-column>
      <el-table-column label="区域名称" prop="areaName"> </el-table-column>
      <el-table-column label="创建时间" prop="createTime"> </el-table-column>
      <el-table-column label="更新时间" prop="updateTime"> </el-table-column>
      <el-table-column>
        <!--<template slot="header" slot-scope="scope">
                        <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
                    </template>\-->
        <template slot-scope="scope">
          <el-button size="mini" @click="editById(scope.row.id)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteDataById(scope.row.id)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="text-align:center">
      <!--上一页，当前页，下一页   绑定data中的total   当前改变执行这个方法-->
      <el-pagination layout="prev, pager, next" :total="total" @current-change="getData">
      </el-pagination>
    </div>
  </div>
</template>

<script>
const axios = require('axios')
export default {
  data () {
    return {

      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      title: '添加用户',
      multipleSelection: [],
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        id: '',
        areaCode: '',
        areaName: ''
      },
      formLabelWidth: '120px'
    }
  },
  created () {
    this.getData()
  },
  methods: {
    resetData () {
      this.search = ''
      this.getData()
      this.title = '添加用户'
      this.currentPage = 1
      this.form = {
        id: '',
        areaCode: '',
        areaName: ''
      }
    },
    handleEdit (id) {
      console.log(id)
    },
    getData (currentPage = 1) {
      this.currentPage = currentPage
      axios.get('http://49.232.98.234:8083/area/' + this.currentPage + '/' + this.pageSize)
        .then(response => {
          console.log(response.error)
          this.tableData = response.data.data.iPage.records
          this.total = response.data.data.iPage.total
        })
    },
    deleteDataById (id) {
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete('http://49.232.98.234:8083/area/' + id).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getData()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    deleteByIds () {
      let ids = []
      if (this.multipleSelection && this.multipleSelection.length > 0) {
        ids = this.multipleSelection.map((dom) => {
          return dom.id
        })
      } else {
        this.$message({
          message: '请选择需要删除的数据！',
          type: 'warning'
        })
        return
      }
      axios.post('http://49.232.98.234:8083/area/deleteByIds', ids).then(response => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
        this.getData()
      })
    },
    editById (id) {
      this.title = '修改用户'
      this.dialogFormVisible = true
      axios.get('http://49.232.98.234:8083/area/' + id).then(response => {
        console.log(response)
        this.form = response.data
      })
    },
    savebutton () {
      this.dialogFormVisible = true
      this.resetData()
      this.getData()
    },
    insertOrUpdata () {
      console.info(this.form.id)
      console.info(this.form.id.length)
      if (this.form.id > 0) {
        this.updataUser()
      } else {
        this.insertUser()
      }
    },
    updataUser () {
      axios.put('http://49.232.98.234:8083/area', this.form).then(response => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.dialogFormVisible = false
        this.resetData()
        this.getData()
      })
    },
    insertUser () {
      axios.post('http://49.232.98.234:8083/area', this.form).then(response => {
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.dialogFormVisible = false
        this.resetData()
        this.getData()
      })
    }
  }
}
</script>
