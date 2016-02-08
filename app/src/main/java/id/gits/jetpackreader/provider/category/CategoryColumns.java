package id.gits.jetpackreader.provider.category;

import android.net.Uri;
import android.provider.BaseColumns;

import id.gits.jetpackreader.provider.JetpackProvider;
import id.gits.jetpackreader.provider.category.CategoryColumns;
import id.gits.jetpackreader.provider.post.PostColumns;

/**
 * Category
 */
public class CategoryColumns implements BaseColumns {
    public static final String TABLE_NAME = "category";
    public static final Uri CONTENT_URI = Uri.parse(JetpackProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String S_ID = "s_id";

    public static final String NAME = "name";

    public static final String SLUG = "slug";

    public static final String DESCRIPTION = "description";

    public static final String POST_COUNT = "post_count";

    public static final String PARENT = "parent";

    public static final String LINK = "link";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            S_ID,
            NAME,
            SLUG,
            DESCRIPTION,
            POST_COUNT,
            PARENT,
            LINK
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(S_ID) || c.contains("." + S_ID)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(SLUG) || c.contains("." + SLUG)) return true;
            if (c.equals(DESCRIPTION) || c.contains("." + DESCRIPTION)) return true;
            if (c.equals(POST_COUNT) || c.contains("." + POST_COUNT)) return true;
            if (c.equals(PARENT) || c.contains("." + PARENT)) return true;
            if (c.equals(LINK) || c.contains("." + LINK)) return true;
        }
        return false;
    }

}
