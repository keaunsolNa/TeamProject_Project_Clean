package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLEMENT_SERVICE")
public class SupplementService implements Serializable{

	private static final long serialVersionUID = -5594428855626340583L;
	
	@Id
	@Column(name="SERVICE_RESERVATION_NO")
	private int serviceReservationNo;
	
	@Column(name="GASHOOD_CLEAN_YN")
	private String gashoodCleanYn;
	
	@Column(name="MOLD_CLEAN_YN")
	private String moldCleanYn;
	
	@Column(name="FILTER_CLEAN_YN")
	private String filterCleanYn;
	
	@Column(name="WAREHOUSE_CLEAN_YN")
	private String warehouseCleanYn;
	
	@Column(name="PET_YN")
	private String petYn;
	
	@Column(name="MULTIPLE_LAYER_YN")
	private String multipleLayerYn;

	public SupplementService() {
	}

	public SupplementService(int serviceReservationNo, String gashoodCleanYn, String moldCleanYn, String filterCleanYn,
			String warehouseCleanYn, String petYn, String multipleLayerYn) {
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
		return "SupplementService [serviceReservationNo=" + serviceReservationNo + ", gashoodCleanYn=" + gashoodCleanYn
				+ ", moldCleanYn=" + moldCleanYn + ", filterCleanYn=" + filterCleanYn + ", warehouseCleanYn="
				+ warehouseCleanYn + ", petYn=" + petYn + ", multipleLayerYn=" + multipleLayerYn + "]";
	}
	
	
	
	
}
