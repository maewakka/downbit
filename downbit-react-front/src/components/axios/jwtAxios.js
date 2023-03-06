import axios from "axios";

const headers = {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.getItem("accessToken"),
    'Access-Control-Allow-Origin': '*',
}

const jwtAxios = axios.create({
    baseURL: '/api',
    headers: headers
});

jwtAxios.interceptors.request.use(
    (config) => {
        const accessToken = localStorage.getItem("accessToken")

        config.headers.Authorization = 'Bearer ' + accessToken;

        return config;
    }, (error) => {
        return Promise.reject(error);
    }
)

export default jwtAxios;