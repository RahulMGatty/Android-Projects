package com.example.gps_demo;

import static java.util.TimeZone.getDefault;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager manager;
    LocationListener listener;

    HashMap<String,String> hashMapData;

    ArrayList<HashMap<String,String>> dataList;

    ListView gpsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        manager =(LocationManager) getSystemService(LOCATION_SERVICE);
        dataList = new ArrayList<HashMap<String, String>>();
        gpsListView =findViewById(R.id.lvGPS);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                updateMyLocation(location);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(
                    android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                manager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,0,listener);
                Location lastLocation = manager.getLastKnownLocation
                        (LocationManager.GPS_PROVIDER);
                updateMyLocation(lastLocation);
            } else {
                requestPermissions(new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},
                        123);
            }
        }
    }

    private void updateMyLocation(Location lastLocation) {
        Geocoder geocoder = new Geocoder(this,
                Locale.getDefault());
        try {
            List<Address> addressList =
                    geocoder.getFromLocation(
                            lastLocation.getLatitude(),
                            lastLocation.getLongitude(),
                            1);
            for (Address address : addressList) {
                Log.e("GPS",address + "");
            }
            String[] dataNames = new String []{
                    "Latitude",
                    "Longtitude",
                    "Address Line",
                    "Road/Street",
                    "Locality",
                    "State",
                    "District",
                    "Pin Code",
                    "Country"
            };

            String [] dataValues = new String [] {
                    addressList.get(0).getLatitude()+ "",
                    addressList.get(0).getLongitude() + "",
                    addressList.get(0).getAddressLine(0) + "",
                    addressList.get(0).getThoroughfare() + "",
                    addressList.get(0).getLocality() + "",
                    addressList.get(0).getAdminArea() +"",
                    addressList.get(0).getSubAdminArea() + "",
                    addressList.get(0).getPostalCode() + "",
                    addressList.get(0).getCountryName() + "",
            };

            for (int i=0;i<dataNames.length;i++) {
                hashMapData = new HashMap<String,String>();
                hashMapData.put("data_name",dataNames[i]);
                hashMapData.put("data_value",dataValues[i]);
                dataList.add(hashMapData);
            }
            gpsListView.setAdapter(new SimpleAdapter(
                    this,
                    dataList, android.R.layout.two_line_list_item,
                    new String[]{"data_name","data_value"},
                    new int[]{android.R.id.text1,android.R.id.text2}
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!manager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "GPS Or Location is not tunred on!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,
                    "This app does not work without GPS!",
                    Toast.LENGTH_SHORT).show();
        }else {
            recreate();
        }
    }
}