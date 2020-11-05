package com.sist.dao;
import java.nio.channels.SeekableByteChannel;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.sist.vo.MemberVO;
/**
 * @author 이보미
 *
 */
public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	public static int memberIdCheck(String id) {
		SqlSession session = ssf.openSession();
		int count = session.selectOne("memberIdcheck", id);
		session.close();
		return count;
	}
	
	public static void memberInsert(MemberVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("memberInsert", vo);
		session.close();
	}

	public static MemberVO memberLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		SqlSession session = ssf.openSession();
		int count = session.selectOne("memberIdcheck", id);
		if (count == 0) {
			vo.setMsg("NOID");
		} else {
			MemberVO dVO = session.selectOne("memberLogin", id);
			if (pwd.equals(dVO.getPwd())) {
				vo.setMsg("OK");
				vo.setId(dVO.getId());
				vo.setName(dVO.getName());
				vo.setAdmin(dVO.getAdmin());
			} else {
				vo.setMsg("NOPWD");
			}
		}

		return vo;
	}
	public static List<MemberVO> memberRcList1(String id)
	{
		SqlSession session = ssf.openSession();
		List<MemberVO> list1 = session.selectList("memberRcList1",id);
		session.close();
		
		return list1;
	}
	
	public static List<MemberVO> memberRcList2(String id)
	{
		SqlSession session = ssf.openSession();
		List<MemberVO> list2 = session.selectList("memberRcList2",id);
		session.close();
		
		return list2;
	}
}