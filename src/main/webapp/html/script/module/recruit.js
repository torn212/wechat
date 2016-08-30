define(function(require, exports, module) {
	var $ = require('jquery');
	var common = require('common');

	$(function() {

		$("div[class='weui_panel']").click(function() {
			window.location.href = "recruitinfo.html";
		});
	});
})
