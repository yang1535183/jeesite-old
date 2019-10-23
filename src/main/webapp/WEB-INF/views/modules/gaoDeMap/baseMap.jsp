<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>高德地图</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.15&key=ec96d68b306ec6809a2ec3f5512c9d0b&plugin=AMap.Autocomplete,AMap.PlaceSearch,AMap.Geocoder"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div id="container"></div>
<div id="myPageTop">
    <table>
        <tr>
            <td>
                <label>请输入关键字：</label>
            </td>
        </tr>
        <tr>
            <td>
                <input id="tipinput"/>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    var jwd = '${jwd}'; // 已选坐标点
    var map;
    if (jwd != null && jwd != ''){
        map = new AMap.Map('container', {
            resizeEnable: true,
            center: [jwd.split(',')[0],jwd.split(',')[1]],
            zoom: 13
        });
        addMarker(jwd.split(',')[0],jwd.split(',')[1]);
        map.setFitView(); //自适应标注点
    }else{
        //地图加载
        map = new AMap.Map("container", {
        resizeEnable: true
        });
    }
    //输入提示
    var autoOptions = {
        input: "tipinput"
    };
    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({
        map: map
    });  //构造地点查询类
    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
        placeSearch.setCity(e.poi.adcode);
        placeSearch.search(e.poi.name);  //关键字查询查询
    }

    map.on('click', showInfoClick);

    //地理编码与逆地理编码类，用于地址描述与坐标之间的转换。
    var geocoder = new AMap.Geocoder({
        city: "010", //城市设为北京，默认：“全国”
        radius: 1000 //范围，默认：500
    });
    // 点击事件
    function showInfoClick(e){
        geocoder.getAddress(e.lnglat, function(status, result) {
            if (status === 'complete'&&result.regeocode) {
                var address = result.regeocode.formattedAddress;
                window.opener.chcekPoint(e.lnglat.getLng(),e.lnglat.getLat(),address);
                addMarker(e.lnglat.getLng(),e.lnglat.getLat()); // 添加标注
                setTimeout (closePage,1000);      //1秒后执行
            }else{
                log.error('根据经纬度查询地址失败')
            }
        });
        // var text = '您在 [ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ] 的位置单击了地图！'
    }

    // 添加标注
    function addMarker(j,w){
        // 清除地图上所有添加的覆盖物
        map.clearMap();
        map.add(new AMap.Marker({
                position:  new AMap.LngLat(j,w)
            })
        );
    }

    // 关闭当前页
    function closePage() {
        window.close();
    }
</script>
</body>
</html>