import HttpRequest from "infrastructure/utils/request";
import localStore from "infrastructure/utils/storage";


export default new HttpRequest({
	baseURL: process.env.apiBaseUrl,
	timeout: 30,
}, {
	requestInterceptor: (config) => {
		const token = localStore.getItem('token');
		if (token) {
			// @ts-ignore
			config.headers.Authorization = `Bearer ${token}`
		}
		return config
	},
	requestInterceptorCatch: (err) => err,
	responseInterceptor: (res) => res.data,
	responseInterceptorCatch: (err) => err
});