<template>
  <div>
    <div class="filter-container">
      <el-input
        placeholder="学生姓名"
        v-model="pagination.name"
        style="width: 200px"
        class="filter-item"
      ></el-input>
      <el-input
        placeholder="学生年龄"
        v-model="pagination.age"
        style="width: 200px"
        class="filter-item"
      ></el-input>
      <el-input
        placeholder="学生班级"
        v-model="pagination.classes"
        style="width: 200px"
        class="filter-item"
      ></el-input>
      
      <el-button @click="getBySome()" class="dalfBut">查询</el-button>
      <el-button type="primary" class="butT" @click="handleCreate()"
        >新建</el-button
      >
    </div>

    <div class="div-line"></div>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column align="center" prop="id" label="序号" width="220">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="name" label="姓名" width="380">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top" prop="address">
            <p>姓名: {{ scope.row.name }}</p>
            <p>家庭住址: {{ scope.row.address }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="age" label="年龄" width="300">
        <template slot-scope="scope">
          <span>{{ scope.row.age }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="classes" label="班级" width="300">
        <template slot-scope="scope">
          <span>{{ scope.row.classes }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="specialty" label="专业" width="300">
        <template slot-scope="scope">
          <span>{{ scope.row.specialty }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleUpdate(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!--分页组件-->
    <div class="pagination-container">
      <el-pagination
        class="pagiantion"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-size="pagination.pageSize"
        layout="total, prev, pager, next, jumper"
        :total="pagination.total"
      >
      </el-pagination>
    </div>
    <!-- 新增标签弹层 -->
    <div class="add-form">
      <el-dialog title="新增学生" :visible.sync="dialogFormVisible">
        <el-form
          ref="dataAddForm"
          :model="formData"
          :rules="rules"
          label-position="right"
          label-width="100px"
        >
          <el-row>
            <el-col :span="12">
              <el-form-item label="学生姓名" prop="name">
                <el-input v-model="formData.name" />
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="学生年龄" prop="age">
                <el-input v-model="formData.age" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="学生班级" prop="classes">
                <el-input v-model="formData.classes" ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="学生专业" prop="specialty">
                <el-input
                  v-model="formData.specialty"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="学生地址" prop="address">
                <el-input v-model="formData.address" type="textarea"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel()">取消</el-button>

          <el-button type="primary" @click="handleAdd()">确定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 编辑标签弹层 -->
    <div class="add-form">
      <el-dialog title="编辑检查项" :visible.sync="dialogFormVisible4Edit">
        <el-form
          ref="dataEditForm"
          :model="formData"
          :rules="rules"
          label-position="right"
          label-width="100px"
        >
          <el-row>
            <el-col :span="12">
              <el-form-item label="学生姓名" prop="name">
                <el-input v-model="formData.name" align="center" />
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="学生年龄" prop="age">
                <el-input v-model="formData.age" align="center" />
              </el-form-item> </el-col
            >
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="学生班级" prop="classes">
                <el-input
                  v-model="formData.classes"
                  align="center"
                ></el-input>
              </el-form-item>
            </el-col>
         
            <el-col :span="12">
              <el-form-item label="学生专业" prop="specialty">
                <el-input
                  v-model="formData.specialty"
                  align="center"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="学生地址" prop="address">
                <el-input
                  v-model="formData.address"
                  type="textarea"
                  align="center"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel()">取消</el-button>

          <el-button type="primary" @click="handleEdit()">确定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      //当前页要展示的列表数据
      tableData: [],
      dialogFormVisible: false, //添加表单是否可见
      dialogFormVisible4Edit: false, //编辑表单是否可见
      formData: {}, //表单数据
      rules: {
        //校验规则
        name: [
          { required: true, message: "学生姓名为必填项", trigger: "blur" },
        ],
        age: [{ required: true, message: "学生年龄为必填项", trigger: "blur" }],
        classes: [
          { required: true, message: "学生班级为必填项", trigger: "blur" },
        ],
        specialty: [
          { required: true, message: "学生专业为必填项", trigger: "blur" },
        ],
        address: [
          { required: true, message: "学生家庭住址为必填项", trigger: "blur" },
        ],
      },
      pagination: {
        //分页相关模型数据
        currentPage: 1, //当前页码
        pageSize: 10, //每页显示的记录数
        total: 0, //总记录数
        id: "",
        name: "",
        age: "",
        classes: "",
        specialty:"",
        address:""
      },
    };
  },
  //钩子函数，VUE对象初始化完成后自动执行
  created() {
    //调用查询全部数据的操作
    this.getAll();
  },

  methods: {
    getAll() {
        this.$axios.get(`http://localhost/student/${this.pagination.currentPage}/${this.pagination.pageSize}`).then(
        (res) => {
          this.pagination.pageSize = res.data.data.size;
          this.pagination.currentPage= res.data.data.current;
          this.pagination.total = res.data.data.total;
          this.tableData = res.data.data.records;},
        (err) => {console.log('请求失败')}
        )      
    },
    getBySome(){
      this.$axios.post(`http://localhost/student/find/${this.pagination.currentPage}/${this.pagination.pageSize}`,
      { 
        name:this.pagination.name,
        age:this.pagination.age,
        classes:this.pagination.classes,
        specialty:this.pagination.specialty,
        address: this.pagination.address
      },
      ).then(
        (res) => {
          this.pagination.pageSize = res.data.data.size;
          this.pagination.currentPage= res.data.data.current;
          this.pagination.total = res.data.data.total;
          this.tableData = res.data.data.records;},
        (err) => {console.log('请求失败')}
        )      
    },
    //切换页码
    handleCurrentChange(currentPage) {
      //修改页码值为当前选中的页码值
      this.pagination.currentPage = currentPage;
      //执行查询
      this.getAll();
    },

    //弹出添加窗口
    handleCreate() {
      this.dialogFormVisible = true;
      this.resetForm();
    },

    //重置表单
    resetForm() {
      this.formData = {};
    },

    //添加
    handleAdd() {
      this.$axios
        .post("http://localhost/student", this.formData)
        .then((res) => {
          //判断当前操作是否成功
          if (res.data.flag) {
            //1.关闭弹层
            this.dialogFormVisible = false;
            this.$message.success(res.data.mes);
          } else {
            this.$message.error(res.data.mes);
          }
        })
        .finally(() => {
          //2.重新加载数据
          this.getAll();
        });
    },

    //取消
    cancel() {
      this.dialogFormVisible = false;
      this.dialogFormVisible4Edit = false;
      this.$message.info("当前操作取消");
    },

    // 删除
    handleDelete(row) {
      this.$confirm("此操作永久删除当前信息，是否继续？", "提示", {
        type: "info",
      })
        .then(() => {
          axios
            .delete(`http://localhost/student/${row.id}`)
            .then((res) => {
              if (res.data.flag) {
                this.$message.success(res.data.mes);
              } else {
                this.$message.error(res.data.mes);
              }
            })
            .finally(() => {
              //2.重新加载数据
              this.getAll();
            });
        })
        .catch(() => {
          this.$message.info("取消操作");
        });
    },

    //弹出编辑窗口
    handleUpdate(row) {
      
      axios
        .get(`http://localhost/student/${row.id}`)
        .then((res) => {
          if (res.data.flag && res.data.data != null) {
            this.dialogFormVisible4Edit = true;
            this.formData = res.data.data;
          } else {
            this.$message.error("数据同步失败，自动刷新");
          }
        })
        .finally(() => {
          //2.重新加载数据
          this.getAll();
        });
    },
    handleEdit() {
       this.$axios
        .put("http://localhost/student", this.formData)
        .then((res) => {
          //判断当前操作是否成功
          if (res.data.flag) {
            //1.关闭弹层
            this.dialogFormVisible4Edit = false;
            this.$message.success(res.data.mes);
          } else { 
            this.$message.error(res.data.mes);
          }
        })
        .finally(() => {
          //2.重新加载数据
          this.getAll();
        });
    },
  },
};
</script>

<style>
.filter-container {
  padding: 10px 0 15px 0;
}
.div-line {
  width: auto;
  height: 10px;
  background-color: #409eff;
}
.filter-item {
  margin-left: 10px;
}
.filter-item2 {
  margin-right: 10px;
}
.pagination-container {
  float: right;
}
</style>