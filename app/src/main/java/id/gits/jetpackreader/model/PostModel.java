package id.gits.jetpackreader.model;

import java.io.Serializable;

import id.gits.jetpackreader.api.dao.PostDao;
import id.gits.jetpackreader.provider.post.PostCursor;

/**
 * Created by ibun on 30/12/15.
 */
public class PostModel implements Serializable {
    private long id;
    private String slug;
    private String title;
    private String content;
    private String excerpt;
    private String authorName;
    private String authorImage;
    private String dateCreated;
    private String dateModified;
    private String image;
    private long commentCount;
    private long likeCount;
    private String category;
    private String url;

    public PostModel(long id, String slug, String title, String content, String excerpt, String authorName, String authorImage, String dateCreated, String dateModified, String image, long commentCount, long likeCount, String category, String url) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.authorName = authorName;
        this.authorImage = authorImage;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.image = image;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.category = category;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public static PostModel fromCursor(PostCursor cursor) {
        return new PostModel(cursor.getId(), cursor.getSlug(), cursor.getTitle(), cursor.getContent(),
                cursor.getExcerpt(), cursor.getAuthorName(), cursor.getAuthorImage(), cursor.getDateCreated(),
                cursor.getDateModified(), cursor.getImage(), cursor.getCommentCount(), cursor.getLikeCount(),
                cursor.getCategory(), cursor.getLink());
    }

    public static PostModel fromDao(PostDao dao) {
        return new PostModel(dao.getID(), dao.getSlug(), dao.getTitle(), dao.getContent(),
                dao.getExcerpt(), dao.getAuthor().getName(), dao.getAuthor().getAvatar_URL(), dao.getDate(),
                dao.getModified(), dao.getFeatured_image(), (dao.getDiscussion() != null) ? dao.getDiscussion().getComment_count() : 0,
                dao.getLike_count(), dao.getCategoryString(), dao.getURL());
    }
}
