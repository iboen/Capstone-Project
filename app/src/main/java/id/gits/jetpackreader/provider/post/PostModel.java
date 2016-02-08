package id.gits.jetpackreader.provider.post;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.gits.jetpackreader.provider.base.BaseModel;

/**
 * Post
 */
interface PostModel extends BaseModel {

    /**
     * Get the {@code s_id} value.
     */
    long getSId();

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Get the {@code slug} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSlug();

    /**
     * Get the {@code content} value.
     * Can be {@code null}.
     */
    @Nullable
    String getContent();

    /**
     * Get the {@code excerpt} value.
     * Can be {@code null}.
     */
    @Nullable
    String getExcerpt();

    /**
     * Get the {@code author_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getAuthorName();

    /**
     * Get the {@code author_image} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getAuthorImage();

    /**
     * Get the {@code date_created} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDateCreated();

    /**
     * Get the {@code date_modified} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDateModified();

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImage();

    /**
     * Get the {@code comment_count} value.
     */
    long getCommentCount();

    /**
     * Get the {@code like_count} value.
     */
    long getLikeCount();

    /**
     * Get the {@code link} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getLink();

    /**
     * Get the {@code category} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getCategory();
}
