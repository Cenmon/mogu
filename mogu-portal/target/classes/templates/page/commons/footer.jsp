<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w" clstag="homepage|keycount|home2013|38a">
	<jsp:include page="footer-links.jsp"></jsp:include>
</div>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/jquery-extend.js"></script>
<script type="text/javascript" src="/js/lib-v1.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/taotao.js" charset="utf-8"></script>
<script type="text/javascript"> (function(){
var A="<strong>热门搜索：</strong><a href='http://sale.jd.com/act/OfHQzJ2GLoYlmTIu.html' target='_blank' style='color:#ff0000' clstag='homepage|keycount|home2013|03b1'>校园之星</a><a href='http://sale.jd.com/act/aEBHqLFMfVzDZUvu.html' target='_blank'>办公打印</a><a href='http://www.jd.com/pinpai/878-12516.html' target='_blank'>美菱冰箱</a><a href='http://sale.jd.com/act/nuzKb6ZiYL.html' target='_blank'>无肉不欢</a><a href='http://sale.jd.com/act/ESvhtcAJNbaj.html' target='_blank'>万件好货</a><a href='http://sale.jd.com/act/nAqiWgU34frQolt.html' target='_blank'>iPhone6</a><a href='http://sale.jd.com/act/p0CmUlEFPHLX.html' target='_blank'>哈利波特</a><a href='http://sale.jd.com/act/FstSdb2vCOLa8BRi.html' target='_blank'>美模接驾</a>";
var B=["java","apple","LG G3","天梭","保温杯","三个爸爸"];
B=pageConfig.FN_GetRandomData(B);
$("#hotwords").html(A);
var _searchValue = "${query}";
if(_searchValue.length == 0){
	_searchValue = B;
}
$("#key").val(_searchValue).bind("focus",function(){if (this.value==B){this.value="";this.style.color="#333"}}).bind("blur",function(){if (!this.value){this.value=B;this.style.color="#999"}});
})();
</script>