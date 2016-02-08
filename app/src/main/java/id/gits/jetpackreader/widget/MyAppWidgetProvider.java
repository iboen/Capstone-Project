package id.gits.jetpackreader.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import id.gits.jetpackreader.PostDetailActivity;
import id.gits.jetpackreader.PostDetailFragment;
import id.gits.jetpackreader.R;
import id.gits.jetpackreader.model.PostModel;

/**
 * Created by ibun on 30/01/16.
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    public static final String CLICK_ACTION = "id.gits.jetpackreader.WIDGET_CLICK_ACTION";
//    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";

    // Called when the BroadcastReceiver receives an Intent broadcast.
    // Checks to see whether the intent's action is CLICK_ACTION. If it is, the app widget
    // displays a Toast message for the current item.
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(CLICK_ACTION)) {
//            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
//                    AppWidgetManager.INVALID_APPWIDGET_ID);
            PostModel postModel = (PostModel) intent.getBundleExtra("bundle").getSerializable(PostDetailFragment.ARG_ITEM);
            PostDetailActivity.startThisActivity(context, null, postModel, true);
        }
        super.onReceive(context, intent);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // update each of the app widgets with the remote adapter
        for (int appWidgetId : appWidgetIds) {

            Intent intent = new Intent(context, PostsWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            // Instantiate the RemoteViews object for the app widget layout.
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            // Set up the RemoteViews object to use a RemoteViews adapter.
            // This adapter connects
            // to a RemoteViewsService  through the specified intent.
            // This is how you populate the data.
            rv.setRemoteAdapter(R.id.listView, intent);

            // The empty view is displayed when the collection has no items.
            // It should be in the same layout used to instantiate the RemoteViews
            // object above.
            rv.setEmptyView(R.id.listView, R.id.emptyView);

//            Intent clickIntent = new Intent(context, PostDetailActivity.class);
            Intent clickIntent = new Intent(context, MyAppWidgetProvider.class);
            clickIntent.setAction(MyAppWidgetProvider.CLICK_ACTION);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            PendingIntent pndingIntent = PendingIntent.getBroadcast(context, 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            rv.setPendingIntentTemplate(R.id.listView, pndingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, rv);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
