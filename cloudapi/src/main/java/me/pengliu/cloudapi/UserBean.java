package me.pengliu.cloudapi;

import java.io.Serializable;

public class UserBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer id;
	private Other[] others;
	
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Other[] getOthers()
	{
		return others;
	}
	public void setOthers(Other[] others)
	{
		this.others = others;
	}
}
