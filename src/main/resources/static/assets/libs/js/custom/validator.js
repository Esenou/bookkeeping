// Displaying errors
$.validator.setDefaults({
	highlight : function(element) {
		$(element).closest('.form-group').addClass('has-error');
	},
	unhighlight : function(element) {
		$(element).closest('.form-group').removeClass('has-error');
	},
	errorElement : 'span',
	errorClass : 'help-block',
	errorPlacement : function(error, element) {
		if (element.parent('.input-group').length) {
			error.insertAfter(element.parent());
		} else {
			error.insertAfter(element);
		}
	}
});

// Custom rule - value is more than minimum set
$.validator.addMethod('minStrict', function(value, el, param) {
	return parseInt(value) >= param;
});

// Custom rule - value is less than minimum set
$.validator.addMethod('maxStrict', function(value, el, param) {
	return parseInt(value) <= param;
});

//Custom rule - value not equals
$.validator.addMethod("notEqualTo", function(value, el, param) {
	return this.optional(el) || value != param;
});