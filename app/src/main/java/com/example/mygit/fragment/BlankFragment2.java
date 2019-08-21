package com.example.mygit.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.example.mygit.Decoration.SpacesItemDecoration;
import com.example.mygit.Listener.OnItemClickListener;
import com.example.mygit.R;
import com.example.mygit.activity.ItemInfoActivity;
import com.example.mygit.adapter.RecyclerAdapter;
import com.example.mygit.bean.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlankFragment2 extends Fragment implements LocationSource, AMapLocationListener, OnItemClickListener {
    private MapView mapView=null;
    private View mView;
    private AMap aMap;
    private String Address=null;
    private TextView textView;
    private AMapLocationClient mLocationClient=null;
    public AMapLocationClientOption mLocationOption=null;
    private LocationSource.OnLocationChangedListener mListener=null;
    private boolean isFirstLoc=true;
    //轮播下面网格布局
    private RecyclerView ry2;
    private GridLayoutManager layoutManager;
    private RecyclerAdapter mAdapter;
    private static List<Item> mList;
    static{
        mList=new ArrayList();
        for (int i = 0; i < 1; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_TITLE;
            item.imageId = R.drawable.ic_cover;
            item.title = "附近活动";
            mList.add(item);
        }
        for (int i = 0; i < 4; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_GRID_TWO;
            item.imageId = R.drawable.ic_cover;
            item.title = "Perfect Day";
            mList.add(item);
        }
        for (int i = 0; i < 1; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_TITLE;
            item.imageId = R.drawable.ic_cover;
            item.title = "讲座、宣讲";
            mList.add(item);
        }
        for (int i = 0; i < 4; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_GRID_TWO;
            item.imageId = R.drawable.ic_cover;
            item.title = "Perfect Day";
            mList.add(item);
        }
        for (int i = 0; i < 1; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_TITLE;
            item.imageId = R.drawable.ic_cover;
            item.title = "志愿公益";
            mList.add(item);
        }
        for (int i = 0; i < 6; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_LIST;
            item.imageId = R.drawable.ic_cover;
            item.title = "去看《复仇者联盟四》前，你应该知道的三件事";
            mList.add(item);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        mapView=(MapView)mView.findViewById(R.id.map_view);
        textView=(TextView)mView.findViewById(R.id.address);

        mapView.onCreate(savedInstanceState);
        if(aMap==null){
            aMap=mapView.getMap();
            UiSettings settings=aMap.getUiSettings();
            aMap.setLocationSource(this);
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String []{android.Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }
        location();
        setView();
        return mView;
    }
public void setView(){
    ry2=(RecyclerView)mView.findViewById(R.id.ry2);
    layoutManager=new GridLayoutManager(getContext(),6);
    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            int type = mList.get(position).type;
            if (type == Item.TYPE.TYPE_GRID_TWO) {
                return 3;
            } else if (type == Item.TYPE.TYPE_LIST) {
                return 6;
            } else if (type == Item.TYPE.TYPE_TITLE) {
                return 6;
            }
            return 0;
        }
    });
    ry2.setLayoutManager(layoutManager);
    ry2.addItemDecoration(new SpacesItemDecoration(2));
    // 填充数据
    mAdapter = new RecyclerAdapter(getContext(), mList);
    mAdapter.setOnItemClickListener(this);
    ry2.setAdapter(mAdapter);
}

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }

    @Override

    public void onLocationChanged(AMapLocation aMapLocation) {

        if (aMapLocation != null) {

            if (aMapLocation.getErrorCode() == 0) {

                //定位成功回调信息，设置相关消息

                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表

                aMapLocation.getLatitude();//获取纬度

                aMapLocation.getLongitude();//获取经度

                aMapLocation.getAccuracy();//获取精度信息

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date date = new Date(aMapLocation.getTime());

                df.format(date);//定位时间

                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

                aMapLocation.getCountry();//国家信息

                aMapLocation.getProvince();//省信息

                aMapLocation.getCity();//城市信息

                aMapLocation.getDistrict();//城区信息

                aMapLocation.getStreet();//街道信息

                aMapLocation.getStreetNum();//街道门牌号信息

                aMapLocation.getCityCode();//城市编码

                aMapLocation.getAdCode();//地区编码
                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    //  aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Address=buffer.toString();
                    Toast.makeText(getActivity().getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                    textView.append(Address);
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());

                Toast.makeText(getActivity().getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

        mListener = onLocationChangedListener;

    }
    @Override

    public void deactivate() {

        mListener = null;

    }
    //请求允许的结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            int result = grantResults[0];
            if (result == PackageManager.PERMISSION_GRANTED) {
                //权限同意
                mLocationClient.startLocation();
            } else {
                //权限拒绝
                Toast.makeText(getActivity(), "请同意定位权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //监听方法
    @Override
    public void onItemClick(int position){
        int imageId = mList.get(position).imageId;
        //Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getActivity(), ItemInfoActivity.class);
        i.putExtra("tupian",imageId);
        startActivity(i);
    }
}
