// let current_page=1;
// let pageInit = false;

export default class Page {
    constructor(currentPage, pageInit) {
        this.currentPage = currentPage;
        this.pageInit = pageInit;
    }
}

/**
 * setPagination : 페이징 세팅
 * total_count : 총 카운트, perPage : 페이지별 갯수, callBackFunc : 콜백
 */
// const setPagination = (total_count, perPage, callBackFunc) => {
//     const pagination = new tui.Pagination('pagination', {
//         totalItems: total_count,
//         itemsPerPage: perPage,
//         visiblePages: 10
//     });
//
//     pagination.on('beforeMove', function(eventData) {
//         callBackFunc(eventData.page);
//     });
//
//     pagination.on('afterMove', function(eventData) {
//         callBackFunc(eventData.page);
//     });
//
//     return pagination;
// }

