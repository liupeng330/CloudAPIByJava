package me.pengliu.cloudapi.models;

import java.io.Serializable;

public class MediaListBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int total;
	private int start;
	private int count;
	private MediaInfoBean[] results;
	
	public int getTotal()
	{
		return total;
	}
	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public int getStart()
	{
		return start;
	}
	
	public void setStart(int start)
	{
		this.start = start;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public MediaInfoBean[] getResults()
	{
		return results;
	}
	
	public void setResults(MediaInfoBean[] results)
	{
		this.results = results;
	}
}
