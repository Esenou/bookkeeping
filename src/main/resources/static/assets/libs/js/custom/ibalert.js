function showAlert(type, layout, title, text) {
	switch (layout) {
		case 'stack-custom-top':
			show_stack_custom_top(type, title, text);
			break;
		case 'stack-custom-bottom':
			show_stack_custom_bottom(type, title, text);
			break;
		case 'stack-custom-center':
			show_stack_custom_center(type, title, text);
			break;
		case 'stack-top-left':
			show_stack_top_left(type, title, text);
			break;
		case 'stack-bottom-left':
			show_stack_bottom_left(type, title, text);
			break;
		case 'stack-top-right':
			show_stack_top_right(type, title, text);
			break;
		case 'stack-bottom-right':
			show_stack_bottom_right(type, title, text);
			break;
	}
}

// Define directions
var stack_top_left = {
	"dir1" : "down",
	"dir2" : "right",
	"push" : "top"
};
var stack_bottom_left = {
	"dir1" : "right",
	"dir2" : "up",
	"push" : "top"
};
var stack_bottom_right = {
	"dir1" : "up",
	"dir2" : "left",
	"firstpos1" : 25,
	"firstpos2" : 25
};
var stack_custom_left = {
	"dir1" : "right",
	"dir2" : "down"
};
var stack_custom_right = {
	"dir1" : "left",
	"dir2" : "up",
	"push" : "top"
};
var stack_custom_top = {
	"dir1" : "down",
	"dir2" : "right",
	"push" : "top",
	"spacing1" : 1
};
var stack_custom_bottom = {
	"dir1" : "up",
	"dir2" : "right",
	"spacing1" : 1
};
var stack_custom_center = {
	"dir1" : "right",
	"dir2" : "down"
};

//Top
function show_stack_custom_top(type, title, text) {
	var opts = {
		title : title,
		text : text,
		width : "100%",
		cornerclass: "no-border-radius",
        addclass: "stack-custom-top bg-primary",
		stack : stack_custom_top
	};
	switch (type) {
	case 'error':
		opts.addclass = "stack-custom-top bg-danger";
		opts.type = "error";
		break;

	case 'info':
		opts.addclass = "stack-custom-top bg-info";
		opts.type = "info";
		break;

	case 'success':
		opts.addclass = "stack-custom-top bg-success";
		opts.type = "success";
		break;
	}
	new PNotify(opts);
}

//Bottom
function show_stack_custom_bottom(type, title, text) {
    var opts = {
    	title : title,
    	text : text,
        width: "100%",
        cornerclass: "no-border-radius",
        addclass: "stack-custom-bottom bg-primary",
        stack: stack_custom_bottom
    };
    switch (type) {
        case 'error':
        opts.addclass = "stack-custom-bottom bg-danger";
        opts.type = "error";
        break;

        case 'info':
        opts.addclass = "stack-custom-bottom bg-info";
        opts.type = "info";
        break;

        case 'success':
        opts.addclass = "stack-custom-bottom bg-success";
        opts.type = "success";
        break;
    }
    new PNotify(opts);
}

// Center
function show_stack_custom_center(type, title, text) {
	var opts = {
		title : title,
		text : text,
		width : "70%",
		addclass : "stack-custom-center bg-primary",
		stack : stack_custom_center
	};
	switch (type) {
	case 'error':
		opts.addclass = "stack-custom-center bg-danger";
		opts.type = "error";
		break;

	case 'info':
		opts.addclass = "stack-custom-center bg-info";
		opts.type = "info";
		break;

	case 'success':
		opts.addclass = "stack-custom-center bg-success";
		opts.type = "success";
		break;
	}
	new PNotify(opts);
}

//Top left
function show_stack_top_left(type,title, text) {
    var opts = {
    	title : title,
    	text : text,
        addclass: "stack-top-left bg-primary",
        stack: stack_top_left
    };
    switch (type) {
        case 'error':
        opts.addclass = "stack-top-left bg-danger";
        opts.type = "error";
        break;

        case 'info':
        opts.addclass = "stack-top-left bg-info";
        opts.type = "info";
        break;

        case 'success':
        opts.addclass = "stack-top-left bg-success";
        opts.type = "success";
        break;
    }
    new PNotify(opts);
}

//Bottom left
function show_stack_bottom_left(type, title, text) {
    var opts = {
    	title : title,
        text : text,
        addclass: "stack-bottom-left bg-primary",
        stack: stack_bottom_left
    };
    switch (type) {
        case 'error':
        opts.addclass = "stack-bottom-left bg-danger";
        opts.type = "error";
        break;

        case 'info':
        opts.addclass = "stack-bottom-left bg-info";
        opts.type = "info";
        break;

        case 'success':
        opts.addclass = "stack-bottom-left bg-success";
        opts.type = "success";
        break;
    }
    new PNotify(opts);
}

//Bottom right
function show_stack_bottom_right(type, title, text) {
    var opts = {
    	title : title,
        text : text,
        addclass: "stack-bottom-right bg-primary",
        stack: stack_bottom_right
    };
    switch (type) {
        case 'error':
        opts.addclass = "stack-bottom-right bg-danger";
        opts.type = "error";
        break;

        case 'info':
        opts.addclass = "stack-bottom-right bg-info";
        opts.type = "info";
        break;

        case 'success':
        opts.addclass = "stack-bottom-right bg-success";
        opts.type = "success";
        break;
    }
    new PNotify(opts);
}

//Top right
function show_stack_bottom_right(type, title, text) {
	var opts = {
    	title : title,
        text : text,
        addclass: "bg-primary"
    };
    switch (type) {
        case 'error':
        opts.addclass = "bg-danger";
        break;

        case 'info':
        opts.addclass = "bg-info";
        break;

        case 'success':
        opts.addclass = "bg-success";
        break;
        
        case 'warning':
        opts.addclass = "bg-warning";
        break;
    }
    new PNotify(opts);
}