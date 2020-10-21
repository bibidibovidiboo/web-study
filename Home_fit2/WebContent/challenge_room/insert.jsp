<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
  	<div class="row">
  		<h1 class="text-center">도전 만들기</h1>
  		
  		  <form method="post" action="../challenge_room/insert_ok.do" enctype="multipart/form-data">
  		
	  		<table class="table table-hover">
	  			<tr>
			       <th class="danger text-right" width=15%>카테고리</th>
			       <td width=85%>
			         <SELECT NAME=cate SIZE=1>
				        <OPTION VALUE=food>food</OPTION>
				        <OPTION VALUE=exercise>exercise</OPTION>
				        <OPTION VALUE=etc>etc</OPTION>
				    </SELECT>
			       </td>
			     </tr>
	  	
	  			<tr>
			       <th class="danger text-right" width=15%>대표 사진을 등록하세요</th>
			       <td width=85%>
			         <input type=file name=poster size=20 class="input-sm">
			       </td>
			     </tr>
			     
			     <tr>
			       <th class="danger text-right" width=15%>방 제목을 입력하세요</th>
			       <td width=85%>
			         <input type=text name=title size=15 class="input-sm">
			       </td>
			     </tr>
			     
			     <tr>
			       <th class="danger text-right" width=15%>도전 시작일</th>
			       <td width=85%>
			         <input type=date name=start_day class="input-sm">
			       </td>
			     </tr>
			     
			     <tr>
			       <th class="danger text-right" width=15%>도전 종료일</th>
			       <td width=85%>
			         <input type=date name=end_day class="input-sm">
			       </td>
			     </tr>
			     <tr>
			       <th class="danger text-right" width=15%>도전 가능 인원</th>
			       <td width=85%>
			         <input type=text name=limit class="input-sm">
			       </td>
			     </tr>
			     
			     
			     <tr>
			       <th class="danger text-right" width=15%>도전소개글을 입력하세요</th>
			       <td width=85%>
			         <textarea rows="10" cols="50" name=content></textarea>
			       </td>
			     </tr>
			     <tr>
			       <td colspan="2" class="text-center">
			       
			         <input type=submit value="도전 만들기" class="btn btn-sm btn-primary">
			         <input type=button value="취소하기" class="btn btn-sm btn-primary"
			           onclick="javascript:history.back()">
			       </td>
			     </tr>
	   </table>
	  </form>
  	</div>
  </div>
</body>
</html>