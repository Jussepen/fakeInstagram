package com.example.fakeinstagram;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fakeinstagram.model.Post;
import com.parse.ParseImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    ArrayList<Post> mPost;
    public Context context;
    public String USER_KEY = "post";

    public PostAdapter(ArrayList<Post> mPost){
        this.mPost = mPost;
    }
    //only called to make a new row
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.activity_item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    // binds the value based of position
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = mPost.get(position);
        // gets the data according to position
        holder.ivImagePost.setParseFile(post.getImage());
        holder.ivImagePost.loadInBackground();
        holder.tvDescription.setText(post.getKeyDescription());
        holder.tvTimeStamp.setText(getRelativeTimeAgo(post.getCreatedAt().toString()));
        holder.tvUsername.setText(post.getUser().getUsername());

        holder.ivUserProfile.setParseFile(post.getUser().getParseFile("profilePicture"));
        holder.ivUserProfile.loadInBackground();


    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivProfileImage;

        public ParseImageView ivImagePost;
        //public String tvUsername;
        public TextView tvDescription;
        public TextView tvTimeStamp;
        public TextView tvUsername;
        public ParseImageView ivUserProfile;

        public ViewHolder(View itemView){
            super(itemView);
            // connects with imageView
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            ivImagePost = itemView.findViewById(R.id.ivImagePost);
            tvDescription= itemView.findViewById(R.id.tvDescription);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivUserProfile = itemView.findViewById(R.id.ivUserProfile);


            ivImagePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION){
                        Post post = mPost.get(position);
                        Intent intent = new Intent(context, PostDetail.class);
                        intent.putExtra(USER_KEY,post);
                        context.startActivity(intent);

                }
            }

        });
    }

    // Clean all elements of the recycler
    public void clear() {
        mPost.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(ArrayList<Post> list) {
        mPost.addAll(list);
        notifyDataSetChanged();
    }

    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return relativeDate;
    }



}

