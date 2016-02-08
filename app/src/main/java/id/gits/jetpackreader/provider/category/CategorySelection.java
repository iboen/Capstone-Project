package id.gits.jetpackreader.provider.category;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import id.gits.jetpackreader.provider.base.AbstractSelection;

/**
 * Selection for the {@code category} table.
 */
public class CategorySelection extends AbstractSelection<CategorySelection> {
    @Override
    protected Uri baseUri() {
        return CategoryColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CategoryCursor} object, which is positioned before the first entry, or null.
     */
    public CategoryCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CategoryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CategoryCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CategoryCursor} object, which is positioned before the first entry, or null.
     */
    public CategoryCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CategoryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CategoryCursor query(Context context) {
        return query(context, null);
    }


    public CategorySelection id(long... value) {
        addEquals("category." + CategoryColumns._ID, toObjectArray(value));
        return this;
    }

    public CategorySelection idNot(long... value) {
        addNotEquals("category." + CategoryColumns._ID, toObjectArray(value));
        return this;
    }

    public CategorySelection orderById(boolean desc) {
        orderBy("category." + CategoryColumns._ID, desc);
        return this;
    }

    public CategorySelection orderById() {
        return orderById(false);
    }

    public CategorySelection sId(long... value) {
        addEquals(CategoryColumns.S_ID, toObjectArray(value));
        return this;
    }

    public CategorySelection sIdNot(long... value) {
        addNotEquals(CategoryColumns.S_ID, toObjectArray(value));
        return this;
    }

    public CategorySelection sIdGt(long value) {
        addGreaterThan(CategoryColumns.S_ID, value);
        return this;
    }

    public CategorySelection sIdGtEq(long value) {
        addGreaterThanOrEquals(CategoryColumns.S_ID, value);
        return this;
    }

    public CategorySelection sIdLt(long value) {
        addLessThan(CategoryColumns.S_ID, value);
        return this;
    }

    public CategorySelection sIdLtEq(long value) {
        addLessThanOrEquals(CategoryColumns.S_ID, value);
        return this;
    }

    public CategorySelection orderBySId(boolean desc) {
        orderBy(CategoryColumns.S_ID, desc);
        return this;
    }

    public CategorySelection orderBySId() {
        orderBy(CategoryColumns.S_ID, false);
        return this;
    }

    public CategorySelection name(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public CategorySelection nameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public CategorySelection nameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public CategorySelection nameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public CategorySelection nameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public CategorySelection nameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public CategorySelection orderByName(boolean desc) {
        orderBy(CategoryColumns.NAME, desc);
        return this;
    }

    public CategorySelection orderByName() {
        orderBy(CategoryColumns.NAME, false);
        return this;
    }

    public CategorySelection slug(String... value) {
        addEquals(CategoryColumns.SLUG, value);
        return this;
    }

    public CategorySelection slugNot(String... value) {
        addNotEquals(CategoryColumns.SLUG, value);
        return this;
    }

    public CategorySelection slugLike(String... value) {
        addLike(CategoryColumns.SLUG, value);
        return this;
    }

    public CategorySelection slugContains(String... value) {
        addContains(CategoryColumns.SLUG, value);
        return this;
    }

    public CategorySelection slugStartsWith(String... value) {
        addStartsWith(CategoryColumns.SLUG, value);
        return this;
    }

    public CategorySelection slugEndsWith(String... value) {
        addEndsWith(CategoryColumns.SLUG, value);
        return this;
    }

    public CategorySelection orderBySlug(boolean desc) {
        orderBy(CategoryColumns.SLUG, desc);
        return this;
    }

    public CategorySelection orderBySlug() {
        orderBy(CategoryColumns.SLUG, false);
        return this;
    }

    public CategorySelection description(String... value) {
        addEquals(CategoryColumns.DESCRIPTION, value);
        return this;
    }

    public CategorySelection descriptionNot(String... value) {
        addNotEquals(CategoryColumns.DESCRIPTION, value);
        return this;
    }

    public CategorySelection descriptionLike(String... value) {
        addLike(CategoryColumns.DESCRIPTION, value);
        return this;
    }

    public CategorySelection descriptionContains(String... value) {
        addContains(CategoryColumns.DESCRIPTION, value);
        return this;
    }

    public CategorySelection descriptionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DESCRIPTION, value);
        return this;
    }

    public CategorySelection descriptionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DESCRIPTION, value);
        return this;
    }

    public CategorySelection orderByDescription(boolean desc) {
        orderBy(CategoryColumns.DESCRIPTION, desc);
        return this;
    }

    public CategorySelection orderByDescription() {
        orderBy(CategoryColumns.DESCRIPTION, false);
        return this;
    }

    public CategorySelection postCount(long... value) {
        addEquals(CategoryColumns.POST_COUNT, toObjectArray(value));
        return this;
    }

    public CategorySelection postCountNot(long... value) {
        addNotEquals(CategoryColumns.POST_COUNT, toObjectArray(value));
        return this;
    }

    public CategorySelection postCountGt(long value) {
        addGreaterThan(CategoryColumns.POST_COUNT, value);
        return this;
    }

    public CategorySelection postCountGtEq(long value) {
        addGreaterThanOrEquals(CategoryColumns.POST_COUNT, value);
        return this;
    }

    public CategorySelection postCountLt(long value) {
        addLessThan(CategoryColumns.POST_COUNT, value);
        return this;
    }

    public CategorySelection postCountLtEq(long value) {
        addLessThanOrEquals(CategoryColumns.POST_COUNT, value);
        return this;
    }

    public CategorySelection orderByPostCount(boolean desc) {
        orderBy(CategoryColumns.POST_COUNT, desc);
        return this;
    }

    public CategorySelection orderByPostCount() {
        orderBy(CategoryColumns.POST_COUNT, false);
        return this;
    }

    public CategorySelection parent(long... value) {
        addEquals(CategoryColumns.PARENT, toObjectArray(value));
        return this;
    }

    public CategorySelection parentNot(long... value) {
        addNotEquals(CategoryColumns.PARENT, toObjectArray(value));
        return this;
    }

    public CategorySelection parentGt(long value) {
        addGreaterThan(CategoryColumns.PARENT, value);
        return this;
    }

    public CategorySelection parentGtEq(long value) {
        addGreaterThanOrEquals(CategoryColumns.PARENT, value);
        return this;
    }

    public CategorySelection parentLt(long value) {
        addLessThan(CategoryColumns.PARENT, value);
        return this;
    }

    public CategorySelection parentLtEq(long value) {
        addLessThanOrEquals(CategoryColumns.PARENT, value);
        return this;
    }

    public CategorySelection orderByParent(boolean desc) {
        orderBy(CategoryColumns.PARENT, desc);
        return this;
    }

    public CategorySelection orderByParent() {
        orderBy(CategoryColumns.PARENT, false);
        return this;
    }

    public CategorySelection link(String... value) {
        addEquals(CategoryColumns.LINK, value);
        return this;
    }

    public CategorySelection linkNot(String... value) {
        addNotEquals(CategoryColumns.LINK, value);
        return this;
    }

    public CategorySelection linkLike(String... value) {
        addLike(CategoryColumns.LINK, value);
        return this;
    }

    public CategorySelection linkContains(String... value) {
        addContains(CategoryColumns.LINK, value);
        return this;
    }

    public CategorySelection linkStartsWith(String... value) {
        addStartsWith(CategoryColumns.LINK, value);
        return this;
    }

    public CategorySelection linkEndsWith(String... value) {
        addEndsWith(CategoryColumns.LINK, value);
        return this;
    }

    public CategorySelection orderByLink(boolean desc) {
        orderBy(CategoryColumns.LINK, desc);
        return this;
    }

    public CategorySelection orderByLink() {
        orderBy(CategoryColumns.LINK, false);
        return this;
    }
}
