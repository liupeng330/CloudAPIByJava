package me.pengliu.cloudapi;

import java.io.Serializable;

import me.pengliu.cloudapi.models.MediaListBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
    	CloudAPI api = new CloudAPI();
    	api.Login("pengliu@realnetworks.com", "123456");
//    	MediaListBean medias = api.GetAllV1();
//    	String id = api.UploadFile("/home/peng/Pictures/Wildlife.wmv");
//    	api.DeleteFile(id);
    	api.DeleteAll();
	}
}



