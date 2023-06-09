<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<!-- 부트스트랩 css -->
<link
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


</head>

<body>
	<div id="wrap">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/guestbook/addList">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li>ajax방명록</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>일반방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">일반방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">
				<table id="guestAdd">
					<colgroup>
						<col style="width: 70px;">
						<col>
						<col style="width: 70px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th><label class="form-text" for="input-uname">이름</label></th>
							<td><input id="input-uname" type="text" name="name"></td>
							<th><label class="form-text" for="input-pass">패스워드</label></th>
							<td><input id="input-pass" type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
						</tr>
						<tr class="button-area">
							<td colspan="4" class="text-center"><button id="btnSubmit"
									type="submit">등록</button></td>
						</tr>
					</tbody>

				</table>
				<!-- //guestWrite -->
				<!-- </form> -->
				
				<!-- div태그 안에 반복문 태그 위치 확인 필수 -->
				<div id="guestbookListArea">

					<c:forEach items="${guestbookList}" var="guestbookVo">
						<table id="t${guestbookVo.no}" class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${guestbookVo.no}</td>
								<td>${guestbookVo.name}</td>
								<td>${guestbookVo.regDate}</td>
								<td>
									<button type="button" class="btn btn-danger btn-sm btnModal"
										data-delno="${guestbookVo.no}">삭제</button><!-- 버튼 data의 속성이름과 ajax이름이 같아야함 -->
								</td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${guestbookVo.content}</td>
							</tr>
						</table>
					</c:forEach>
				</div>
				<!-- //guestbookListArea -->
				
			</div>
			<!-- //guestRead -->

		</div>
		<!-- //guestbook -->
	</div>
	<!-- //content  -->
	<div class="clear"></div>

	<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	<!-- //footer -->


	<!-- //wrap -->

	<!-- 삭제폼 모달창 ----------------------------------------------------------------------->
	
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">삭제 모달창</h4>
      </div>
      <div class="modal-body">
        <input id="modalPassword" type="password" name=""><br>
        <input id="modalNo" type="text" name="no">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button id="btnDel" type="button" class="btn btn-danger">삭제</button>
      </div>
    </div>
  </div>
</div>

<!-- //삭제폼 모달창 ------------------------------------------------------------------------->
</body>

<script type="text/javascript">
//모달창에 있는 삭제 버튼 클릭했을때 (진짜삭제)
$("#btnDel").on("click", function(){//btnDel이라는 속성에 button클릭 합수를 호출한 것
	console.log("삭제버튼 클릭");
	
//서버에 데이타보내기  --->
    //데이타 모으기
	var password = $("#modalPassword").val();//$("#modalPassword")는 modalPassword라는 id를 가진 요소를 선택하는 코드, .val() 함수는 해당 요소의 값을 반환하는 역할
	var no = $("#modalNo").val();//modalNo의 id를 가져와 해당 요소값을 반환 하여 no변수에 저장하는 코드
	//객체로 만들기
	var guestVo = {
		password: password,
		no: no
	};//guestVo 객체는 password와 no 속성을 가지고 있고, 이전에 선언된 변수 password와 no의 값을 각각 가지게 된다.
	
	//요청
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/remove",//사용자가 요청한 http 패이지의 주소url값 controller의 호출 url주소와 같아야 함.		
		type : "post",//http호출 방식
		data : guestVo,//guestVo객체 전송
		dataType : "json",//json 서버의 응답 데이터 타입
		success : function(jsonResult){//$.ajax() 함수의 옵션 중 하나인 success 콜백 함수를 정의 = 서버 요청이 성공적으로 완료되었을 때 호출
			//(jsonResult)는 서버 응답response의 JSON 데이터를 나타내는 매개변수
			//success 콜백 함수는 일반적으로 서버 응답을 처리, 
			console.log(jsonResult);//jsonResult.data 값을 비교하여 조건에 따라 화면에서 요소를 제거하거나 경고창을 표시하는 등의 작업을 수행
			/*성공시 처리해야될 코드 작성*/
			if(jsonResult.data>0){
				//화면에서 지우기
				$("#t"+guestVo.no).remove();
				$("#myModal").modal("hide");
			}else{
				alert("비밀번호가 틀렸습니다.")
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	console.log("test"+ guestVo.no);//비동기화 
	
});

//삭제모달창 호출
$("#guestbookListArea").on("click",".btnModal", function() {
	console.log("모달창 호출");

	//초기화
	$("#modalPassword").val("");
	$("#modalNo").val("");

	//방명록 글번호 input창
	//data("no")삭제버튼에서 no값 가져오기 data-delno=3
	var no = $(this).data("delno");//delno jsp foreach문에 버튼 data속성 이름과 일치하는 이름

	//모달창 input태그에 no값 넣기
	$("#modalNo").val(no);

	//모달창 호출
	$("#myModal").modal("show");

});


//방명록 저장 버튼 클릭할때
$("#btnSubmit").on("click",function() {
	console.log("버튼클릭");

	//데이터 수집
	var name = $("[name='name']").val();
	var password = $("[name='password']").val();
	var content = $("[name='content']").val();

	var guestbookVo = {//data수집
		name : name,
		password : password,
		content : content
	};// --> 컨버트

	//ajax 통신 -> 요청은 같은 기술 응답이 데이터만 온다.

	//$("button").click(function(){

	//ajax통신  -> 요청은 같은 기술 , 응답 이 데이터만 온다
	$.ajax({
		url : "${pageContext.request.contextPath }/api/guestbook/add",		
		type : "post",
		/* contentType : "application/json", */
		data : guestbookVo,
		dataType : "json",
		success : function(jsonResult){
			/* 성공시 처리해야될 코드 작성 */
			console.log(jsonResult);
			
			if(jsonResult.result == "success"){
				//정상처리
				render(jsonResult.data); //리스트에 추가
				
				//등록폼 비우기
				$("[name='name']").val("");//.var()주로 form태그의 value값을 불러오거나 입력할때 사용
				$("[name='password']").val("");
				$("[name='content']").val("");
				
			}else {
				//오류처리
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});	

//방명록 리스트 그리기
function render(guestbookVo){
	
	var str = "";
	str += '<table id="t'+ guestbookVo.no + '"class="guestRead">';
	str += '   <colgroup>';
	str += '        <col style="width: 10%;">';
	str += '        <col style="width: 40%;">';
	str += '        <col style="width: 40%;">';
	str += '        <col style="width: 10%;">';
	str += '   </colgroup>';
	
	str += '   <tr>';
	str += '        <td>' + guestbookVo.no + '</td>';
	str += '        <td>' + guestbookVo.name + '</td>';
	str += '        <td>' + guestbookVo.regDate + '</td>';
	str += '        <td><button type="button" class="btn btn-danger btn-sm btnModal" data-no="'+guestbookVo.no+'">삭제</button></td>';
	str += '   </tr>';
	
	str += '   <tr>';
	str += '        <td colspan=4 class="text-left">'+ guestbookVo.content + '</td>';
	str += '   </tr>';
	str += '</table>';
	
	$("#guestbookListArea").prepend(str);
	//prepend 지정한 태그 내의 속성 앞에 html문자열을 추가
	//append 지정한 태그 내의 속성 뒤에 html문자열을 추가
}
		
		
		
				
</script>


</html>