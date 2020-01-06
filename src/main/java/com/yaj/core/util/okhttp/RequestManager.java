package com.yaj.core.util.okhttp;

import com.alibaba.fastjson.JSON;

import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Peach on 2016-12-13.
 */

public class RequestManager {
	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");// mdiatype
	private static final String TAG = RequestManager.class.getSimpleName();
//	private static final String BASE_URL = "http://localhost:8080";// 请求接口根地址
	
	
	private static volatile RequestManager mInstance;// 单例引用
	public static final int TYPE_GET = 0;// get请求
	public static final int TYPE_POST_JSON = 1;// post请求参数为json
	public static final int TYPE_POST_FORM = 2;// post请求参数为表单
	private OkHttpClient mOkHttpClient;// okHttpClient 实例

	/**
	 * 初始化RequestManager
	 */
	private RequestManager() {
		// 初始化OkHttpClient
		mOkHttpClient = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)// 设置超时时间
				.readTimeout(10, TimeUnit.SECONDS)// 设置读取超时时间
				.writeTimeout(10, TimeUnit.SECONDS)// 设置写入超时时间
				.build();
	}

	/**
	 * 获取单例引用
	 *
	 * @return
	 */
	public static RequestManager getInstance() {
		RequestManager inst = mInstance;
		if (inst == null) {
			synchronized (RequestManager.class) {
				inst = mInstance;
				if (inst == null) {
					inst = new RequestManager();
					mInstance = inst;
				}
			}
		}
		return inst;
	}

	/**
	 * okHttp同步请求统一入口
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param requestType
	 *            请求类型
	 * @param paramsMap
	 *            请求参数
	 */
//	public String requestSyn(String actionUrl, int requestType, HashMap<String, String> paramsMap) {
//		String ResultPay = "";
//		switch (requestType) {
//		case TYPE_GET:
//			ResultPay = requestGetBySyn(actionUrl, paramsMap);
//			break;
//		}
//		return ResultPay;
//	}
	
	
	/**
	* @Title: requestSyn
	* @Description: okHttp同步请求统一入口,返回反序列化对象
	* @param @param actionUrl
	* @param @param requestType
	* @param @param paramsMap
	* @param @param clazz
	* @param @return    参数
	* @return T    返回类型
	* @throws
	* @author Peach
	* @date 2017年4月17日
	*/
//	public <T> T requestSyn(String actionUrl, int requestType, HashMap<String, String> paramsMap,Class clazz) {
//		String ResultPay = "";
//		switch (requestType) {
//		case TYPE_GET:
//			ResultPay = requestGetBySyn(actionUrl, paramsMap);
//			break;
//		case TYPE_POST_JSON:
//			ResultPay = requestPostBySyn(actionUrl, paramsMap);
//			break;
//		case TYPE_POST_FORM:
//			ResultPay = requestPostBySynWithForm(actionUrl, paramsMap);
//			break;
//		}
//		return ResponseManager.parseObject(ResultPay, clazz);
//	}

	/**
	 * okHttp get同步请求
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param paramsMap
	 *            请求参数
	 */
	public String requestGetBySyn(String actionUrl, HashMap<String, String> paramsMap) {
		StringBuilder tempParams = new StringBuilder();
		String result = "";
		try {
			// 处理参数
			int pos = 0;
			for (String key : paramsMap.keySet()) {
				if (pos > 0) {
					tempParams.append("&");
				}
				// 对参数进行URLEncoder
				tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
				pos++;
			}
			// 补全请求地址
			String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
			// 创建一个请求
			Request request = addHeaders().url(requestUrl).build();
			// 创建一个Call
			final Call call = mOkHttpClient.newCall(request);
			// 执行请求
			final Response response = call.execute();
			result = response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * okHttp post同步请求
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param paramsMap
	 *            请求参数
	 */
	public String requestPostJsonBySyn(String actionUrl, Map paramsMap) {
		String result = "";
		try {
			// 补全请求地址
			String requestUrl = actionUrl;
			// 生成参数
			String params = JSON.toJSONString(paramsMap);
			// 创建一个请求实体对象 RequestBody
			RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
			// 创建一个请求
			final Request request = addHeaders().url(requestUrl).post(body).build();
			// 创建一个Call
			final Call call = mOkHttpClient.newCall(request);
			// 执行请求
			Response response = call.execute();
			// 请求执行成功
			if (response.isSuccessful()) {
				result = response.body().string();
				// 获取返回数据 可以是String，bytes ,byteStream
				log(TAG, "response ----->" + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	* @Title: requestPostJsonBySynResponseImage
	* @Description: okHttp post同步请求并返回图片
	* @param @param actionUrl
	* @param @param paramsMap
	* @param @return    参数
	* @return String    返回类型
	* @throws
	* @author Orange
	* @date 2017年8月1日
	*/
	public InputStream requestPostJsonBySynResponseImage(String actionUrl, Map paramsMap) {
		InputStream inputStream = null;
		try {
			// 补全请求地址
			String requestUrl = actionUrl;
			// 生成参数
			String params = JSON.toJSONString(paramsMap);
			// 创建一个请求实体对象 RequestBody
			RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
			// 创建一个请求
			final Request request = addHeaders().url(requestUrl).post(body).build();
			// 创建一个Call
			final Call call = mOkHttpClient.newCall(request);
			// 执行请求
			Response response = call.execute();
			// 请求执行成功
			if (response.isSuccessful()) {
				inputStream = response.body().byteStream();
				// 获取返回数据 可以是String，bytes ,byteStream
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}


	/**
	 * okHttp post同步请求表单提交
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param paramsMap
	 *            请求参数
	 */
//	private String requestPostBySynWithForm(String actionUrl, HashMap<String, String> paramsMap) {
//		String ResultPay = "";
//		try {
//			// 创建一个FormBody.Builder
//			FormBody.Builder builder = new FormBody.Builder();
//			for (String key : paramsMap.keySet()) {
//				// 追加表单信息
//				builder.add(key, paramsMap.get(key));
//			}
//			// 生成表单实体对象
//			RequestBody formBody = builder.build();
//			// 补全请求地址
//			String requestUrl = String.format("%s", actionUrl);
//			// 创建一个请求
//			final Request request = addHeaders().url(requestUrl).post(formBody).build();
//			// 创建一个Call
//			final Call call = mOkHttpClient.newCall(request);
//			// 执行请求
//			Response response = call.execute();
//			if (response.isSuccessful()) {
//				ResultPay = response.body().string();
//				log(TAG, "response ----->" + ResultPay);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResultPay;
//	}
	
	//------------------------------------------------------我是分割线--------------------------------------------------------

	/**
	 * okHttp异步请求统一入口
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param requestType
	 *            请求类型
	 * @param paramsMap
	 *            请求参数
	 * @param callBack
	 *            请求返回数据回调
	 * @param <T>
	 *            数据泛型
	 */
	public <T> Call requestAsyn(String actionUrl, int requestType, HashMap<String, String> paramsMap, RequestCallBack<T> callBack) {
		Call call = null;
		switch (requestType) {
		case TYPE_GET:
			call = requestGetByAsyn(actionUrl, paramsMap, callBack);
			break;
		case TYPE_POST_JSON:
			call = requestPostByAsyn(actionUrl, paramsMap, callBack);
			break;
		case TYPE_POST_FORM:
			call = requestPostByAsynWithForm(actionUrl, paramsMap, callBack);
			break;
		}
		return call;
	}
	
	public <T> Call requestAsyn(String actionUrl, int requestType, HashMap<String, String> paramsMap,Class clazz,RequestCallBack<T> callBack) {
		Call call = null;
		switch (requestType) {
		case TYPE_GET:
			call = requestGetByAsyn(actionUrl, paramsMap, callBack,clazz);
			break;
		case TYPE_POST_JSON:
			call = requestPostByAsyn(actionUrl, paramsMap, callBack,clazz);
			break;
		case TYPE_POST_FORM:
			call = requestPostByAsynWithForm(actionUrl, paramsMap, callBack,clazz);
			break;
		}
		return call;
	}

	/**
	 * okHttp get异步请求
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param paramsMap
	 *            请求参数
	 * @param callBack
	 *            请求返回数据回调
	 * @param <T>
	 *            数据泛型
	 * @return
	 */
	private <T> Call requestGetByAsyn(String actionUrl, HashMap<String, String> paramsMap, final RequestCallBack<T> callBack) {
		StringBuilder tempParams = new StringBuilder();
		try {
			int pos = 0;
			for (String key : paramsMap.keySet()) {
				if (pos > 0) {
					tempParams.append("&");
				}
				tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
				pos++;
			}
			String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
			final Request request = addHeaders().url(requestUrl).build();
			final Call call = mOkHttpClient.newCall(request);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					failedCallBack("访问失败", callBack);
					log(TAG, e.toString());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						String string = response.body().string();
						log(TAG, "response ----->" + string);
						successCallBack((T) string, callBack);
					} else {
						failedCallBack("服务器错误", callBack);
					}
				}
			});
			return call;
		} catch (Exception e) {
			log(TAG, e.toString());
		}
		return null;
	}
	
	private <T> Call requestGetByAsyn(String actionUrl, HashMap<String, String> paramsMap, final RequestCallBack<T> callBack,final Class clazz) {
		StringBuilder tempParams = new StringBuilder();
		try {
			int pos = 0;
			for (String key : paramsMap.keySet()) {
				if (pos > 0) {
					tempParams.append("&");
				}
				tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
				pos++;
			}
			String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
			final Request request = addHeaders().url(requestUrl).build();
			final Call call = mOkHttpClient.newCall(request);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					failedCallBack("访问失败", callBack);
					log(TAG, e.toString());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						String json = response.body().string();
						log(TAG, "response ----->" + json);
						T t = ResponseManager.parseObject(json, clazz);
						successCallBack(t, callBack);
					} else {
						failedCallBack("服务器错误", callBack);
					}
				}
			});
			return call;
		} catch (Exception e) {
			log(TAG, e.toString());
		}
		return null;
	}

	/**
	 * okHttp post异步请求
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param paramsMap
	 *            请求参数
	 * @param callBack
	 *            请求返回数据回调
	 * @param <T>
	 *            数据泛型
	 * @return
	 */
	private <T> Call requestPostByAsyn(String actionUrl, HashMap<String, String> paramsMap, final RequestCallBack<T> callBack) {
		try {
			StringBuilder tempParams = new StringBuilder();
			int pos = 0;
			if(paramsMap!=null){
				for (String key : paramsMap.keySet()) {
					if (pos > 0) {
						tempParams.append("&");
					}
					tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
					pos++;
				}
			}
			String params = tempParams.toString();
			RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
			String requestUrl = String.format("%s", actionUrl);
			final Request request = addHeaders().url(requestUrl).post(body).build();
			final Call call = mOkHttpClient.newCall(request);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					failedCallBack("访问失败", callBack);
					log(TAG, e.toString());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						String string = response.body().string();
						log(TAG, "response ----->" + string);
						successCallBack((T) string, callBack);
					} else {
						failedCallBack("服务器错误", callBack);
					}
				}
			});
			return call;
		} catch (Exception e) {
			log(TAG, e.toString());
		}
		return null;
	}
	
	private <T> Call requestPostByAsyn(String actionUrl, HashMap<String, String> paramsMap, final RequestCallBack<T> callBack,final Class clazz) {
		try {
			StringBuilder tempParams = new StringBuilder();
			int pos = 0;
			if(paramsMap!=null){
				for (String key : paramsMap.keySet()) {
					if (pos > 0) {
						tempParams.append("&");
					}
					tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
					pos++;
				}
			}
			String params = tempParams.toString();
			RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
			String requestUrl = String.format("%s", actionUrl);
			final Request request = addHeaders().url(requestUrl).post(body).build();
			final Call call = mOkHttpClient.newCall(request);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					failedCallBack("访问失败", callBack);
					log(TAG, e.toString());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						String json = response.body().string();
						log(TAG, "response ----->" + json);
						T t = ResponseManager.parseObject(json, clazz);
						successCallBack(t, callBack);
					} else {
						failedCallBack("服务器错误", callBack);
					}
				}
			});
			return call;
		} catch (Exception e) {
			log(TAG, e.toString());
		}
		return null;
	}

	/**
	 * okHttp post异步请求表单提交
	 *
	 * @param actionUrl
	 *            接口地址
	 * @param paramsMap
	 *            请求参数
	 * @param callBack
	 *            请求返回数据回调
	 * @param <T>
	 *            数据泛型
	 * @return
	 */
	private <T> Call requestPostByAsynWithForm(String actionUrl, HashMap<String, String> paramsMap, final RequestCallBack<T> callBack) {
		try {
			FormBody.Builder builder = new FormBody.Builder();
			for (String key : paramsMap.keySet()) {
				builder.add(key, paramsMap.get(key));
			}
			RequestBody formBody = builder.build();
			String requestUrl = String.format("%s", actionUrl);
			final Request request = addHeaders().url(requestUrl).post(formBody).build();
			final Call call = mOkHttpClient.newCall(request);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					failedCallBack("访问失败", callBack);
					log(TAG, e.toString());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						String string = response.body().string();
						log(TAG, "response ----->" + string);
						successCallBack((T) string, callBack);
					} else {
						failedCallBack("服务器错误", callBack);
					}
				}
			});
			return call;
		} catch (Exception e) {
			log(TAG, e.toString());
		}
		return null;
	}
	
	private <T> Call requestPostByAsynWithForm(String actionUrl, HashMap<String, String> paramsMap, final RequestCallBack<T> callBack,final Class clazz) {
		try {
			FormBody.Builder builder = new FormBody.Builder();
			for (String key : paramsMap.keySet()) {
				builder.add(key, paramsMap.get(key));
			}
			RequestBody formBody = builder.build();
			String requestUrl = String.format("%s", actionUrl);
			final Request request = addHeaders().url(requestUrl).post(formBody).build();
			final Call call = mOkHttpClient.newCall(request);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					failedCallBack("访问失败", callBack);
					log(TAG, e.toString());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						String json = response.body().string();
						log(TAG, "response ----->" + json);
						T t = ResponseManager.parseObject(json, clazz);
						successCallBack(t, callBack);
					} else {
						failedCallBack("服务器错误", callBack);
					}
				}
			});
			return call;
		} catch (Exception e) {
			log(TAG, e.toString());
		}
		return null;
	}

	/**
	 * 统一为请求添加头信息
	 *
	 * @return
	 */
	private Request.Builder addHeaders() {
		Request.Builder builder = new Request.Builder();
		// .addHeader("Connection", "keep-alive")
		// .addHeader("platform", "2")
		// .addHeader("phoneModel", Build.MODEL)
		// .addHeader("systemVersion", Build.VERSION.RELEASE)
		// .addHeader("appVersion", "1.0.0");
		return builder;
	}

	/**
	 * 统一同意处理成功信息
	 *
	 * @param result
	 * @param callBack
	 * @param <T>
	 */
	private <T> void successCallBack(final T result, final RequestCallBack<T> callBack) {
		// okHttpHandler.post(new Runnable() {
		// @Override
		// public void run() {
		// if (callBack != null) {
		// callBack.onRequestSuccess(ResultPay);
		// }
		// }
		// });
		if (callBack != null) {
			callBack.onRequestSuccess(result);
		}
	}

	/**
	 * 统一处理失败信息
	 *
	 * @param errorMsg
	 * @param callBack
	 * @param <T>
	 */
	private <T> void failedCallBack(final String errorMsg, final RequestCallBack<T> callBack) {
		// okHttpHandler.post(new Runnable() {
		// @Override
		// public void run() {
		// if (callBack != null) {
		// callBack.onRequestFailed(errorMsg);
		// }
		// }
		// });
		if (callBack != null) {
			callBack.onRequestFailed(errorMsg);
		}
	}

	private void log(String s1, String s2) {
		System.out.println(s1+"-"+s2);
	}
}
