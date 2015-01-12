package me.pengliu.cloudapi.models;

import java.io.Serializable;

public class MediaInfoBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long duration;
	private String title;
	private String type;
	private String status;
	private int	width;
	private int height;
	private int bitrate;
	private String id;
	private String file_name;
	private long file_size;
	private String media_type;
	private String audio_format;
	private String video_format;
	private String file_format;
	private String user_id;
	private Boolean media_owner;
	private String shared;
	private MediaInfoShareBean[] shares;
	
	public long getDuration()
	{
		return duration;
	}
	public void setDuration(long duration)
	{
		this.duration = duration;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public int getBitrate()
	{
		return bitrate;
	}
	public void setBitrate(int bitrate)
	{
		this.bitrate = bitrate;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getFile_name()
	{
		return file_name;
	}
	public void setFile_name(String file_name)
	{
		this.file_name = file_name;
	}
	public long getFile_size()
	{
		return file_size;
	}
	public void setFile_size(long file_size)
	{
		this.file_size = file_size;
	}
	public String getMedia_type()
	{
		return media_type;
	}
	public void setMedia_type(String media_type)
	{
		this.media_type = media_type;
	}
	public String getAudio_format()
	{
		return audio_format;
	}
	public void setAudio_format(String audio_format)
	{
		this.audio_format = audio_format;
	}
	public String getVideo_format()
	{
		return video_format;
	}
	public void setVideo_format(String video_format)
	{
		this.video_format = video_format;
	}
	public String getFile_format()
	{
		return file_format;
	}
	public void setFile_format(String file_format)
	{
		this.file_format = file_format;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public Boolean getMedia_owner()
	{
		return media_owner;
	}
	public void setMedia_owner(Boolean media_owner)
	{
		this.media_owner = media_owner;
	}
	public String getShared()
	{
		return shared;
	}
	public void setShared(String shared)
	{
		this.shared = shared;
	}
	public MediaInfoShareBean[] getShares()
	{
		return shares;
	}
	public void setShares(MediaInfoShareBean[] shares)
	{
		this.shares = shares;
	}
	
	/*	
	create_date: 1394416171972
	add_date: 1394416171972
	mod_date: 1394416184016
	access_date: 1394416171972
	mime_type: "video/x-flv"
	image_url: "256/i.jpg"
	image_base_path: http://rpcloudmedia-int2.s3.amazonaws.com/5bebd750954d11e3a0430294ca82c76d/397773d0a7f611e392240294ca82c76d/images/
	-image_files: [
	"256/i.jpg"
	"src/i.jpg"
	]
	-image_overlay_files: [
	"380/i.jpg"
	]
	collection_ids: [ ]
	shares: [
		-{
		status: "active"
		-recipients: [
		-{
		type: "email"
		email: "tcoleman@real.com"
		id: "tcoleman@real.com"
		status: "active"
		username: "tcoleman@real.com"
		add_date: 1394183604123
		rpc_id: "336c60e84ec748d3a5143f7a849b7b2d"
		}
		-{
		type: "email"
		email: "jdsmith@realnetworks.com"
		id: "jdsmith@realnetworks.com"
		status: "active"
		username: "jdsmith@realnetworks.com"
		add_date: 1394187663143
		}
		-{
		type: "email"
		email: "jdsmith@real.com"
		id: "jdsmith@real.com"
		status: "active"
		username: "jdsmith@real.com"
		add_date: 1394187663143
		rpc_id: "4e6b68fb19f140f483805e36d82c262b"
		}
		]
		share_type: "by_me"
		add_date: 1394183604123
		mod_date: 1394187663143
		}
		]
	shared: "by_me"
	 */
}
