package id.gits.jetpackreader.provider.post;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.gits.jetpackreader.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code post} table.
 */
public class PostContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PostColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable PostSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable PostSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PostContentValues putSId(long value) {
        mContentValues.put(PostColumns.S_ID, value);
        return this;
    }


    public PostContentValues putTitle(@Nullable String value) {
        mContentValues.put(PostColumns.TITLE, value);
        return this;
    }

    public PostContentValues putTitleNull() {
        mContentValues.putNull(PostColumns.TITLE);
        return this;
    }

    public PostContentValues putSlug(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("slug must not be null");
        mContentValues.put(PostColumns.SLUG, value);
        return this;
    }


    public PostContentValues putContent(@Nullable String value) {
        mContentValues.put(PostColumns.CONTENT, value);
        return this;
    }

    public PostContentValues putContentNull() {
        mContentValues.putNull(PostColumns.CONTENT);
        return this;
    }

    public PostContentValues putExcerpt(@Nullable String value) {
        mContentValues.put(PostColumns.EXCERPT, value);
        return this;
    }

    public PostContentValues putExcerptNull() {
        mContentValues.putNull(PostColumns.EXCERPT);
        return this;
    }

    public PostContentValues putAuthorName(@Nullable String value) {
        mContentValues.put(PostColumns.AUTHOR_NAME, value);
        return this;
    }

    public PostContentValues putAuthorNameNull() {
        mContentValues.putNull(PostColumns.AUTHOR_NAME);
        return this;
    }

    public PostContentValues putAuthorImage(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("authorImage must not be null");
        mContentValues.put(PostColumns.AUTHOR_IMAGE, value);
        return this;
    }


    public PostContentValues putDateCreated(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("dateCreated must not be null");
        mContentValues.put(PostColumns.DATE_CREATED, value);
        return this;
    }


    public PostContentValues putDateModified(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("dateModified must not be null");
        mContentValues.put(PostColumns.DATE_MODIFIED, value);
        return this;
    }


    public PostContentValues putImage(@Nullable String value) {
        mContentValues.put(PostColumns.IMAGE, value);
        return this;
    }

    public PostContentValues putImageNull() {
        mContentValues.putNull(PostColumns.IMAGE);
        return this;
    }

    public PostContentValues putCommentCount(long value) {
        mContentValues.put(PostColumns.COMMENT_COUNT, value);
        return this;
    }


    public PostContentValues putLikeCount(long value) {
        mContentValues.put(PostColumns.LIKE_COUNT, value);
        return this;
    }


    public PostContentValues putLink(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("link must not be null");
        mContentValues.put(PostColumns.LINK, value);
        return this;
    }


    public PostContentValues putCategory(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("category must not be null");
        mContentValues.put(PostColumns.CATEGORY, value);
        return this;
    }

}
