package io.github.scrumboot.langs.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingdyee
 * @since 2022/03/23
 */
public interface IHttpRequest<T> {

    /**
     * 获取接口路径
     *
     * @return 接口路径
     */
    String getApiUri();

    /**
     * 获取请求方式
     *
     * @return 请求方式
     */
    RequestMethod getRequestMethod();

    /**
     * 获取请求参数
     *
     * @return 请求参数
     */
    Map<String, String> getRequestParams();

    /**
     * 获取请求体参数
     *
     * @return 请求体参数
     */
    default Map<String, String> getBodyRequestParams() {
        return new HashMap<>();
    }

    /**
     * 获取请求头
     *
     * @return 请求头
     */
    default Map<String, String> getHeaders() {return new HashMap<>(); }

    /**
     * 获取返回数据类型
     *
     * @return 返回数据类型
     */
    Class<T> getResponseClass();


}
