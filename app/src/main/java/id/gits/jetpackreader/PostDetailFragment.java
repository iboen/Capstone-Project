package id.gits.jetpackreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.gits.jetpackreader.model.PostModel;

/**
 * A fragment representing a single Post detail screen.
 * This fragment is either contained in a {@link PostListActivity}
 * in two-pane mode (on tablets) or a {@link PostDetailActivity}
 * on handsets.
 */
public class PostDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM = "item";
    private static final int URL_LOADER_POST = 1;
    private PostModel mPost;

    @Bind(R.id.wvContent)
    public WebView mWebView;

    private CollapsingToolbarLayout mAppBarLayout;
    private ImageView mIvCoverImage;
    private ShareActionProvider mShareActionProvider;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments().containsKey(ARG_ITEM)) {
            mPost = (PostModel) getArguments().getSerializable(ARG_ITEM);
        }

        Activity activity = this.getActivity();
        mAppBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        mIvCoverImage = (ImageView) activity.findViewById(R.id.ivCover);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post_detail, container, false);

        ButterKnife.bind(this, rootView);

        initContent(mPost);

        return rootView;
    }

    private void initContent(PostModel data) {
        if (mAppBarLayout != null) {
            mAppBarLayout.setTitle(Html.fromHtml(data.getTitle()));
            Glide.with(getActivity()).load(data.getImage()).into(mIvCoverImage);
        }

        String strDate;
        SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);
        SimpleDateFormat sdfFormat = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault());
        try {
            strDate = sdfFormat.format(sdfParse.parse(data.getDateCreated()));
        } catch (ParseException e) {
            e.printStackTrace();
            strDate = data.getDateCreated();
        }

        String html = "<html><head><style>img{text-align:center;width:100%;}</style></head>"
                + "<body style='padding:16'><h2>" + data.getTitle() + "</h2>"
                + "<div>Posted by <b>" + data.getAuthorName() + "</b> on " + strDate + "</div>"
                + data.getContent()
                + "</body></html>";
        mWebView.loadDataWithBaseURL("http://droidindonesia.com", html,
                "text/html", "utf-8", null);


        Tracker mTracker = ((MyApp) getActivity().getApplication()).getDefaultTracker();
        mTracker.setScreenName(data.getSlug());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);
        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        doShare();
    }

    // Call to update the share intent
    private void doShare() {
        // populate the share intent with data
        if (mShareActionProvider != null) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, mPost.getTitle() + " " + mPost.getUrl());
            mShareActionProvider.setShareIntent(intent);
        }
    }
}
