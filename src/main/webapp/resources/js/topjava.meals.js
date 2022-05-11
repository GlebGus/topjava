const mealAjaxUrl = "ajax/meals/";

const ctx = {
    ajaxUrl: mealAjaxUrl,
    updateTable : function (){
        $.ajax({
            type:"GET",
            url: mealAjaxUrl + "filter",
            data : $("#filter").serialize()
        }).done(updateTableByData())
    }
};
function clearFilter(){
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData());
}
$(function (){
    makeEditable(
        $("datatable").DataTable({
            "paging": false,
            "info": true,
            "columns":[
                {
                    "data" : "date"
                },
                {
                    "data" : "description"
                },
                {
                    "data" : "calories",
                },
                {
                    "defaultContent" : "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0, "desc"
                ]
            ]
        })
    );
});

