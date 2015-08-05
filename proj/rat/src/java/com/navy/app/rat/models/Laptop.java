/*
 * @(#)Laptop.java  2007-1-14
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.models;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.navy.framework.models.BaseObject;
import com.navy.framework.util.Utilities;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 * @hibernate.class table="rat_laptop"
 * @struts.form include-all="true" extends="com.navy.app.rat.web.action.ProductForm"
 */
public class Laptop extends BaseObject {

	private Long id;

	private String series;

	private String cpu;

	private String ram;

	private String displayCard;

	private String displayRam;

	private String monitor;

	private String hardDisk;

	private String os;

	private String software;

	private String power;

	private String battery;

	private Product product;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Laptop))
			return false;

		final Laptop laptop = (Laptop) o;
		if (!Utilities.objectEqulas(id, laptop.getId())) {
			return false;
		}
		if (!Utilities.objectEqulas(product, laptop.getProduct())) {
			return false;
		}
		if (!Utilities.objectEqulas(series, laptop.getSeries())) {
			return false;
		}

		return true;

	}

	/**
	 * @hibernate.property length="255"
	 * @return the battery
	 */
	public String getBattery() {
		return this.battery;
	}

	/**
	 * @hibernate.property length="255"
	 * @return the cpu
	 */
	public String getCpu() {
		return this.cpu;
	}

	/**
	 * @hibernate.property length="255"
	 * @return the displayCard
	 */
	public String getDisplayCard() {
		return this.displayCard;
	}

	/**
	 * @hibernate.property length="128"
	 * @return the displayRam
	 */
	public String getDisplayRam() {
		return this.displayRam;
	}

	/**
	 * @hibernate.property length="128"
	 * @return the hardDisk
	 */
	public String getHardDisk() {
		return this.hardDisk;
	}

	/**
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @hibernate.property length="128"
	 * @return the monitor
	 */
	public String getMonitor() {
		return this.monitor;
	}

	/**
	 * @hibernate.property length="64"
	 * @return the os
	 */
	public String getOs() {
		return this.os;
	}

	/**
	 * @hibernate.property length="64"
	 * @return the power
	 */
	public String getPower() {
		return this.power;
	}

	/**
	 * @hibernate.many-to-one cascade="all"
	 * @return the product
	 */
	public Product getProduct() {
		return this.product;
	}

	/**
	 * @hibernate.property length="64"
	 * @return the ram
	 */
	public String getRam() {
		return this.ram;
	}

	/**
	 * @hibernate.property length="255" not-null="true"
	 *                     index="laptopSeriesIndex"
	 * @return the series
	 */
	public String getSeries() {
		return this.series;
	}

	/**
	 * @hibernate.property length="255"
	 * @return the software
	 */
	public String getSoftware() {
		return this.software;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#hashCode()
	 */
	public int hashCode() {
		return Utilities.buildHashCode(new Object[] { id, product, series });
	}

	/**
	 * @param battery
	 *            the battery to set
	 */
	public void setBattery(String battery) {
		this.battery = battery;
	}

	/**
	 * @param cpu
	 *            the cpu to set
	 */
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	/**
	 * @param displayCard
	 *            the displayCard to set
	 */
	public void setDisplayCard(String displayCard) {
		this.displayCard = displayCard;
	}

	/**
	 * @param displayRam
	 *            the displayRam to set
	 */
	public void setDisplayRam(String displayRam) {
		this.displayRam = displayRam;
	}

	/**
	 * @param hardDisk
	 *            the hardDisk to set
	 */
	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param monitor
	 *            the monitor to set
	 */
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @param power
	 *            the power to set
	 */
	public void setPower(String power) {
		this.power = power;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @param ram
	 *            the ram to set
	 */
	public void setRam(String ram) {
		this.ram = ram;
	}

	/**
	 * @param series
	 *            the series to set
	 */
	public void setSeries(String series) {
		this.series = series;
	}

	/**
	 * @param software
	 *            the software to set
	 */
	public void setSoftware(String software) {
		this.software = software;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#toString()
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("product", this.product)
				.append("series", this.series).append("battery", this.battery)
				.append("cpu", this.cpu)
				.append("displayCard", this.displayCard).append("displayRam",
						this.displayRam).append("hardDisk", this.hardDisk)
				.append("monitor", this.monitor).append("os", this.os).append(
						"software", this.software).append("power", this.power);

		return sb.toString();
	}

}
