package id.gits.jetpackreader.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class PostsWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new PostsRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

