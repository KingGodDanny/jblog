<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디중복체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>

</body>


<script type="text/javascript">
	
	//Form 전송 버튼을 클릭했을때 
	$("#joinForm").on("submit", function () {
		console.log("form 전송 클릭!")
		
		//아이디 입력체크
		var id = $("#txtId").val();
		if(id.length < 3) {
			alert("아이디를 입력해 주세요.")
			return false;
		}
		
		//아이디 중복체크 여부
		
		
		
		//패스워드 체크
		var password = $("#txtPassword").val();
		if(password.length < 1) {
			alert("패스워드를 입력하세요.")
			return false;
		}
		
		//이름체크
		var name = $("#txtUserName").val();
		if(name.length < 1) {
			alert("이름을 입력해 주세요.");
			return false;
		}
		
		//약관동의
		var agree = $("#chkAgree").is(":checked");
		if(agree == false) {
			alert("약관에 동의해 주세요.")
			return false;
		}
		
		return true;
	})
	
	
	//회원가입시 중복체크 이벤트
	$("#btnIdCheck").on("click", function() {
		console.log("아이디 중복체크")
		
		var id = $("#txtId").val();
		console.log(id);
		
		$.ajax({
		       
		       url : "${pageContext.request.contextPath }/user/idCheck",      
		       type : "post",
		       //contentType : "application/json",   //json방식으로 보내겠다!
		       data : {id :id},
		       
		       dataType : "json",
		       success : function(state){
		          /*성공시 처리해야될 코드 작성*/
		          console.log(state)
		          
		          if(state == true) {
		          	  $("#tdMsg").html("사용가능한 아이디입니다.")
		          } else if(state == false) {
		        	  $("#tdMsg").html("이미 사용중인 아이디입니다.")
		          } else {
		        	  $("#tdMsg").html("고객센터에 문의 바랍니다.")
		          }
		          
		       },
		       error : function(XHR, status, error) {
		          console.error(status + " : " + error);
		       }
		    }); 
		
		
		
		
		
	})
	
</script>

</html>