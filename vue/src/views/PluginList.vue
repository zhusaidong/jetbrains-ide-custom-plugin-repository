<script setup>
import {pluginListApi} from '@/api/api';

let data = reactive({
  list: [],
  page: {
    currentPage: 1,
    pageSize: 5,
    total: 0
  }
});

watch(
    () => data.page.currentPage,
    (currentPage) => {
      console.log(`currentPage is: ${currentPage}`)
    }
)
watch(
    () => data.page.pageSize,
    (pageSize) => {
      console.log(`pageSize is: ${pageSize}`)
    }
)

onMounted(() => {
  pluginListApi().then(res => data.list = res)
  data.page = {
    currentPage: 1,
    pageSize: 5,
    total: 100
  }
})

</script>

<template>
  <div class="flex">
    <el-table :data="data.list" height="250">
      <el-table-column label="插件名称" prop="name" width="180"/>
      <el-table-column label="插件版本" prop="version" width="180"/>
      <el-table-column label="ide版本" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            {{ scope.row.ideaVersionXml?.sinceBuild }}
            <div v-if="scope.row.ideaVersionXml?.untilBuild"> - {{ scope.row.ideaVersionXml?.untilBuild }}</div>
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
    <el-pagination
        v-model:current-page="data.page.currentPage"
        v-model:page-size="data.page.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="data.page.total"
        layout="prev,pager,next,total,sizes"
    />
  </div>
</template>

<style scoped>

</style>
