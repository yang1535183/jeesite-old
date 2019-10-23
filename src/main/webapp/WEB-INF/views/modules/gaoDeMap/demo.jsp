<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>逆地理编码(经纬度->地址)</title>
    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css"/>
    <style>
        html,body,#container{
            height:100%;
            width:100%;
        }
        .btn{
            width:10rem;
            margin-left:6.8rem;
        }
    </style>
</head>
<body>
<div id="container"></div>
<div class='info'>输入或点击地图获取经纬度。</div>
<div class="input-card" style='width:28rem;'>
    <label style='color:grey'>逆地理编码，根据经纬度获取地址信息</label>
    <div class="input-item">
        <div class="input-item-prepend"><span class="input-item-text">经纬度</span></div>
        <input id='lnglat' type="text" value='116.39,39.9'>
    </div>
    <div class="input-item">
        <div class="input-item-prepend"><span class="input-item-text" >地址</span></div>
        <input id='address' type="text" disabled>
    </div>
    <input id="regeo" type="button" class="btn" value="经纬度 -> 地址" >
</div>
<script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.15&key=ec96d68b306ec6809a2ec3f5512c9d0b&plugin=AMap.Geocoder"></script>
<script type="text/javascript">
    var map = new AMap.Map("container", {
        resizeEnable: true
    });

    //地理编码与逆地理编码类，用于地址描述与坐标之间的转换。
    var geocoder = new AMap.Geocoder({
        city: "010", //城市设为北京，默认：“全国”
        radius: 1000 //范围，默认：500
    });
    var marker = new AMap.Marker();;
    function regeoCode() {

        var lnglat  = document.getElementById('lnglat').value.split(',');
        map.add(marker);
        marker.setPosition(lnglat);

        geocoder.getAddress(lnglat, function(status, result) {
            if (status === 'complete'&&result.regeocode) {
                var address = result.regeocode.formattedAddress;
                document.getElementById('address').value = address;
            }else{
                log.error('根据经纬度查询地址失败')
            }
        });
    }

    map.on('click',function(e){
        document.getElementById('lnglat').value = e.lnglat;
        regeoCode();
    })
    document.getElementById("regeo").onclick = regeoCode;
    document.getElementById('lnglat').onkeydown = function(e) {
        if (e.keyCode === 13) {
            regeoCode();
            return false;
        }
        return true;
    };
</script>
</body>
</html>