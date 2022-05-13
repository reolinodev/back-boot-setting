import Grid from 'tui-grid';

/**
 * setBasicGrid : 기본 그리드 생성
 * columns: 컬럼, gridData : 데이터
 */
export function setBasicGrid (columns, gridData) {

    const grid = new Grid({
        el: document.getElementById('grid'),
        data: gridData,
        scrollX: false,
        scrollY: false,
        columns: columns
    });

    Grid.applyTheme('striped');

    grid.resetData(gridData);

    return grid;
}
/**
 * setGridClickEvent : 그리드 클릭 이벤트
 * grid: 그리드, select_column : 선택할 컬럼, return_column: 반환할 컬럼, callbackFunc: 콜백
 */
export function setGridClickEvent(grid, select_column, return_column, callbackFunc) {

    grid.getData().forEach(row => {
        grid.addCellClassName(row.rowKey, select_column, 'cell-click');
    });

    grid.on('focusChange', ev => {
        if(ev.columnName === select_column){
            callbackFunc(grid.getValue(ev.rowKey, return_column));
        }
    });
}