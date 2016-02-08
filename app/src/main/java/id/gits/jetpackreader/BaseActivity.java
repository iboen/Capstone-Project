package id.gits.jetpackreader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.Tracker;

import butterknife.ButterKnife;

/**
 * Created by ibun on 16/01/16.
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    //    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    public Tracker mTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTracker = ((MyApp) getApplication()).getDefaultTracker();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }
}
