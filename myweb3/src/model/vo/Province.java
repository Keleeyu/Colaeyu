package model.vo;

public class Province {
	private int provinceCode;
	private String provinceName;
	/**
	 * @return the provinceCode
	 */
	public int getProvinceCode() {
		return provinceCode;
	}
	/**
	 * @param provinceCode the provinceCode to set
	 */
	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}
	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Province(int provinceCode, String provinceName) {
		super();
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
	}
	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
