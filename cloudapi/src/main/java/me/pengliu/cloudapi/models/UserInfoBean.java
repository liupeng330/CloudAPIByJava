package me.pengliu.cloudapi.models;

import java.io.Serializable;
import java.util.*;

public class UserInfoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	public UserInfoBean(){}
	
    private String id;

    private String status;

    private String username;

    private String first_name;

    private String last_name;

    private String email;

	private long allocated_storage;

    private long used_storage;

    private Boolean rpplus_benefit;

    private String user_token;

    private String user_token_expiry;

    private List<Integer> service_ids;

    private String user_type;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getFirst_name()
	{
		return first_name;
	}

	public void setFirst_name(String first_name)
	{
		this.first_name = first_name;
	}

	public String getLast_name()
	{
		return last_name;
	}

	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public long getAllocated_storage()
	{
		return allocated_storage;
	}

	public void setAllocated_storage(long allocated_storage)
	{
		this.allocated_storage = allocated_storage;
	}

	public long getUsed_storage()
	{
		return used_storage;
	}

	public void setUsed_storage(long used_storage)
	{
		this.used_storage = used_storage;
	}

	public Boolean getRpplus_benefit()
	{
		return rpplus_benefit;
	}

	public void setRpplus_benefit(Boolean rpplus_benefit)
	{
		this.rpplus_benefit = rpplus_benefit;
	}

	public String getUser_token()
	{
		return user_token;
	}

	public void setUser_token(String user_token)
	{
		this.user_token = user_token;
	}

	public String getUser_token_expiry()
	{
		return user_token_expiry;
	}

	public void setUser_token_expiry(String user_token_expiry)
	{
		this.user_token_expiry = user_token_expiry;
	}

	public List<Integer> getService_ids()
	{
		return service_ids;
	}

	public void setService_ids(List<Integer> service_ids)
	{
		this.service_ids = service_ids;
	}

	public String getUser_type()
	{
		return user_type;
	}

	public void setUser_type(String user_type)
	{
		this.user_type = user_type;
	}
}
