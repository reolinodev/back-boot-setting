import { setCodeSelBox } from "../../module/component";
import Page, { setPagination } from "../../module/pagination"
import { serializeFormJson } from "../../module/json";
import { setBasicGrid, setGridClickEvent } from "../../module/grid";
import { checkKr } from "../../module/validation";
import { Alert } from "../../module/alert";

let page = new Page(1, false, 10, 0);
let grid;
let pagination;

$(document).ready(function() {
    //셀렉트 박스(공통코드) : 권한구분 => AUTH_ROLE
    setCodeSelBox('viewAuthRole','AUTH_ROLE','ALL','' );

    //셀렉트 박스(공통코드) : 사용구분 => USE_YN
    setCodeSelBox('viewUseYn','USE_YN','ALL','' );

    //그리드 세팅
    grid = setGridLayout();

    //페이징 세팅
    pagination = setPagination(page, pagingCallback);

    //검색버튼
    $("#searchBtn").click(function(){
        pageInit();
        search();
    });


    //사용여부, 권한 구분 변경시 검색
    $("#viewUseYn, #viewAuthRole").change(function(){
        pageInit();
        search();
    });
    
    //사페이지 개수 변경시 검색
    $("#pagePer").change(function(){
        pageInit();
        pagination = setPagination(page, pagingCallback);
        search();
    });

    //등록버튼 클릭시 모달을 초기화한다.
    $("#writeBtn").click(function(){

        //셀렉트 박스(공통코드) : 권한구분 => AUTH_ROLE
        setCodeSelBox('writeAuthRole','AUTH_ROLE','','' );

        initAuthMngWrite();

        window.$('#authMngWrite').modal('show');
    });

    //권한을 등록한다.
    $("#insertBtn").click(function(){
        insertProc();
    });

    //권한을 수정한다.
    $("#editBtn").click(function(){
        editProc();
    });

});

const pageInit = () => {
    page = new Page(1, false, Number($("#pagePer").val()),  0);
}

/**
 * search : 조회
 */
const search = () => {

    let params = serializeFormJson('authMngViewFrm');
    params.current_page = page.currentPage;
    params.page_per = page.pagePer;

    $.ajax({
        url : '/api/auth/',
        type: 'POST',
        data: JSON.stringify(params),
        headers: {'Content-Type': 'application/json'},
        success : function (result){
            const gridData = result.data;
            page.totalCount = result.total;
            grid.resetData(gridData);

            setGridClickEvent(grid, "auth_nm", "auth_id", authMngEdit);

            if(page.pageInit === false){
                pagination.reset(result.total);
                page.pageInit = true;
            }
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
        {header: 'SEQ', name: 'auth_id', width :100, align : 'center', hidden : true},
        {header: 'Auth Role', name: 'auth_role_nm', align : 'center'},
        {header: 'Auth Nm', name: 'auth_nm', align : 'center'},
        {header: 'Auth Value', name: 'auth_val', align : 'center'},
        {header: 'Use', name: 'use_yn_nm', width :150, align : 'center'}
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

const $writeAuthNm = $('#writeAuthNm');
const $writeAuthVal = $('#writeAuthVal');
const $writeAuthRole = $('#writeAuthRole');
const $writeOrd = $('#writeOrd');
const $writeBigo = $('#writeBigo');


/**
 * initAuthMngWrite : 등록화면의 값 초기화
 */
const initAuthMngWrite = () => {
    $writeAuthNm.val('');
    $writeAuthVal.val('');
    $writeOrd.val('');
    $writeBigo.val('');
    $("#writeMsg").html('');
}

/**
 *  insertProc : 권한 등록
 */
const insertProc = () => {
    let msg = '';

    if (checkKr($writeAuthVal.val())) {
        msg = 'You cannot enter Korean characters in the value';
        $('#writeMsg').html(msg);
        $writeAuthVal.focus();
        return;
    }

    const param = {
        auth_nm: $writeAuthNm.val(),
        auth_val:  $writeAuthVal.val(),
        auth_role: $writeAuthRole.val(),
        ord: $writeOrd.val(),
        bigo: $writeBigo.val(),
    };

    $.ajax({
        url: "/api/auth/",
        type: 'PUT',
        data: JSON.stringify(param),
        headers: {'Content-Type': 'application/json'},
    }).then((data) => {
        if (data.header.resultCode === 'ok') {
            Alert(data.header.message);
            search();
            closeModal();
        } else {
            $('#writeMsg').html(data.header.message);
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
                    $('#writeMsg').html(message);
                }
            }else {
                const data = request.responseJSON.header;
                $('#writeMsg').html(data.message);
            }
        }
    });
};

const $editAuthId = $('#editAuthId');
const $editAuthNm = $('#editAuthNm');
const $editAuthVal = $('#editAuthVal');
const $editAuthRole = $('#editAuthRole');
const $editOrd = $('#editOrd');
const $editBigo = $('#editBigo');
const $editUseYn = $('#editUseYn');

/**
 * authMngEdit : 권한 수정 화면 호출
 */
const authMngEdit = (auth_id) =>  {
    $.ajax({
        url: '/api/auth/info/'+auth_id,
        type: 'GET',
        headers: {'Content-Type': 'application/json'},
        success : function (result){
            setEditData(result.data)
        },
        error : function (request, status, error){
            console.log('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

/**
 * setEditData : 에디트 데이터 셋
 */
const setEditData = (data) => {

    $editAuthId.val(data.auth_id);
    $editAuthNm.val(data.auth_nm);
    $editAuthVal.val(data.auth_val);
    $editOrd.val(data.ord);
    $editBigo.val(data.bigo);

    setCodeSelBox('editAuthRole','AUTH_ROLE','',data.auth_role);
    setCodeSelBox('editUseYn','USE_YN','',data.use_yn);

    window.$('#authMngEdit').modal('show');
}

/**
 *  editProc : 권한 수정
 */
const editProc = () => {

    const param = {
        auth_id: $editAuthId.val(),
        auth_nm: $editAuthNm.val(),
        auth_role: $editAuthRole.val(),
        ord: $editOrd.val(),
        bigo: $editBigo.val(),
        use_yn: $editUseYn.val()
    };

    $.ajax({
        url: "/api/auth/info/"+$editAuthId.val(),
        type: 'PUT',
        data: JSON.stringify(param),
        headers: {'Content-Type': 'application/json'},
    }).then((data) => {
        if (data.header.resultCode === 'ok') {
            Alert(data.header.message);
            search();
            closeModal();
        } else {
            $('#editMsg').html(data.header.message);
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
                    $('#editMsg').html(message);
                }
            }else {
                const data = request.responseJSON.header;
                $('#editMsg').html(data.message);
            }
        }
    });
};


/**
 * closeModal : 모달 닫기
 */
const closeModal = () => {
    window.$('#authMngWrite').modal('hide');
    window.$('#authMngEdit').modal('hide');
}


