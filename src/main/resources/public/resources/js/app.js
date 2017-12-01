function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function deleteCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function checkAuthCookie() {
	return getCookie("FacialSecurity");
}

function deleteAuthCookie() {
	deleteCookie("FacialSecurity");
}

function setAuthCookie(value, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = value + ";" + expires + ";path=/";
}

var App = {
	get : function(url) {
		return $.ajax({
			method : 'GET',
			url : url,
			dataType : "json",
			headers : {
				'Authorization' : 'FacialSecurity='
						+ getCookie('FacialSecurity')
			}
		});
	},
	post : function(url, data) {
		return $.ajax({
			method : 'POST',
			url : url,
			dataType : "json",
			data: JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			headers : {
				'Authorization' : 'FacialSecurity='
						+ getCookie('FacialSecurity')
			}
		});
	}
}