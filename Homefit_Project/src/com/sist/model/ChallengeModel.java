package com.sist.model;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.controller.RequestMapping;
import com.sist.dao.ChallengeDAO;
import com.sist.dao.Challenge_CertifiedDAO;
import com.sist.vo.ChallengeVO;
import com.sist.vo.Challenge_CertifiedVO;
import com.sist.vo.Challenge_ParticipationVO;

public class ChallengeModel {
	
	
	// 내가 참여한 방
	// 마이 인증 목록 디테일
		@RequestMapping("challenge/myChallengeRoom.do")
		public String myChallengeRoom(HttpServletRequest request)
		{
			// 로그인 중인 아이디 받기
			HttpSession session= request.getSession();
			String id= (String) session.getAttribute("id");
			
			Map map = new HashMap();
			map.put("id", id);
			
			List<ChallengeVO> myRoomfList = new ArrayList<ChallengeVO>();
			
			if(id!=null)
			{
				myRoomfList = ChallengeDAO.mychallengeRoomList(map);

			}
			else
			{
				myRoomfList = null;
			}
			// 해당 id로 검색한 전체 인증내역
			
			
			//map.put("my_selected_date", date);
			session.setAttribute("id", id);
			session.setAttribute("myRoomfList",myRoomfList);
			return "../challenge/myChallengeRoom.jsp";
		}
		
	
	// 마이 인증 목록
	@RequestMapping("challenge/mychallenge.do")
	public String myChallenge(HttpServletRequest request)
	{
		request.setAttribute("main_jsp", "../challenge/mychallenge.jsp");
		return "../main/main.jsp";
	}
	 
	// 마이 인증 목록 디테일
	@RequestMapping("challenge/myProofDetail.do")
	public String myProofDetail(HttpServletRequest request)
	{
		// 로그인 중인 아이디 받기
		HttpSession session= request.getSession();
		String id= (String) session.getAttribute("id");
		
		// 사용자가 클릭한 날짜 받기
		String day= request.getParameter("day");
		day=day.replaceAll("[^0-9]", "");
		String year= request.getParameter("year");
		String month= request.getParameter("month");
		
		if(month.length()<2)
			month="0"+month;
		if(day.length()==1)
			day="0"+day;
//		
		String date = year+"-"+month+"-"+day;
		//String date = String.format("%4d-%02d-%02d",year,month,Integer.parseInt(day) );
		//System.out.println(date);
		
		Map map = new HashMap();
		map.put("id", id);
		map.put("date",date);
		
		List<Challenge_CertifiedVO> myProofList = new ArrayList<Challenge_CertifiedVO>();
		
		if(id!=null)
		{
			myProofList = ChallengeDAO.myChallenge_CertifiedData(map);

		}
		
		else
		{
			myProofList = null;
		}
		// 해당 id로 검색한 전체 인증내역

		//map.put("my_selected_date", date);
		session.setAttribute("id", id);
		session.setAttribute("myProofList",myProofList);
		return "../challenge/myProofDetail.jsp";
	}
	
	
	// 마이챌린지 달력만들기
	@RequestMapping("challenge/certifiedcalendar.do")
	public String certifiedcalendar(HttpServletRequest request)
	{
		// 현재 년, 월 구하기
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1; // Calendar.MONTH : 0~11
		
		// 두 번째 호출된 페이지에서 요청된 년도와 월을 저장하기
		String paramYear=request.getParameter("year");
		String paramMonth=request.getParameter("month");
		
		if(paramYear!=null) {
			year = Integer.parseInt(paramYear);
		}
		if(paramMonth!=null) {
			month = Integer.parseInt(paramMonth);
		}
		
		// 요청받은 년도와 월의 일자로 캘린더 셋팅
		cal.set(year, month-1, 1);
		
		// 매월 1일의 요일 구하기 = 첫 주 빈공백의 갯수
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
		//System.out.println(dayOfWeek);
		
		// 월의 최대 일수 구하기
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//System.out.println(lastDay);
		
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		System.out.println(id);
		
		// 인증 있는 날짜 받을 배열
		int[] arr=new int[31];
		
		String strday="";
		// 달력마다 날짜에 데이터 있는지 확인하기
		for(int i=0;i<31;i++)
		{
			strday= year+"-"+month+"-";
			String day="";
			// day에 0붙이기
			if(i>=0 && i<=8)
			{
				day+="0"+(i+1);
			}
			else
			{
				day+=String.valueOf(i+1);
			}
			strday+=day;
			
			if(id!=null)
			{
				
			Map map = new HashMap();
			map.put("id", id);
			map.put("strday", strday);
			
			int count = ChallengeDAO.myChallenge_Certified_check(map);
			
			arr[i]=count;
			}
		}

		// 인증 테이블에서 세션 id값과 일지하는 정보 가져오기
		List<ChallengeVO> cfList = new ArrayList<ChallengeVO>();
				
		Map map2 = new HashMap();
		map2.put("id", id);

		if(id!=null)
		{
		cfList = ChallengeDAO.myChallenge_roomdetail(map2);
		}
		
		
		
		request.setAttribute("cfList", cfList);
		request.setAttribute("id", id);		
		request.setAttribute("strday", strday);
		request.setAttribute("arr", arr);
		request.setAttribute("lastDay", lastDay);
		request.setAttribute("dayOfWeek", dayOfWeek);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		
		return "../challenge/certifiedcalendar.jsp";
	}
		
	
	// 달력만들기
		@RequestMapping("challenge/calendar.do")
		public String challengeCalendar(HttpServletRequest request)
		{
			// 현재 년, 월 구하기
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1; // Calendar.MONTH : 0~11
			
			// 두 번째 호출된 페이지에서 요청된 년도와 월을 저장하기
			String paramYear=request.getParameter("year");
			String paramMonth=request.getParameter("month");
			
			if(paramYear!=null) {
				year = Integer.parseInt(paramYear);
			}
			if(paramMonth!=null) {
				month = Integer.parseInt(paramMonth);
			}
		
			// 요청받은 년도와 월의 일자로 캘린더 셋팅
			cal.set(year, month-1, 1);
			
			// 매월 1일의 요일 구하기 = 첫 주 빈공백의 갯수
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
			System.out.println(dayOfWeek);
			
			// 월의 최대 일수 구하기
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			//System.out.println(lastDay);
			
			// 마지막 주 빈 공백의 갯수
			//System.out.println(7-(dayOfWeek+lastDay)%7);
			
			request.setAttribute("lastDay", lastDay);
			request.setAttribute("dayOfWeek", dayOfWeek);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			
			return "../challenge/calendar.jsp";
		}
	
	@RequestMapping("challenge/list.do")
	public String challengeListData(HttpServletRequest request) {
		
		request.setAttribute("main_jsp", "../challenge/list.jsp");
		return "../main/main.jsp";

	}	
	
	// 검색 결과 받기
	@RequestMapping("challenge/search.do")
	public String searchListData(HttpServletRequest request) {
		 System.out.println("검색 호출 했음");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		// 검색 결과 받기
		String keyword = request.getParameter("keyword");
		
		// 페이징 처리
		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = rowSize * (curpage - 1) + 1;
		int end = rowSize * curpage;

		int totalpage = ChallengeDAO.challengeSearchTotalPage(keyword);

		int BLOCK = 5;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalpage)
			endPage = totalpage;
		
		// 목록 리스트 데이터 결과값 받기
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("keyword", keyword);
		
		List<ChallengeVO> list = ChallengeDAO.challengeSearchData(map);
		
		// 아이디 받기
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		// 게시날짜와 비교할 오늘날짜 받기
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
		
		for (ChallengeVO vo : list) {
			
			// 제목 글자수 자르기
			String str = vo.getTitle();
			if (str.length() > 20) {
				str = str.substring(0, 10);
				str += "...";
			}
			vo.setTitle(str);
			
			int no=vo.getChallenge_no();
			
			// 참여중인지 확인하기
			Challenge_ParticipationVO pVO=new Challenge_ParticipationVO();
			pVO.setChallenge_id(id);
			pVO.setChallenge_no(no);
			int ptCheck = Challenge_CertifiedDAO.Challnege_paticipation_check(pVO);
			vo.setParticipantionCheck(ptCheck);

			// 방의 총 인원 수 구하기
			int total=ChallengeDAO.totalPaticipantCount(pVO.getChallenge_no());
			vo.setParticipantCount(total);
			
			String state ="";
			// d-day , 진행중, 대기중 , 완료 확인하기
//			if(vo.getStart_day() < today)
//			{
//				state="대기중";
//			}
			
		}	
		
		request.setAttribute("today", today);
		request.setAttribute("id", id);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		return "../challenge/search.jsp";

	}	
	
	
	// 도전 목록: sublist
	@RequestMapping("challenge/sublist.do")
	public String subListData(HttpServletRequest request) {
		// System.out.println("sublist 호출 했음");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		// 검색 결과 받기
		String keyword = request.getParameter("keyword");
		
		// 정렬 sorting정보 받기
		String sorting = request.getParameter("sorting");
		System.out.println("sorting:"+sorting);
		if (sorting == null || sorting.equals(""))
			sorting = "regdate";
		
		// 카테고리 정보 받기
		String cate = request.getParameter("cate");
		//System.out.println("cate!!!!!!!!!!!!!!!!"+cate);
		
		// 페이징 처리
		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		
		System.out.println(page);
		
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = rowSize * (curpage - 1) + 1;
		int end = rowSize * curpage;

		int totalpage = ChallengeDAO.challengeCateTotalPage(cate);
		//System.out.println(totalpage);

		int BLOCK = 5;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		//System.out.println(startPage);
		
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalpage)
			endPage = totalpage;
		
		// 목록 리스트 데이터 결과값 받기
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("cate", cate);
		map.put("sorting", sorting);
		
		List<ChallengeVO> list = ChallengeDAO.challengeCateListData(map);
		
		// 아이디 받기
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		// 게시날짜와 비교할 오늘날짜 받기
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
		
		for (ChallengeVO vo : list) {
			
			// 제목 글자수 자르기
			String str = vo.getTitle();
			if (str.length() > 20) {
				str = str.substring(0, 20);
				str += "...";
			}
			vo.setTitle(str);
			
			int no=vo.getChallenge_no();
			
			// 참여중인지 확인하기
			Challenge_ParticipationVO pVO=new Challenge_ParticipationVO();
			pVO.setChallenge_id(id);
			pVO.setChallenge_no(no);
			int ptCheck = Challenge_CertifiedDAO.Challnege_paticipation_check(pVO);
			vo.setParticipantionCheck(ptCheck);

			// 방의 총 인원 수 구하기
			int total=ChallengeDAO.totalPaticipantCount(pVO.getChallenge_no());
			vo.setParticipantCount(total);
			
			String state ="";
			// d-day , 진행중, 대기중 , 완료 확인하기
//			if(vo.getStart_day() < today)
//			{
//				state="대기중";
//			}
			
		}	
		
		request.setAttribute("today", today);
		request.setAttribute("id", id);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		return "../challenge/sublist.jsp";

	}
	
	@RequestMapping("challenge/insert.do")
	public String Challenge_room_Certified(HttpServletRequest request) {
		request.setAttribute("main_jsp", "../challenge/insert.jsp");
		return "../main/main.jsp";
	}

	// 새로운 도전방 만들기
	@RequestMapping("challenge/insert_ok.do")
	public String challengeInsert_Ok(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");// 한글 디코딩
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String filename="";
//		String path = "/Users/haeni/Documents/jsp-project/Homefit/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Home_fit/challenge_poster";
		String path = "C:\\webDev\\webStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Home_fit\\challenge_poster";
		
//		String path = "C:\\webDev\\webStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Home_fit\\challenge_poster"; // 파일이
	      File Folder = new File(path);

	      // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
	      if (!Folder.exists()) {
	         try{
	             Folder.mkdir(); //폴더 생성합니다.
	             System.out.println("폴더가 생성되었습니다.");
	              } 
	              catch(Exception e){
	             e.getStackTrace();
	         }        
	            }else {
	         System.out.println("이미 폴더가 생성되어 있습니다.");
	      }
		
		String enctype = "UTF-8"; // 한글파일명을 사용 여부
		int size = 1024 * 1024 * 100;// 파일의 최대크기

		// MultipartRequest : 사용자가 보내준 데이터를 받는다 (request=>파일을 받을 수 없다 , 일반데이터만 받는다)
		MultipartRequest mr = new MultipartRequest(request, path, size, enctype, new DefaultFileRenamePolicy());

		String title = mr.getParameter("title"); // 업로드시에만 사용
		String limit = mr.getParameter("limit");
		String content = mr.getParameter("content");
		String cate = mr.getParameter("cate");
		
		String id_leader = (String) session.getAttribute("id");
		
		String start_day = mr.getParameter("start_day");
		String end_day = mr.getParameter("end_day");
		
		long difDays = 0;
		
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			Date start_date=format.parse(start_day);
			Date end_date=format.parse(end_day);
			
			long dif=end_date.getTime() - start_date.getTime();
			difDays =dif/(24*60*60*1000);
			difDays=Math.abs(difDays)+1;
		} catch (Exception e) {
			System.out.println("period오류");
		}
		
		// 받은 데이터들을 DAO => DAO에서 오라클에 INSERT
		ChallengeVO vo = new ChallengeVO();
		vo.setTitle(title);
		vo.setLimit(Integer.parseInt(limit));
		vo.setContent(content);
		vo.setCate(cate);
		
		vo.setId_leader(id_leader);
		
		vo.setDb_start_day(start_day);
		vo.setDb_end_day(end_day);
		vo.setPeriod(difDays);

		filename = mr.getFilesystemName("upload");
		System.out.println("파일네임은 이거임듕"+filename);
		if (filename == null)// 파일을 올리지 않을 경우
		{
			vo.setPoster("ChallengeDefault.jpg");
		} else// 파일 올릴 경우
		{
			vo.setPoster(filename);
			System.out.println("파일네임은 이거임듕222"+filename);
		}

		ChallengeDAO.challengeInsert(vo); // 추가
		
		// 방금 만든 방의 번호
		int challenge_no = vo.getChallenge_no();
		Challenge_ParticipationVO pVO = new Challenge_ParticipationVO();

		pVO.setChallenge_no(challenge_no);
		pVO.setChallenge_id(id_leader);

		// 방장도 참가자 목록에 추가하기
		Challenge_CertifiedDAO.Challenge_participation(pVO);
		
		//System.out.println("challenge_no:"+pVO.getChallenge_no());
		//System.out.println("challenge_id:"+pVO.getChallenge_id());
		System.out.println(vo.getStart_day());
		System.out.println(vo.getEnd_day());

//		request.setAttribute("main_jsp", "../challenge/list.jsp");
//		return "../main/main.jsp";
		return "redirect:../challenge/list.do";
	}
	


}