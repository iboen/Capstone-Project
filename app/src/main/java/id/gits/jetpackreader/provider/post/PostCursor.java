package id.gits.jetpackreader.provider.post;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.gits.jetpackreader.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code post} table.
 */
public class PostCursor extends AbstractCursor implements PostModel {
    public PostCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PostColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code s_id} value.
     */
    public long getSId() {
        Long res = getLongOrNull(PostColumns.S_ID);
        if (res == null)
            throw new NullPointerException("The value of 's_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        return getStringOrNull(PostColumns.TITLE);
    }

    /**
     * Get the {@code slug} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSlug() {
        String res = getStringOrNull(PostColumns.SLUG);
        if (res == null)
            throw new NullPointerException("The value of 'slug' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code content} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getContent() {
        return getStringOrNull(PostColumns.CONTENT);
    }

    /**
     * Get the {@code excerpt} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getExcerpt() {
        return getStringOrNull(PostColumns.EXCERPT);
    }

    /**
     * Get the {@code author_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getAuthorName() {
        return getStringOrNull(PostColumns.AUTHOR_NAME);
    }

    /**
     * Get the {@code author_image} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getAuthorImage() {
        String res = getStringOrNull(PostColumns.AUTHOR_IMAGE);
        if (res == null)
            throw new NullPointerException("The value of 'author_image' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code date_created} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDateCreated() {
        String res = getStringOrNull(PostColumns.DATE_CREATED);
        if (res == null)
            throw new NullPointerException("The value of 'date_created' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code date_modified} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDateModified() {
        String res = getStringOrNull(PostColumns.DATE_MODIFIED);
        if (res == null)
            throw new NullPointerException("The value of 'date_modified' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        return getStringOrNull(PostColumns.IMAGE);
    }

    /**
     * Get the {@code comment_count} value.
     */
    public long getCommentCount() {
        Long res = getLongOrNull(PostColumns.COMMENT_COUNT);
        if (res == null)
            throw new NullPointerException("The value of 'comment_count' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code like_count} value.
     */
    public long getLikeCount() {
        Long res = getLongOrNull(PostColumns.LIKE_COUNT);
        if (res == null)
            throw new NullPointerException("The value of 'like_count' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code link} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getLink() {
        String res = getStringOrNull(PostColumns.LINK);
        if (res == null)
            throw new NullPointerException("The value of 'link' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code category} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCategory() {
        String res = getStringOrNull(PostColumns.CATEGORY);
        if (res == null)
            throw new NullPointerException("The value of 'category' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
