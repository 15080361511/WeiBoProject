package com.qzz.weibo.entity;
/**
 * ˽�ŵ�ʵ����
 * @author Administrator
 *
 */
public class W_message {
	private int MESSAGEID;
	private String SENDNAME;
	private String RECEIVENAME;
	private String CONTENT;
	private String SENDTIME;
	private String STATE;
	private String TOUXIANG;
	
	public String getTOUXIANG() {
		return TOUXIANG;
	}
	public void setTOUXIANG(String tOUXIANG) {
		TOUXIANG = tOUXIANG;
	}
	public int getMESSAGEID() {
		return MESSAGEID;
	}
	public void setMESSAGEID(int mESSAGEID) {
		MESSAGEID = mESSAGEID;
	}
	public String getSENDNAME() {
		return SENDNAME;
	}
	public void setSENDNAME(String sENDNAME) {
		SENDNAME = sENDNAME;
	}
	public String getRECEIVENAME() {
		return RECEIVENAME;
	}
	public void setRECEIVENAME(String rECEIVENAME) {
		RECEIVENAME = rECEIVENAME;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getSENDTIME() {
		return SENDTIME;
	}
	public void setSENDTIME(String sENDTIME) {
		SENDTIME = sENDTIME;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	
	
	@Override
	public String toString() {
		return "W_message [MESSAGEID=" + MESSAGEID + ", SENDNAME=" + SENDNAME + ", RECEIVENAME=" + RECEIVENAME
				+ ", CONTENT=" + CONTENT + ", SENDTIME=" + SENDTIME + ", STATE=" + STATE + ", TOUXIANG=" + TOUXIANG
				+ "]";
	}
	public W_message(int mESSAGEID, String sENDNAME, String rECEIVENAME, String cONTENT, String sENDTIME, String sTATE,
			String tOUXIANG) {
		super();
		MESSAGEID = mESSAGEID;
		SENDNAME = sENDNAME;
		RECEIVENAME = rECEIVENAME;
		CONTENT = cONTENT;
		SENDTIME = sENDTIME;
		STATE = sTATE;
		TOUXIANG = tOUXIANG;
	}
	public W_message() {
		// TODO Auto-generated constructor stub
	}
}
