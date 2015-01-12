package me.pengliu.cloudapi.models;

import java.io.Serializable;

public class MediaInfoShareBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String status;
	private String share_type;
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getShare_type()
	{
		return share_type;
	}
	public void setShare_type(String share_type)
	{
		this.share_type = share_type;
	}
	
}
