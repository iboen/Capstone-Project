package id.gits.jetpackreader.provider.post;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import id.gits.jetpackreader.provider.base.AbstractSelection;

/**
 * Selection for the {@code post} table.
 */
public class PostSelection extends AbstractSelection<PostSelection> {
    @Override
    protected Uri baseUri() {
        return PostColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PostCursor} object, which is positioned before the first entry, or null.
     */
    public PostCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PostCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PostCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PostCursor} object, which is positioned before the first entry, or null.
     */
    public PostCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PostCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PostCursor query(Context context) {
        return query(context, null);
    }


    public PostSelection id(long... value) {
        addEquals("post." + PostColumns._ID, toObjectArray(value));
        return this;
    }

    public PostSelection idNot(long... value) {
        addNotEquals("post." + PostColumns._ID, toObjectArray(value));
        return this;
    }

    public PostSelection orderById(boolean desc) {
        orderBy("post." + PostColumns._ID, desc);
        return this;
    }

    public PostSelection orderById() {
        return orderById(false);
    }

    public PostSelection sId(long... value) {
        addEquals(PostColumns.S_ID, toObjectArray(value));
        return this;
    }

    public PostSelection sIdNot(long... value) {
        addNotEquals(PostColumns.S_ID, toObjectArray(value));
        return this;
    }

    public PostSelection sIdGt(long value) {
        addGreaterThan(PostColumns.S_ID, value);
        return this;
    }

    public PostSelection sIdGtEq(long value) {
        addGreaterThanOrEquals(PostColumns.S_ID, value);
        return this;
    }

    public PostSelection sIdLt(long value) {
        addLessThan(PostColumns.S_ID, value);
        return this;
    }

    public PostSelection sIdLtEq(long value) {
        addLessThanOrEquals(PostColumns.S_ID, value);
        return this;
    }

    public PostSelection orderBySId(boolean desc) {
        orderBy(PostColumns.S_ID, desc);
        return this;
    }

    public PostSelection orderBySId() {
        orderBy(PostColumns.S_ID, false);
        return this;
    }

    public PostSelection title(String... value) {
        addEquals(PostColumns.TITLE, value);
        return this;
    }

    public PostSelection titleNot(String... value) {
        addNotEquals(PostColumns.TITLE, value);
        return this;
    }

    public PostSelection titleLike(String... value) {
        addLike(PostColumns.TITLE, value);
        return this;
    }

    public PostSelection titleContains(String... value) {
        addContains(PostColumns.TITLE, value);
        return this;
    }

    public PostSelection titleStartsWith(String... value) {
        addStartsWith(PostColumns.TITLE, value);
        return this;
    }

    public PostSelection titleEndsWith(String... value) {
        addEndsWith(PostColumns.TITLE, value);
        return this;
    }

    public PostSelection orderByTitle(boolean desc) {
        orderBy(PostColumns.TITLE, desc);
        return this;
    }

    public PostSelection orderByTitle() {
        orderBy(PostColumns.TITLE, false);
        return this;
    }

    public PostSelection slug(String... value) {
        addEquals(PostColumns.SLUG, value);
        return this;
    }

    public PostSelection slugNot(String... value) {
        addNotEquals(PostColumns.SLUG, value);
        return this;
    }

    public PostSelection slugLike(String... value) {
        addLike(PostColumns.SLUG, value);
        return this;
    }

    public PostSelection slugContains(String... value) {
        addContains(PostColumns.SLUG, value);
        return this;
    }

    public PostSelection slugStartsWith(String... value) {
        addStartsWith(PostColumns.SLUG, value);
        return this;
    }

    public PostSelection slugEndsWith(String... value) {
        addEndsWith(PostColumns.SLUG, value);
        return this;
    }

    public PostSelection orderBySlug(boolean desc) {
        orderBy(PostColumns.SLUG, desc);
        return this;
    }

    public PostSelection orderBySlug() {
        orderBy(PostColumns.SLUG, false);
        return this;
    }

    public PostSelection content(String... value) {
        addEquals(PostColumns.CONTENT, value);
        return this;
    }

    public PostSelection contentNot(String... value) {
        addNotEquals(PostColumns.CONTENT, value);
        return this;
    }

    public PostSelection contentLike(String... value) {
        addLike(PostColumns.CONTENT, value);
        return this;
    }

    public PostSelection contentContains(String... value) {
        addContains(PostColumns.CONTENT, value);
        return this;
    }

    public PostSelection contentStartsWith(String... value) {
        addStartsWith(PostColumns.CONTENT, value);
        return this;
    }

    public PostSelection contentEndsWith(String... value) {
        addEndsWith(PostColumns.CONTENT, value);
        return this;
    }

    public PostSelection orderByContent(boolean desc) {
        orderBy(PostColumns.CONTENT, desc);
        return this;
    }

    public PostSelection orderByContent() {
        orderBy(PostColumns.CONTENT, false);
        return this;
    }

    public PostSelection excerpt(String... value) {
        addEquals(PostColumns.EXCERPT, value);
        return this;
    }

    public PostSelection excerptNot(String... value) {
        addNotEquals(PostColumns.EXCERPT, value);
        return this;
    }

    public PostSelection excerptLike(String... value) {
        addLike(PostColumns.EXCERPT, value);
        return this;
    }

    public PostSelection excerptContains(String... value) {
        addContains(PostColumns.EXCERPT, value);
        return this;
    }

    public PostSelection excerptStartsWith(String... value) {
        addStartsWith(PostColumns.EXCERPT, value);
        return this;
    }

    public PostSelection excerptEndsWith(String... value) {
        addEndsWith(PostColumns.EXCERPT, value);
        return this;
    }

    public PostSelection orderByExcerpt(boolean desc) {
        orderBy(PostColumns.EXCERPT, desc);
        return this;
    }

    public PostSelection orderByExcerpt() {
        orderBy(PostColumns.EXCERPT, false);
        return this;
    }

    public PostSelection authorName(String... value) {
        addEquals(PostColumns.AUTHOR_NAME, value);
        return this;
    }

    public PostSelection authorNameNot(String... value) {
        addNotEquals(PostColumns.AUTHOR_NAME, value);
        return this;
    }

    public PostSelection authorNameLike(String... value) {
        addLike(PostColumns.AUTHOR_NAME, value);
        return this;
    }

    public PostSelection authorNameContains(String... value) {
        addContains(PostColumns.AUTHOR_NAME, value);
        return this;
    }

    public PostSelection authorNameStartsWith(String... value) {
        addStartsWith(PostColumns.AUTHOR_NAME, value);
        return this;
    }

    public PostSelection authorNameEndsWith(String... value) {
        addEndsWith(PostColumns.AUTHOR_NAME, value);
        return this;
    }

    public PostSelection orderByAuthorName(boolean desc) {
        orderBy(PostColumns.AUTHOR_NAME, desc);
        return this;
    }

    public PostSelection orderByAuthorName() {
        orderBy(PostColumns.AUTHOR_NAME, false);
        return this;
    }

    public PostSelection authorImage(String... value) {
        addEquals(PostColumns.AUTHOR_IMAGE, value);
        return this;
    }

    public PostSelection authorImageNot(String... value) {
        addNotEquals(PostColumns.AUTHOR_IMAGE, value);
        return this;
    }

    public PostSelection authorImageLike(String... value) {
        addLike(PostColumns.AUTHOR_IMAGE, value);
        return this;
    }

    public PostSelection authorImageContains(String... value) {
        addContains(PostColumns.AUTHOR_IMAGE, value);
        return this;
    }

    public PostSelection authorImageStartsWith(String... value) {
        addStartsWith(PostColumns.AUTHOR_IMAGE, value);
        return this;
    }

    public PostSelection authorImageEndsWith(String... value) {
        addEndsWith(PostColumns.AUTHOR_IMAGE, value);
        return this;
    }

    public PostSelection orderByAuthorImage(boolean desc) {
        orderBy(PostColumns.AUTHOR_IMAGE, desc);
        return this;
    }

    public PostSelection orderByAuthorImage() {
        orderBy(PostColumns.AUTHOR_IMAGE, false);
        return this;
    }

    public PostSelection dateCreated(String... value) {
        addEquals(PostColumns.DATE_CREATED, value);
        return this;
    }

    public PostSelection dateCreatedNot(String... value) {
        addNotEquals(PostColumns.DATE_CREATED, value);
        return this;
    }

    public PostSelection dateCreatedLike(String... value) {
        addLike(PostColumns.DATE_CREATED, value);
        return this;
    }

    public PostSelection dateCreatedContains(String... value) {
        addContains(PostColumns.DATE_CREATED, value);
        return this;
    }

    public PostSelection dateCreatedStartsWith(String... value) {
        addStartsWith(PostColumns.DATE_CREATED, value);
        return this;
    }

    public PostSelection dateCreatedEndsWith(String... value) {
        addEndsWith(PostColumns.DATE_CREATED, value);
        return this;
    }

    public PostSelection orderByDateCreated(boolean desc) {
        orderBy(PostColumns.DATE_CREATED, desc);
        return this;
    }

    public PostSelection orderByDateCreated() {
        orderBy(PostColumns.DATE_CREATED, false);
        return this;
    }

    public PostSelection dateModified(String... value) {
        addEquals(PostColumns.DATE_MODIFIED, value);
        return this;
    }

    public PostSelection dateModifiedNot(String... value) {
        addNotEquals(PostColumns.DATE_MODIFIED, value);
        return this;
    }

    public PostSelection dateModifiedLike(String... value) {
        addLike(PostColumns.DATE_MODIFIED, value);
        return this;
    }

    public PostSelection dateModifiedContains(String... value) {
        addContains(PostColumns.DATE_MODIFIED, value);
        return this;
    }

    public PostSelection dateModifiedStartsWith(String... value) {
        addStartsWith(PostColumns.DATE_MODIFIED, value);
        return this;
    }

    public PostSelection dateModifiedEndsWith(String... value) {
        addEndsWith(PostColumns.DATE_MODIFIED, value);
        return this;
    }

    public PostSelection orderByDateModified(boolean desc) {
        orderBy(PostColumns.DATE_MODIFIED, desc);
        return this;
    }

    public PostSelection orderByDateModified() {
        orderBy(PostColumns.DATE_MODIFIED, false);
        return this;
    }

    public PostSelection image(String... value) {
        addEquals(PostColumns.IMAGE, value);
        return this;
    }

    public PostSelection imageNot(String... value) {
        addNotEquals(PostColumns.IMAGE, value);
        return this;
    }

    public PostSelection imageLike(String... value) {
        addLike(PostColumns.IMAGE, value);
        return this;
    }

    public PostSelection imageContains(String... value) {
        addContains(PostColumns.IMAGE, value);
        return this;
    }

    public PostSelection imageStartsWith(String... value) {
        addStartsWith(PostColumns.IMAGE, value);
        return this;
    }

    public PostSelection imageEndsWith(String... value) {
        addEndsWith(PostColumns.IMAGE, value);
        return this;
    }

    public PostSelection orderByImage(boolean desc) {
        orderBy(PostColumns.IMAGE, desc);
        return this;
    }

    public PostSelection orderByImage() {
        orderBy(PostColumns.IMAGE, false);
        return this;
    }

    public PostSelection commentCount(long... value) {
        addEquals(PostColumns.COMMENT_COUNT, toObjectArray(value));
        return this;
    }

    public PostSelection commentCountNot(long... value) {
        addNotEquals(PostColumns.COMMENT_COUNT, toObjectArray(value));
        return this;
    }

    public PostSelection commentCountGt(long value) {
        addGreaterThan(PostColumns.COMMENT_COUNT, value);
        return this;
    }

    public PostSelection commentCountGtEq(long value) {
        addGreaterThanOrEquals(PostColumns.COMMENT_COUNT, value);
        return this;
    }

    public PostSelection commentCountLt(long value) {
        addLessThan(PostColumns.COMMENT_COUNT, value);
        return this;
    }

    public PostSelection commentCountLtEq(long value) {
        addLessThanOrEquals(PostColumns.COMMENT_COUNT, value);
        return this;
    }

    public PostSelection orderByCommentCount(boolean desc) {
        orderBy(PostColumns.COMMENT_COUNT, desc);
        return this;
    }

    public PostSelection orderByCommentCount() {
        orderBy(PostColumns.COMMENT_COUNT, false);
        return this;
    }

    public PostSelection likeCount(long... value) {
        addEquals(PostColumns.LIKE_COUNT, toObjectArray(value));
        return this;
    }

    public PostSelection likeCountNot(long... value) {
        addNotEquals(PostColumns.LIKE_COUNT, toObjectArray(value));
        return this;
    }

    public PostSelection likeCountGt(long value) {
        addGreaterThan(PostColumns.LIKE_COUNT, value);
        return this;
    }

    public PostSelection likeCountGtEq(long value) {
        addGreaterThanOrEquals(PostColumns.LIKE_COUNT, value);
        return this;
    }

    public PostSelection likeCountLt(long value) {
        addLessThan(PostColumns.LIKE_COUNT, value);
        return this;
    }

    public PostSelection likeCountLtEq(long value) {
        addLessThanOrEquals(PostColumns.LIKE_COUNT, value);
        return this;
    }

    public PostSelection orderByLikeCount(boolean desc) {
        orderBy(PostColumns.LIKE_COUNT, desc);
        return this;
    }

    public PostSelection orderByLikeCount() {
        orderBy(PostColumns.LIKE_COUNT, false);
        return this;
    }

    public PostSelection link(String... value) {
        addEquals(PostColumns.LINK, value);
        return this;
    }

    public PostSelection linkNot(String... value) {
        addNotEquals(PostColumns.LINK, value);
        return this;
    }

    public PostSelection linkLike(String... value) {
        addLike(PostColumns.LINK, value);
        return this;
    }

    public PostSelection linkContains(String... value) {
        addContains(PostColumns.LINK, value);
        return this;
    }

    public PostSelection linkStartsWith(String... value) {
        addStartsWith(PostColumns.LINK, value);
        return this;
    }

    public PostSelection linkEndsWith(String... value) {
        addEndsWith(PostColumns.LINK, value);
        return this;
    }

    public PostSelection orderByLink(boolean desc) {
        orderBy(PostColumns.LINK, desc);
        return this;
    }

    public PostSelection orderByLink() {
        orderBy(PostColumns.LINK, false);
        return this;
    }

    public PostSelection category(String... value) {
        addEquals(PostColumns.CATEGORY, value);
        return this;
    }

    public PostSelection categoryNot(String... value) {
        addNotEquals(PostColumns.CATEGORY, value);
        return this;
    }

    public PostSelection categoryLike(String... value) {
        addLike(PostColumns.CATEGORY, value);
        return this;
    }

    public PostSelection categoryContains(String... value) {
        addContains(PostColumns.CATEGORY, value);
        return this;
    }

    public PostSelection categoryStartsWith(String... value) {
        addStartsWith(PostColumns.CATEGORY, value);
        return this;
    }

    public PostSelection categoryEndsWith(String... value) {
        addEndsWith(PostColumns.CATEGORY, value);
        return this;
    }

    public PostSelection orderByCategory(boolean desc) {
        orderBy(PostColumns.CATEGORY, desc);
        return this;
    }

    public PostSelection orderByCategory() {
        orderBy(PostColumns.CATEGORY, false);
        return this;
    }
}
