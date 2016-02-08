package id.gits.jetpackreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.gits.jetpackreader.R;
import id.gits.jetpackreader.model.PostModel;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private final List<PostModel> mList;
    private final OnItemClickListener mOnItemClickListener;

    public PostListAdapter(Context context, List<PostModel> list, OnItemClickListener onItemClickListener) {
        mList = list;
        mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvTitle)
        public TextView tvTitle;
        @Bind(R.id.tvAuthor)
        public TextView tvAuthor;
        @Bind(R.id.tvDate)
        public TextView tvDate;
        @Bind(R.id.ivAuthorImage)
        public ImageView ivAuthor;
        @Bind(R.id.ivImage)
        public ImageView ivImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final PostModel item = mList.get(position);
        viewHolder.tvTitle.setText(Html.fromHtml(item.getTitle()));

        SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);
        SimpleDateFormat sdfFormat = new SimpleDateFormat("dd MMM yy", Locale.getDefault());
        try {
            viewHolder.tvDate.setText(sdfFormat.format(sdfParse.parse(item.getDateCreated())));
        } catch (ParseException e) {
            e.printStackTrace();
            viewHolder.tvDate.setText(item.getDateCreated());
        }
        viewHolder.tvAuthor.setText(Html.fromHtml(item.getAuthorName()));
        Glide.with(viewHolder.itemView.getContext()).load(item.getImage())
                .placeholder(R.color.md_blue_grey_300).error(R.mipmap.ic_launcher)
                .into(viewHolder.ivImage);
        Glide.with(viewHolder.itemView.getContext()).load(item.getAuthorImage())
                .placeholder(R.color.md_blue_grey_300).error(R.mipmap.ic_launcher)
                .into(viewHolder.ivAuthor);

//        final long id = item.get();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, item);
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(View v, PostModel postModel);
    }
}