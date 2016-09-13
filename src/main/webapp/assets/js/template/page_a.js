function queryDataFunc(queryApiUrl, addApiUrl, editApiUrl, deleteApiUrl) {
    if (!formObject.valid()) return;
    QueryForm.initTable(queryApiUrl);
    QueryForm.initItemAdd(addApiUrl);
    QueryForm.initItemEdit(editApiUrl);
    QueryForm.initItemDelete(deleteApiUrl);
}
