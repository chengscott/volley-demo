package io.github.chengscott.volleydemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class AmazeCardAdapter extends RecyclerView.Adapter<AmazeCardAdapter.ViewHolder> {
    private ImageLoader mImageLoader;
    private ArrayList<String> mUrls;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (NetworkImageView) view.findViewById(R.id.imageView);
        }
    }

    public AmazeCardAdapter(ArrayList<String> urls) {
        mUrls = urls;
        mImageLoader = AppController.getInstance().getImageLoader();
    }

    @Override
    public AmazeCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.amaze_card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageUrl(mUrls.get(position), mImageLoader);
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }
}
