define(function(require, exports, module) {
	var $ = require('jquery');
	var common = require('common');
	var api = require('api');

	$(function() {
		seajs.use("wxjs", function(wx) {
			init();
			var signatureObj = api.getSignature(window.location.href);
			var signature = $.parseJSON(signatureObj.responseText);
			wx.config({
				debug : false,
				appId : signature[0],
				timestamp : signature[1],
				nonceStr : signature[2],
				signature : signature[3],
				jsApiList : [ 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage' ]
			});

			$("span[class='weui_uploader_input']").click(
					function() {
						var len = $("ul[class='weui_uploader_files']").children().length;
						if (len >= 6) {
							$('#dialog2').show().on('click', '.weui_btn_dialog', function() {
								$('#dialog2').off('click').hide();
							});
							return;
						}
						wx.chooseImage({
							count : 6 - len, // 默认9
							sizeType : [ 'original', 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
							sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
							success : function(res) {
								setTimeout(function() {
									var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
									$(localIds).each(
											function(i, n) {
												var id = api.uuid();
												var img = '<li id="' + id + '" class="weui_uploader_file weui_uploader_status_me" style="background-image: url(' + n
														+ ')"><img class="uploadloading" src="images/loading.gif"></img></li>';
												$("ul[class='weui_uploader_files']").append(img);
												wx.uploadImage({
													localId : n,
													isShowProgressTips : 0,
													success : function(res) {
														$("#" + id).removeClass('weui_uploader_status');
														$("#" + id).html('');
														$("#" + id).html('<input type="hidden" name="serverid" value="' + res.serverId + '"><i class="closeimg weui_icon_clear"></i>');
														init();
													},
													fail : function(res) {
														alert(JSON.stringify(res));
													}
												});
											});
								}, 100);
							}
						});
					});

			function init() {
				$(".closeimg").click(function() {
					$(this).parent().remove();
				});
				$("#uploadbtn").click(function() {
				});
			}
		});
	});
})
