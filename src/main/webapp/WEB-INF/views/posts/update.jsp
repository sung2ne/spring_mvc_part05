<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ include file="../base/top.jsp" %>
<%@ include file="../base/navbar.jsp" %>
<%@ include file="../base/title.jsp" %>
<%@ include file="../base/message.jsp" %>

<!-- 페이지 내용 -->
<div class="row">
    <div class="col-12">
        <!-- 게시글 수정 -->
        <form id="updateForm" action="/posts/${postsVo.id}/update" method="POST">
            <div class="card mb-3">
                <div class="card-header">
                    <span class="text-danger">*</span> 표시는 필수항목입니다.
                </div>
                <div class="card-body">                
                    <div class="mb-3">
                        <label for="title" class="form-label">제목</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요" required value="${postsVo.title}">
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">내용</label>
                        <textarea class="form-control" id="content" name="content" rows="5" placeholder="내용을 입력하세요" required>${postsVo.content}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="username" class="form-label">작성자</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="작성자를 입력하세요" required value="${postsVo.username}">
                    </div>
                    <div class="mb-3">
                        <label for="passwd" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" id="passwd" name="passwd" placeholder="비밀번호를 입력하세요" required>
                    </div>
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">수정하기</button>
                <a href="/posts/${postsVo.id}" class="btn btn-secondary">취소</a>
            </div>
        </form>
        <!--// 게시글 수정 -->
    </div>
</div>
<!--// 페이지 내용 -->

<%@ include file="../base/script.jsp" %>

<!-- script -->
<script>
    /* 자바스크립트 */
</script>
<!--// script -->

<%@ include file="../base/bottom.jsp" %>