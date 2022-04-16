$(function() {


    // Configuration
    // -------------------------

    // Add options
    i18n.init({
    	resGetPath: path + '/resources/locales/__lng__.json',
    	fallbackLng: false,
    	load: 'unspecific',
    	useCookie: false,
    	lng: $("meta[name='lang']").attr('content')
    },
    function () {
        $('body').i18n(); // Init
    });



    // Change languages in dropdown
    // -------------------------

    // English
    if(i18n.lng() === "en" || i18n.lng() === "en-US") {

        // Set active class
        $('.english').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.english').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
    }


    // Russian
    if(i18n.lng() === "ru") {

        // Set active class
        $('.russian').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.russian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
    }

    
    // Kyrgyz
    if(i18n.lng() === "kg") {
    	
    	// Set active class
    	$('.kyrgyz').parent().addClass('active');
    	
    	// Change language in dropdown
    	$('.language-switch').children('.dropdown-toggle').html(
    			$('.language-switch').find('.kyrgyz').html() + ' <i class="caret" />'
    	).children('img').addClass('position-left');
    }
    
    /* Language change will reload page
     
    // Change languages in navbar
    // -------------------------

    // Define switcher container
    var switchContainer = $('.language-switch');


    // English
    $('.english').on('click', function () {

        // Set language
        $.i18n.setLng('en', function() {
            $('body').i18n();
        });

        // Change lang in dropdown
        switchContainer.children('.dropdown-toggle').html(
            $('.english').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');

        // Set active class
        switchContainer.find('li').removeClass('active');
        $('.english').parent().addClass('active');
    });


    // Russian
    $('.russian').on('click', function () {

        // Set language
        $.i18n.setLng('ru', function() {
            $('body').i18n();
        });

        // Change lang in dropdown
        switchContainer.children('.dropdown-toggle').html(
            $('.russian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
        
        // Set active class
        switchContainer.find('li').removeClass('active');
        $('.russian').parent().addClass('active');
    });

    
    // Kyrgyz
    $('.kyrgyz').on('click', function () {
    	
    	// Set language
    	$.i18n.setLng('kg', function() {
    		$('body').i18n();
    	});
    	
    	// Change lang in dropdown
    	switchContainer.children('.dropdown-toggle').html(
    			$('.kyrgyz').html() + ' <i class="caret" />'
    	).children('img').addClass('position-left');
    	
    	// Set active class
    	switchContainer.find('li').removeClass('active');
    	$('.kyrgyz').parent().addClass('active');
    });
    
    */
    
});
