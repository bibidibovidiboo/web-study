package com.sist.dao;
import java.util.*;
import java.sql.*;
public class ReplyBoardDAO {
	        // ���� 
			private Connection conn;
			// SQL ����
			private PreparedStatement ps;
			// ����Ŭ �ּ� 
			private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
			// 1. ���� ����Ŭ �������ִ� ����̹� oracle.jdbc.driver.OracleDriver
			public ReplyBoardDAO()
			{
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					// ����̹��� �̿��ؼ� ���� => thin����̹� 
				}catch(Exception ex) {}
			}
			// 2. ���� �޼ҵ�
			public void getConnection()
			{
				try
				{
					conn=DriverManager.getConnection(URL,"hr","happy");
					// conn hr/happy
				}catch(Exception ex) {}
			}
			// 3. �ݴ� �޼ҵ� 
			public void disConnection()
			{
				try
				{
					if(ps!=null) ps.close();
					if(conn!=null) conn.close();
					// exit 
				}catch(Exception ex) {}
			}
			// 2001 => 19���� �ڵ� ==> MVC/MyBatis
			// 1. ��� ��� => ������ ������ 
			public ArrayList<ReplyBoardVO> boardListData(int page)
			{
				ArrayList<ReplyBoardVO> list=
						new ArrayList<ReplyBoardVO>();
				try
				{
					getConnection();
					String sql="SELECT no,subject,name,regdate,hit,group_tab,num "
							  +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
							  +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
							  +"FROM replyBoard ORDER BY group_id DESC,group_step ASC)) "
							  +"WHERE num BETWEEN ? AND ?";
							  /* 
							   *                         group_id  group_step
							   *        KKKKKK             3
							   *           =>              3
							   *        AAAAAA             2           0
							   *          ->BBBBB          2           1
							   *           ->CCCCCC        2           2
							   *          ->KKKKKK         2           3
							   *        DDDDDD             1           0
							   */
					ps=conn.prepareStatement(sql);
					int rowSize=10;
					int start=(rowSize*page)-(rowSize-1);
					int end=rowSize*page;
					
					// ? �� �� ä��� 
					ps.setInt(1, start);
					ps.setInt(2, end);
					
					// �����Ŀ� ��� �� �б�
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						ReplyBoardVO vo=new ReplyBoardVO();
						vo.setNo(rs.getInt(1));
						vo.setSubject(rs.getString(2));
						vo.setName(rs.getString(3));
						vo.setRegdate(rs.getDate(4));
						vo.setHit(rs.getInt(5));
						vo.setGroup_tab(rs.getInt(6));
						
						list.add(vo);
					}
					rs.close();
					
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
				return list;
			}
			// 1-1 ��ü ����
			public int boardRowCount()
			{
				int count=0;
				try
				{
					getConnection();
					String sql="SELECT COUNT(*) FROM replyBoard";
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					rs.next();
					count=rs.getInt(1);
					rs.close();
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
				return count;
			}
			// 2. �󼼺���
			public ReplyBoardVO boardDetail(int no,int type) {
				ReplyBoardVO vo=new ReplyBoardVO();
				try {
					getConnection();
					// ��ȸ�� 
					String sql="";
					if(type==1) {
						sql="UPDATE replyBoard SET "
							+ "hit=hit+1 "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
					}

					// ������ ��ȸ�� �б� => sql ���� �ѹ��� ����
					sql="SELECT no,name,subject,content,regdate,hit "
							+ "FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					// ����� 
					ResultSet rs=ps.executeQuery();
					// Ŀ����ġ ���� => ������ ��� ��ġ�� Ŀ������
					rs.next();
					vo.setNo(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setSubject(rs.getString(3));
					vo.setContent(rs.getString(4));
					vo.setRegdate(rs.getDate(5));
					vo.setHit(rs.getInt(6));
					
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
				return vo;
			}
			// 3. ���� ��� => INSERT
			public void boardInsert(ReplyBoardVO vo)
			{
				try
				{
					getConnection();
					String sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id) " 
					         + "VALUES(rb_no_seq.nextval,?,?,?,?,"
							 +"(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))"; 
					ps=conn.prepareStatement(sql);
					ps.setString(1, vo.getName());
					ps.setString(2, vo.getSubject());
					ps.setString(3, vo.getContent());
					ps.setString(4, vo.getPwd());
					
					ps.executeUpdate();
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
			}
			/*
			 *                no   gi  gs  gt  root depth
			 *   AAAAAAA      1     1  0   0    0    0
			 *     =>BBBBBB   2     1  1   1
			 *     
			 */
			// 4. �亯�ϱ� => SQL => ���ļ� ó�� (��������)
			public void boardReplyInsert(int root,ReplyBoardVO vo) {
				try {
					getConnection();
					String sql="SELECT group_id,group_step,group_tab "
							+ "FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);
					ResultSet rs=ps.executeQuery();
					rs.next();
					int gi=rs.getInt(1); // gi
					int gs=rs.getInt(2); // gs+1
					int gt=rs.getInt(3); // gt+1
					rs.close();
					// ���� �߻�
					sql="UPDATE replyBoard SET "
							+ "group_step=group_step+1 "
							+ "WHERE group_id=? AND group_step>?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, gi);
					ps.setInt(2, gs);
					ps.executeUpdate();
					
					/*
					 *                     gi    gs   gt
					 *     AAAAA            1    0    0
					 *      =>HHHHH         1    1    1
					 *      =>OOOOO         1    2    1
					 *      =>KKKKK         1    3    1
					 *      =>DDDDD         1    4    1 
					 *      =>BBBBB         1    5    1
					 *       =>PPPP         1    6    2
					 *       =>CCCCCC       1    7    2      
					 */
					
					// INSERT
					sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) " 
					         + "VALUES(rb_no_seq.nextval,?,?,?,?,"
							 +"?,?,?,?)"; 
					ps=conn.prepareStatement(sql);
					ps.setString(1, vo.getName());
					ps.setString(2, vo.getSubject());
					ps.setString(3, vo.getContent());
					ps.setString(4, vo.getPwd());
					ps.setInt(5, gi);
					ps.setInt(6, gs+1);
					ps.setInt(7, gt+1);
					ps.setInt(8, root);
					ps.executeUpdate();
					// root => depth+1
					
					sql="UPDATE replyBoard SET "
							+ "depth=depth+1 "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);
					ps.executeUpdate();
					
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
			}
			// 5. �����ϱ� => UPDATE
			public boolean boardUpdate(ReplyBoardVO vo) {
				boolean bCheck=false;
				try {
					getConnection();
					String sql="SELECT pwd FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, vo.getNo());
					ResultSet rs=ps.executeQuery();
					rs.next();
					String db_pwd=rs.getString(1); // ���ο���Ȯ�� 
					rs.close();
					
					if(db_pwd.equals(vo.getPwd())) {
						bCheck=true;
						
						sql="UPDATE replyBoard SET "
								+ "name=?,subject=?,content=? "
								+ "WHERE no=?";
						ps=conn.prepareStatement(sql);
						ps.setString(1, vo.getName());
						ps.setString(2, vo.getSubject());
						ps.setString(3, vo.getContent());
						ps.setInt(4, vo.getNo());
						ps.executeUpdate();
					}
					
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}return bCheck;
			}
			// 6. �����ϱ� => SQL => ���ļ� ó�� (��������)
			// 7. ã�� => LIKE , REGEXP_LIKE
			public ArrayList<ReplyBoardVO> boardFindData(String fs,String ss){
				ArrayList<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
				try {
					getConnection();
					String sql="SELECT no,subject,name,regdate,hit "
							+ "FROM replyBoard "
							+ "WHERE "+fs+" LIKE '%"+ss+"%'";
					// ps.setString(1,fs) ==> 'name'
					// WHERE 'name' LIKE
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						ReplyBoardVO vo=new ReplyBoardVO();
						vo.setNo(rs.getInt(1));
						vo.setName(rs.getString(3));
						vo.setSubject(rs.getString(2));
						vo.setRegdate(rs.getDate(4));
						vo.setHit(rs.getInt(5));
						
						list.add(vo);
					}
					rs.close();
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
				return list;
			}
	
			
}