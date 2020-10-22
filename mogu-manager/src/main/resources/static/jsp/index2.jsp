<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蘑菇商城后台管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5/themes/metro/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
<!--padding: 10px 10px 10px 10px;-->
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
<!-- width:15px;height:15px;background:url("/images/bushroom.png") no-repeat; 

style="height:52px;background:#19a97b;"-->
</head>
<!-- class="easyui-layout" -->
	
<body class="easyui-layout">

	<!--<div data-options="region:'north'," >
	 <header>
		 <h1><img src="images/bushroom.png" "/></h1>
		 <ul class="rt_nav">
			  <li><a href="http://localhost:8083/" target="_blank" class="website_icon"  >站点首页</a></li>
			  <li><a href="http://localhost:8083/login1.html" class="quit_icon">安全退出</a></li>
		 </ul>
	</header>
	</div>-->
    <div data-options="region:'west',split:true" style="width:180px" title="菜单">
    	<ul id="menu" class="easyui-tree" style="margin-top:10px;font-size:20px;cascadeCheck:false">
         	<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'item-add'}">新增商品</li>
	         		<li data-options="attributes:{'url':'item-list'}">商品列表</li>
	         		<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'content'}">内容管理</li>
	         	</ul>
         	</li>
         	<li>
         		<span>订单信息管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'order-list'}">订单列表</li>
	         	</ul>
         	</li>
         	<li>
         		<span>用户管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'user-list'}">用户列表</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''" style="background:white">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;" align="left"></div>
		</div>
    </div>
    
    
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>