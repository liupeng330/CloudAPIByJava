package me.pengliu.cloudapi;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.Base64.Encoder;

import javax.print.DocFlavor.STRING;

import org.apache.commons.collections.functors.NullPredicate;

import me.pengliu.cloudapi.models.*;
import me.pengliu.util.*;

public class CloudAPI
{
	private String usertoken;
	
	public String getUsertoken()
	{
		return usertoken;
	}
	
	public void setUsertoken(String usertoken) 
	{
		this.usertoken = usertoken;
	}
	
	private Map<String, String> GetDefaultHeader()
	{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "RPCToken " + getUsertoken());
		headers.put("X-RPC-AUTHORIZATION", "test:c2VjcmV0");
		return headers;
	}

	public void Login(String username, String passwd) throws Exception
	{
		Encoder encoder =  Base64.getEncoder();
		String usernameAndPasswd = username + ":" + passwd;
		byte[] base64 = encoder.encode(usernameAndPasswd.getBytes());
		String base64InString = new String(base64);
		
    	Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Basic " + base64InString);
		headers.put("X-RPC-AUTHORIZATION", "test:c2VjcmV0");
		headers.put("Content-Type", "application/json");
		
		String postContent= "{\"device_id\": \"1234567890\", "
				+ "\"device_user_agent\": \"your_device/your_app\", "
				+ "\"device_name\": \"Postman REST Client\", "
				+ "\"trackin\": {\"distcode\" : \"DIST1234\", "
								+ "\"origcode\" : \"cx1234\", "
								+ "\"\" : \"windowsapp\", "
								+ "\"cpath\" : \"windowsapp\", "
								+ "\"rsrc\" : \"firstrun\" }, "
								+ "\"tos_agree\": true }";
		 
    	HttpResponse response =  HttpHelper.sendHttpsPost("https://users.int2.real.com/v1/users/login", headers, postContent);
    	System.out.println();
    	
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(UserInfoBean.class);
		jsonConfig.setJavaPropertyFilter(
				HttpHelper.GetPropertyFilter("entitlements", "devices", "preferences", "geolocation", "authentication_modes", "image_files"));
		
		JSONObject responseJsonObject = JSONObject.fromObject(response.getResponseString());
		UserInfoBean userInfo = (UserInfoBean)JSONObject.toBean(responseJsonObject, jsonConfig);
		this.setUsertoken(userInfo.getUser_token());
	}
	
	public MediaListBean GetAllV1() throws Exception
	{
		if(this.getUsertoken() == null)
		{
			throw new Exception("Usertoken is null!!");
		}
		Map<String, String> headers = GetDefaultHeader();
		HttpResponse response = HttpHelper.sendHttpGet("http://media.int2.real.com/v1/media_info?media_type=photo,video", headers);
		JSONArray responseJsonObject = JSONArray.fromObject("[" + response.getResponseString() + "]");
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(MediaListBean.class);
		jsonConfig.setJavaPropertyFilter(
				HttpHelper.GetPropertyFilter("create_date", 
						"add_date", "mod_date", "access_date", "mime_type", "image_base_path", 
						"image_files", "mime_type", "image_overlay_files", "collection_ids" , 
						"recipients" , "add_date" , "mod_date" , "image_url"));
		MediaListBean[] medias = null; 
		if(responseJsonObject.size() > 0)
		{
			medias = (MediaListBean[])JSONArray.toArray(responseJsonObject, jsonConfig);
		}
		
		return medias[0];
	}
	
	public String UploadFile(String fullFilePath) throws Exception
	{
		if(this.getUsertoken() == null)
		{
			throw new Exception("Usertoken is null!!");
		}
		
		System.out.println("---Start to update init---");
		Map<String, String> headers = GetDefaultHeader();
		headers.put("Content-Type", "application/json");
		
		File file = new File(fullFilePath);
		String postContent = String.format("{\"file_name\": \"%s\",\"file_size\": %d,\"title\": \"%s\"}", 
				file.getName(), 
				file.length(), 
				FileUtil.GetFileNameWithoutExtension(file.getName()));
		
    	HttpResponse response =  HttpHelper.sendHttpPost("http://media.int2.real.com/v1/media_files/init", headers, postContent);
		
		JSONObject responseJsonObject = JSONObject.fromObject(response.getResponseString());
		UploadFileIDBean uploadFileId = (UploadFileIDBean)JSONObject.toBean(responseJsonObject, UploadFileIDBean.class);
		if(uploadFileId == null)
		{
			throw new Exception("Fail to get upload file id from upload init api!!");
		}
		
		System.out.println("---Start to upload chunk---");
		RandomAccessFile accessFile = new RandomAccessFile(fullFilePath, "r");
		FileChannel inChannel = accessFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024000);
		while(inChannel.read(buffer) > 0)
		{
			buffer.flip();
			
            String url = null;
            if (uploadFileId.getOffset() == 0)
            {
                url = String.format("http://media.int2.real.com/v1/media_files?upload_id=%s", uploadFileId.getUpload_id());
            }
            else
            {
                url = String.format("http://media.int2.real.com/v1/media_files?upload_id=%s&offset=%d", uploadFileId.getUpload_id(), uploadFileId.getOffset());
            }
    		headers.put("Content-Type", "application/octet-stream");
    		
			// Send post request
            HttpURLConnection con = (HttpURLConnection)HttpHelper.getHttpRequest(HttpVerb.Post, false, url, headers);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			try
			{
				wr.write(buffer.array());
				wr.flush();
			}
			finally
			{
				wr.close();
			}
			buffer.clear();
			
			int responseCode = con.getResponseCode();

			System.out.println("Post parameters : " + postContent);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			try
			{
				String inputLine;
				StringBuffer responseSB = new StringBuffer();
				while ((inputLine = in.readLine()) != null) 
				{
					responseSB.append(inputLine);
				}
				System.out.println("Response Contents : " + responseSB.toString());
				responseJsonObject = JSONObject.fromObject(responseSB.toString());
				uploadFileId = (UploadFileIDBean)JSONObject.toBean(responseJsonObject, UploadFileIDBean.class);
			}
			finally
			{
				in.close();
			}
		}
		
		System.out.println("---Start to commit---");
		headers.put("Content-Type", "application/json");
		postContent = String.format("{\"file_name\": \"%s\",\"title\": \"%s\"}", 
				file.getName(), 
				FileUtil.GetFileNameWithoutExtension(file.getName()));
    	response =  HttpHelper.sendHttpPost("http://media.int2.real.com/v1/media_files/commit?upload_id=" + uploadFileId.getUpload_id(), headers, postContent);
    	
    	System.out.println("--Start to parse response string--");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(MediaInfoBean.class);
		jsonConfig.setJavaPropertyFilter(
				HttpHelper.GetPropertyFilter("create_date", 
						"add_date", "mod_date", "access_date", "mime_type", "image_base_path", 
						"image_files", "mime_type", "image_overlay_files", "collection_ids" , 
						"recipients" , "add_date" , "mod_date" , "image_url"));
		responseJsonObject = JSONObject.fromObject(response.getResponseString());
		MediaInfoBean mediaInfo = (MediaInfoBean)JSONObject.toBean(responseJsonObject, jsonConfig);
		return mediaInfo.getId();
	}
	
	public void DeleteFile(String guid) throws Exception
	{
		Map<String, String> header = GetDefaultHeader();
		String url = "http://media.int2.real.com/v1/media_info/root/items/" + guid + "?action=delete_from_all";
		HttpResponse response = HttpHelper.sendHttpDel(url, header);
	}
	
	public void DeleteAll() throws Exception
	{
		MediaListBean mediaList = GetAllV1();
		MediaInfoBean[] allMedias = mediaList.getResults();
		for(int i = 0; i < allMedias.length; i++)
		{
			MediaInfoBean media = allMedias[i];
			if(media.getType().equals("collection"))
			{
				System.out.println("--Ignore collection '" + media.getTitle() + "'");
				continue;
			}
			System.out.println("--Start to delete '" + media.getTitle() + "'");
			DeleteFile(media.getId());
		}
	}
}
