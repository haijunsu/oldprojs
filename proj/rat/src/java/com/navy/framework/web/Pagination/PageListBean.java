/*
 * @(#)PageListBean.java  Sep 30, 2005
 * Copyright (c) TrueTel Communications 2005. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.web.Pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class PageListBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 99567081294074194L;

	private int m_rowsPerPage = 10;

	private int m_totalRecords;

	private int m_currentPage = 1;

	private Collection m_records = new ArrayList();

	public int getRowsPerPage() {
		return m_rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		m_rowsPerPage = rowsPerPage;
	}

	public int getCurrentPage() {
		if (m_currentPage > getTotalPages()) {
			return getTotalPages();
		}
		return (m_currentPage <= 0) ? 1 : m_currentPage;
	}

	public void setCurrentPage(int currentPage) {
		m_currentPage = currentPage;
	}

	public Collection getRecords() {
		return m_records;
	}

	public void setRecords(Collection records) {
		m_records = records;
	}

	public int getTotalRecords() {
		return m_totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		m_totalRecords = totalRecords;
	}

	public int getStartIndex() {
		if (getCurrentPage() < 1) {
			return 0;
		}
		return (getCurrentPage() - 1) * m_rowsPerPage;
	}

	public int getTotalPages() {
		if (m_totalRecords < m_rowsPerPage) {
			return 1;
		}
		return (m_totalRecords % m_rowsPerPage == 0) ? m_totalRecords
				/ m_rowsPerPage : (m_totalRecords / m_rowsPerPage + 1);
	}

	public boolean getShowPreButton() {
		return (getCurrentPage() > 1);
	}

	public boolean getShowNextButton() {
		return (getCurrentPage() < getTotalPages());
	}
}
