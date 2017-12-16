package com.share.image.tollsmart;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class Encryption extends AppCompatActivity implements GoogleMap.OnCameraMoveListener, LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener  {
    ArrayList<GeoPoint> supplied_route = new ArrayList<GeoPoint>();
    String strLicense,strTollCast,scheduleride;
    Button TollCalculation;
    Boolean TollTest = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        TollCalculation =(Button) findViewById(R.id.TollCalculation);

        TollCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TollTest){
                    TollTest =false;
                }else {
                    TollTest =true;
                }

            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!=null){
            AddWayPointTollApi(location.getLatitude(),location.getLongitude());
        }



    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onCameraMove() {

    }
    public void TollSmarthApiInterface(){
        final OutputStream[] os = {null};
        final BufferedReader[] br = {null};
        // Http classes
        final HttpURLConnection[] httpConnection = {null};
        // Prepare TollPostRequest object to make HTTP POST request to Tollsmart API
        final TollPostRequest tollRequest = new TollPostRequest();

        tollRequest.setMap_provider("google");
        tollRequest.setUse_express_lanes(true);
        tollRequest.setInclude_unavailable_express_lanes(true);

//Optionally, set a departure time, such as one hour ago:
        //tollRequest.setDeparture_time("-3600");

        ArrayList<String> canadaAccounts = new ArrayList<String>();
        ArrayList<String> usaAccounts = new ArrayList<String>();

//Add any user-owned transponders here
        usaAccounts.add("New Jersey E-ZPass®");
        tollRequest.setUsaccounts(usaAccounts);
        tollRequest.setCanadaAccounts(canadaAccounts);
        tollRequest.setKey(Constants.TOLLSMARTKEY);

//Prepare Vehicle object
        Vehicle vehicle = new Vehicle();
        // or
//       vehicle = getVehicle();

        tollRequest.setVehicle(vehicle);
        System.out.println("The POST request way point  is: " + getWayPoint());
        // route base get toll information
        tollRequest.setSupplied_route(getWayPoint());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Gson gson = new Gson();
                    System.out.println("enter the current toll api"+tollRequest);
                    String jsonObject = gson.toJson(tollRequest);

                    System.out.println("The POST request content is: " + jsonObject);


                    URL tollURL = new URL(Constants.TOLLSMARTSANDBOX);
                    httpConnection[0] = (HttpURLConnection) tollURL.openConnection();
                    httpConnection[0].setDoInput(true);
                    httpConnection[0].setDoOutput(true);
                    httpConnection[0].setRequestProperty("Content-Type", "application/json");
                    httpConnection[0].setRequestMethod("POST");

                    os[0] = httpConnection[0].getOutputStream();

                    os[0].write(jsonObject.getBytes());
                    //os.write(new String(jsonObject.getBytes(),"UTF-8").getBytes());
                    os[0].flush();

                    //Read the result of POST request
                    StringBuilder sb = new StringBuilder();
                    int httpResult = httpConnection[0].getResponseCode();
                    if (httpResult == HttpURLConnection.HTTP_OK) {
                        br[0] = new BufferedReader(new InputStreamReader(
                                httpConnection[0].getInputStream(), "UTF-8"));
                        String content = null;

                        while ((content = br[0].readLine()) != null) {
                            sb.append(content);
                        }
                        System.out.println("RESULT" + sb +"END OF THE TOLL");

                        try {
                            JSONArray array = new JSONArray(sb.toString());
                            System.out.println("enter the array reponse"+array);
                            for (int i=0;i < array.length();i++){
                                JSONObject jsonObj  = array.getJSONObject(i);
                                //strTollCast   =  jsonObj.optJSONArray("array_of_tolls").getJSONObject(i).optString("cash_value");
                                strTollCast   =  jsonObj.optString("array_of_tolls");
                                //strTollCast   =  jsonObj.optJSONArray("array_of_tolls").getJSONObject(i).optString("etc_value");
                                //strTollCast   =  jsonObj.optJSONArray("array_of_tolls").getJSONObject(i).optString("personalized_value");
                                System.out.println("enter the toll array Values"+strTollCast+"end of the log");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        br[0].close();
                    } else {
                        System.out.println("The response code is: " + httpResult);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    try {
                        if (br[0] != null) {
                            br[0].close();
                        }
                        if (os[0] != null) {
                            os[0].close();
                        }
                        if (httpConnection[0] != null) {
                            httpConnection[0].disconnect();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }
    public void AddWayPointTollApi(double lat,double lng){
        supplied_route.add(new GeoPoint(lat,lng));
    }
    public void setWayPoint(ArrayList<GeoPoint> supplied_route){
        this.supplied_route = supplied_route;

    }
    public ArrayList<GeoPoint> getWayPoint() {
        return supplied_route;
    }


}
