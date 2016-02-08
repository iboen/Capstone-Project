package id.gits.jetpackreader.provider.category;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.gits.jetpackreader.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code category} table.
 */
public class CategoryContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CategoryColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CategorySelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CategorySelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CategoryContentValues putSId(long value) {
        mContentValues.put(CategoryColumns.S_ID, value);
        return this;
    }


    public CategoryContentValues putName(@Nullable String value) {
        mContentValues.put(CategoryColumns.NAME, value);
        return this;
    }

    public CategoryContentValues putNameNull() {
        mContentValues.putNull(CategoryColumns.NAME);
        return this;
    }

    public CategoryContentValues putSlug(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("slug must not be null");
        mContentValues.put(CategoryColumns.SLUG, value);
        return this;
    }


    public CategoryContentValues putDescription(@Nullable String value) {
        mContentValues.put(CategoryColumns.DESCRIPTION, value);
        return this;
    }

    public CategoryContentValues putDescriptionNull() {
        mContentValues.putNull(CategoryColumns.DESCRIPTION);
        return this;
    }

    public CategoryContentValues putPostCount(long value) {
        mContentValues.put(CategoryColumns.POST_COUNT, value);
        return this;
    }


    public CategoryContentValues putParent(long value) {
        mContentValues.put(CategoryColumns.PARENT, value);
        return this;
    }


    public CategoryContentValues putLink(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("link must not be null");
        mContentValues.put(CategoryColumns.LINK, value);
        return this;
    }

}
