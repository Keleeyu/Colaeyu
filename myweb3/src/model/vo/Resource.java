package model.vo;

public class Resource {

	private int resourceId;
	private String resourceName;
	private String url;
	
	public Resource(int resourceId, String resourceName, String url) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.url = url;
	}
	/**
	 * @return the resourceId
	 */
	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
