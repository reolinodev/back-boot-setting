import { Alert, AlertMove } from '../module/alert';
import { checkKr, checkEmail, checkPw } from '../module/validation';

const $loginId = $('#loginId');
const $userNm = $('#userNm');
const $email = $('#email');
const $userPw = $('#userPw');
const $userPwRe = $('#userPwRe');
const $frm = $('#frm');

/**
 *  signUpCheck : 회원 가입시 중복 아이디 체크
 */
const signUpCheck = () => {
    if ($loginId.val() === '') {
        $('#msg').html('Please enter your ID');
        return;
    }

    const param = {
        login_id: $loginId.val(),
    };

    $.ajax({
        url: '/signUpCheck',
        dataType: 'json',
        data: param,
        success(data) {
            $('#msg').html(data.msg);
            if (data.code === 'ok') {
                $('#signUpChk').val('Y');
            }
        },
        error(request, status, error) {
            console.log(
                `code:${request.status}\n` +
                    `message:${request.responseText}\n` +
                    `error:${error}`
            );
        },
    });
};

/**
 *  signUpProc : 회원가입 실행
 */
const signUpProc = () => {
    let msg = '';

    if ($loginId.val() === '') {
        msg = 'Please enter your ID';
        $('#msg').html(msg);
        Alert(msg);
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
        msg = 'Please double check the ID';
        $('#msg').html(msg);
        return;
    }
    if ($userNm.val() === '') {
        msg = 'Input your name, please';
        $('#msg').html(msg);
        $('#userNm').focus();
        return;
    }
    if ($email.val() === '') {
        msg = 'Please enter your e-mail';
        $('#msg').html(msg);
        $('#email').focus();
        return;
    }
    if (!checkEmail($email.val())) {
        msg = 'This is not a valid email';
        $('#msg').html(msg);
        $('#email').focus();
        return;
    }
    if ($userPw.val() === '') {
        msg = 'Please enter a password';
        $('#msg').html(msg);
        $userPw.focus();
        return;
    }
    if (!checkPw($userPw.val())) {
        msg =
            'Please enter a password of at least 8 characters and at least one letter, number, or special character.';
        $('#msg').html(msg);
        $('#userPw').focus();
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
        user_pw: $userPw.val(),
    };

    $.ajax({
        url: '/admin/home/signUpProc.do',
        dataType: 'json',
        data: param,
        success(data) {
            if (data.code === 'ok') {
                AlertMove(data.msg, '/login');
            } else {
                $('#msg').html(data.msg);
            }
        },
        error(request, status, error) {
            console.log(
                `code:${request.status}\n` +
                    `message:${request.responseText}\n` +
                    `error:${error}`
            );
        },
    });
};

/**
 *  login : 로그인 화면 이동
 */
const login = () => {
    $frm.attr('action', '/login');
    $frm.attr('method', 'get');
    $frm.submit();
};

$(document).ready(() => {
    $('#loginId').change(() => {
        $('#signUpChk').val('N');
    });

    // 회원가입 체크 이벤트
    $('#signUpCheckBtn').click(() => {
        signUpCheck();
    });

    // 패스워드 변경 이벤트
    $('#signUpBtn').click(() => {
        signUpProc();
    });

    // 돌아가기 이벤트
    $('#returnBtn').click(() => {
        login();
    });
});
