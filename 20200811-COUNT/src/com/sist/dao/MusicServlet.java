package com.sist.dao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
@WebServlet("/MusicServlet")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR"); // html 보내겠다는 형식
		PrintWriter out=response.getWriter();
		// 데이터 읽기
		MusicDAO dao=new MusicDAO();
		ArrayList<MusicVO> list=dao.musicAllData();
		// s.getOutputStream()
		// 브라우저에서 메모리에 출력된 HTML을 읽어 간다
		// html을 똑같이 출력
		// 테이블 윗 부분
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>뮤직 Top200</h1>");
		out.println("<table width=1200 border=1 bordercolor=black>");
		out.println("<tr>");
		out.println("<th>순위</th>");
		out.println("<th></th>");
		out.println("<th>곡명</th>");
		out.println("<th>가수명</th>");
		out.println("<th>앨범</th>");
		out.println("</tr>");
		
		// 테이블 안에 내용 (for문으로)
		// for문 안쓰면 하나하나 다 입력해야함
		for(MusicVO vo:list) {
			out.println("<tr>");
			out.println("<td>"+vo.getMno()+"</td>");
			out.println("<td><img src="+vo.getPoster()+" width=30 height=30></td>");
			out.println("<td><a href=MusicDetail?mno="+vo.getMno()+">"+vo.getTitle()+"</td>"); // a태그 이용해서 MusicDetail로 이동
			out.println("<td>"+vo.getSinger()+"</td>");
			out.println("<td>"+vo.getAlbum()+"</td>");
			out.println("</tr>");
		}		
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}