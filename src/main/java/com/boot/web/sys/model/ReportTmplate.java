package com.boot.web.sys.model;

import java.util.List;
import java.util.Map;

import com.boot.util.BaseEntity;


public class ReportTmplate extends BaseEntity<SysUser> {

    private static final long serialVersionUID = 1L;
    List<String> lst_today;


	List<String>  lst_tomorrow;
    List<String> lst_bydate;
	List<String>  lst_summary;
    String [] todaywork;
	String [] tmorrowwork;
    String [] summary;
    String [] bydate;
    String report_date;
    String next_date;
    String add_date;
    public String getReport_date() {
		return report_date;
	}

	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}

	public String getNext_date() {
		return next_date;
	}

	public void setNext_date(String next_date) {
		this.next_date = next_date;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}

	String formaterr;

    public String[] getBydate() {
		return bydate;
	}

	public void setBydate(String[] bydate) {
		this.bydate = bydate;
	}
	public List<String> getLst_bydate() {
		return lst_bydate;
	}

	public void setLst_bydate(List<String> lst_bydate) {
		this.lst_bydate = lst_bydate;
	}
    
    

	public String getStrSummary() {
		return strSummary;
	}

	public void setStrSummary(String strSummary) {
		this.strSummary = strSummary;
	}

	String strSummary;
    public List<String> getLst_today() {
		return lst_today;
	}

	public void setLst_today(List<String> lst_today) {
		this.lst_today = lst_today;
	}

	public List<String> getLst_tomorrow() {
		return lst_tomorrow;
	}

	public void setLst_tomorrow(List<String> lst_tomorrow) {
		this.lst_tomorrow = lst_tomorrow;
	}

	public List<String> getLst_summary() {
		return lst_summary;
	}

	public void setLst_summary(List<String> lst_summary) {
		this.lst_summary = lst_summary;
	}


    
    public String getFormaterr() {
		return formaterr;
	}

	public void setFormaterr(String formaterr) {
		this.formaterr = formaterr;
	}

	public String[] getTodaywork() {
		return todaywork;
	}

	public void setTodaywork(String[] todaywork) {
		this.todaywork = todaywork;
	}

	public String[] getTmorrowwork() {
		return tmorrowwork;
	}

	public void setTmorrowwork(String[] tmorrowwork) {
		this.tmorrowwork = tmorrowwork;
	}

	public String[] getSummary() {
		return summary;
	}

	public void setSummary(String[] summary) {
		this.summary = summary;
	}


    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}