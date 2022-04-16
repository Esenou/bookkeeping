/* ------------------------------------------------------------------------------
*
*  # Datatables API
*
*  Specific JS code additions for datatable_api.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Table setup
    // ------------------------------

    // Setting datatable defaults
	// moved to master.jsp to make i18n
//    $.extend( $.fn.dataTable.defaults, {
//        autoWidth: false,
//        dom: '<"datatable-header"fl><"datatable-scroll"t><"datatable-footer"ip>',
//        retrieve: true,
//        language: {
//        	decimal: '.',
//        	thousands: ',',
//            search: '<span>Filter:</span> _INPUT_',
//            lengthMenu: '<span>Show:</span> _MENU_',
//            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
//        },
//        drawCallback: function () {
//            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
//        },
//        preDrawCallback: function() {
//            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
//        }
//    });

    // Basic responsive configuration
    $('.datatable-responsive').DataTable({responsive:true});

    // Custom responsive configuration
    $('.datatable-custom').DataTable({responsive:true, aaSorting:[]});

    // Custom responsive with search inputs
    $('.datatable-column-search-inputs').DataTable({responsive:true, aaSorting:[], "aoColumns": [null,null,null,null,null,null,{"bSortable": false}],
        buttons: ['copy', 'csv', 'excel', 'print']});

    // Custom responsive with search inputs
    $('.datatable-column-search-inputs-waittran').DataTable({responsive:true, aaSorting:[], "aoColumns": [{"bSortable": false},null,null,null,null,null,null,{"bSortable": false}],
    	buttons: ['copy', 'csv', 'excel', 'print']});

    // tdaccount responsive configuration
    var dtaccounts = $('.datatable-tdaccounts').DataTable({responsive:true, aaSorting:[3, "desc"]});

    // credit card transaction inquiry
    var cctraninq = $('.datatable-cctraninq').DataTable({responsive:true, aaSorting:[0, "desc"]});

    // credit card transaction inquiry
    var ccpndtran = $('.datatable-ccpndtran').DataTable({responsive:true, aaSorting:[0, "desc"]});
    
    // credit card transaction inquiry
    var ccstmtinq = $('.datatable-ccstmtinq').DataTable({responsive:true, aaSorting:[0, "desc"]});
    
    // credit card next statement transactions
    var ccnextstmt = $('.datatable-ccnextstmt').DataTable({responsive:true, aaSorting:[1, "desc"]});
    
    // account history
    var acchistory = $('.datatable-acchistory').DataTable({responsive:true, aaSorting:[0, "desc"],
	    	"aoColumns": [
	    		{"bSortable": true},
	    		{"bSortable": false},
	    		{"bSortable": false},
	    		{"bSortable": false},
	    		{"bSortable": false},
	    		{"bSortable": false},
	    		{"bSortable": false},
	    	],
	    	"columnDefs": [{"targets": [0], "visible": false, "searchable": false}]
    	});
    
    // Basic datatable implementation without responsive
    $('.datatable-basic').DataTable();
    
    $('.datatable-selection-single').DataTable();

	$('.datatable-fixed-left').DataTable({
	    columnDefs: [
	        { 
	            orderable: false,
	            targets: [9]
	        },
	        { 
	            width: "5%",
	            targets: [0]
	        },
	        { 
	            width: "15%",
	            targets: [1]
	        },
	        { 
	            width: "10%",
	            targets: [5, 9]
	        },
	        { 
	            width: "10%",
	            targets: [2, 4]
	        }
	    ],
	    scrollX: true,
	    scrollY: '350px',
	    scrollCollapse: true
	});    
    
    // Single row selection
    $('.datatable-selection-single tbody').on('click', 'tr', function() {
        if ($(this).hasClass('success')) {}
        else {
        	// Remove success class from row only in current table
        	var singleSelect = $(this).closest('.datatable-selection-single').DataTable();
            singleSelect.$('tr.success').removeClass('success');
            $(this).addClass('success');
        }
    });
   
    // Multiple rows selection
    $('.datatable-selection-multiple').DataTable();
    $('.datatable-selection-multiple tbody').on('click', 'tr', function() {
        $(this).toggleClass('success');
    });


    // Individual column searching with text inputs
    $('.datatable-column-search-inputs tfoot td').not(':last-child').each(function () {
        var title = $('.datatable-column-search-inputs thead th').eq($(this).index()).text();
        $(this).html('<input type="text" class="form-control input-sm" placeholder="Search '+title+'" />');
    });
    var table = $('.datatable-column-search-inputs').DataTable();
    table.columns().every( function () {
        var that = this;
        $('input', this.footer()).on('keyup change', function () {
            that.search(this.value).draw();
        });
    });

    // Individual column searching with selects
    $('.datatable-column-search-selects').DataTable({
        initComplete: function () {
            this.api().columns().every( function() {
                var column = this;
                var select = $('<select class="filter-select" data-placeholder="Filter"><option value=""></option></select>')
                    .appendTo($(column.footer()).not(':last-child').empty())
                    .on('change', function() {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    });
 
                column.data().unique().sort().each( function (d, j) {
                    select.append('<option value="'+d+'">'+d+'</option>')
                });
            });
        }
    });

    // External table additions
    // ------------------------------

    // Add placeholder to the datatable filter option
    $('.dataTables_filter input[type=search]').attr('placeholder','Type to filter...');


    // Enable Select2 select for the length option
    $('.dataTables_length select').select2({
        minimumResultsForSearch: "-1"
    });


    //Enable Select2 select for individual column searching
    $('.filter-select').select2({
        width: '100%'
    });
    
    // Responsive 
    $('.table-togglable').footable();
    
});
function datatable(colNum){
	$('.datatable-responsive-custom').DataTable({
		columnDefs: [{ 
            orderable: false,
            width: '100px',
            targets: [ colNum]
        }]
    });
}
