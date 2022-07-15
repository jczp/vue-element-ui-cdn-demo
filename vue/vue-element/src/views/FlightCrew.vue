<template>
  <div class="FlightCrew">
    <el-row>
      <el-button type="primary" @click="savebutton()">添加信息</el-button>
      <el-button type="danger" @click="deleteByIds">批量删除</el-button>
      <el-select v-model="value" placeholder="请选择" @visible-change='getSelectData' style="margin-left:10px">
        <el-option v-for="item in options" :key="item.areaCode" :label="item.areaName" :value="item.areaCode"></el-option>
      </el-select>
    </el-row>
    <!-- Form -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="form">
        <el-form-item label="机组编码" :label-width="formLabelWidth">
          <el-input v-model="form.flightCrewCode" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="机组名称" :label-width="formLabelWidth">
          <el-input v-model="form.flightCrewName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="机组组长名称" :label-width="formLabelWidth">
          <el-input v-model="form.flightCrewHandName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="区域编码" :label-width="formLabelWidth">
          <el-select v-model="form.areaCode" placeholder="请选择" @visible-change='getSelectData'>
            <el-option v-for="item in options" :key="item.areaCode" :label="item.areaName" :value="item.areaCode"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="insertOrUpdata()">确 定</el-button>
      </div>
    </el-dialog>

    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" v-if="false">
      </el-table-column>
      <el-table-column label="ID" prop="id" v-if="false">
      </el-table-column>
      <el-table-column label="机组编码" prop="flightCrewCode">
      </el-table-column>
      <el-table-column label="机组名称" prop="flightCrewName">
      </el-table-column>
      <el-table-column label="机组组长名称" prop="flightCrewHandName">
      </el-table-column>
      <el-table-column label="区域编码" prop="areaCode">
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime">
      </el-table-column>
      <el-table-column label="更新时间" prop="updateTime">
      </el-table-column>
      <el-table-column>
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
      title: '添加信息',
      multipleSelection: [],
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        id: '',
        areaCode: '',
        flightCrewCode: '',
        flightCrewHandName: '',
        flightCrewName: ''
      },
      formLabelWidth: '120px',
      options: [],
      value: ''
    }
  },
  created () {
    this.getData()
    this.getselect()
  },
  methods: {
    resetData () {
      this.search = ''
      this.getData()
      this.title = '添加信息'
      this.currentPage = 1
      this.form = {
        id: '',
        areaCode: '',
        flightCrewCode: '',
        flightCrewHandName: '',
        flightCrewName: ''
      }
    },
    handleEdit (id) {
      console.log(id)
    },
    getData (currentPage = 1) {
      this.currentPage = currentPage
      axios.get('http://49.232.98.234:8083/FlightCrew/' + this.currentPage + '/' + this.pageSize)
        .then(response => {
          console.log(response.error)
          this.tableData = response.data.data.iPage.records
          this.total = response.data.data.iPage.total
        })
    },
    getSelectData () {
      this.currentPage = 1
      axios.get('http://49.232.98.234:8083/FlightCrew/' + this.currentPage + '/' + this.pageSize + '/' + this.value)
        .then(response => {
          console.log(response.error)
          this.tableData = response.data.data.iPage.records
          this.total = response.data.data.iPage.total
        })
    },
    getselect () {
      axios.get('http://49.232.98.234:8083/area')
        .then(response => {
          this.options = response.data
        })
    },
    deleteDataById (id) {
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete('http://49.232.98.234:8083/FlightCrew/' + id).then(response => {
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
      axios.post('http://49.232.98.234:8083/FlightCrew/deleteByIds', ids).then(response => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
        this.getData()
      })
    },
    editById (id) {
      this.title = '修改信息'
      this.dialogFormVisible = true
      axios.get('http://49.232.98.234:8083/FlightCrew/' + id).then(response => {
        console.log(response)
        this.form = response.data
      })
    },
    savebutton () {
      this.dialogFormVisible = true
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
      axios.put('http://49.232.98.234:8083/FlightCrew', this.form).then(response => {
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
      axios.post('http://49.232.98.234:8083/FlightCrew', this.form).then(response => {
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
