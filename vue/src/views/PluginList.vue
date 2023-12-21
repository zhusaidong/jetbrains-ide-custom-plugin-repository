<script setup>
import {pluginListApi} from '@/api/api';

let list;

pluginListApi().then(res => {
  this.list = res
  console.log(list)
})

</script>

<template>
  <div class="flex">
    <el-table :data="list" height="250" style="width: 100%">
      <el-table-column label="插件名称" prop="name" width="180"/>
      <el-table-column label="插件版本" prop="version" width="180"/>
      <el-table-column label="ide版本" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            {{ scope.row.ideaVersionXml?.sinceBuild }} - {{ scope.row.ideaVersionXml?.sinceBuild }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="插件作者" width="180">
        <template #default="scope">
          <a :href="scope.row.vendorXml.url" target="_blank">{{ scope.row.vendorXml.name }} /
            {{ scope.row.vendorXml.email }}</a>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>

</style>
