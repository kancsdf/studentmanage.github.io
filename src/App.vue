<template>
<div class="content-header">
      <img src="./assets/paomian.png" width="100px" class="icon-logo"/>
      <div class="div-text"><h1 >学生管理系统</h1></div>
    <stu-menu></stu-menu>
</div>
</template>
<script>
import StuMenu from './components/StuMenu.vue'
export default {
  name: 'App',
  components: {
    StuMenu
  }
  // methods: {
  //   getAll(){
  //    this.$axios.get('http://localhost/user').then(
  //       response => {console.log('请求成功'),response.data}
  //     )
  //   }
    
  // }
}
</script>
<style>
.div-text{
  width: 15%;
  height: 100px;
  margin: 0px auto auto 100px;
}
.content-header h1{
   color: #409EFF;
   padding: 30px 20px 0px 0px ;
}
.icon-logo{
  float: left;
}
</style>