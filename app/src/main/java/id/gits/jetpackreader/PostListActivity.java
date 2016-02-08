package id.gits.jetpackreader;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import id.gits.jetpackreader.adapter.PostListAdapter;
import id.gits.jetpackreader.api.core.ApiCallback;
import id.gits.jetpackreader.api.dao.CategoryDao;
import id.gits.jetpackreader.api.dao.PostDao;
import id.gits.jetpackreader.api.dao.PostListApiDao;
import id.gits.jetpackreader.api.jetpack.GetPostsApi;
import id.gits.jetpackreader.model.PostModel;
import id.gits.jetpackreader.provider.JetpackProvider;
import id.gits.jetpackreader.provider.category.CategoryColumns;
import id.gits.jetpackreader.provider.category.CategoryCursor;
import id.gits.jetpackreader.provider.post.PostColumns;
import id.gits.jetpackreader.provider.post.PostCursor;
import id.gits.jetpackreader.provider.post.PostSelection;
import id.gits.jetpackreader.sync.SyncAdapter;
import id.gits.jetpackreader.view.EndlessRecyclerOnScrollListener;

/**
 * An activity representing a list of Posts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PostDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PostListActivity extends BaseActivity implements PostListAdapter.OnItemClickListener {

    private static final int URL_LOADER_CATEGORY = 1;
    private static final int URL_LOADER_POST = 2;

    @Bind(R.id.post_list)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private Drawer mDrawer;
    private CategoryDao mCatSelected = null;


    private PostListAdapter mAdapter;
    private final List<PostModel> mPostList = new ArrayList<>();
    private EndlessRecyclerOnScrollListener mScrollListener;

    private final ContentObserver mCategoryObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            getSupportLoaderManager().initLoader(URL_LOADER_CATEGORY, null, mLoaderCallback);
        }
    };

    private final ContentObserver mPostObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            getSupportLoaderManager().restartLoader(URL_LOADER_POST, null, mLoaderCallback);
        }
    };

    private final ApiCallback<PostListApiDao> mPostApiCallback = new ApiCallback<PostListApiDao>() {
        @Override
        public void onApiSuccess(PostListApiDao result) {
            if (mScrollListener.getCurrentPage() == 1) {
                mPostList.clear();
            }
            for (int i = 0; i < result.getPosts().size(); i++) {
                PostDao postDao = result.getPosts().get(i);
                //save if home category
                if (mCatSelected == null) {
                    postDao.saveToDb(getContentResolver());
                }
                mPostList.add(PostModel.fromDao(postDao));
            }
            mAdapter.notifyDataSetChanged();
            mSwipeRefresh.setRefreshing(false);
            if (mScrollListener.getCurrentPage() == 1)
                recyclerView.addOnScrollListener(mScrollListener);
        }

        @Override
        public void onApiError(String errorMessage) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        setupRecyclerView(recyclerView);

        mSwipeRefresh.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSwipeRefresh.setRefreshing(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mSwipeRefresh.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    //noinspection deprecation
                    mSwipeRefresh.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initContentFromDb();
            }
        });

        if (findViewById(R.id.post_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        getContentResolver().registerContentObserver(CategoryColumns.CONTENT_URI, true, mCategoryObserver);
        getContentResolver().registerContentObserver(CategoryColumns.CONTENT_URI, true, mPostObserver);

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        mDrawer.closeDrawer();
                        CategoryDao categoryDao = (CategoryDao) drawerItem.getTag();
                        if (getSupportActionBar() != null) {
                            if (categoryDao != null)
                                getSupportActionBar().setTitle(categoryDao.getName());
                            else
                                getSupportActionBar().setTitle(R.string.app_name);
                        }

                        //remove scroll listener
                        recyclerView.removeOnScrollListener(mScrollListener);

                        //clear data if any
                        mPostList.clear();
                        mAdapter.notifyDataSetChanged();

                        mSwipeRefresh.setRefreshing(false);

                        mCatSelected = (categoryDao == null) ? null : categoryDao;
                        initContentFromDb();

                        return true;
                    }
                })
                .build();


        getSupportLoaderManager().initLoader(URL_LOADER_CATEGORY, null, mLoaderCallback);
        initContentFromDb();

        Bundle bundle = new Bundle();
        bundle.putInt("type", SyncAdapter.TYPE_POST);
        ContentResolver.requestSync(createDummyAccount(PostListActivity.this),
                JetpackProvider.AUTHORITY, bundle);

        //Admob
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("2CFF04A44371822FAE66A3FE5B2D19F2").build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String screenName = mCatSelected == null ? "Home" : mCatSelected.getName();
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    private void initContentFromDb() {
        //cancel all api call
        GetPostsApi.cancel();

        getSupportLoaderManager().restartLoader(URL_LOADER_POST, null, mLoaderCallback);
    }

    private Account createDummyAccount(Context context) {
        Account dummyAccount = new Account("Jetpack Reader", "id.gits.jetpackreader");
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(dummyAccount, null, null);
        ContentResolver.setSyncAutomatically(dummyAccount, JetpackProvider.AUTHORITY, true);
        return dummyAccount;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new PostListAdapter(this, mPostList, this);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (mPostList.size() > 0) {
                    String slug = mCatSelected == null ? null : mCatSelected.getSlug();
                    GetPostsApi.callSyncApi(PostListActivity.this, slug,
                            mPostList.get(mPostList.size() - 1).getDateCreated(),
                            mPostApiCallback);
                }
            }
        };
    }

    private void setupNavigationDrawer(CategoryCursor cursor) {
        mDrawer.removeAllItems();

        //add first menu
        PrimaryDrawerItem homeItem = new PrimaryDrawerItem().withName("HOME")
                .withTag(null);
        mDrawer.addItem(homeItem);

        //iterate menu from db
        Map<Long, List<CategoryDao>> mapCat = new LinkedHashMap<>();
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getPostCount() > 0) {
                    if (cursor.getParent() == 0) {
                        mapCat.put(cursor.getSId(), new ArrayList<CategoryDao>());
                        mapCat.get(cursor.getSId()).add(CategoryDao.fromCursor(cursor));
                    } else if (mapCat.containsKey(cursor.getParent())) {
                        mapCat.get(cursor.getParent()).add(CategoryDao.fromCursor(cursor));
                    }
                }
            } while (cursor.moveToNext());
        }

        for (Map.Entry<Long, List<CategoryDao>> entry
                : mapCat.entrySet()) {
            CategoryDao categoryModel = entry.getValue().get(0);
            mDrawer.addItem(new PrimaryDrawerItem()
                    .withName(Html.fromHtml(categoryModel.getName()).toString())
                    .withTag(categoryModel));
        }

        for (Map.Entry<Long, List<CategoryDao>> entry
                : mapCat.entrySet()) {
            if (entry.getValue().size() > 1) {
                mDrawer.addItem(new SectionDrawerItem().withName(
                        Html.fromHtml(entry.getValue().get(0).getName().toUpperCase()).toString()));


                for (int i = 1; i < entry.getValue().size(); i++) {
                    CategoryDao categoryModel = entry.getValue().get(i);
                    mDrawer.addItem(new PrimaryDrawerItem()
                            .withName(Html.fromHtml(categoryModel.getName()).toString())
                            .withIcon(R.drawable.ic_navigate_next_black_24dp).withTag(categoryModel));
                }
            }
        }

        if (mDrawer.getCurrentSelectedPosition() < 0)
            mDrawer.setSelection(homeItem, false);
    }

    private final LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
            switch (loaderID) {
                case URL_LOADER_CATEGORY:
                    // Returns a new CursorLoader
                    return new CursorLoader(
                            PostListActivity.this,   // Parent activity context
                            CategoryColumns.CONTENT_URI,        // Table to query
                            null,     // Projection to return
                            null,            // No selection clause
                            null,            // No selection arguments
                            CategoryColumns.PARENT  // Default sort order
                    );
                case URL_LOADER_POST:
                    PostSelection postSelection = new PostSelection();
                    if (mCatSelected != null) {
                        postSelection.categoryLike("%\"" + mCatSelected.getID() + "\"%");
                    }
                    // Returns a new CursorLoader
                    return new CursorLoader(
                            PostListActivity.this,   // Parent activity context
                            PostColumns.CONTENT_URI,        // Table to query
                            null,     // Projection to return
                            postSelection.sel(),            // Selection clause
                            postSelection.args(),            // Selection arguments
                            PostColumns.DATE_CREATED + " DESC"             // Default sort order
                    );
                default:
                    // An invalid id was passed in
                    return null;
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            switch (loader.getId()) {
                case URL_LOADER_CATEGORY:
                    if (data.getCount() == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", SyncAdapter.TYPE_CAT);
                        ContentResolver.requestSync(createDummyAccount(PostListActivity.this),
                                JetpackProvider.AUTHORITY, bundle);
                    } else {
                        setupNavigationDrawer(new CategoryCursor(data));
                    }
                    break;
                case URL_LOADER_POST:
                    mPostList.clear();

                    PostCursor postCursor = new PostCursor(data);
                    if (data.moveToFirst()) {
                        do {
                            mPostList.add(PostModel.fromCursor(postCursor));
                        } while (data.moveToNext());
                    }
                    mAdapter.notifyDataSetChanged();

                    mScrollListener.reset();
                    //call api if not home
                    if (mCatSelected != null) {
                        mSwipeRefresh.setRefreshing(true);
                        GetPostsApi.callSyncApi(PostListActivity.this, mCatSelected.getSlug(), null, mPostApiCallback);
                    } else {
                        mSwipeRefresh.setRefreshing(false);
                        recyclerView.addOnScrollListener(mScrollListener);
                    }
                    break;
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mCategoryObserver);
        getContentResolver().unregisterContentObserver(mPostObserver);
        if (mScrollListener != null)
            recyclerView.removeOnScrollListener(mScrollListener);
    }

    @Override
    public void onItemClick(View v, PostModel postModel) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(PostDetailFragment.ARG_ITEM, postModel);
            PostDetailFragment fragment = new PostDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.post_detail_container, fragment)
                    .commit();
        } else {
            PostDetailActivity.startThisActivity(this, v, postModel, false);
        }
    }

    @Override
    public void finish() {
        if (mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else {
            super.finish();
        }
    }
}
