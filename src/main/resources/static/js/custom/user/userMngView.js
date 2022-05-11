import { setCodeSelBox } from "../../module/component";
import Page, { setPagination } from "../../module/pagination"
import { serializeFormJson } from "../../module/json";
import { setBasicGrid } from "../../module/grid";
import { checkKr } from "../../module/validation";
import { Alert } from "../../module/alert";

let page = new Page(1, false, 10, 0);
let grid;
let pagination;

$(document).ready(function() {
    // //셀렉트 박스(공통코드) : 사용구분 => page
    setCodeSelBox('viewUseYn','USE_YN','ALL','' );

    //그리드 세팅
    grid = setGridLayout();

    //페이징 세팅
    pagination = setPagination(page, pagingCallback);

    //검색버튼
    $("#searchBtn").click(function(){
        page = new Page(1, false);
        search();
    });

    //사용여부, 페이지 개수 변경시 검색
    $("#viewUseYn, #pagePer").change(function(){
        page = new Page(1, false);
        search();
    });

    //등록버튼 클릭시 모달을 초기화한다.
    $("#userMngViewFrm #writeBtn").click(function(){
        initUserMngWrite();
    });

    //아이디 체크
    $("#userMngWriteFrm #signUpCheckBtn").click(function(){
        signUpCheck();
    });

    //사용자를 등록한다.
    $("#userMngWriteFrm #submitBtn").click(function(){
        signUpProc();
    });
});

/**
 * search : 조회
 */
const search = () => {

    page.pagePer = $("#pagePer option:selected").val();
    page.pageInit = true;

    let params = serializeFormJson('userMngViewFrm');
    params.current_page = page.currentPage;
    params.page_per = page.pagePer;

    $.ajax({
        url : '/api/user/',
        type: 'POST',
        data: JSON.stringify(params),
        headers: {'Content-Type': 'application/json'},
        success : function (result){
            console.log(result);
            const gridData = result.data;
            page.totalCount = result.total;
            grid.resetData(gridData);
            //
            // setGridClickEvent(grid, "login_id", "user_id", userMngEdit);
            //

            pagination = setPagination(page, pagingCallback);
        },
        error : function (request, status, error){
            console.log('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

/**
 * setGridLayout : 그리드 구성
 */
const setGridLayout = () => {
    //헤더 생성
    const columns = [
        {header: 'No', name: 'rnum', width :100, align : 'center'},
        {header: 'SEQ', name: 'user_id', width :100, align : 'center', hidden : true},
        {header: '아이디', name: 'login_id', align : 'center'},
        {header: '이메일', name: 'email'},
        {header: '이름', name: 'user_nm', align : 'center'},
        {header: '사용여부', name: 'use_yn_nm', width :150, align : 'center'}
    ];
    //데이터
    const gridData = [];

    return setBasicGrid(columns,gridData);
}

/**
 * pagingCallback : 페이징 콜백
 */
const pagingCallback = (returnPage) => {
    page.currentPage = returnPage;
    search();
}

const $loginId = $('#userMngWrite #loginId');
const $userNm = $('#userMngWrite #userNm');
const $email = $('#userMngWrite #email');
const $userPw = $('#userMngWrite #userPw');
const $userPwRe = $('#userMngWrite #userPwRe');
const $cellPhone = $('#userMngWrite #cellPhone');

/**
 * signUpCheck : 아이디 체크
 */
const signUpCheck = () => {
    if ($loginId.val() === '') {
        $('#msg').html('Please enter ID');
        return;
    }

    $.ajax({
        url: '/api/user/'+$loginId.val(),
    }).then((data) => {
        if (data.header.resultCode === 'ok') {
            $('#signUpChk').val('Y');
            $('#msg').html(data.header.message);
        }
    }, (request, status, error) => {
        if (request.responseJSON.header.resultCode === 'fail') {
            $('#signUpChk').val('N');
            $('#msg').html(request.responseJSON.header.message);
        }
        console.log(
            `code:${request.status}\n` +
            `message:${request.responseText}\n` +
            `error:${error}`
        );
    });
}

/**
 * initUserMngWrite : 등록화면의 값 초기화
 */
const initUserMngWrite = () => {
    $loginId.val('');
    $userNm.val('');
    $email.val('');
    $email.val('');
    $cellPhone.val('');
    $userPw.val('');
    $userPwRe.val('');
    $("#userMngWrite #useYn option:eq(0)").attr('selected','selected');
    $("#userMngWrite #msg").html('');
}

/**
 *  signUpProc : 회원가입 실행
 */
const signUpProc = () => {
    let msg = '';

    if ($loginId.val() === '') {
        msg = 'Please enter ID';
        $('#msg').html(msg);
        $('#loginId').focus();
        return;
    }
    if (checkKr($loginId.val())) {
        msg = 'You cannot enter Korean characters in the ID';
        $('#msg').html(msg);
        $('#loginId').focus();
        return;
    }
    if ($('#signUpChk').val() === 'N') {
        msg = 'Please check the ID';
        $('#msg').html(msg);
        $('#loginId').focus();
        return;
    }
    if ($userNm.val() === '') {
        msg = 'Please enter name.';
        $('#msg').html(msg);
        $('#userNm').focus();
        return;
    }
    if ($email.val() === '') {
        msg = 'Please enter e-mail';
        $('#msg').html(msg);
        $('#email').focus();
        return;
    }
    if ($cellPhone.val() === '') {
        msg = 'Please enter phone number';
        $('#msg').html(msg);
        $('#cellPhone').focus();
        return;
    }
    if ($userPw.val() === '') {
        msg = 'Please enter a password';
        $('#msg').html(msg);
        $userPw.focus();
        return;
    }
    if ($userPwRe.val() === '') {
        msg = 'Please re-enter your password';
        $('#msg').html(msg);
        $('#userPwRe').focus();
        return;
    }
    if ($userPw.val() !== $userPwRe.val()) {
        msg = 'Passwords do not match';
        $('#msg').html(msg);
        $('#userPwRe').focus();
        return;
    }

    const param = {
        login_id: $loginId.val(),
        user_nm: $userNm.val(),
        email: $email.val(),
        cell_phone: $cellPhone.val(),
        user_pw: $userPw.val(),
    };

    $.ajax({
        url: "/api/user/",
        type: 'PUT',
        data: JSON.stringify(param),
        headers: {'Content-Type': 'application/json'},
    }).then((data) => {
        if (data.header.resultCode === 'ok') {
            Alert(data.header.message);
            search();
            closeModal();
        } else {
            $('#msg').html(data.header.message);
        }
    }, (request, status, error) => {
        if(request.status === 500){
            console.log(
                `code:${request.status}\n` +
                `message:${request.responseText}\n` +
                `error:${error}`
            );
        }else if(request.status === 400){
            const errorList = request.responseJSON.errorList;
            if(errorList !== undefined){
                if(errorList.lengh !==0){
                    const message = errorList[0].message;
                    $('#msg').html(message);
                }
            }else {
                const data = request.responseJSON.header;
                $('#msg').html(data.message);
            }
        }
    });
};

const closeModal = () => {
    $('#userMngWriteFrm').hide();
    $('.modal-backdrop').hide();
}


