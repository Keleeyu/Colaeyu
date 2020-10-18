package model.vo;

public class City {
	
	private int cityCode;
	private String cityName;
	/**
	 * @return the cityCode
	 */
	public int getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public City(int cityCode, String cityName) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
