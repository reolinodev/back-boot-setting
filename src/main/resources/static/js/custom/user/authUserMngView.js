import { setCodeSelBox, setCommSelBox } from "../../module/component";


$(document).ready(function() {

    //셀렉트 박스(공통코드) : 권한구분 => page
    setCodeSelBox('viewAuthRole','AUTH_ROLE','ALL','' );

    //셀렉트 박스(일반) : 권한명(권한 아이디로 조회) => page
    setCommSelBox('viewAuthId','','','ALL', '', '', '');

    /**
     * 권한구분 변경시 권한명 셀렉트 박스의 옵션을 갱신한다
     */
    $("#viewAuthRole").change(function(){
        const authRole =  $("#viewAuthRole").val();

        if(authRole === ''){
            setCommSelBox('viewAuthId','','','ALL', '', '', '');
        }else{
            let params= {
                auth_role : authRole
            }
            let option = {
                oTxt: 'auth_nm',
                oVal: 'auth_id'
            }
            setCommSelBox('viewAuthId','/api/auth/role','POST', '', '', params, option);
        }
    });

    /**
     * 사용자 권한 등록
     */
    $("#writeBtn").click(function(){
        location.href = '/user/authUserMng/write';
    });
});