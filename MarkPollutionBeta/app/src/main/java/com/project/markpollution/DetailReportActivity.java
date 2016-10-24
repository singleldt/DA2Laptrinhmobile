package com.project.markpollution;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.markpollution.CustomAdapter.CircleTransform;
import com.project.markpollution.ModelObject.PollutionPoint;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailReportActivity extends AppCompatActivity implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private ImageView ivPicture, ivAvatar, ivSpam, ivResolved;
    private RatingBar ratingBar;
    private TextView tvTitle, tvDesc, tvRate, tvTime, tvEmail, tvCate;
    private ListView lvComment;
    private String url_RetrieveUserById = "http://2dev4u.com/dev/markpollution/RetrieveUserById.php?id_user=";
    private GoogleMap gMap;
    private double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_report);

        initView();
        getPoInfo();
    }

    private void initView() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapDetail);
        ivPicture = (ImageView) findViewById(R.id.imageViewDetail);
        ivAvatar = (ImageView) findViewById(R.id.imageViewAvatarDetail);
        ivSpam = (ImageView) findViewById(R.id.imageViewSpam);
        ivResolved = (ImageView) findViewById(R.id.imageViewResolved);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvTitle = (TextView) findViewById(R.id.textViewTitleDetail);
        tvDesc = (TextView) findViewById(R.id.textViewDescDetail);
        tvRate = (TextView) findViewById(R.id.textViewRateDetail);
        tvTime = (TextView) findViewById(R.id.textViewTimeDetail);
        tvEmail = (TextView) findViewById(R.id.textViewEmailDetail);
        tvCate = (TextView) findViewById(R.id.textViewCategoryDetail);
        lvComment = (ListView) findViewById(R.id.listViewComment);

        mapFragment.getMapAsync(this);
    }

    private void getPoInfo() {
        Intent intent = getIntent();
        String id_po = intent.getStringExtra("id_po");

        String id_user = "";
        String id_cate = "";
        String title = "";
        String desc = "";
        String image = "";
        String time = "";

        for(PollutionPoint po: MainActivity.listPo){
            if(po.getId().equals(id_po)){
                id_user = po.getId_user();
                id_cate = po.getId_cate();
                lat = po.getLat();
                lng = po.getLng();
                title = po.getTitle();
                desc = po.getDesc();
                image = po.getImage();
                time = po.getTime();
                break;
            }
        }

        // pass values into Views
        tvTitle.setText(title);
        tvDesc.setText(desc);
        Picasso.with(this).load(Uri.parse(image))
                .placeholder(R.drawable.placeholder)
                .into(ivPicture);
        tvTime.setText(formatDateTime(time));

        // set email & avatar of user
        setEmailnAvatar(id_user);
        // set category
        tvCate.setText(getCategoryName(Integer.parseInt(id_cate)));
    }

    private String formatDateTime(String time){
        SimpleDateFormat originFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat resultFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");

        Date datetime = null;
        try {
            datetime = originFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultFormat.format(datetime);
    }

    private void setEmailnAvatar(String UserID){
        String finalUrl = url_RetrieveUserById + UserID;
        JsonObjectRequest objReq = new JsonObjectRequest(Request.Method.GET, finalUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("result");
                    JSONObject user = arr.getJSONObject(0);
                    String email = user.getString("email");
                    String avatar = user.getString("avatar");

                    tvEmail.setText(email);
                    Picasso.with(DetailReportActivity.this).load(Uri.parse(avatar)).resize(100,100).transform(new CircleTransform()).into(ivAvatar);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(objReq);
    }

    private String getCategoryName(int CateID){
        String value = "";
        switch (CateID){
            case 1:
                value = "Plan Pollution";
                break;
            case 2:
                value = "Air Pollution";
                break;
            case 3:
                value = "Water Pollution";
                break;
            case 4:
                value = "Thermal Pollution";
                break;
            case 5:
                value = "Light Pollution";
                break;
            case 6:
                value = "Noise Pollution";
                break;
        }
        return value;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng point = new LatLng(lat, lng);
        gMap.addMarker(new MarkerOptions().position(point));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 16));
        gMap.getUiSettings().setMapToolbarEnabled(false);
//        gMap.getUiSettings().setAllGesturesEnabled(false);
    }
}
