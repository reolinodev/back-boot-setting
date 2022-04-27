import { Alert } from '../module/alert';

const $frm = $('#frm');
const $loginId = $('#loginId');
const $userPw = $('#userPw');

/**
 *  login : 로그인 실행
 */
const loginProc = () => {
    if ($loginId.val() === '') {
        Alert('Please input the ID.');
        $loginId.focus();
        return;
    }

    if ($userPw.val() === '') {
        Alert('Please input the password.');
        $userPw.focus();
        return;
    }

    // let id_save_check = document.getElementById('idSaveCheck').checked;
    // if(id_save_check){
    //     setCookie("userId", $("#loginId").val(), 30);
    // }

    $frm.attr('action', '/login');
    $frm.attr('method', 'post');

    $frm.submit();
};

/**
 *  signUp : 회원가입 화면 이동
 */
const signUp = () => {
    location.href = 'signUp';
};

/**
 *  pwChange : 비밀번호 변경 화면 이동
 */
const pwChange = () => {
    location.href = 'pwChange';
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

    // 패스워드 변경 화면 이동
    $('#pwChangeBtn').click(() => {
        pwChange();
    });

    // 사용자 등록 화면 이동
    $('#signUpBtn').click(() => {
        signUp();
    });
});
