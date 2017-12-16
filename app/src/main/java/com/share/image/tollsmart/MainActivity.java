package com.share.image.tollsmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String URL = "http://tollsmart-cv.elasticbeanstalk.com/TollsAPI/tolls";
    private static String KEY = "328716513f1805d9f4fbd21ab5c62f29";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final OutputStream[] os = {null};
        final BufferedReader[] br = {null};
        // Http classes
        final HttpURLConnection[] httpConnection = {null};
        // Prepare TollPostRequest object to make HTTP POST request to Tollsmart API
        final TollPostRequest tollRequest = new TollPostRequest();


//Case 1: set origin/destination and any via-points and the API will return Google routes with tolls:
  /*      tollRequest.setOrigin("41.8339042,-88.0121575");
        tollRequest.setDestination("40.6976701,-74.2598753");*/
//      tollRequest.setWaypoints(getWaypoints());
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
        tollRequest.setKey(KEY);

//Prepare Vehicle object
        Vehicle vehicle = new Vehicle();
        // or
//       vehicle = getVehicle();

        tollRequest.setVehicle(vehicle);

//Case 2:  you have a Google-generated route for which you'd like to get toll info:
    tollRequest.setSupplied_route(getSuppliedRoute());

//Make query
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Gson gson = new Gson();
                    System.out.println("enter the current toll api"+tollRequest.toString());
                    String jsonObject = gson.toJson(tollRequest);

                    System.out.println("The POST request content is: " + gson);

                    URL tollURL = new URL(URL);
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

                        JSONArray array = new JSONArray(sb.toString());
                        System.out.println("enter the array reponse"+array);
                        for (int i=0;i < array.length();i++){
                            JSONObject jsonObj  = array.getJSONObject(i);
                           String strLicense=jsonObj.optString("array_of_tolls");
                            System.out.println("enter the toll array Values"+strLicense+"end of the log");
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

    public static String getJSON(){
        String json = "";
        return json;
    }
    public static ArrayList<GeoPoint>getSuppliedRoute(){
        ArrayList<GeoPoint> supplied_route = new ArrayList<GeoPoint>();
        supplied_route.add(new GeoPoint(37.832928,-122.488896));
        supplied_route.add(new GeoPoint(37.83288,-122.488752));
        supplied_route.add(new GeoPoint(37.83264,-122.488392));
        supplied_route.add(new GeoPoint(37.83245,-122.49126));
        supplied_route.add(new GeoPoint(37.832328,-122.488144));
        supplied_route.add(new GeoPoint(37.831992,-122.487688));
        supplied_route.add(new GeoPoint(37.83176,-122.48752));
        supplied_route.add(new GeoPoint(37.83136,-122.487344));
        supplied_route.add(new GeoPoint(37.83104,-122.487088));
        supplied_route.add(new GeoPoint(37.83082,-122.486968));
        supplied_route.add(new GeoPoint(37.82944,-122.486928));
        supplied_route.add(new GeoPoint(37.82932,-122.486888));
        supplied_route.add(new GeoPoint(37.829192,-122.48676));
        supplied_route.add(new GeoPoint(37.828888,-122.48628));
        supplied_route.add(new GeoPoint(37.828848,-122.486096));
        supplied_route.add(new GeoPoint(37.828928,-122.485824));
        supplied_route.add(new GeoPoint(37.8293,-122.4854));
        supplied_route.add(new GeoPoint(37.829472,-122.48512));
        supplied_route.add(new GeoPoint(37.829512,-122.48492));
        supplied_route.add(new GeoPoint(37.8295,-122.483768));
        supplied_route.add(new GeoPoint(37.829568,-122.483568));
        supplied_route.add(new GeoPoint(37.8297,-122.483456));
        supplied_route.add(new GeoPoint(37.829928,-122.483448));
        supplied_route.add(new GeoPoint(37.83034,-122.483968));
        supplied_route.add(new GeoPoint(37.83056,-122.48408));
        supplied_route.add(new GeoPoint(37.831072,-122.484064));
        supplied_route.add(new GeoPoint(37.831472,-122.483952));
        supplied_route.add(new GeoPoint(37.83256,-122.483368));
        supplied_route.add(new GeoPoint(37.832792,-122.483368));
        supplied_route.add(new GeoPoint(37.833288,-122.483528));
        supplied_route.add(new GeoPoint(37.83344,-122.483672));
        supplied_route.add(new GeoPoint(37.8336,-122.483744));
        supplied_route.add(new GeoPoint(37.833752,-122.483632));
        supplied_route.add(new GeoPoint(37.83336,-122.482944));
        supplied_route.add(new GeoPoint(37.832512,-122.481264));
        supplied_route.add(new GeoPoint(37.83248,-122.481104));
        supplied_route.add(new GeoPoint(37.831952,-122.480448));
        supplied_route.add(new GeoPoint(37.831712,-122.48028));
        supplied_route.add(new GeoPoint(37.831472,-122.480144));
        supplied_route.add(new GeoPoint(37.83088,-122.47992));
        supplied_route.add(new GeoPoint(37.830072,-122.479808));
        supplied_route.add(new GeoPoint(37.828832,-122.479688));
        supplied_route.add(new GeoPoint(37.82446,-122.479136));
        supplied_route.add(new GeoPoint(37.81736,-122.47836));
        supplied_route.add(new GeoPoint(37.81462,-122.478008));
        supplied_route.add(new GeoPoint(37.80936,-122.477424));
        supplied_route.add(new GeoPoint(37.808832,-122.4772));
        supplied_route.add(new GeoPoint(37.808048,-122.4766));
        supplied_route.add(new GeoPoint(37.807128,-122.475664));
        supplied_route.add(new GeoPoint(37.80622,-122.474568));
        supplied_route.add(new GeoPoint(37.805,-122.473264));
        supplied_route.add(new GeoPoint(37.804648,-122.472816));
        supplied_route.add(new GeoPoint(37.803928,-122.471744));
        supplied_route.add(new GeoPoint(37.803608,-122.47112));
        supplied_route.add(new GeoPoint(37.80316,-122.470088));
        supplied_route.add(new GeoPoint(37.80282,-122.469048));
        supplied_route.add(new GeoPoint(37.80264,-122.468368));
        supplied_route.add(new GeoPoint(37.802288,-122.466592));
        supplied_route.add(new GeoPoint(37.80158,-122.462768));
        supplied_route.add(new GeoPoint(37.801528,-122.462168));
        supplied_route.add(new GeoPoint(37.801528,-122.461296));
        supplied_route.add(new GeoPoint(37.80164,-122.460512));
        supplied_route.add(new GeoPoint(37.80254,-122.45768));
        supplied_route.add(new GeoPoint(37.802872,-122.45636));
        supplied_route.add(new GeoPoint(37.803072,-122.454528));
        supplied_route.add(new GeoPoint(37.80312,-122.453616));
        supplied_route.add(new GeoPoint(37.803312,-122.45228));
        supplied_route.add(new GeoPoint(37.803312,-122.451928));
        supplied_route.add(new GeoPoint(37.803248,-122.451504));
        supplied_route.add(new GeoPoint(37.803072,-122.450928));
        supplied_route.add(new GeoPoint(37.80284,-122.45052));
        supplied_route.add(new GeoPoint(37.8018,-122.449272));
        supplied_route.add(new GeoPoint(37.799568,-122.446064));
        supplied_route.add(new GeoPoint(37.79956,-122.446192));
        supplied_route.add(new GeoPoint(37.799488,-122.446192));
        supplied_route.add(new GeoPoint(37.7977,-122.445808));
        supplied_route.add(new GeoPoint(37.797912,-122.44416));
        supplied_route.add(new GeoPoint(37.79886,-122.44436));
        supplied_route.add(new GeoPoint(37.798872,-122.44472));
        supplied_route.add(new GeoPoint(37.798928,-122.444912));
        supplied_route.add(new GeoPoint(37.79906,-122.445136));

        return supplied_route;
    }

    public static Vehicle getVehicle() {

        Vehicle vehicle = new Vehicle();
        //vehicle.setVehicle_type("auto");
        vehicle.setIs_commercial(true);
        //vehicle.setIs_auto(true);
        vehicle.setIs_truck(true);
        vehicle.setHas_trailer(true);
        vehicle.setTrailer_number_of_axles(3);
        vehicle.setVehicle_type("tractor_trailer");
        vehicle.setTrailer_has_dual_tires(true);
        vehicle.setTotal_number_of_axles(5);
        vehicle.setNumber_of_axles_without_trailer(2);
        vehicle.setHeight(110);
        vehicle.setLength(300);
        vehicle.setWidth(80);

        return vehicle;
    }
    public static ArrayList<String>getWaypoints(){
        ArrayList<String> waypoints = new ArrayList<String>();
        waypoints.add("40.2500,-75.6600");
        waypoints.add("42.6100,-70.6700");
        waypoints.add("41.5800,-71.5400");
        waypoints.add("40.6300,-75.3900");
        waypoints.add("38.8000,-77.0800");
        waypoints.add("36.9000,-76.1400");
        waypoints.add("36.9100,-76.2700");
        waypoints.add("36.8400,-76.0100");
        waypoints.add("37.3400,-79.2800");
        waypoints.add("32.6800,-117.1000");
        waypoints.add("32.7200,-117.1600");
        waypoints.add("32.8100,-117.1500");
        waypoints.add("32.6900,-117.1200");
        waypoints.add("33.6800,-117.8100");
        waypoints.add("34.4100,-119.7000");
        waypoints.add("36.6000,-121.8700");
        waypoints.add("37.7000,-122.4600");
        waypoints.add("38.2700,-121.9500");
        return waypoints;
    }
}
