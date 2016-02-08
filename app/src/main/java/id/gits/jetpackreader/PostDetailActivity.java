package id.gits.jetpackreader;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import id.gits.jetpackreader.model.PostModel;
import id.gits.jetpackreader.widget.MyAppWidgetProvider;

/**
 * An activity representing a single Post detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PostListActivity}.
 */
public class PostDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(PostDetailFragment.ARG_ITEM,
                    getIntent().getSerializableExtra(PostDetailFragment.ARG_ITEM));
            PostDetailFragment fragment = new PostDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.post_detail_container, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, upIntent) || getIntent().getAction() != null) {
                // This activity is NOT part of this app's task, so create a new task
                // when navigating up, with a synthesized back stack.
                TaskStackBuilder.create(this)
                        // Add all of this activity's parents to the back stack
                        .addNextIntentWithParentStack(upIntent)
                        // Navigate up to the closest parent
                        .startActivities();
            } else {
                // This activity is part of this app's task, so simply
                // navigate up to the logical parent activity.
//                NavUtils.navigateUpTo(this, upIntent);
                supportFinishAfterTransition();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void startThisActivity(Context context,
                                         View v, PostModel postModel, boolean isFromWidget) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra(PostDetailFragment.ARG_ITEM, postModel);
        if (isFromWidget) {
            intent.setAction(MyAppWidgetProvider.CLICK_ACTION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (v != null) {
            AppCompatActivity activity = (AppCompatActivity) context;
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    v.findViewById(R.id.ivImage), activity.getString(R.string.shared_image));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                activity.startActivity(intent, options.toBundle());
                return;
            }
        }
        context.startActivity(intent);
    }

}
