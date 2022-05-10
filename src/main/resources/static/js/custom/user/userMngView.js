import Grid from 'tui-grid';

// let pagination;
import { setCodeSelBox } from "../../module/component";
import Page from "../../module/pagination"
import { serializeFormJson } from "../../module/json";
import { setBasicGrid } from "../../module/grid";

let page = new Page(1, false);
let grid;

$(document).ready(function() {
    // //셀렉트 박스(공통코드) : 사용구분 => page
    setCodeSelBox('useYn','USE_YN','ALL','' );


    // const instance = new Grid({
    //     el: document.getElementById('grid'), // Container element
    //     columns: [
    //         {
    //             header: 'Name',
    //             name: 'name'
    //         },
    //         {
    //             header: 'Artist',
    //             name: 'artist'
    //         },
    //         {
    //             header: 'Release',
    //             name: 'release'
    //         },
    //         {
    //             header: 'Genre',
    //             name: 'genre'
    //         }
    //     ],
    //     data: [
    //         {
    //             name: 'Beautiful Lies',
    //             artist: 'Birdy',
    //             release: '2016.03.26',
    //             genre: 'Pop'
    //         }
    //     ]
    // });

    //instance.resetData(newData); // Call API of instance's public method

    // Grid.applyTheme('striped'); // Call API of static method

    //
    //그리드 세팅
    grid =
    grid = setGridLayout();
    //
    // //사용여부, 페이지 개수 변경시 검색
    // $("#useYn, #pagePer").change(function(){
    //     initPaging(search);
    // });
    //
    //검색버튼
    $("#searchBtn").click(function(){
        page = new Page(1, false);
        search();
    });
    //
    // //등록버튼
    // $("#writeBtn").click(function(){
    //     userMngWrite();
    // });
    //
    // //페이징 세팅
    // pagination = setPagination(0,10, pagingCallback);
});

/**
 * search : 조회
 */
const search = () => {

    let params = serializeFormJson('userMngViewFrm');
    params.current_page = page.currentPage;
    params.page_per = 10;
    page.pageInit = true;

    $.ajax({
        url : '/api/user/',
        type: 'POST',
        data: JSON.stringify(params),
        headers: {'Content-Type': 'application/json'},
        success : function (result){
            console.log(result);
            const gridData = result.data;
            // const totalCount = result.totalCount;
            grid.resetData(gridData);
            //
            // setGridClickEvent(grid, "login_id", "user_id", userMngEdit);
            //
            // if(pageInit === false){
            //     pageInit = true;
            //     pagination = setPagination(totalCount,params.page_per, pagingCallback);
            // }
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
//
// /**
//  * pagingCallback : 페이징 콜백
//  */
// const pagingCallback = (return_page) => {
//     current_page = return_page;
//     search();
// }