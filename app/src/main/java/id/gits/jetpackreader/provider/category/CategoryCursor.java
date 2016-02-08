package id.gits.jetpackreader.provider.category;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.gits.jetpackreader.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code category} table.
 */
public class CategoryCursor extends AbstractCursor implements CategoryModel {
    public CategoryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CategoryColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code s_id} value.
     */
    public long getSId() {
        Long res = getLongOrNull(CategoryColumns.S_ID);
        if (res == null)
            throw new NullPointerException("The value of 's_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        return getStringOrNull(CategoryColumns.NAME);
    }

    /**
     * Get the {@code slug} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSlug() {
        String res = getStringOrNull(CategoryColumns.SLUG);
        if (res == null)
            throw new NullPointerException("The value of 'slug' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code description} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDescription() {
        return getStringOrNull(CategoryColumns.DESCRIPTION);
    }

    /**
     * Get the {@code post_count} value.
     */
    public long getPostCount() {
        Long res = getLongOrNull(CategoryColumns.POST_COUNT);
        if (res == null)
            throw new NullPointerException("The value of 'post_count' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code parent} value.
     */
    public long getParent() {
        Long res = getLongOrNull(CategoryColumns.PARENT);
        if (res == null)
            throw new NullPointerException("The value of 'parent' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code link} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getLink() {
        String res = getStringOrNull(CategoryColumns.LINK);
        if (res == null)
            throw new NullPointerException("The value of 'link' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
