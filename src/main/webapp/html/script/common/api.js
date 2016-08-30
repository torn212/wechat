define(function(require, exports, module) {
	require('jquery');
	exports.getSignature = function(url) {
		return $.ajax({
			url : "../wechat/wxConfig.do",
			dataType : "jsonp",
			data : {
				url : url
			},
			timeout : 5000,
			async : false
		});
	};
	exports.uuid = function uuid() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 36; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4";
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the
		s[8] = s[13] = s[18] = s[23] = "-";

		var uuid = s.join("");
		return uuid;
	};
})
