package id.gits.jetpackreader.api.dao;

import java.io.Serializable;
import java.util.List;

public class PostListApiDao extends BaseApiDao {
    private int found;
    private List<PostDao> posts;

    public class Author implements Serializable{
        private int ID;
        private String login;
        private boolean email;
        private String name;
        private String first_name;
        private String last_name;
        private String nice_name;
        private String URL;
        private String avatar_URL;
        private String profile_URL;

        public int getID() {
            return ID;
        }

        public String getLogin() {
            return login;
        }

        public boolean isEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getNice_name() {
            return nice_name;
        }

        public String getURL() {
            return URL;
        }

        public String getAvatar_URL() {
            return avatar_URL;
        }

        public String getProfile_URL() {
            return profile_URL;
        }
    }

    public class Discussion implements Serializable{
        private boolean comments_open;
        private String comment_status;
        private boolean pings_open;
        private String ping_status;
        private int comment_count;

        public boolean isComments_open() {
            return comments_open;
        }

        public String getComment_status() {
            return comment_status;
        }

        public boolean isPings_open() {
            return pings_open;
        }

        public String getPing_status() {
            return ping_status;
        }

        public int getComment_count() {
            return comment_count;
        }
    }

    public class Tags implements Serializable {
    }

    public class Meta {
        public Links links;
    }

    public class Attachments implements Serializable{
    }

    public class Links {
        private String self;
        private String help;
        private String site;
        private String replies;
        private String likes;
    }

    public class Capabilities {
        private boolean publish_post;
        private boolean delete_post;
        private boolean edit_post;
    }

    public class OtherURLs {
    }

    public int getFound() {
        return found;
    }

    public List<PostDao> getPosts() {
        return posts;
    }
}