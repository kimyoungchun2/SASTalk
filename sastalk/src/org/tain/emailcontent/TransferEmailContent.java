package org.tain.emailcontent;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferEmailContent {

	private static final boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		if (flag) System.out.println(">>>>> Transfer Email Content from one to other.. <<<<<");
		
		TransferEmailContent instance = new TransferEmailContent();
		
		instance.setSourceInfo("jdbc:oracle:thin:@192.168.57.30:1521:xe|TMSAS|TMSAS|select val_clob from cm_test_clob where id='id-1'");
		instance.setTargetInfo("jdbc:oracle:thin:@127.0.0.1:11521:xe|TMSAS|TMSAS|update cm_test_clob set val_clob = ? where id='id-1'");
		
		/*
		 * grade
		 *   1: print Job Info
		 *   2: check connection
		 *   3~: all job
		 */
		int ret = instance.exec(3);
		if (flag) System.out.println(">>>>> result value = " + ret);
	}
	
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	
	private String srcInfo = null;
	private String tgtInfo = null;
	
	private JobInfo jobInfo = null;
	private String driverName = "oracle.jdbc.driver.OracleDriver";
	
	private Connection srcConn = null;
	private PreparedStatement srcPstmt = null;
	private ResultSet srcRs = null;
	private Clob srcClob = null;
	
	private Connection tgtConn = null;
	private PreparedStatement tgtPstmt = null;
	private ResultSet tgtRs = null;
	private Clob tgtClob = null;

	private int lenClob = 0;
	private String strClob = null;

	public void setSourceInfo(String srcInfo) throws Exception {
		this.srcInfo = srcInfo;
	}
	
	public void setTargetInfo(String tgtInfo) throws Exception {
		this.tgtInfo = tgtInfo;
	}
	
	////////////////////////////////////////////////////////////////////////
	// STEP 1
	public int exec(int grade) throws Exception {
		if (flag) System.out.println("[STEP 1] Starting Job..");

		int ret = 0;
		
		this.jobInfo = new JobInfo(srcInfo + "|" + tgtInfo);
		if (grade == 1)
			return ret;
		
		try {
			sourceConnection();   // [STEP 3]
			targetConnection();   // [STEP 4]
			
			if (2 < grade) {
				getClobFromSource();  // [STEP 5]
				setClobToTarget();    // [STEP 6]
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			sourceClose();  // STEP 7
			targetClose();  // STEP 8
		}
		
		return ret;
	}
	
	////////////////////////////////////////////////////////////////////////
	// STEP 3
	private void sourceConnection() throws Exception {
		if (flag) System.out.println("[STEP 3] connect to Source TNS .....[START]");

		// 1. JDBC 드라이버 로딩 
		Class.forName(this.driverName);
		// 2. Connection 생성 
		this.srcConn = DriverManager.getConnection(this.jobInfo.getSrcUrl(), this.jobInfo.getSrcUser(), this.jobInfo.getSrcPass());
		this.srcConn.setAutoCommit (false);
		
		//데이터베이스 연결 
		if (flag) System.out.println("[STEP 3] connect to Source TNS .....[END]");
	}
	
	////////////////////////////////////////////////////////////////////////
	// STEP 4
	private void targetConnection() throws Exception {
		if (flag) System.out.println("[STEP 4] connect to Target TNS .....[START]");
		
		// 1. JDBC 드라이버 로딩 
		Class.forName(this.driverName);
		// 2. Connection 생성 
		this.tgtConn = DriverManager.getConnection(this.jobInfo.getTgtUrl(), this.jobInfo.getTgtUser(), this.jobInfo.getTgtPass());
		this.tgtConn.setAutoCommit (false);
		
		//데이터베이스 연결 
		if (flag) System.out.println("[STEP 4] connect to Target TNS .....[END]");
	}
	
	////////////////////////////////////////////////////////////////////////
	// STEP 5
	private void getClobFromSource() throws Exception {
		if (flag) System.out.println("[STEP 5] get CLOB data from Source.....[START]");
		
		// 3. Statement
		this.srcPstmt = this.srcConn.prepareStatement(this.jobInfo.getSrcQuery());
		
		// 4. execute query
		this.srcRs = this.srcPstmt.executeQuery();
		int cnt = 0;
		while (this.srcRs.next()) {
			this.srcClob = this.srcRs.getClob(1);
			
			this.lenClob = (int) this.srcClob.length();
			this.strClob = this.srcClob.getSubString(1, this.lenClob);
			cnt++;
			break;
		}
		
		if (flag) System.out.printf("[STEP 5] get CLOB data from Source.....[END] -> clob size = %d, cnt = %d\n", this.lenClob, cnt);
		if (cnt == 0)
			throw new SQLException("There is no rows on select resultset!!");
	}
	
	////////////////////////////////////////////////////////////////////////
	// STEP 6
	private void setClobToTarget() throws Exception {
		if (flag) System.out.println("[STEP 6] set CLOB data to Target.....[START]");
		
		// 3. PrepatedStatement
		this.tgtPstmt = this.tgtConn.prepareStatement(this.jobInfo.getTgtQuery());
		this.tgtClob = this.tgtConn.createClob();
		int nwritten = this.tgtClob.setString(1, this.strClob);
		this.tgtPstmt.setClob(1, this.tgtClob);
		
		// 4. execute update
		int result = this.tgtPstmt.executeUpdate();
		
		if (flag) System.out.printf("[STEP 6] set CLOB data to Target.....[END] -> nwritten=%d, result=%d\n", nwritten, result);
		if (result != 1)
			throw new SQLException("There is no row for update!!");
	}
	
	////////////////////////////////////////////////////////////////////////
	// STEP 7
	private void sourceClose() throws Exception {
		if (flag) System.out.println("[STEP 7] close Source Info .....");

		if (this.srcClob != null)
			this.srcClob.free();
		
		if (this.srcRs != null) {
			try { this.srcRs.close(); } catch (Exception e) {}
		}
		if (this.srcPstmt != null) {
			try { this.srcPstmt.close(); } catch (Exception e) {}
		}
		if (this.srcConn != null) { 
			try { this.srcConn.close(); } catch (Exception e) {}
		} 
	}
	
	////////////////////////////////////////////////////////////////////////
	// STEP 8
	private void targetClose() throws Exception {
		if (flag) System.out.println("[STEP 8] close Target Info .....");
		
		if (this.tgtClob != null)
			this.tgtClob.free();
		
		if (this.tgtRs != null) {
			try { this.tgtRs.close(); } catch (Exception e) {}
		}
		if (this.tgtPstmt != null) {
			try { this.tgtPstmt.close(); } catch (Exception e) {}
		}
		if (this.tgtConn != null) { 
			try { this.tgtConn.close(); } catch (Exception e) {}
		} 
	}
	
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	// STEP 2
	private static class JobInfo {
		private String srcUrl;
		private String srcUser;
		private String srcPass;
		private String srcQuery;
		private String tgtUrl;
		private String tgtUser;
		private String tgtPass;
		private String tgtQuery;
		public String getSrcUrl()   { return srcUrl;   }
		public String getSrcUser()  { return srcUser;  }
		public String getSrcPass()  { return srcPass;  }
		public String getSrcQuery() { return srcQuery; }
		public String getTgtUrl()   { return tgtUrl;   }
		public String getTgtUser()  { return tgtUser;  }
		public String getTgtPass()  { return tgtPass;  }
		public String getTgtQuery() { return tgtQuery; }
		private StringBuffer sb;
		public JobInfo(String strJobInfo) {
			if (flag) System.out.println("[STEP 2] Parsing JobInfo start.....");
			
			String[] arrJobInfo = strJobInfo.split("\\|");
			if (!flag) {
				for (int i=0; i < arrJobInfo.length; i++)
					System.out.printf("%d) %s\n", i, arrJobInfo[i]);
			}
			this.srcUrl   = arrJobInfo[0];
			this.srcUser  = arrJobInfo[1];
			this.srcPass  = arrJobInfo[2];
			this.srcQuery = arrJobInfo[3];

			this.tgtUrl   = arrJobInfo[4];
			this.tgtUser  = arrJobInfo[5];
			this.tgtPass  = arrJobInfo[6];
			this.tgtQuery = arrJobInfo[7];
			
			this.sb = new StringBuffer();
			this.sb.append("--------------- Source Infomation ------------").append('\n');
			this.sb.append("\t src.url   = " + this.getSrcUrl()).append('\n');
			this.sb.append("\t src.user  = " + this.getSrcUser()).append('\n');
			this.sb.append("\t src.pass  = " + this.getSrcPass()).append('\n');
			this.sb.append("\t src.query = " + this.getSrcQuery()).append('\n');
			this.sb.append("--------------- Target Infomation ------------").append('\n');
			this.sb.append("\t tgt.url   = " + this.getTgtUrl()).append('\n');
			this.sb.append("\t tgt.user  = " + this.getTgtUser()).append('\n');
			this.sb.append("\t tgt.pass  = " + this.getTgtPass()).append('\n');
			this.sb.append("\t tgt.query = " + this.getTgtQuery()).append('\n');
			if (flag) System.out.println(this.sb.toString());
		}
		
		public String toString() {
			return this.sb.toString();
		}
	}
}
