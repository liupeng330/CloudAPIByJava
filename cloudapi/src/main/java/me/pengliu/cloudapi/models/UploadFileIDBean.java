package me.pengliu.cloudapi.models;

import java.io.Serializable;

public class UploadFileIDBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int offset;
	private String upload_id;
	
	public int getOffset()
	{
		return offset;
	}
	
	public void setOffset(int offset)
	{
		this.offset = offset;
	}
	
	public String getUpload_id()
	{
		return upload_id;
	}
	
	public void setUpload_id(String upload_id)
	{
		this.upload_id = upload_id;
	}
}
