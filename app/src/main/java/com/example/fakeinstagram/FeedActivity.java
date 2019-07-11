package com.example.fakeinstagram;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fakeinstagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;

    ArrayList<Post> posts;
    RecyclerView rvFeed;
    PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        posts = new ArrayList<>();
        rvFeed = findViewById(R.id.rvFeed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        postAdapter = new PostAdapter(posts);
        rvFeed.setAdapter(postAdapter);
        // Specify which class to query
        final ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // Specify the object id
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null ) {
                    for (int i = 0; i < objects.size(); i++) {
                        Post post = objects.get(i);
                        posts.add(post);
                        postAdapter.notifyItemInserted(posts.size() - 1);
                    }
                }else {
                    e.printStackTrace();
                 }
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);
            }
        });

    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        final ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // Specify the object id
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null ) {
                    posts.clear();
                    postAdapter.notifyDataSetChanged();

                    for (int i = 0; i < objects.size(); i++) {
                        Post post = objects.get(i);
                        posts.add(post);
                        postAdapter.notifyItemInserted(posts.size() - 1);
                    }
                }else {
                    e.printStackTrace();
                }
                swipeContainer.setRefreshing(false);

            }
        });
    }




}