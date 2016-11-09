wx.config({
	debug : false,
	appId : $("#appId").val(),
	timestamp : $("#timestamp").val(),
	nonceStr : $("#noncestr").val(),
	signature : $("#signature").val(),
	jsApiList : [ 'checkJsApi', 'chooseImage', 'previewImage', 'uploadImage',
			'downloadImage', ]
});

wx.ready(function() {
	document.querySelector('#chooseImage1').onclick = function(){ chooseImage("chooseImage1");};
	document.querySelector('#chooseImage2').onclick = function(){ chooseImage("chooseImage2");};
	document.querySelector('#chooseImage3').onclick = function(){ chooseImage("chooseImage3");};
});
var images = {
	localId : [],
	serverId : []
};
function chooseImage(node){
	wx.chooseImage({
		count:1,//上传数量为1
		success : function(res) {
			images.localId = res.localIds;
			alert('已选择 ' + res.localIds.length + ' 张图片');
			if (images.localId.length == 0) {
				alert('请先选择图片');
				return;
			}
			var i = 0, length = images.localId.length;
			function upload() {
				wx.uploadImage({
					localId : images.localId[i],
					success : function(res) {
						i++;
						// alert('已上传：' + i + '/' + length);
						images.serverId.push(res.serverId);
						$.ajax({
							type : "GET",
							url : "/wechat/downImage?serverId="
									+ res.serverId,
							dataType : 'json',
							success : function(data) {
								if (data.error == "ok") {
									//alert(res.serverId);
									$("#"+node+ " img").attr("src",data.img);
									//$("#imagePreview").append("<img src=\"" + data.img + "\" />");
								}
							},
						});
						if (i < length) {
							upload();
						}
					},
					fail : function(res) {
						//alert(JSON.stringify(res));
						alert("出错了");
					}
				});
			}
			upload();

		}
	});
}

	
