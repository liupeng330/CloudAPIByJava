package me.pengliu.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.util.PropertyFilter;

public class HttpHelper
{
	public static HttpResponse sendHttpGet(String url, Map<String, String> headers) throws Exception
	{
		return send(HttpVerb.Get, false, url, headers, null);
	}
	
	public static HttpResponse sendHttpPost(String url, Map<String, String> headers, String postContent) throws Exception
	{
		return send(HttpVerb.Get, false, url, headers, postContent);
	}
	
	public static HttpResponse sendHttpsGet(String url, Map<String, String> headers) throws Exception
	{
		return send(HttpVerb.Get, true, url, headers, null);
	}
	
	public static HttpResponse sendHttpsPost(String url, Map<String, String> headers, String postContent) throws Exception
	{
		return send(HttpVerb.Get, true, url, headers, postContent);
	}
	
	public static HttpResponse sendHttpDel(String url, Map<String, String> header) throws Exception
	{
		return send(HttpVerb.Del, false, url, header, null);
	}
	
	public static URLConnection getHttpRequest(String verb, Boolean https, String url, Map<String, String> headers) throws Exception
	{
		System.out.println("\nSending 'POST' request to URL : " + url);
		URL obj = new URL(url);
		HttpURLConnection con;
		if(https)
		{
			con = (HttpsURLConnection)obj.openConnection();
		}
		else
		{
			con = (HttpURLConnection)obj.openConnection();
		}
		con.setRequestMethod(verb);

		//Add request header
		for(Map.Entry<String, String> entry: headers.entrySet())
		{
			con.setRequestProperty(entry.getKey(), entry.getValue());
		}
		
		return con;
	}
	
	public static HttpResponse send(URLConnection request, String postContent) throws Exception 
	{
		HttpURLConnection con = (HttpURLConnection)request;
		if(postContent != null)
		{
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			try
			{
				wr.writeBytes(postContent);
				wr.flush();
			}
			finally
			{
				wr.close();
			}
		}

		int responseCode = con.getResponseCode();

		System.out.println("Post parameters : " + postContent);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		try
		{
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			System.out.println("Response Contents : " + response.toString());
			return new HttpResponse(responseCode, response.toString());
		}
		finally
		{
			in.close();
		}
	}
	
	private static HttpResponse send(String verb, Boolean https, String url, Map<String, String> headers, String postContent) throws Exception 
	{
		HttpURLConnection con = (HttpURLConnection)getHttpRequest(verb, https, url, headers);
		return send(con, postContent);
	}
	
	public static PropertyFilter GetPropertyFilter(final String... filters)
	{
		return new PropertyFilter()
		{
		   public boolean apply(Object source, String name, Object value) 
		   {  
		      if(Arrays.asList(filters).contains(name))
		      {  
		         return true;  
		      }  
		      return false;  
		   }
		};
	}
}
