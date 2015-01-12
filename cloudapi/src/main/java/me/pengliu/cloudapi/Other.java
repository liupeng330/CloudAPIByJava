package me.pengliu.cloudapi;

import java.io.Serializable;

public class Other implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String otherName;
	private Integer otherIds;
	public Other()
	{
		// TODO Auto-generated constructor stub
	}
	public String getOtherName()
	{
		return otherName;
	}
	public void setOtherName(String otherName)
	{
		this.otherName = otherName;
	}
	public Integer getOtherIds()
	{
		return otherIds;
	}
	public void setOtherIds(Integer otherIds)
	{
		this.otherIds = otherIds;
	}
	
	public String toString()
	{
		return otherName + "#" + otherIds;
	}
}