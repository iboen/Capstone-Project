package id.gits.jetpackreader.provider.category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.gits.jetpackreader.provider.base.BaseModel;

/**
 * Category
 */
interface CategoryModel extends BaseModel {

    /**
     * Get the {@code s_id} value.
     */
    long getSId();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code slug} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSlug();

    /**
     * Get the {@code description} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDescription();

    /**
     * Get the {@code post_count} value.
     */
    long getPostCount();

    /**
     * Get the {@code parent} value.
     */
    long getParent();

    /**
     * Get the {@code link} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getLink();
}
