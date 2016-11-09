/**
 * 弹出选择列表插件
 * 此组件依赖 listpcker ，请在页面中先引入 mui.picker.css + mui.picker.js
 * varstion 1.0.1
 * by Houfeng
 * Houfeng@DCloud.io
 */

(function($, document) {

	//创建 DOM
	$.dom = function(str) {
		if (typeof(str) !== 'string') {
			if ((str instanceof Array) || (str[0] && str.length)) {
				return [].slice.call(str);
			} else {
				return [str];
			}
		}
		if (!$.__create_dom_div__) {
			$.__create_dom_div__ = document.createElement('div');
		}
		$.__create_dom_div__.innerHTML = str;
		return [].slice.call($.__create_dom_div__.childNodes);
	};

	var panelBuffer = '<div class="mui-poppicker">\
		<div class="mui-poppicker-header">\
			<ul class="mui-table-view " style="font-size: 13px;">\
			<li class="mui-table-view-cell" style="height: 73px;">\
				<a style="margin-top: -18px;height: 70px;">\
				<img class="mui-media-object mui-pull-left head-img" src="'+ document.getElementById("pMainPicture").value+'" style="width:48px;height:48px;max-width:50px;max-height:50px"/>\
				<div class="xiangxi" >\
				<button class="mui-btn mui-btn-blue mui-poppicker-btn-ok" style="max-width:15px;max-height:15px;background-image:url(../../img/chahao.png);background-color:transparent;background-size:100% 100%;color:transparent;margin-top:2px"></button>\
				<div style="width:210px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" >\
					<label style="font-size: 13px;">'+ document.getElementById("pName").value+'</label><br/>\
					<p class="mui-ellipsis" style="font-size: small;color: #fc5959;">¥'+ document.getElementById("pPrice").value+'</p>\
				</div>\
				</div>\
				</a>\
			</li>\
		<div class="mui-poppicker-body">\
		</div>\
	</div>';

	var pickerBuffer = '<div class="mui-picker">\
		<div class="mui-picker-inner">\
			\
			<ul class="mui-table-view " style="font-size: 13px;">\
			<li class="mui-table-view-cell"style="height: 73px;">\
				<a style="padding-top: 1px;">规格：</a>\
				<button style="margin-top: 3px;width: auto;height: 33px;background-color: #FE6D47;color: white;">'+document.getElementById("pGuiGe").value+'</button>\
			</li>\
			<li class="mui-table-view-cell"style="height: 65px;">\
				<a style="padding-top: 14px;">\
					购买数量：\
					<div class="mui-numbox mui-pull-right" >\
						<button class="mui-btn mui-btn-numbox-minus" type="button">-</button>\
							<input class="mui-input-numbox" type="number" value="1" />\
						<button class="mui-btn mui-btn-numbox-plus" type="button">+</button>\
					</div>\
				</a>\
			</li>\
			<li class="mui-table-view-cell"style="height: 73px;">\
				<a style="padding-top: 15px;">\
					<button class="mui-poppicker-btn-ok" style="width: 100%;height: 38px;background-color: #FE6D47;color:white">下一步</button>\
				</a>\
			</li>\
		</ul>\
			</ul>\
			\
		</div>\
	</div>';

	//定义弹出选择器类
	var PopPicker = $.PopPicker = $.Class.extend({
		//构造函数
		init: function(options) {
			var self = this;
			
			self.options = options || {};
			self.options.buttons = self.options.buttons || ['确定'];
			self.panel = $.dom(panelBuffer)[0];
			document.body.appendChild(self.panel);
			self.ok = self.panel.querySelector('.mui-poppicker-btn-ok');
			//self.cancel = self.panel.querySelector('.mui-poppicker-btn-cancel');
			self.body = self.panel.querySelector('.mui-poppicker-body');
			self.mask = $.createMask();
			//self.cancel.innerText = self.options.buttons[0];
			self.ok.innerText = self.options.buttons[1];		
			
			self.ok.addEventListener('tap', function(event) {
				if (self.callback) {
					var rs = self.callback(self.getSelectedItems());
					if (rs !== false) {
						self.hide();
					}
				}
			}, false);
		
			self.mask[0].addEventListener('tap', function() {
				self.hide();
			}, false);
				
			self._createPicker();
			//防止滚动穿透
			self.panel.addEventListener('touchstart', function(event) {
				event.preventDefault();
			}, false);
			self.panel.addEventListener('touchmove', function(event) {
				event.preventDefault();
			}, false);
		},
		_createPicker: function() {
			var self = this;
			var layer = self.options.layer || 1;
			var width = (100 / layer) + '%';
			self.pickers = [];
			for (var i = 1; i <= layer; i++) {
				var pickerElement = $.dom(pickerBuffer)[0];
				self.exchange=pickerElement.querySelector(".mui-poppicker-btn-ok");
				
				self.mins=pickerElement.querySelector(".mui-btn-numbox-minus");
				self.plus=pickerElement.querySelector(".mui-btn-numbox-plus");
				self.number=pickerElement.querySelector(".mui-input-numbox");
				
				self.body.appendChild(pickerElement);
				var picker = $(pickerElement).picker();
				self.pickers.push(picker);
				
				self.mins.addEventListener('tap', function() {
					if(parseInt(self.number.value)>1){
						var mins=parseInt(self.number.value)-1;
						self.number.value=mins.toString();					
					}
				});
					
				self.plus.addEventListener('tap', function() {
					if(parseInt(self.number.value)>0){
						var plus=parseInt(self.number.value)+1;
						self.number.value=plus.toString();		
					}
				});
				
				self.exchange.addEventListener('tap', function() {
					if(parseInt(self.number.value) > parseInt(document.getElementById("pRemain").value)){
						var btnArray = ["抱歉，仓库只剩"+ document.getElementById("pRemain").value +"件"];
	                      mui.alert(' ',' ',btnArray, function() {
	                      });
					}else if(document.getElementById("pLimit").value != "0" && parseInt(self.number.value) > parseInt(document.getElementById("pLimit").value)){
						var btnArray = ["抱歉，每人限购"+ document.getElementById("pLimit").value +"件"];
	                      mui.alert(' ',' ',btnArray, function() {
	                      });
					}else{
						$.ajax({
			                 type:"POST",
			                 url:document.getElementById("basePath").value + "wechat/aigou/checkUser",
			                 error:function(data){
			                     alert("出錯了！");
			                 },
			                 success:function(data){
			                     if (data == "null") {
			                      var btnArray = ["请先注册"];
			                      mui.alert(' ',' ',btnArray, function() {
			                    	  window.location.href= document.getElementById("basePath").value + "register/signin";
			                      });
			                     }
			                     else if(data == "true"){
			                    	 var count = parseFloat(document.getElementById("pPrice").value) * parseInt(self.number.value);
			     					 window.location.href = document.getElementById("basePath").value + "wechat/aigou/payment?pId="+document.getElementById("pId").value+"&pNum="+self.number.value+"&allNum=1&count="+count+"&resource=";
			                         
			                     }else{
			                    	 alert("出錯了！");
			                     }
			                 }
			           });
					}
				});
			}
		},
		
		//填充数据
		setData: function(data) {
			var self = this;
			data = data || [];
			self.pickers[0].setItems(data);
		},
		//获取选中的项（数组）
		getSelectedItems: function() {
			var self = this;
			var items = [];
			for (var i in self.pickers) {
				var picker = self.pickers[i];
				items.push(picker.getSelectedItem() || {});
			}
			return items;
		},
		//显示
		show: function(callback) {
			var self = this;
			self.callback = callback;
			self.mask.show();
			document.body.classList.add($.className('poppicker-active-for-page'));
			self.panel.classList.add($.className('active'));
			//处理物理返回键
			self.__back = $.back;
			$.back = function() {
				self.hide();
			};
		},
		//隐藏
		hide: function() {
			var self = this;
			if (self.disposed) return;
			self.panel.classList.remove($.className('active'));
			self.mask.close();
			document.body.classList.remove($.className('poppicker-active-for-page'));
			//处理物理返回键
			$.back=self.__back;
		},
		dispose: function() {
			var self = this;
			self.hide();
			setTimeout(function() {
				self.panel.parentNode.removeChild(self.panel);
				for (var name in self) {
					self[name] = null;
					delete self[name];
				};
				self.disposed = true;
			}, 300);
		}
	});

})(mui, document);