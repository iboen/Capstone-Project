package id.gits.jetpackreader.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.gits.jetpackreader.PostDetailFragment;
import id.gits.jetpackreader.R;
import id.gits.jetpackreader.model.PostModel;
import id.gits.jetpackreader.provider.post.PostColumns;
import id.gits.jetpackreader.provider.post.PostCursor;

class PostsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final List<PostModel> mWidgetItems = new ArrayList<>();
    private final Context mContext;

    public PostsRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
//        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
//                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        mWidgetItems.clear();
        PostCursor cursor = new PostCursor(mContext.getContentResolver().query(PostColumns.CONTENT_URI,
                null, null, null, PostColumns.DATE_CREATED + " DESC"));
        if (cursor.moveToFirst()) {
            do {
                mWidgetItems.add(PostModel.fromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }


    // Given the position (index) of a WidgetItem in the array, use the item's text value in
    // combination with the app widget item XML file to construct a RemoteViews object.
    public RemoteViews getViewAt(int position) {
        PostModel postModel = mWidgetItems.get(position);

        // Construct a RemoteViews item based on the app widget item XML file, and set the
        // text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.row_widget);
        rv.setTextViewText(R.id.tvTitle, Html.fromHtml(postModel.getTitle()));
        SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);
        SimpleDateFormat sdfFormat = new SimpleDateFormat("dd MMM yy", Locale.getDefault());
        try {
            rv.setTextViewText(R.id.tvDate, sdfFormat.format(sdfParse.parse(postModel.getDateCreated())));
        } catch (ParseException e) {
            e.printStackTrace();
            rv.setTextViewText(R.id.tvDate, postModel.getDateCreated());
        }

        //image
        Bitmap image = null;
        try {
            image = Glide.with(mContext)
                    .load(postModel.getImage())
                    .asBitmap()
                    .into(112, 96).get();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        if (image != null) {
            rv.setImageViewBitmap(R.id.ivImage, image);
        } else {
            rv.setImageViewResource(R.id.ivImage, R.color.md_blue_grey_300);
        }

//        Glide.with(mContext)
//                .load(postModel.getImage())
//                .asBitmap()
//                .into(new AppWidgetTarget(mContext, rv, R.id.ivImage, mAppWidgetId));

        // Next, set a fill-intent, which will be used to fill in the pending intent template
        // that is set on the collection view in StackWidgetProvider.
        Bundle extras = new Bundle();
        extras.putSerializable(PostDetailFragment.ARG_ITEM, postModel);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("bundle", extras);

        // Make it possible to distinguish the individual on-click
        // action of a given item
        rv.setOnClickFillInIntent(R.id.root, fillInIntent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return mWidgetItems.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}