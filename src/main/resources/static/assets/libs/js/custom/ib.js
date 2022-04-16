//pass password inputs on every form submission
$("form").submit(function(e) {
	var allPasswords = $('[type=password]');

	var pwdIds = [];
	$.each(allPasswords, function(i, val) {
		pwdIds.push(allPasswords[i].id);
	});
	$.ajax({
		url : path + "/setPassword",
		data : {
			ids : pwdIds.join(";")
		},
		method: "POST"
	});
});

// Escape characters for Javascript parameters
function escapeJS(str){
	return str.replace(/([;&,\.\+\*\~':"\!\^#$%@\[\]\(\)=>\|])/g, '\\$1');
}

//Block whole page
function blockPage(){
	$.blockUI({ 
        message: '<i class="icon-spinner4 spinner"></i>',
        overlayCSS: {
            backgroundColor: '#1b2024',
            opacity: 0.8,
            cursor: 'wait'
        },
        css: {
            border: 0,
            color: '#fff',
            padding: 0,
            backgroundColor: 'transparent'
        }
    });
}

//Unblock whole page
function unblockPage(){
	$.unblockUI();
}