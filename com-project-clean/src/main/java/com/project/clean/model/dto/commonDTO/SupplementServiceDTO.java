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
		super();
		this.serviceReservationNo = serviceReservationNo;
		this.gashoodCleanYn = gashoodCleanYn;
		this.moldCleanYn = moldCleanYn;
		this.filterCleanYn = filterCleanYn;
		this.warehouseCleanYn = warehouseCleanYn;
		this.petYn = petYn;
		this.multipleLayerYn = multipleLayerYn;
	}
	public int getServiceReservationNo() {
		return serviceReservationNo;
	}
	public void setServiceReservationNo(int serviceReservationNo) {
		this.serviceReservationNo = serviceReservationNo;
	}
	public String getGashoodCleanYn() {
		return gashoodCleanYn;
	}
	public void setGashoodCleanYn(String gashoodCleanYn) {
		this.gashoodCleanYn = gashoodCleanYn;
	}
	public String getMoldCleanYn() {
		return moldCleanYn;
	}
	public void setMoldCleanYn(String moldCleanYn) {
		this.moldCleanYn = moldCleanYn;
	}
	public String getFilterCleanYn() {
		return filterCleanYn;
	}
	public void setFilterCleanYn(String filterCleanYn) {
		this.filterCleanYn = filterCleanYn;
	}
	public String getWarehouseCleanYn() {
		return warehouseCleanYn;
	}
	public void setWarehouseCleanYn(String warehouseCleanYn) {
		this.warehouseCleanYn = warehouseCleanYn;
	}
	public String getPetYn() {
		return petYn;
	}
	public void setPetYn(String petYn) {
		this.petYn = petYn;
	}
	public String getMultipleLayerYn() {
		return multipleLayerYn;
	}
	public void setMultipleLayerYn(String multipleLayerYn) {
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
