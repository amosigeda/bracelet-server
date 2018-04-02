package com.bracelet.util;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);

	public final static String accessKeyId = "LTAIIqNyt5D3mDM0";
	public final static String accessKeySecret = "acfLjKRCYBPcait4TDAIljSjWO9axl";
	public final static String accessKeyIdOfBeidou = "LTAI7YNEkaz5J7Vy";
	public final static String accessKeySecretOfBeidou = "EIHW3lpcPMXnxsdW7CW9jnriovXTch";
	public final static String MD5_MAGIC = "water";

	// public static final String KEY = "8ae0a1e668fdcd49bb95bf597f7e5b34";
	public static final String KEY = "b4a2748e41314ae117645aa9589c6723";
	// public static final String GPS_KEY = "c6a272fdecf96b343c31719d6b8cb0be";
	public static final String GPS_KEY = "568c03fa7604ffb79f7f44d399aa6a76";
	public static final String URL = "http://apilocate.amap.com/position";
	public static final String GPS_URL = "http://restapi.amap.com/v3/assistant/coordinate/convert";
	public final static String OneString = "1";
	public final static int distance = 550;

	public static final String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	// appid和appSecret 是在公众平台上申请的
	// AppId
	public static final String WX_APP_ID = "wx5552efe4fa746264";
	// AppSecret
	public static final String WX_APP_KEY = "1df21c326edd6f12fc9851ff1cc9f490";

	public static String randomString(int len) {
		if (len <= 0) {
			len = 32;
		}
		String chars = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
		int maxPos = chars.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(chars.charAt((int) Math.floor(Math.random() * maxPos)));
		}
		return sb.toString();
	}

	public static int randomInt(int low, int high) {
		return (int) Math.floor(Math.random() * (high - low) + low);
	}

	/**
	 * 生成md5
	 *
	 * @param message
	 */
	public static String getMD5(String message) {
		String md5str = "";
		try {
			// 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 2 将消息变成byte数组
			byte[] input = message.getBytes();

			// 3 计算后获得字节数组,这就是那128位了
			byte[] buff = md.digest(input);

			// 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
			md5str = bytesToHex(buff);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	/**
	 * 二进制转十六进制
	 *
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];

			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}

	/**
	 * 计算两个坐标点距离
	 * 
	 * @param lngA
	 * @param latA
	 * @param lngB
	 * @param latB
	 * @return 单位：米
	 */
	public static double calcDistance(double lngA, double latA, double lngB, double latB) {
		double earthR = 6371000;
		double x = Math.cos(latA * Math.PI / 180.) * Math.cos(latB * Math.PI / 180.)
				* Math.cos((lngA - lngB) * Math.PI / 180);
		double y = Math.sin(latA * Math.PI / 180.) * Math.sin(latB * Math.PI / 180.);
		double s = x + y;
		if (s > 1)
			s = 1;
		if (s < -1)
			s = -1;
		double alpha = Math.acos(s);
		return alpha * earthR;
	}

	public static String format14DateString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = dateFormat.format(date);
		return dateString;
	}

	public static String format14DateString(long time) {
		return format14DateString(new Date(time));
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String formatDateDay(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateString = dateFormat.format(date);
		return dateString;
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 90mmHg<收缩压<140mmHg 60mmHg<舒张压<90mmHg
	 */
	public static List<Map<String, Object>> checkHeartPressure(Integer maxHeartPressure, Integer minHeartPressure) {
		List<Map<String, Object>> resultList = new LinkedList<Map<String, Object>>();
		Map<String, Object> maxResultMap = new HashMap<>();
		if (maxHeartPressure < 90) {
			maxResultMap.put("type", 1);
			maxResultMap.put("code", 2);
			maxResultMap.put("msg", "偏低");
		} else if (maxHeartPressure > 140) {
			maxResultMap.put("type", 1);
			maxResultMap.put("code", 1);
			maxResultMap.put("msg", "偏高");
		} else {
			maxResultMap.put("type", 1);
			maxResultMap.put("code", 0);
			maxResultMap.put("msg", "正常");
		}
		Map<String, Object> minResultMap = new HashMap<>();
		if (minHeartPressure < 60) {
			minResultMap.put("type", 2);
			minResultMap.put("code", 2);
			minResultMap.put("msg", "偏低");
		} else if (minHeartPressure > 90) {
			minResultMap.put("type", 2);
			minResultMap.put("code", 1);
			minResultMap.put("msg", "偏高");
		} else {
			minResultMap.put("type", 2);
			minResultMap.put("code", 0);
			minResultMap.put("msg", "正常");
		}
		resultList.add(maxResultMap);
		resultList.add(minResultMap);
		return resultList;
	}

	/**
	 * 60～100次/分
	 */
	public static Map<String, Object> checkHeartRate(Integer heartRate) {
		Map<String, Object> resultMap = new HashMap<>();
		if (heartRate < 60) {
			resultMap.put("code", 2);
			resultMap.put("msg", "偏慢");
		} else if (heartRate > 100) {
			resultMap.put("code", 1);
			resultMap.put("msg", "偏快");
		} else {
			resultMap.put("code", 0);
			resultMap.put("msg", "正常");
		}
		return resultMap;
	}

	// 根据UnicodeBlock方法判断中文标点符号
	public static boolean isChinesePunctuation(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
				|| ub == Character.UnicodeBlock.VERTICAL_FORMS) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 围栏 判断点是否在多边形内
	 * 
	 * @param point
	 *            检测点
	 * @param pts
	 *            多边形的顶点
	 * @return 点在多边形内返回true,否则返回false
	 * @author wei
	 */
	public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts) {

		int N = pts.size();
		boolean boundOrVertex = true; // 如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
		int intersectCount = 0;// cross points count of x
		double precision = 2e-10; // 浮点类型计算时候与0比较时候的容差
		Point2D.Double p1, p2;// neighbour bound vertices
		Point2D.Double p = point; // 当前点

		p1 = pts.get(0);// left vertex
		for (int i = 1; i <= N; ++i) {// check all rays
			if (p.equals(p1)) {
				return boundOrVertex;// p is an vertex
			}

			p2 = pts.get(i % N);// right vertex
			if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {// ray
																			// is
																			// outside
																			// of
																			// our
																			// interests
				p1 = p2;
				continue;// next ray left point
			}

			if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {// ray
																			// is
																			// crossing
																			// over
																			// by
																			// the
																			// algorithm
																			// (common
																			// part
																			// of)
				if (p.y <= Math.max(p1.y, p2.y)) {// x is before of ray
					if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {// overlies
																		// on a
																		// horizontal
																		// ray
						return boundOrVertex;
					}

					if (p1.y == p2.y) {// ray is vertical
						if (p1.y == p.y) {// overlies on a vertical ray
							return boundOrVertex;
						} else {// before ray
							++intersectCount;
						}
					} else {// cross point on the left side
						double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;// cross
																								// point
																								// of
																								// y
						if (Math.abs(p.y - xinters) < precision) {// overlies on
																	// a ray
							return boundOrVertex;
						}

						if (p.y < xinters) {// before ray
							++intersectCount;
						}
					}
				}
			} else {// special case when ray is crossing through the vertex
				if (p.x == p2.x && p.y <= p2.y) {// p crossing over p2
					Point2D.Double p3 = pts.get((i + 1) % N); // next vertex
					if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {// p.x
																						// lies
																						// between
																						// p1.x
																						// &
																						// p3.x
						++intersectCount;
					} else {
						intersectCount += 2;
					}
				}
			}
			p1 = p2;// next ray left point
		}

		if (intersectCount % 2 == 0) {// 偶数在多边形外
			return false;
		} else { // 奇数在多边形内
			return true;
		}
	}

	/**
	 * 将经纬度点集合转换为GeneralPath对象
	 *
	 * @param points
	 *            点集合(有序)
	 *
	 * @return GeneralPath对象
	 * @author Administrator 存在点重合的问题
	 */
	public static GeneralPath genGeneralPath(ArrayList<Point2D.Double> points) {
		GeneralPath path = new GeneralPath();

		if (points.size() < 3) {
			return null;
		}

		path.moveTo((float) points.get(0).getX(), (float) points.get(0).getY());

		for (Iterator<Point2D.Double> it = points.iterator(); it.hasNext();) {
			Point2D.Double point = (Point2D.Double) it.next();

			path.lineTo((float) point.getX(), (float) point.getY());
		}

		path.closePath();

		return path;
	}

	public static String get(String url) {
		String body = null;
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			logger.info("create httppost:" + url);
			HttpGet get = new HttpGet(url);
			get.addHeader("Accept-Charset", "utf-8");
			HttpResponse response = sendRequest(httpClient, get);
			body = parseResponse(response);
		} catch (IOException e) {
			logger.error("send post request failed: {}", e.getMessage());
		}

		return body;
	}

	@SuppressWarnings("unused")
	private static String paramsToString(Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		try {
			for (String key : params.keySet()) {
				sb.append(String.format("&%s=%s", key,
						URLEncoder.encode(params.get(key), StandardCharsets.UTF_8.toString())));
			}
		} catch (UnsupportedEncodingException e) {
			logger.warn("{}: encode url parameters failed", e.getMessage());
		}
		return sb.length() > 0 ? "?".concat(sb.substring(1)) : "";
	}

	private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost)
			throws ClientProtocolException, IOException {
		HttpResponse response = null;
		response = httpclient.execute(httpost);
		return response;
	}

	private static String parseResponse(HttpResponse response) {
		logger.info("get response from http server..");
		HttpEntity entity = response.getEntity();

		logger.info("response status: " + response.getStatusLine());
		Charset charset = ContentType.getOrDefault(entity).getCharset();
		if (charset != null) {
			logger.info(charset.name());
		}

		String body = null;
		try {
			body = EntityUtils.toString(entity, "utf-8");
			logger.info("body " + body);
		} catch (IOException e) {
			logger.warn("{}: cannot parse the entity", e.getMessage());
		}

		return body;
	}

	/*
	 * 处理https GET/POST请求 请求地址、请求方法、参数
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = null;
		try {
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			;
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			// 设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.connect();
			// 往服务器端写内容
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}

			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		String s = httpsRequest("url：https://api.yaokongyun.cn/chip/m.php", "POST", "abad");
		System.out.println(s);
	}
}
