import axios from "axios";
import {ElMessage} from "element-plus";
import {userStore} from "@/stores";

function requestError(response) {
    return new Promise((_, reject) => {
        const {data} = response;
        ElMessage({
            message: data.msg,
            type: "error",
        });
        reject(data);
    });
}

function throttleToLogin() {
    alert("to login page")
}

export function request(config) {
    // 设置请求配置
    const instance = axios.create({
        baseURL: import.meta.env.VITE_APP_BASEAPI,
        timeout: 5000,
    });

    // 配置请求头
    instance.interceptors.request.use((config) => {
        const userData = userStore();
        if (userData.isLogin && !requestInstance.removeAuth) {
            requestInstance.headers["Authorization"] = userData.token;
        }
        return config;
    });

    instance.interceptors.response.use(
        (response) => {
            const {code, data} = response.data;
            // 目前和公司后端口头约定是字符串,以防万一强制转字符串
            switch (`${code}`) {
                //代表没有错误
                case "200":
                    return data;
                //代表token 过期打回登录页
                case "401":
                    throttleToLogin();
                    break
                default:
                    // 不是正确的 code
                    return requestError(response);
            }
        },
        (error) => {
            ElMessage({
                message: error.message,
                type: "error",
            });
            return Promise.reject(error);
        }
    );
    return instance;
}
