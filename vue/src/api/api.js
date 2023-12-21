import {request} from '@/api';

export const pluginList = () => request({url: "/admin/plugin/list"});
export const pluginListApi = () => request().get("/admin/plugin/list", {params: {}});
export const pluginInfo = (id) => request({url: "/admin/plugin/get", id: id});

