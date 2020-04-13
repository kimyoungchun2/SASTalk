package org.tain.emailcontent;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sastalk2.SendtalkMsgMain;

public class EmailContentMain {

	private static final boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Hello, world!!!");
		
		if (!flag) {
			if (flag) test01();  // connection test
			if (flag) test02();  // select all
			
			if (flag) test04();  // insert
			if (flag) test03();  // condition select

			if (flag) test05();  // update
			if (flag) test03();  // condition select
			
			if (flag) test06();  // delete
			if (flag) test03();  // condition select
		}
		
		if (!flag) {
			if (flag) test11();  // create table with clob field
			if (flag) test12();  // update table with clob field
			if (flag) test13();  // select table with clob field
		}
		
		if (flag) {
			String srcInfo = String.format("jdbc:oracle:thin:@192.168.57.30:1521:xe|TMSAS|TMSAS|select val_clob from cm_test_clob where id='%s'", "id-1");
			String tgtInfo = String.format("jdbc:oracle:thin:@localhost:11521:xe|TMSAS|TMSAS|update cm_test_clob set val_clob = ? where id='%s'", "id-1");
			String strJobInfo = String.format("%s|%s", srcInfo, tgtInfo);
			
			if (flag) test21(strJobInfo);

			/*
			// 전송SendtalkMsgMainalkMsg sendtalkMsg SendtalkMsgMainalkMsg();
			SendtalkMsgMain instance = new SendtalkMsgMain();
			
			//테스트 데이터 셋팅  , 운영모드 일 경우 Remarking 
			instance.setAndroid_messageData("안드로이드 메시지~~개인화테스트입니다~44번째(포맷변경)");
			instance.setIos_messageData	("아이폰 메시지~개인화테스트입니다~44번째(포맷유지)");
			instance.setTalkMsgTmpltNo	 ("001");
			instance.setMemno  ("18468196"); //42751905 18468196 20750578
			
			int ret = instance.exec();
			System.out.println(">>>>> ret = " + ret);
			*/
		}
	}
	
	// connection test
	private static void test01() throws Exception {
		if (flag) System.out.println(">>>>> test01. connection <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit (false);
			
			//데이터베이스 연결 
			if (flag) System.out.println("[Database 연결 성공]"); 
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {} 
			} 
		}
	}
	
	// select all
	private static void test02() throws Exception {
		if (flag) System.out.println(">>>>> test02. select all <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select COMM_CODE_ID, COMM_CODE_NAME from CM_CODE_MST";
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			conn.setAutoCommit (false);
			
			// 3. Statement
			stmt = conn.createStatement();
			
			// 4. execute query
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("COMM_CODE_ID");
				String name = rs.getString("COMM_CODE_NAME");
				if (flag) System.out.printf("%s\t%s\n", id, name);
			}
			
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (rs != null) {
				try { rs.close(); } catch (Exception e) {}
			}
			if (stmt != null) {
				try { stmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}
	
	// condition select
	private static void test03() throws Exception {
		if (flag) System.out.println(">>>>> test03. select where <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select COMM_CODE_ID, COMM_CODE_NAME, CODE_DESC from CM_CODE_MST where COMM_CODE_ID = ?";
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			conn.setAutoCommit (false);
			
			// 3. Statement
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "KS");
			
			// 4. execute query
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("COMM_CODE_ID");
				String name = rs.getString("COMM_CODE_NAME");
				String desc = rs.getString("CODE_DESC");
				if (flag) System.out.printf("%s\t%s\t%s\n", id, name, desc);
			}
			
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (rs != null) {
				try { rs.close(); } catch (Exception e) {}
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}

	// insert test
	private static void test04() throws Exception {
		if (flag) System.out.println(">>>>> test04. insert <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into CM_CODE_MST ("
				+ " COMM_CODE_ID"
				+ ", COMM_CODE_NAME"
				+ ", CODE_DESC"
				+ ", SORT_SEQ"
				+ ", USE_YN"
				+ ", CREATE_ID"
				+ ", CREATE_DT"
				+ ") values (?,?,?,?,?,?,SYSDATE)";
		@SuppressWarnings("unused")
		int result = 0;
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			conn.setAutoCommit (false);
			
			// 3. PrepatedStatement
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "KS");
			pstmt.setString(2, "Kang Seok");
			pstmt.setString(3, "Hello Kang Seok");
			pstmt.setInt(4, 0);
			pstmt.setString(5, "N");
			pstmt.setInt(6, 12345);

			// 4. execute update
			result = pstmt.executeUpdate();
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (pstmt != null) {
				try { pstmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}
	
	
	// update test
	private static void test05() throws Exception {
		if (flag) System.out.println(">>>>> test05. update <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "update CM_CODE_MST set"
				+ " CODE_DESC = ?"
				+ " where COMM_CODE_ID = ?";
		@SuppressWarnings("unused")
		int result = 0;
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			conn.setAutoCommit (false);
			
			// 3. PrepatedStatement
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "Hello Kang Seok 123");
			pstmt.setString(2, "KS");

			// 4. execute update
			result = pstmt.executeUpdate();
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (pstmt != null) {
				try { pstmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}
	
	// delete test
	private static void test06() throws Exception {
		if (flag) System.out.println(">>>>> test06. delete <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "delete from CM_CODE_MST"
				+ " where COMM_CODE_ID = ?";
		@SuppressWarnings("unused")
		int result = 0;
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			conn.setAutoCommit (false);
			
			// 3. PrepatedStatement
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "KS");

			// 4. execute update
			result = pstmt.executeUpdate();
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (pstmt != null) {
				try { pstmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}
	
	// create, insert and select table with clob field
	private static void test11() throws Exception {
		if (flag) System.out.println(">>>>> test11. create table with clob field <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		//String query = "select COMM_CODE_ID, COMM_CODE_NAME from CM_CODE_MST";

		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			conn.setAutoCommit (false);
			
			// 3. Statement
			stmt = conn.createStatement();
			
			try {
				stmt.execute("drop table CM_TEST_CLOB");
			} catch (SQLException e) {}
			
			stmt.execute("create table CM_TEST_CLOB (ID varchar2(30), VAL_BLOB blob, VAL_CLOB clob)");
			
			stmt.execute("insert into CM_TEST_CLOB values ('id-1', '010101010101010101010101010101', 'onetwothreefour')");
			stmt.execute("insert into CM_TEST_CLOB values ('id-2', '0202020202020202020202020202', 'twothreefourfivesix')");
			conn.commit();
			// conn.rollback();
			
			// Select the lobs   
			rs = stmt.executeQuery ("select * from CM_TEST_CLOB");
			while (rs.next ())
			{
				// Get the lobs
				//Blob valBlob = rs.getBlob(2);
				Clob valClob = rs.getClob(3);
				
				int lenClob = (int) valClob.length();
				String body = valClob.getSubString(1, Math.min(100, lenClob));
				System.out.printf(">>>>> VAL_CLOB (%d): %s\n", lenClob, body);
				valClob.free();
			}

		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (stmt != null) {
				try { stmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}

	// update table with clob field
	private static void test12() throws Exception {
		if (flag) System.out.println(">>>>> test12. update table with clob field <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "update CM_TEST_CLOB set"
				+ " VAL_CLOB = ?"
				+ " where ID = ?";
		@SuppressWarnings("unused")
		int result = 0;
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			//conn.setAutoCommit (false);
			
			// 3. PrepatedStatement
			pstmt = conn.prepareStatement(query);
			Clob clob = conn.createClob();
			int nwritten = clob.setString(1, getClobString());
			pstmt.setClob(1, clob);
			pstmt.setString(2, "id-1");
			if (flag) System.out.println(">>>>> nwritten = " + nwritten);
			
			// 4. execute update
			result = pstmt.executeUpdate();
			
			// free clob
			clob.free();
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (pstmt != null) {
				try { pstmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}
	
	// select table with clob field
	private static void test13() throws Exception {
		if (flag) System.out.println(">>>>> test13. select table with clob field <<<<<");
		
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.57.30:1521:xe";
		String username = "TMSAS";
		String password = "TMSAS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select ID, VAL_CLOB from CM_TEST_CLOB where ID = ?";
		
		try {
			// 1. JDBC 드라이버 로딩 
			Class.forName(driverName);
			// 2. Connection 생성 
			conn = DriverManager.getConnection(url, username, password); 
			conn.setAutoCommit (false);
			
			// 3. Statement
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "id-1");
			
			// 4. execute query
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ID");
				Clob valClob = rs.getClob("VAL_CLOB");
				
				int lenClob = (int) valClob.length();
				//String strClob = valClob.getSubString(1, Math.min(100, lenClob));
				String strClob = valClob.getSubString(1024*1024, lenClob);
				
				if (flag) System.out.printf(">>>>> %s (%d)[%s]\n", id, lenClob, strClob);
				
				valClob.free();
			}
			
		} catch (SQLException e) { 
			if (flag) System.out.println("[SQL Error : " + e.getMessage() +"]"); 
		} catch (ClassNotFoundException e1) { 
			if (flag) System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
		} finally { 
			// 사용 후 Close 
			if (rs != null) {
				try { rs.close(); } catch (Exception e) {}
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (Exception e) {}
			}
			if (conn != null) { 
				try { conn.close(); } catch (Exception e) {}
			} 
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	
	private static String getClobString() throws Exception {
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < 1024; i++) {
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"); // 100 bytes
			sb.append("123456789012345678901234"); // 24 bytes
		}
		sb.append("나는 강석입니다. ^^");
		
		return sb.toString();
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	
	private static JobInfo jobInfo = null;
	private static String driverName = "oracle.jdbc.driver.OracleDriver";
	
	private static Connection srcConn = null;
	private static PreparedStatement srcPstmt = null;
	private static ResultSet srcRs = null;
	private static Clob srcClob = null;
	
	private static Connection tgtConn = null;
	private static PreparedStatement tgtPstmt = null;
	private static ResultSet tgtRs = null;
	private static Clob tgtClob = null;

	private static int lenClob = 0;
	private static String strClob = null;
	
	private static void test21(String strJobInfo) throws Exception {
		if (flag) System.out.println("[STEP 1] Job Start");
		
		jobInfo = getJobInfo(strJobInfo);  // [STEP 2]
		if (flag) System.out.println(jobInfo);

		try {
			sourceConnection();   // [STEP 3]
			targetConnection();   // [STEP 4]
			
			getClobFromSource();  // [STEP 5]
			setClobToTarget();    // [STEP 6]
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sourceClose();
			targetClose();
		}
	}

	private static class JobInfo {
		private String srcUrl;
		private String srcUser;
		private String srcPass;
		private String srcQuery;
		private String tgtUrl;
		private String tgtUser;
		private String tgtPass;
		private String tgtQuery;
		public String getSrcUrl() {
			return srcUrl;
		}
		public void setSrcUrl(String srcUrl) {
			this.srcUrl = srcUrl;
		}
		public String getSrcUser() {
			return srcUser;
		}
		public void setSrcUser(String srcUser) {
			this.srcUser = srcUser;
		}
		public String getSrcPass() {
			return srcPass;
		}
		public void setSrcPass(String srcPass) {
			this.srcPass = srcPass;
		}
		public String getSrcQuery() {
			return srcQuery;
		}
		public void setSrcQuery(String srcQuery) {
			this.srcQuery = srcQuery;
		}
		public String getTgtUrl() {
			return tgtUrl;
		}
		public void setTgtUrl(String tgtUrl) {
			this.tgtUrl = tgtUrl;
		}
		public String getTgtUser() {
			return tgtUser;
		}
		public void setTgtUser(String tgtUser) {
			this.tgtUser = tgtUser;
		}
		public String getTgtPass() {
			return tgtPass;
		}
		public void setTgtPass(String tgtPass) {
			this.tgtPass = tgtPass;
		}
		public String getTgtQuery() {
			return tgtQuery;
		}
		public void setTgtQuery(String tgtQuery) {
			this.tgtQuery = tgtQuery;
		}
		
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("--------------- Source Infomation ------------").append('\n');
			sb.append("\t src.url   = " + this.getSrcUrl()).append('\n');
			sb.append("\t src.user  = " + this.getSrcUser()).append('\n');
			sb.append("\t src.pass  = " + this.getSrcPass()).append('\n');
			sb.append("\t src.query = " + this.getSrcQuery()).append('\n');
			sb.append("--------------- Target Infomation ------------").append('\n');
			sb.append("\t tgt.url   = " + this.getTgtUrl()).append('\n');
			sb.append("\t tgt.user  = " + this.getTgtUser()).append('\n');
			sb.append("\t tgt.pass  = " + this.getTgtPass()).append('\n');
			sb.append("\t tgt.query = " + this.getTgtQuery()).append('\n');
			return sb.toString();
		}
	}
	
	private static JobInfo getJobInfo(String strJobInfo) throws Exception {
		if (flag) System.out.println("[STEP 2] Parsing JobInfo start.....");
		
		String[] arrJobInfo = strJobInfo.split("\\|");
		if (!flag) {
			for (int i=0; i < arrJobInfo.length; i++)
				System.out.printf("%d) %s\n", i, arrJobInfo[i]);
		}
		JobInfo jobInfo = new JobInfo();
		jobInfo.setSrcUrl(arrJobInfo[0]);
		jobInfo.setSrcUser(arrJobInfo[1]);
		jobInfo.setSrcPass(arrJobInfo[2]);
		jobInfo.setSrcQuery(arrJobInfo[3]);
		jobInfo.setTgtUrl(arrJobInfo[4]);
		jobInfo.setTgtUser(arrJobInfo[5]);
		jobInfo.setTgtPass(arrJobInfo[6]);
		jobInfo.setTgtQuery(arrJobInfo[7]);
		if (flag) System.out.println(jobInfo);

		return jobInfo;
	}
	
	private static void sourceConnection() throws Exception {
		if (flag) System.out.println("[STEP 3] connect to Source TNS .....[START]");

		// 1. JDBC 드라이버 로딩 
		Class.forName(driverName);
		// 2. Connection 생성 
		srcConn = DriverManager.getConnection(jobInfo.getSrcUrl(), jobInfo.getSrcUser(), jobInfo.getSrcPass());
		srcConn.setAutoCommit (false);
		
		//데이터베이스 연결 
		if (flag) System.out.println("[STEP 3] connect to Source TNS .....[END]");
	}
	
	private static void targetConnection() throws Exception {
		if (flag) System.out.println("[STEP 4] connect to Target TNS .....[START]");
		
		// 1. JDBC 드라이버 로딩 
		Class.forName(driverName);
		// 2. Connection 생성 
		tgtConn = DriverManager.getConnection(jobInfo.getTgtUrl(), jobInfo.getTgtUser(), jobInfo.getTgtPass());
		tgtConn.setAutoCommit (false);
		
		//데이터베이스 연결 
		if (flag) System.out.println("[STEP 4] connect to Target TNS .....[END]");
	}
	
	private static void getClobFromSource() throws Exception {
		if (flag) System.out.println("[STEP 5] get CLOB data from Source.....[START]");
		
		// 3. Statement
		srcPstmt = srcConn.prepareStatement(jobInfo.getSrcQuery());
		
		// 4. execute query
		srcRs = srcPstmt.executeQuery();
		while (srcRs.next()) {
			srcClob = srcRs.getClob(1);
			
			lenClob = (int) srcClob.length();
			strClob = srcClob.getSubString(1, lenClob);
			
			break;
		}
		
		if (flag) System.out.println("[STEP 5] get CLOB data from Source.....[END] -> clob size = " + lenClob);
	}
	
	private static void setClobToTarget() throws Exception {
		if (flag) System.out.println("[STEP 6] set CLOB data to Target.....[START]");
		
		// 3. PrepatedStatement
		tgtPstmt = tgtConn.prepareStatement(jobInfo.getTgtQuery());
		tgtClob = tgtConn.createClob();
		int nwritten = tgtClob.setString(1, strClob);
		tgtPstmt.setClob(1, tgtClob);
		
		// 4. execute update
		int result = tgtPstmt.executeUpdate();
		
		if (flag) System.out.printf("[STEP 6] set CLOB data to Target.....[END] -> nwritten=%d, result=%d\n", nwritten, result);
	}
	
	private static void sourceClose() throws Exception {
		if (flag) System.out.println("[STEP 9] close Source Info .....");

		if (srcClob != null)
			srcClob.free();
		
		if (srcRs != null) {
			try { srcRs.close(); } catch (Exception e) {}
		}
		if (srcPstmt != null) {
			try { srcPstmt.close(); } catch (Exception e) {}
		}
		if (srcConn != null) { 
			try { srcConn.close(); } catch (Exception e) {}
		} 
	}
	
	private static void targetClose() throws Exception {
		if (flag) System.out.println("[STEP 9] close Target Info .....");
		
		if (tgtClob != null)
			tgtClob.free();
		
		if (tgtRs != null) {
			try { tgtRs.close(); } catch (Exception e) {}
		}
		if (tgtPstmt != null) {
			try { tgtPstmt.close(); } catch (Exception e) {}
		}
		if (tgtConn != null) { 
			try { tgtConn.close(); } catch (Exception e) {}
		} 
	}
}
