import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';


interface ResponseData<T> {
	data: T;
	code: number;
	success: boolean;
	message: string;
}


interface InterceptorHooks {
	requestInterceptor?: (config: AxiosRequestConfig) => AxiosRequestConfig;
	requestInterceptorCatch?: (error: any) => any;
	responseInterceptor?: (response: AxiosResponse) => AxiosResponse;
	responseInterceptorCatch?: (error: any) => any;
}


class HttpRequest {
	config: AxiosRequestConfig;
	instance: AxiosInstance;
	interceptorHooks?: InterceptorHooks;

	constructor(options: AxiosRequestConfig, interceptorHooks?: InterceptorHooks) {
		this.config = options;
		this.instance = axios.create(options);
		this.interceptorHooks = interceptorHooks;
		this.setupInterceptor();
	}

	request<T = any>(config: AxiosRequestConfig ): Promise<T> {
		return new Promise((resolve, reject) => {
			this.instance
				.request<any, ResponseData<T>>(config)
				.then((res) => {
					resolve(res.data);
				})
				.catch((err) => {
					reject(err);
				});
		});
	}

	setupInterceptor(): void {
		this.instance.interceptors.request.use(
			this.interceptorHooks?.requestInterceptor,
			this.interceptorHooks?.requestInterceptorCatch
		);
		this.instance.interceptors.response.use(
			this.interceptorHooks?.responseInterceptor,
			this.interceptorHooks?.responseInterceptorCatch
		);
	}

	get<T = any>(config: AxiosRequestConfig ): Promise<T> {
		return this.request({ ...config, method: 'GET' });
	}

	post<T = any>(config: AxiosRequestConfig ): Promise<T> {
		return this.request({ ...config, method: 'POST' });
	}

	delete<T = any>(config: AxiosRequestConfig ): Promise<T> {
		return this.request({ ...config, method: 'DELETE' });
	}

	patch<T = any>(config: AxiosRequestConfig ): Promise<T> {
		return this.request({ ...config, method: 'PATCH' });
	}

	put<T = any>(config: AxiosRequestConfig ): Promise<T> {
		return this.request({ ...config, method: 'PUT' });
	}

}

export default HttpRequest;