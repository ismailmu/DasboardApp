package com.btpns.Dashboard.client.model.eod;

import java.math.BigDecimal;

import com.btpns.Dashboard.client.model.IModelClient;

public class EodDailySummary implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer totalWisma;
	private BigDecimal totalSumTrans;
	private Integer totalCountFile;
	
	public EodDailySummary(){}

	public EodDailySummary(Integer id, Integer totalWisma,
			BigDecimal totalSumTrans, Integer totalCountFile) {
		super();
		this.id = id;
		this.totalWisma = totalWisma;
		this.totalSumTrans = totalSumTrans;
		this.totalCountFile = totalCountFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalWisma() {
		return totalWisma;
	}

	public void setTotalWisma(Integer totalWisma) {
		this.totalWisma = totalWisma;
	}

	public BigDecimal getTotalSumTrans() {
		return totalSumTrans;
	}

	public void setTotalSumTrans(BigDecimal totalSumTrans) {
		this.totalSumTrans = totalSumTrans;
	}

	public Integer getTotalCountFile() {
		return totalCountFile;
	}

	public void setTotalCountFile(Integer totalCountFile) {
		this.totalCountFile = totalCountFile;
	}
}
