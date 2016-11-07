package com.project.markpollution.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.project.markpollution.DetailReportActivity;
import com.project.markpollution.Objects.PollutionPoint;
import com.project.markpollution.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.project.markpollution.Interfaces.OnItemClickListener;

/**
 * Created by Hung on 04-Nov-16.
 */

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedRecyclerViewAdapter.FeedRecyclerViewHolder> {
    private Context context;
    private List<PollutionPoint> listPo;
    private String url_RetrieveUserById = "http://indi.com.vn/dev/markpollution/RetrieveUserById.php?id_user=";
    private OnItemClickListener onItemClickListener;
    public FeedRecyclerViewAdapter(Context context, List<PollutionPoint> listPo) {
        this.context = context;
        this.listPo = listPo;
    }

    @Override
    public FeedRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_feed, parent, false);
        return new FeedRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedRecyclerViewHolder holder, int position) {
        final PollutionPoint curPo = listPo.get(position);
        setCateIcon(curPo.getId_cate(), holder.ivCate);
        holder.tvTime.setText(formatDateTime(curPo.getTime()));
        holder.tvDesc.setText(curPo.getDesc());
        Picasso.with(context).load(Uri.parse(curPo.getImage())).placeholder(R.drawable.placeholder).into(holder.ivFeedImage);
        holder.tvTitle.setText(curPo.getTitle());
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(curPo);
            }
        };
        holder.container.setOnClickListener(clickListener);

    }

    @Override
    public int getItemCount() {
        return listPo.size();
    }

    class FeedRecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView ivCate, ivFeedImage;
        TextView tvTitle, tvTime, tvDesc;
        LinearLayout container;

        public FeedRecyclerViewHolder(View itemView) {
            super(itemView);
            ivCate = (ImageView) itemView.findViewById(R.id.ivCateFeed);
            ivFeedImage = (ImageView) itemView.findViewById(R.id.ivFeed);
//            ivComment = (ImageView) itemView.findViewById(R.id.imageViewCommentFeed);
//            ivRate = (ImageView) itemView.findViewById(R.id.imageViewRateFeed);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleFeed);
            tvTime = (TextView) itemView.findViewById(R.id.tvTimestamp);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDescFeed);
            container = (LinearLayout) itemView.findViewById(R.id.container_feed);
//            tvComment = (TextView) itemView.findViewById(R.id.textViewCommentFeed);
//            tvRate = (TextView) itemView.findViewById(R.id.textViewRateFeed);
        }
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
    private void setCateIcon(String cateID, ImageView holder){
        switch (cateID){
            case "1":
                holder.setImageResource(R.drawable.land_icon);
                break;
            case "2":
                holder.setImageResource(R.drawable.watter_icon);
                break;
            case "3":
                holder.setImageResource(R.drawable.air_icon);
                break;
            case "4":
                holder.setImageResource(R.drawable.thermal_icon);
                break;
            case "5":
                holder.setImageResource(R.drawable.light_icon);
                break;
            case "6":
                holder.setImageResource(R.drawable.noise_icon);
                break;
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
