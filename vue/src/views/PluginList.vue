<script setup>
import isLogin from "@/components/IsLogin.vue";

isLogin.ok();

const pluginList = [
  {
    "id": "cn.xxx.c",
    "name": "xlsx",
    "version": "1.0.0",
    "ideaVersion": {
      "since": "202",
      "until": "222"
    },
    "vendor": {
      "url": "http://localhost:8080",
      "name": "xxx",
      "email": "xxx@qq.com"
    }
  }
]
</script>

<template>
  <div class="flex">
    <el-table :data="pluginList" height="250" style="width: 100%">
      <el-table-column label="插件名称" prop="name" width="180"/>
      <el-table-column label="插件版本" prop="version" width="180"/>
      <el-table-column label="ide版本" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            {{ scope.row.ideaVersion.since }} - {{ scope.row.ideaVersion.until }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="插件作者" width="180">
        <template #default="scope">
          <a :href="scope.row.vendor.url" target="_blank">{{ scope.row.vendor.name }} / {{ scope.row.vendor.email }}</a>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>

</style>
