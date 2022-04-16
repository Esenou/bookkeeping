if ($(window).width() > 767) {

	var numKey = [];
	while(numKey.length < 10){
	    var randomnumber = Math.ceil(Math.random()*10)
	    randomnumber = (randomnumber == 10 ? 0 : randomnumber);
	    if (numKey.indexOf(randomnumber) > -1) continue;
	    numKey[numKey.length] = randomnumber;
	}

	$('.txtKeyboard').keyboard({
		layout : 'custom',
		restrictInput : false, 
	    preventPaste : false,  
	    usePreview: false,
	    autoAccept : true,
	    tabNavigation: true,
	    reposition: true,
	    position: {
	    	of: null,
	    	my: 'left+5px top+5px',
	        at: 'right bottom',
	        at2: 'right bottom'
	    },
	    customLayout: {
	    	'normal': [
				'1 2 3 4 5 6 7 8 9 0',
				'q w e r t y u i o p',
				'a s d f g h j k l {b}',
				'{s} z x c v b n m {a}'
			],
			'shift': [
				'! @ # $ % ^ & * ( )',
				'Q W E R T Y U I O P',
				'A S D F G H J K L {b}',
				'{s} Z X C V B N M {a}'
			]	             
	     },
		css: {
	        // input & preview
	        input: 'form-control input-sm',
	        // keyboard container
	        container: 'center-block dropdown-menu full-keyboard', // jumbotron
	        // default state
	        buttonDefault: 'btn btn-default',
	        // hovered button
	        buttonHover: 'btn-primary',
	        // Action keys (e.g. Accept, Cancel, Tab, etc);
	        // this replaces "actionClass" option
	        buttonAction: 'active',
	        // used when disabling the decimal button {dec}
	        // when a decimal exists in the input area
	        buttonDisabled: 'disabled'
	    }
	});
	$('.txtKeyboardRequired').keyboard({
		layout : 'custom',
		restrictInput : true, 
	    preventPaste : true,  
	    usePreview: false,
	    autoAccept : true,
	    tabNavigation: true,
	    reposition: true,
	    position: {
	    	of: null,
	    	my: 'left+5px top+5px',
	        at: 'right bottom',
	        at2: 'right bottom'
	    },
	    customLayout: {
	    	'normal': [
				'1 2 3 4 5 6 7 8 9 0',
				'q w e r t y u i o p',
				'a s d f g h j k l {b}',
				'{s} z x c v b n m {a}'
			],
			'shift': [
				'! @ # $ % ^ & * ( )',
				'Q W E R T Y U I O P',
				'A S D F G H J K L {b}',
				'{s} Z X C V B N M {a}'
			]	             
	     },
		css: {
	        // input & preview
	        input: 'form-control input-sm',
	        // keyboard container
	        container: 'center-block dropdown-menu full-keyboard', // jumbotron
	        // default state
	        buttonDefault: 'btn btn-default',
	        // hovered button
	        buttonHover: 'btn-primary',
	        // Action keys (e.g. Accept, Cancel, Tab, etc);
	        // this replaces "actionClass" option
	        buttonAction: 'active',
	        // used when disabling the decimal button {dec}
	        // when a decimal exists in the input area
	        buttonDisabled: 'disabled'
	    }
	});
	$('.numKeyboard').keyboard({
		 layout : 'custom',
	     restrictInput : true, 
	     preventPaste : false,  
	     usePreview: false,
	     autoAccept : true,
	     tabNavigation: true,
	     reposition: true,
	     position: {
	     	of: null,
	    	my: 'left+5px top+5px',
	        at: 'right bottom',
	        at2: 'right bottom'
	     },
	     customLayout: {
	         'default': [
	        	 numKey[0] + ' ' + numKey[1] + ' ' + numKey[2],
	        	 numKey[3] + ' ' + numKey[4] + ' ' + numKey[5],
	        	 numKey[6] + ' ' + numKey[7] + ' ' + numKey[8],
	        	 '{a} ' + numKey[9] + ' {b}']          
	     },
	     css: {
		        // input & preview
		        input: 'form-control input-sm',
		        // keyboard container
		        container: 'center-block dropdown-menu num-keyboard', // jumbotron
		        // default state
		        buttonDefault: 'btn btn-default',
		        // hovered button
		        buttonHover: 'btn-primary',
		        // Action keys (e.g. Accept, Cancel, Tab, etc);
		        // this replaces "actionClass" option
		        buttonAction: 'active',
		        // used when disabling the decimal button {dec}
		        // when a decimal exists in the input area
		        buttonDisabled: 'disabled'
		    }
	});
}