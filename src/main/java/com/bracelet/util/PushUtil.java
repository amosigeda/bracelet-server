package com.bracelet.util;

import com.aliyuncs.push.model.v20160801.PushMessageToAndroidRequest;
import com.aliyuncs.push.model.v20160801.PushMessageToAndroidResponse;
import com.aliyuncs.push.model.v20160801.PushNoticeToAndroidRequest;
import com.aliyuncs.push.model.v20160801.PushNoticeToAndroidResponse;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.aliyuncs.utils.ParameterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Date;

public class PushUtil {

	private static Logger logger = LoggerFactory.getLogger(PushUtil.class);
	private static IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", Utils.accessKeyId,
			Utils.accessKeySecret);
	private static Long appKey = 24620906L;

	public static void push(String targetValue, String title, String content, String notifyContent) {
		pushMessage(targetValue, title, content, 0);
		pushNotify(targetValue, title, content, notifyContent, 0);
		pushNotifyProduct(targetValue, title, content, notifyContent, 0);
		//pushMessage(targetValue, title, content, 10);
		//pushNotify(targetValue, title, content, notifyContent, 10);
	}
	

	public static PushResponse pushMessage(String targetValue, String title, String content, int second) {
		logger.info(
				"开始推送[消息][targetValue:" + targetValue + ",title:" + title + ",content:" + content + ",second:" + second + "]");
		PushResponse result = null;
		try {
			DefaultAcsClient client = new DefaultAcsClient(profile);
			PushRequest pushRequest = new PushRequest();
			pushRequest.setProtocol(ProtocolType.HTTP);
			pushRequest.setMethod(MethodType.POST);
			pushRequest.setAppKey(appKey);
			pushRequest.setTarget("ACCOUNT");
			pushRequest.setTargetValue(targetValue);
			pushRequest.setPushType("MESSAGE");
			pushRequest.setDeviceType("ALL");
			pushRequest.setTitle(title);
			pushRequest.setBody(content);
			// 推送控制
			Date pushDate = new Date(System.currentTimeMillis() + second * 1000);
			String pushTime = ParameterHelper.getISO8601Time(pushDate);
			pushRequest.setPushTime(pushTime);
			pushRequest.setStoreOffline(true);
			result = client.getAcsResponse(pushRequest);
			logger.info("收到推送[消息]结果[targetValue:" + targetValue + ",title:" + title + ",content:" + content + ",second:"
					+ second + "] -> RequestId: " + result.getRequestId() + ", MessageId:" + result.getMessageId());
		} catch (Exception e) {
			logger.info("推送发送错误:", e);
		}
		return result;
	}

	public static PushResponse pushNotify(String targetValue, String title, String content, String notifyContent,
			int second) {
		logger.info(
				"开始推送[通知][targetValue:" + targetValue + ",title:" + title + ",content:" + content + ",second:" + second + "]");
		PushResponse result = null;
		try {
			DefaultAcsClient client = new DefaultAcsClient(profile);
			PushRequest pushRequest = new PushRequest();
			pushRequest.setProtocol(ProtocolType.HTTP);
			//内容较大的请求，使用POST请求
			pushRequest.setMethod(MethodType.POST);
			pushRequest.setAppKey(appKey);
			pushRequest.setTarget("ACCOUNT");
			pushRequest.setTargetValue(targetValue);
			pushRequest.setPushType("NOTICE");
			// pushRequest.setDeviceType("ANDROID");
			pushRequest.setDeviceType("ALL");
			pushRequest.setTitle(title);
		    pushRequest.setBody(notifyContent);
			// 推送配置: iOS
			pushRequest.setIOSBadge(1);
			pushRequest.setIOSMutableContent(true);
			pushRequest.setIOSApnsEnv("DEV");//PRODUCT
			
			pushRequest.setIOSRemind(true);
			pushRequest.setIOSRemindBody(title);
			pushRequest.setIOSExtParameters(content);

			// 推送配置: Android
			pushRequest.setAndroidNotifyType("BOTH");
			pushRequest.setAndroidNotificationBarType(1);
			pushRequest.setAndroidNotificationBarPriority(1);
			pushRequest.setAndroidOpenType("APPLICATION");
			pushRequest.setAndroidExtParameters(content);
			// 推送控制
			Date pushDate = new Date(System.currentTimeMillis() + second * 1000);
			String pushTime = ParameterHelper.getISO8601Time(pushDate);
			pushRequest.setPushTime(pushTime);
			pushRequest.setStoreOffline(true);
			result = client.getAcsResponse(pushRequest);
			logger.info("收到推送[通知]结果[targetValue:" + targetValue + ",title:" + title + ",content:" + content + ",second:"
					+ second + "] -> RequestId: " + result.getRequestId() + ", MessageId:" + result.getMessageId());
		} catch (Exception e) {
			logger.info("推送发送错误:", e);
		}
		return result;
	}
	
	public static PushResponse pushNotifyProduct(String targetValue, String title, String content, String notifyContent,
			int second) {
		logger.info(
				"开始推送[通知][targetValue:" + targetValue + ",title:" + title + ",content:" + content + ",second:" + second + "]");
		PushResponse result = null;
		try {
			DefaultAcsClient client = new DefaultAcsClient(profile);
			PushRequest pushRequest = new PushRequest();
			pushRequest.setProtocol(ProtocolType.HTTP);
			//内容较大的请求，使用POST请求
			pushRequest.setMethod(MethodType.POST);
			pushRequest.setAppKey(appKey);
			pushRequest.setTarget("ACCOUNT");
			pushRequest.setTargetValue(targetValue);
			pushRequest.setPushType("NOTICE");
			// pushRequest.setDeviceType("ANDROID");
			pushRequest.setDeviceType("ALL");
			pushRequest.setTitle(title);
		    pushRequest.setBody(notifyContent);
			// 推送配置: iOS
			pushRequest.setIOSBadge(1);
			pushRequest.setIOSMutableContent(true);
			pushRequest.setIOSApnsEnv("PRODUCT");//PRODUCT
			
			pushRequest.setIOSRemind(true);
			pushRequest.setIOSRemindBody(title);
			pushRequest.setIOSExtParameters(content);

			// 推送配置: Android
			pushRequest.setAndroidNotifyType("BOTH");
			pushRequest.setAndroidNotificationBarType(1);
			pushRequest.setAndroidNotificationBarPriority(1);
			pushRequest.setAndroidOpenType("APPLICATION");
			pushRequest.setAndroidExtParameters(content);
			// 推送控制
			Date pushDate = new Date(System.currentTimeMillis() + second * 1000);
			String pushTime = ParameterHelper.getISO8601Time(pushDate);
			pushRequest.setPushTime(pushTime);
			pushRequest.setStoreOffline(true);
			result = client.getAcsResponse(pushRequest);
			logger.info("收到推送[通知]结果[targetValue:" + targetValue + ",title:" + title + ",content:" + content + ",second:"
					+ second + "] -> RequestId: " + result.getRequestId() + ", MessageId:" + result.getMessageId());
		} catch (Exception e) {
			logger.info("推送发送错误:", e);
		}
		return result;
	}
	
	/**
     * 推送消息给android
     * <p>
     * 参见文档 https://help.aliyun.com/document_detail/48085.html
     */
    public static void testPushMessageToAndroid(String targetValue, String title, String content){
    	try {
    	DefaultAcsClient client = new DefaultAcsClient(profile);
        PushMessageToAndroidRequest androidRequest = new PushMessageToAndroidRequest();
        //安全性比较高的内容建议使用HTTPS
        androidRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        androidRequest.setMethod(MethodType.POST);
        androidRequest.setAppKey(appKey);
        androidRequest.setTarget("ACCOUNT");  //"ACCOUNT"
        androidRequest.setTargetValue(targetValue);
        androidRequest.setTitle(title);
        androidRequest.setBody(content);
         PushMessageToAndroidResponse	pushMessageToAndroidResponse = client.getAcsResponse(androidRequest);
			System.out.printf("RequestId: %s, MessageId: %s\n",
					pushMessageToAndroidResponse.getRequestId(), pushMessageToAndroidResponse.getMessageId());
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    /**
     * 推送通知给android
     * <p>
     * 参见文档 https://help.aliyun.com/document_detail/48087.html
     */
    public static void testPushNoticeToAndroid(String targetValue, String title, String content)  {
    	try {
    		DefaultAcsClient client = new DefaultAcsClient(profile);
        PushNoticeToAndroidRequest androidRequest = new PushNoticeToAndroidRequest();
        //安全性比较高的内容建议使用HTTPS
        androidRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        androidRequest.setMethod(MethodType.POST);
        androidRequest.setAppKey(appKey);
        androidRequest.setTarget("ACCOUNT");
        androidRequest.setTargetValue(targetValue);
        androidRequest.setTitle(title);
        androidRequest.setBody(content);
        androidRequest.setExtParameters("{\"k1\":\"v1\"}");
		
         PushNoticeToAndroidResponse	pushNoticeToAndroidResponse = client.getAcsResponse(androidRequest);
			System.out.printf("RequestId: %s, MessageId: %s\n",
					pushNoticeToAndroidResponse.getRequestId(), pushNoticeToAndroidResponse.getMessageId());
		
    	} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
