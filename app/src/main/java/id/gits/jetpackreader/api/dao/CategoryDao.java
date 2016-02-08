package id.gits.jetpackreader.api.dao;

import id.gits.jetpackreader.api.jetpack.GetCategoriesApi;
import id.gits.jetpackreader.provider.category.CategoryContentValues;
import id.gits.jetpackreader.provider.category.CategoryCursor;

public class CategoryDao {
    private long ID;
    private String name;
    private String slug;
    private String description;
    private long post_count;
    private long parent;
    private GetCategoriesApi.Meta meta;

    public static CategoryContentValues toContentValues(CategoryDao categoryDao) {
        CategoryContentValues contentValues = new CategoryContentValues();
        contentValues.putSId(categoryDao.getID());
        contentValues.putName(categoryDao.getName());
        contentValues.putSlug(categoryDao.getSlug());
        contentValues.putDescription(categoryDao.getDescription());
        contentValues.putPostCount(categoryDao.getPost_count());
        contentValues.putParent(categoryDao.getParent());
        contentValues.putLink(categoryDao.getMeta().getLinks().getSelf());
        return contentValues;
    }

    public static CategoryDao fromCursor(CategoryCursor cursor) {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.ID = cursor.getSId();
        categoryDao.name = cursor.getName();
        categoryDao.slug = cursor.getSlug();
        categoryDao.description = cursor.getDescription();
        categoryDao.post_count = cursor.getPostCount();
        categoryDao.parent = cursor.getParent();
        return categoryDao;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public long getPost_count() {
        return post_count;
    }

    public long getParent() {
        return parent;
    }

    public GetCategoriesApi.Meta getMeta() {
        return meta;
    }
}