package id.gits.jetpackreader.api.dao;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import id.gits.jetpackreader.provider.post.PostColumns;
import id.gits.jetpackreader.provider.post.PostContentValues;
import id.gits.jetpackreader.provider.post.PostSelection;

public class PostDao implements Serializable {
    private int ID;
    private int site_ID;
    private PostListApiDao.Author author;
    private String date;
    private String modified;
    private String title;
    private String URL;
    private String short_URL;
    private String content;
    private String excerpt;
    private String slug;
    private String guid;
    private String status;
    private boolean sticky;
    private String password;
    private boolean parent;
    private String type;
    private PostListApiDao.Discussion discussion;
    private boolean likes_enabled;
    private boolean sharing_enabled;
    private int like_count;
    private boolean i_like;
    private boolean is_reblogged;
    private boolean is_following;
    private String global_ID;
    private String featured_image;
    private String format;
    private boolean geo;
    private int menu_order;
    private String page_template;
    private List<String> privateize_URLs;
    private PostListApiDao.Tags tags;
    //        private Categories categories;
    private PostListApiDao.Attachments attachments;
    private int attachment_count;
    private PostListApiDao.Meta meta;
    private PostListApiDao.Capabilities capabilities;
    private PostListApiDao.OtherURLs other_URLs;
    private Map<String, CategoryDao> categories;

    public PostDao() {
    }

    public List<String> getPrivateize_URLs() {
        return privateize_URLs;
    }

    public Map<String, CategoryDao> getCategories() {
        return categories;
    }

    public int getID() {
        return ID;
    }

    public int getSite_ID() {
        return site_ID;
    }

    public PostListApiDao.Author getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getModified() {
        return modified;
    }

    public String getTitle() {
        return title;
    }

    public String getURL() {
        return URL;
    }

    public String getShort_URL() {
        return short_URL;
    }

    public String getContent() {
        return content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getSlug() {
        return slug;
    }

    public String getGuid() {
        return guid;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSticky() {
        return sticky;
    }

    public String getPassword() {
        return password;
    }

    public boolean isParent() {
        return parent;
    }

    public String getType() {
        return type;
    }

    public PostListApiDao.Discussion getDiscussion() {
        return discussion;
    }

    public boolean isLikes_enabled() {
        return likes_enabled;
    }

    public boolean isSharing_enabled() {
        return sharing_enabled;
    }

    public int getLike_count() {
        return like_count;
    }

    public boolean isI_like() {
        return i_like;
    }

    public boolean is_reblogged() {
        return is_reblogged;
    }

    public boolean is_following() {
        return is_following;
    }

    public String getGlobal_ID() {
        return global_ID;
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public String getFormat() {
        return format;
    }

    public boolean isGeo() {
        return geo;
    }

    public int getMenu_order() {
        return menu_order;
    }

    public String getPage_template() {
        return page_template;
    }

    public PostListApiDao.Tags getTags() {
        return tags;
    }

    public PostListApiDao.Attachments getAttachments() {
        return attachments;
    }

    public int getAttachment_count() {
        return attachment_count;
    }

    public PostListApiDao.Meta getMeta() {
        return meta;
    }

    public PostListApiDao.Capabilities getCapabilities() {
        return capabilities;
    }

    public PostListApiDao.OtherURLs getOther_URLs() {
        return other_URLs;
    }

    public String getCategoryString() {
        List<String> categories = new ArrayList<>();
        for (Map.Entry<String, CategoryDao> entry : getCategories().entrySet()) {
            categories.add(entry.getValue().getID() + "");
            if (entry.getValue().getParent() != 0) {
                categories.add(entry.getValue().getParent() + "");
            }
        }
        return new Gson().toJson(categories);
    }

    public static PostContentValues toContentValues(PostDao postDao) {
        PostContentValues contentValues = new PostContentValues();
        contentValues.putSId(postDao.getID());
        contentValues.putTitle(postDao.getTitle());
        contentValues.putSlug(postDao.getSlug());
        contentValues.putAuthorImage(postDao.getAuthor().getAvatar_URL());
        contentValues.putAuthorName(postDao.getAuthor().getName());
        contentValues.putCategory(postDao.getCategoryString());
        contentValues.putContent(postDao.getContent());
        if (postDao.getDiscussion() != null)
            contentValues.putCommentCount(postDao.getDiscussion().getComment_count());
        else
            contentValues.putCommentCount(0);
        contentValues.putDateCreated(postDao.getDate());
        contentValues.putDateModified(postDao.getModified());
        contentValues.putExcerpt(postDao.getExcerpt());
        contentValues.putImage(postDao.getFeatured_image());
        contentValues.putLikeCount(postDao.getLike_count());
        contentValues.putLink(postDao.getURL());
        return contentValues;
    }

    public void saveToDb(ContentProviderClient provider) throws RemoteException {
        PostContentValues pcv = PostDao.toContentValues(this);
        PostSelection selection = new PostSelection();
        selection.sId(getID());
        Cursor cursorExists = provider.query(PostColumns.CONTENT_URI, null, selection.sel(),
                selection.args(), null);
        if (cursorExists != null) {
            if (cursorExists.getCount() > 0) {
                cursorExists.moveToFirst();
                int updatedRow = provider.update(PostColumns.CONTENT_URI, pcv.values()
                        , PostColumns._ID + "= ?"
                        , new String[]{cursorExists.getLong(cursorExists.getColumnIndex(PostColumns._ID)) + ""});
                Log.d("Post", "Updated: " + updatedRow);
            } else {
                Uri insertedUri = provider.insert(PostColumns.CONTENT_URI, pcv.values());
                Log.d("Post", "Insert: " + insertedUri);
            }
            cursorExists.close();
        }
    }

    public void saveToDb(ContentResolver provider) {
        PostContentValues pcv = PostDao.toContentValues(this);
        PostSelection selection = new PostSelection();
        selection.sId(getID());
        Cursor cursorExists = provider.query(PostColumns.CONTENT_URI, null, selection.sel(),
                selection.args(), null);
        if (cursorExists != null) {
            if (cursorExists.getCount() > 0) {
                cursorExists.moveToFirst();
                int updatedRow = provider.update(PostColumns.CONTENT_URI, pcv.values()
                        , PostColumns._ID + "= ?"
                        , new String[]{cursorExists.getLong(cursorExists.getColumnIndex(PostColumns._ID)) + ""});
                Log.d("Post", "Updated: " + updatedRow);
            } else {
                Uri insertedUri = provider.insert(PostColumns.CONTENT_URI, pcv.values());
                Log.d("Post", "Insert: " + insertedUri);
            }
            cursorExists.close();
        }
    }
}