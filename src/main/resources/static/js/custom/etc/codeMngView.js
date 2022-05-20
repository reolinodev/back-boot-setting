import {setCodeSelBox, setCommSelBox} from "../../module/component";
import Page, { setPagination } from "../../module/pagination"
import { serializeFormJson } from "../../module/json";
import {
    setBasicGrid,
    getCheckedRows,
    setCheckBoxGridId, setGridClickEvent,
} from "../../module/grid";
import { Alert } from "../../module/alert";

let page = new Page(1, false, 10, 0);
let grid;
let grid2;
let pagination;
let selectedData = [];

$(document).ready(function() {
    setCodeSelBox('useYn','USE_YN','','Y' );

    setCodeSelBox('pagePer','PAGE_PER','','10' );

    //그리드 세팅
    grid = setGridLayout();
    grid2 = setGridLayout2();

    //페이징 세팅
    pagination = setPagination(page, pagingCallback);

    //검색버튼 클릭시 검색
    $("#searchGrpBtn").click(function(){
        pageInit();
        search_grp();
    });

    //페이지 개수 변경시 검색
    $("#pagePer").change(function(){
        pageInit();
        pagination = setPagination(page, pagingCallback);
        search_grp();
    });


    //코드 그룹추가 버튼 클릭 이벤트(입력화면 호출)
    $("#codeGrpAddBtn").click(function(){
        $("#writeCodeGrpNm").val('');
        $("#writeCodeGrpVal").val('');
        window.$('#codeGrpWrite').modal('show');
    });

    //코드 그룹 입력화면 버튼 클릭 이벤트
    $("#writeCodeGrpBtn").click(function(){
        insertGrpProc();
    });

    //코드 그룹 수정화면 버튼 클릭 이벤트
    $("#editCodeGrpBtn").click(function(){
        editGrpProc();
    });

    //사용여부 변경시 검색
    $("#useYn").change(function(){
        pageInit();
        search_grp();
    });





    //삭제 버튼 클릭 이벤트
    $("#delBtn").click(function(){
        deleteAuthUser();
    });


    //저장 버튼 클릭 이벤트
    $("#saveBtn").click(function(){
        // insertProc();
    });

});

//페이징 초기화
const pageInit = () => {
    page = new Page(1, false, Number($("#pagePer").val()),  0);
}

/**
 * search : 조회
 */
const search_grp = () => {

    let params = serializeFormJson('codeGrpFrm');
    params.current_page = page.currentPage;
    params.page_per = page.pagePer;

    $.ajax({
        url : '/api/codeGrp/',
        type: 'POST',
        data: JSON.stringify(params),
        headers: {'Content-Type': 'application/json'},
        success : function (result){
            const gridData = result.data;
            page.totalCount = result.total;
            grid.resetData(gridData);

            if(page.pageInit === false){
                pagination.reset(result.total);
                page.pageInit = true;
            }

            setGridClickEvent(grid, "code_grp_nm", "code_grp_id", search);
            setGridClickEvent(grid, "code_grp_val", "code_grp_id", codeMngEdit);
        },
        error : function (request, status, error){
            console.log('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

/**
 * setGridLayout : Choose User 그리드 구성
 */
const setGridLayout = () => {
    const columns = [
        {header: 'SEQ', name: 'code_grp_id', align : 'center', hidden : true},
        {header: 'Name', name: 'code_grp_nm', align : 'center'},
        {header: 'Code', name: 'code_grp_val', align : 'center'},
    ];
    const gridData = [];

    return setBasicGrid(columns,gridData);
}

/**
 * setGridLayout2 : Selected User 그리드 구성
 */
const setGridLayout2 = () => {
    const columns = [
        {header: 'SEQ', name: 'user_id', align : 'center', hidden : true},
        {header: 'ID', name: 'login_id', align : 'center'},
        {header: 'Name', name: 'user_nm', align : 'center'},
        {header: 'Email', name: 'email', align : 'center'},
    ];
    const gridData = [];
    return setCheckBoxGridId(columns,gridData, 'grid2');
}

/**
 * pagingCallback : 페이징 콜백
 */
const pagingCallback = (returnPage) => {
    page.currentPage = returnPage;
    search_grp();
}

const $writeCodeGrpNm = $("#writeCodeGrpNm");
const $writeCodeGrpVal = $("#writeCodeGrpVal");

/**
 *  insertGrpProc : 코드 그룹 등록
 */
const insertGrpProc = () => {

    let msg = '';

    if ($writeCodeGrpNm.val() === '') {
        msg = 'Please enter name.';
        $('#writeMsg').html(msg);
        $writeCodeGrpNm.focus();
        return;
    }
    if ($writeCodeGrpVal.val() === '') {
        msg = 'Please enter code';
        $('#writeMsg').html(msg);
        $writeCodeGrpVal.focus();
        return;
    }

    const params = {
        code_grp_nm: $("#writeCodeGrpNm").val(),
        code_grp_val:  $("#writeCodeGrpVal").val(),
    };

    $.ajax({
        url: "/api/codeGrp/",
        type: 'PUT',
        data: JSON.stringify(params),
        headers: {'Content-Type': 'application/json'},
    }).then((data) => {
        if (data.header.resultCode === 'ok') {
            Alert(data.header.message);
            pageInit();
            search_grp();
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

/**
 *  codeMngEdit : 코드 그룹 수정 호출
 */
const codeMngEdit = (code_grp_id) => {

    // editUseYn
    $.ajax({
        url: '/api/codeGrp/info/'+code_grp_id,
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

const $editCodeGrpNm = $("#editCodeGrpNm");
const $editCodeGrpId = $("#editCodeGrpId");
const $editCodeGrpVal = $("#editCodeGrpVal");

/**
 *  setEditData : 데이터 매핑 및 모달 오픈
 */
const setEditData = (data) => {
    setCodeSelBox('editUseYn','USE_YN','',data.use_yn);

    $editCodeGrpId.val(data.code_grp_id);
    $editCodeGrpNm.val(data.code_grp_nm);
    $editCodeGrpVal.val(data.code_grp_val);

    window.$('#codeGrpEdit').modal('show');
}

/**
 *  editGrpProc : 코드 그룹 수정
 */
const editGrpProc = () => {

    let msg = '';

    if ($editCodeGrpNm.val() === '') {
        msg = 'Please enter name.';
        $('#editMsg').html(msg);
        $editCodeGrpNm.focus();
        return;
    }

    const params = {
        code_grp_id: $editCodeGrpId.val(),
        code_grp_nm: $editCodeGrpNm.val(),
        code_grp_val:  $editCodeGrpVal.val(),
        use_yn:  $("#editUseYn").val(),
    };

    $.ajax({
        url: "/api/codeGrp/info/"+$editCodeGrpId.val(),
        type: 'PUT',
        data: JSON.stringify(params),
        headers: {'Content-Type': 'application/json'},
    }).then((data) => {
        if (data.header.resultCode === 'ok') {
            Alert(data.header.message);
            pageInit();
            search_grp();
            window.$('#codeGrpEdit').modal('hide');
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
 * search : 조회
 */
const search = (code_grp_id) => {
    alert(code_grp_id);
    // let params = serializeFormJson('codeGrpFrm');
    // params.current_page = page.currentPage;
    // params.page_per = page.pagePer;
    //
    // $.ajax({
    //     url : '/api/codeGrp/',
    //     type: 'POST',
    //     data: JSON.stringify(params),
    //     headers: {'Content-Type': 'application/json'},
    //     success : function (result){
    //         const gridData = result.data;
    //         page.totalCount = result.total;
    //         grid.resetData(gridData);
    //
    //         if(page.pageInit === false){
    //             pagination.reset(result.total);
    //             page.pageInit = true;
    //         }
    //
    //         setGridClickEvent(grid, "code_grp_nm", "code_grp_id", search);
    //         setGridClickEvent(grid, "code_grp_val", "code_grp_id", codeMngEdit);
    //     },
    //     error : function (request, status, error){
    //         console.log('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    //     }
    // });
}







/**
 * setAuthUser : 사용자 선택
 */
const setAuthUser = () => {
    if($("#authId").val() === ''){
        Alert('Please Choose auth');
        return;
    }

    const checkedRows =  getCheckedRows(grid);
    if(checkedRows.length === 0 ){
        Alert('Please Check Row');
        return;
    }

    setSelectedUser(checkedRows);
}

/**
 * setSelectedUser : Selected User 그리드에 데이터 매핑핑
 */
const setSelectedUser = (list) => {

    for (const obj of list) {
        selectedData.push(obj);
    }

    selectedData = removeDuplicateItem(selectedData);

    grid2.resetData(selectedData);
}
/**
 * removeDuplicateItem : 사용자 아이디 중복 항목 제거
 */
const removeDuplicateItem = (data) => {
    let uniqueData;
    uniqueData = data.filter((character, idx, arr) => {
        return arr.findIndex((item) => item.user_id === character.user_id) === idx
    });

    return uniqueData;
}

/**
 * deleteAuthUser : 사용자 삭제
 */
const deleteAuthUser = () => {
    grid2.removeCheckedRows();
    selectedData = grid2.getData();
}


/**
 *  refreshSearch :  재검색하기
 */

const refreshSearch = () => {
    pageInit();
    $("#searchStr").val("");
    selectedData = [];
    grid2.resetData(selectedData);
    search_grp();
}
