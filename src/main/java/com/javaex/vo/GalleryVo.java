package com.javaex.vo;

public class GalleryVo {
	private int no;
	private int user_no;
	private String content;
	private String filepath;
	private String orgname;
	private String savename;
	private String filesize;
	public GalleryVo() {
		super();
	}
	public GalleryVo(int no, int user_no, String content, String filepath, String orgname, String savename,
			String filesize) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.content = content;
		this.filepath = filepath;
		this.orgname = orgname;
		this.savename = savename;
		this.filesize = filesize;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", user_no=" + user_no + ", content=" + content + ", filepath=" + filepath
				+ ", orgname=" + orgname + ", savename=" + savename + ", filesize=" + filesize + "]";
	}
	
	
}
