import { Alert } from '../module/alert';

const $frm = $('#frm');
const $loginId = $('#loginId');
const $userPw = $('#userPw');

/**
 *  login : 로그인 실행
 */
const loginProc = () => {
    if ($loginId.val() === '') {
        Alert('Please input the id');
        $loginId.focus();
        return;
    }

    if ($userPw.val() === '') {
        Alert('Please input the password');
        $userPw.focus();
        return;
    }

    // let id_save_check = document.getElementById('idSaveCheck').checked;
    // if(id_save_check){
    //     setCookie("userId", $("#loginId").val(), 30);
    // }

    // const $frm = $('#frm');
    $frm.attr('action', '/loginProc');
    $frm.attr('method', 'post');
    $frm.submit();
};

/**
 *  signUp : 회원가입 화면 이동
 */
const signUp = () => {
    $frm.attr('action', 'signUp');
    $frm.attr('method', 'get');
    $frm.submit();
};

/**
 *  pwChange : 비밀번호 변경 화면 이동
 */
const pwChange = () => {
    $frm.attr('action', 'pwChange');
    $frm.attr('method', 'get');
    $frm.submit();
};

$(document).ready(() => {
    // 엔터 입력시 로그인 처리
    $(window).on('keydown', (e) => {
        if (e.keyCode === 13) {
            loginProc();
        }
    });

    // 로그인 이벤트
    $('#loginBtn').click(() => {
        loginProc();
    });

    // 패스워드 변경 이벤트
    $('#pwChangeBtn').click(() => {
        pwChange();
    });

    // 회원가입 이벤트
    $('#signUpBtn').click(() => {
        signUp();
    });
});
