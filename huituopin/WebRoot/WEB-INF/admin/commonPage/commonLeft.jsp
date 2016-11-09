<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
            $(function(){   
                //左侧导航切换
                $(".menuson li").click(function(){
                    $(".menuson li.active").removeClass("active");
                    $(this).addClass("active");
                });
                
                $('.title').click(function(){
                    var $ul = $(this).next('ul');
                    $('dd').find('ul').slideUp();
                    if($ul.is(':visible')){
                        $(this).next('ul').slideUp();
                    }else{
                        $(this).next('ul').slideDown();
                    }
                });
            });
        </script>
        <!--左侧导航-->
          <div class="main-left">
            <h1 class="logo" style="margin-top:0px;"></h1>
            <div id="sidebar-nav" class="sidebar-nav">
              <div class="list-group selected" style="display: block;">
                <div class="list-wrap">
                  <div class="lefttop"><span></span>控制面板</div>
                  <dl class="leftmenu">
                   <!--  <dd>
                      <div class="title"><span class="dj"></span>系统日志</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="">操作日志</a><i></i></li>
                        <li><cite></cite><a href="">错误日志</a><i></i></li>
                      </ul>
                    </dd> -->
                    <dd>
                      <div class="title"><span class="dj"></span>衣旧有爱</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/yjya/commodityManager">产品管理</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/yjya/clothTypeList">类别管理</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/yjya/commentManager">评价管理</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/yjya/setRule">规则管理</a><i></i></li>
                      </ul>
                    </dd>
                    <dd>
                      <div class="title"><span class="dj"></span>滴水之恩</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/dsze/dszeList">所有项目</a><i></i></li>
                      </ul>
                    </dd>
                    <dd>
                      <div class="title"><span class="dj"></span>重大救助</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/zdjz/activityList">所有项目</a><i></i></li>
                      </ul>
                    </dd>
                    <dd>
                      <div class="title"><span class="dj"></span>爱购</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/aigou/goodsManager">商品管理</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/aigou/goodCategory">商品类别</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/aigou/orderManager">订单管理</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/aigou/commentManager">评价管理</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/aigou/logisticsTool">物流工具</a><i></i></li>
                      </ul>
                    </dd>
                    <dd>
                      <div class="title"><span class="dj"></span>爱心人士</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/getAllLP">所有会员</a><i></i></li>
                      </ul>
                    </dd>
                    
                    <dd>
                      <div class="title"><span class="dj"></span>贫困人士</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/getAllPP">所有会员</a><i></i></li>
                      </ul>
                    </dd>
                    <dd>
                      <div class="title"><span class="dj"></span>爱心银行</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/bank/lovingBankList">所有会员积分</a><i></i></li>
                        <li><cite></cite><a href="<%=path%>/admin/bank/setRule">积分规则</a><i></i></li>
                      </ul>
                    </dd>
                     <dd>
                      <div class="title"><span class="dj"></span>设置</div>
                      <ul class="menuson">
                        <li><cite></cite><a href="<%=path%>/admin/changePassword">安全中心</a><i></i></li>
                      </ul>
                    </dd>
                  </dl>
                </div>
              </div>
            </div>
          </div>
          <!--/左侧导航--> 