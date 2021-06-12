package com.example.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationListener locationListener;
    LocationManager locationManager;
    Location currLocation;
    Marker myMarker;


    public void centreMapOnLocation(Location location,String title){
        if(location!=null) {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLongClickListener(this);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        if(intent.getIntExtra("placeName",0)==0){
            //zoom to user location
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener= new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    centreMapOnLocation(location,"Your Location");
                }
            };
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1 );
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centreMapOnLocation(lastKnownLocation,"Your Location");
            }

        }

//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(@NonNull Location location) {
//                mMap.clear();
//                Log.i("Location",location.toString());
//                currLocation = location;
//                LatLng userLocation = new LatLng(currLocation.getLatitude(),currLocation.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
//                List<Address> addressList = null;
//                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
//
//                try {
//                    addressList =  geocoder.getFromLocation(currLocation.getLatitude(),currLocation.getLongitude(),1);
//                    if(addressList!=null && addressList.size()>0){
//                        String address = "";
//
//                        if(addressList.get(0).getThoroughfare() != null){
//                            address+=addressList.get(0).getThoroughfare() +" ";
//                        }
//
//                        if(addressList.get(0).getLocality() != null){
//                            address+=addressList.get(0).getLocality() +" ";
//                        }
//
//                        if(addressList.get(0).getPostalCode() != null){
//                            address+=addressList.get(0).getPostalCode() +" ";
//                        }
//
//                        if(addressList.get(0).getAdminArea() != null){
//                            address+=addressList.get(0).getAdminArea() +" ";
//                        }
//
//                        if(addressList.get(0).getCountryName() != null){
//                            address+=addressList.get(0).getCountryName();
//                        }
//
//                        Toast.makeText(MapsActivity.this, address, Toast.LENGTH_LONG).show();
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.i("Problem in","addressList");
//                }
//            }
//        };
//        if(Build.VERSION.SDK_INT<23){
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//
//        }
//
//        else if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1 );
//        }else{
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
//            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            mMap.clear();
//            currLocation = lastKnownLocation;
//            LatLng userLocation = new LatLng(currLocation.getLatitude(),currLocation.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
//        }


    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";

            try {
                List<Address> addressList = geocoder.getFromLocation(currLocation.getLatitude(), currLocation.getLongitude(), 1);
                if (addressList != null && addressList.size() > 0) {


                    if (addressList.get(0).getThoroughfare() != null) {
                        if (addressList.get(0).getSubThoroughfare() != null) {
                            address += addressList.get(0).getSubThoroughfare() + " ";
                        }
                        address += addressList.get(0).getThoroughfare() + " ";
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Problem in", "addressList");
            }
            if(address.equals("")){
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy-MM-dd");
                address = sdf.format(new Date());
            }
            mMap.addMarker(new MarkerOptions().position(latLng).title(address));
    }
}