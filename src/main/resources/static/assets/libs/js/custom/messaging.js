var stompClient = null;
var socket = new SockJS(path + '/message',null,{transports:'websocket',timeout :10000});
stompClient = Stomp.over(socket);
stompClient.debug = null;
var connectCallback = function() {
	stompClient.subscribe('/queue/messages', addMessage);
	stompClient.subscribe('/user/queue/messages', addMessage);
};
var errorCallback = function(error) {
	console.log(error);
};

stompClient.connect({}, connectCallback, errorCallback);

function disconnect() {
	stompClient.disconnect();
}
function addMessage(message) {
	var path = "${pageContext.request.contextPath}";
	var msg = JSON.parse(message.body);
	var li = '<li class="media">';
	if(msg.icon !==undefined && msg.icon !== null && msg.icon !== '')
		li += '<div class="media-left">'
	 	  + '<img src="'	+ msg.icon + '" class="img-circle img-sm" alt=""></div>';
	li += '<div class="media-body">'
	 	  + '<a href="' + ((msg.linkUrl !==undefined && msg.linkUrl !== null && msg.linkUrl !== '') ? path+msg.linkUrl : 'javascript;:') + '" class="media-heading" id="msgLink">'
	 	  + '<span class="text-semibold">' + msg.title	+ '</span>'
	 	  + '<span class="media-annotation pull-right">' + msg.timeStr + '</span></a>'
	 	  + '<span class="text-muted">'	+ msg.body + '</span>'
		  + '<input type="hidden" id="msgId" name="msgId" value="' + msg.id + '"></div></li>';
		  
	$("#headerMsgList").prepend(li);
	// update badge
	size++;
	$("span.badge").html(size);
}