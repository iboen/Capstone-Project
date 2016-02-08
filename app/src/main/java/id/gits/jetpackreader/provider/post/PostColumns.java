package id.gits.jetpackreader.provider.post;

import android.net.Uri;
import android.provider.BaseColumns;

import id.gits.jetpackreader.provider.JetpackProvider;
import id.gits.jetpackreader.provider.category.CategoryColumns;
import id.gits.jetpackreader.provider.post.PostColumns;

/**
 * Post
 */
public class PostColumns implements BaseColumns {
    public static final String TABLE_NAME = "post";
    public static final Uri CONTENT_URI = Uri.parse(JetpackProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String S_ID = "s_id";

    public static final String TITLE = "title";

    public static final String SLUG = "slug";

    public static final String CONTENT = "content";

    public static final String EXCERPT = "excerpt";

    public static final String AUTHOR_NAME = "author_name";

    public static final String AUTHOR_IMAGE = "author_image";

    public static final String DATE_CREATED = "date_created";

    public static final String DATE_MODIFIED = "date_modified";

    public static final String IMAGE = "image";

    public static final String COMMENT_COUNT = "comment_count";

    public static final String LIKE_COUNT = "like_count";

    public static final String LINK = "link";

    public static final String CATEGORY = "category";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            S_ID,
            TITLE,
            SLUG,
            CONTENT,
            EXCERPT,
            AUTHOR_NAME,
            AUTHOR_IMAGE,
            DATE_CREATED,
            DATE_MODIFIED,
            IMAGE,
            COMMENT_COUNT,
            LIKE_COUNT,
            LINK,
            CATEGORY
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(S_ID) || c.contains("." + S_ID)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(SLUG) || c.contains("." + SLUG)) return true;
            if (c.equals(CONTENT) || c.contains("." + CONTENT)) return true;
            if (c.equals(EXCERPT) || c.contains("." + EXCERPT)) return true;
            if (c.equals(AUTHOR_NAME) || c.contains("." + AUTHOR_NAME)) return true;
            if (c.equals(AUTHOR_IMAGE) || c.contains("." + AUTHOR_IMAGE)) return true;
            if (c.equals(DATE_CREATED) || c.contains("." + DATE_CREATED)) return true;
            if (c.equals(DATE_MODIFIED) || c.contains("." + DATE_MODIFIED)) return true;
            if (c.equals(IMAGE) || c.contains("." + IMAGE)) return true;
            if (c.equals(COMMENT_COUNT) || c.contains("." + COMMENT_COUNT)) return true;
            if (c.equals(LIKE_COUNT) || c.contains("." + LIKE_COUNT)) return true;
            if (c.equals(LINK) || c.contains("." + LINK)) return true;
            if (c.equals(CATEGORY) || c.contains("." + CATEGORY)) return true;
        }
        return false;
    }

}
