<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=DHG3t36AhGe7W1diT4F9uVnztNprnn1c"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
    <title>百度地图</title>
</head>

<style type="text/css">
    html{height:100%}
    body{height:100%;margin:0px;padding:0px}
    #container{height:100%}
</style>
<body>
<input id="checkName" type="text"><button onclick="checkName()">搜索</button>
<div id="container"></div>

<script type="text/javascript">
    var jwd = '${jwd}'; // 已选坐标点
    var map = new BMap.Map("container");          // 创建地图实例
    var point = new BMap.Point(117.217258,31.845827);  // 创建点坐标
    if (jwd != null && jwd != ''){
        var oldPoint = new BMap.Point(jwd.split(',')[0],jwd.split(',')[1]);
        map.centerAndZoom(oldPoint,16);
        addMarker(oldPoint);
    }else{
        map.centerAndZoom(point, 16);                 // 初始化地图，设置中心点坐标和地图级别
    }
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());
    map.addControl(new BMap.MapTypeControl());

    // 关键字检索
    function checkName() {
        var checkName = $("#checkName").val();
        var local = new BMap.LocalSearch(map, {
            renderOptions:{map: map}
        });
        local.search(checkName);
    }

    //进行浏览器定位
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
         // 定位成功事件
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            //alert('您的位置：'+r.point.lng+','+r.point.lat);
            var point = new BMap.Point(r.point.lng, +r.point.lat);
        }
    },{enableHighAccuracy: true})
    //addEventListener--添加事件监听函数
    //click--点击事件获取经纬度
    map.addEventListener("click",function(e){
        var pointClick = new BMap.Point(e.point.lng,e.point.lat);
        var address;
        var myGeo = new BMap.Geocoder();
        myGeo.getLocation(pointClick, function (rs) {
            var addComp = rs.addressComponents;
            address=addComp.city+ addComp.district+addComp.street+addComp.streetNumber;
            //window.parent.checkPoint(e.point.lng,e.point.lat,address);  // 坐标点回显
            window.opener.chcekPoint(e.point.lng,e.point.lat,address);  // 坐标点回显
        });

        addMarker(pointClick);
        setTimeout (closePage,1000);      //1秒后执行
    });

    function addMarker(point){  // 创建图标对象
        map.clearOverlays(); // 清除所有覆盖物
        // 创建标注对象并添加到地图
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    }

    // 关闭当前页
    function closePage() {
        window.close();
    }
</script>
</body>
</html>