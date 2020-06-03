package com.promise09th.mvvmproject.presentation.main.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.model.thumbnail.Thumbnail;
import com.promise09th.mvvmproject.model.thumbnail.VideoThumbnail;
import com.promise09th.mvvmproject.presentation.main.ThumbnailViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailHolder> {

    private List<Thumbnail> mItems = new ArrayList<>();

    private ThumbnailViewModel mThumbnailViewModel;
    private ThumbnailViewModel.ClickView mType;

    public ThumbnailAdapter(ThumbnailViewModel thumbnailViewModel, ThumbnailViewModel.ClickView type) {
        mThumbnailViewModel = thumbnailViewModel;
        mType = type;
    }

    @Override
    @NonNull
    public ThumbnailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageViewBinding binding = ItemImageViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ThumbnailHolder(binding, mThumbnailViewModel, mType);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).hashCode();
    }

    public void setItem(List<Thumbnail> items) {
        if (items != null) {
            mItems.clear();
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    @BindingAdapter("item")
    public static void bindItems(RecyclerView recyclerView, ArrayList<Thumbnail> items) {
        ThumbnailAdapter adapter = (ThumbnailAdapter)recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setItem(items);
        }
    }

    @BindingAdapter("thumbnailUrl")
    public static void loadImage(ImageView imageView, String thumbnailUrl) {
        Picasso.get().load(thumbnailUrl).into(imageView);
    }

    @BindingAdapter("bind_type_color")
    public static void bindTypeColor(TextView textView, Thumbnail thumbnail) {
        if (thumbnail instanceof VideoThumbnail) {
            textView.setTextColor(textView.getContext().getColor(R.color.color_39b4ee));
        } else {
            textView.setTextColor(textView.getContext().getColor(R.color.color_f4754e));
        }
    }
}