/// <reference types="vite/client" />
declare module 'chroma-js';
declare module 'notistack';
declare module '@tinymce/tinymce-react';
declare module 'mui-datatables' {
	export interface AxiosResponse<T = any> extends Promise<T> {}
}