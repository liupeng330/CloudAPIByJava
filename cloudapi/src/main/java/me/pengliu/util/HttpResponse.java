package me.pengliu.util;

public class HttpResponse
{
	private int responseCode;
	private String responseString;
	
	public HttpResponse(int code, String response)
	{
		this.responseCode = code;
		this.responseString = response;
	}
	
	public String getResponseString()
	{
		return responseString;
	}
	
	public void setResponseString(String responseString)
	{
		this.responseString = responseString;
	}
	
	public int getResponseCode()
	{
		return responseCode;
	}
	
	public void setResponseCode(int responseCode)
	{
		this.responseCode = responseCode;
	}
}
