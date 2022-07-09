package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class SupplementServiceDTO implements Serializable {

	private static final long serialVersionUID = 1154544386816500121L;
	private int serviceReservationNo;
	private String gashoodCleanYn;
	private String moldCleanYn;
	private String filterCleanYn;
	private String warehouseCleanYn;
	private String petYn;
	private String multipleLayerYn;
	public SupplementServiceDTO() {
	}
	public SupplementServiceDTO(int serviceReservationNo, String gashoodCleanYn, String moldCleanYn,
			String filterCleanYn, String warehouseCleanYn, String petYn, String multipleLayerYn) {
		this.serviceReservationNo = serviceReservationNo;
		this.gashoodCleanYn = gashoodCleanYn;
		this.moldCleanYn = moldCleanYn;
		this.filterCleanYn = filterCleanYn;
		this.warehouseCleanYn = warehouseCleanYn;
		this.petYn = petYn;
		this.multipleLayerYn = multipleLayerYn;
	}
	int getServiceReservationNo() {
		return serviceReservationNo;
	}
	void setServiceReservationNo(int serviceReservationNo) {
		this.serviceReservationNo = serviceReservationNo;
	}
	String getGashoodCleanYn() {
		return gashoodCleanYn;
	}
	void setGashoodCleanYn(String gashoodCleanYn) {
		this.gashoodCleanYn = gashoodCleanYn;
	}
	String getMoldCleanYn() {
		return moldCleanYn;
	}
	void setMoldCleanYn(String moldCleanYn) {
		this.moldCleanYn = moldCleanYn;
	}
	String getFilterCleanYn() {
		return filterCleanYn;
	}
	void setFilterCleanYn(String filterCleanYn) {
		this.filterCleanYn = filterCleanYn;
	}
	String getWarehouseCleanYn() {
		return warehouseCleanYn;
	}
	void setWarehouseCleanYn(String warehouseCleanYn) {
		this.warehouseCleanYn = warehouseCleanYn;
	}
	String getPetYn() {
		return petYn;
	}
	void setPetYn(String petYn) {
		this.petYn = petYn;
	}
	String getMultipleLayerYn() {
		return multipleLayerYn;
	}
	void setMultipleLayerYn(String multipleLayerYn) {
		this.multipleLayerYn = multipleLayerYn;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SupplementServiceDTO [serviceReservationNo=" + serviceReservationNo + ", gashoodCleanYn="
				+ gashoodCleanYn + ", moldCleanYn=" + moldCleanYn + ", filterCleanYn=" + filterCleanYn
				+ ", warehouseCleanYn=" + warehouseCleanYn + ", petYn=" + petYn + ", multipleLayerYn=" + multipleLayerYn
				+ "]";
	}
	
	
}
