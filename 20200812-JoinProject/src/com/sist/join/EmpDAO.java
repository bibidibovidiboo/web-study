package com.sist.join;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.*;
public class EmpDAO {
	// 오라클 연결
	private Connection conn;
	// SQL문장을 오라클로 전송
	private PreparedStatement ps;
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 드라이버 설치
	public EmpDAO() {
		// 생성자 => 멤버변수의 초기화 , 네트워크 서버 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	// 연결
	public void getConnetion() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {
		}
	}
	// 해제
	public void disConnetion() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch (Exception ex) {}	
	}
	public ArrayList<EmpVO> empDeptJoinData()
	{
		ArrayList<EmpVO> list=new ArrayList<EmpVO>();
		try {
			getConnetion();
			String sql="SELECT empno,ename,job,hiredate,sal,dname,loc "
					+"FROM emp,dept "
					+"WHERE emp.deptno=dept.deptno";
			ps=conn.prepareStatement(sql); // 문장을 보냄
			ResultSet rs=ps.executeQuery(); // rs값 저장
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.getDvo().setDname(rs.getString(6));
				vo.getDvo().setLoc(rs.getString(7));
				list.add(vo);
			}
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnetion();
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
}
