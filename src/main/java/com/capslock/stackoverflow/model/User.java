package com.capslock.stackoverflow.model;

/**
 * Created by alvin.
 */
public class User {
    private final int id;
    private int reputation = 0;
    private String displayName = "''";
    private long creationDate;
    private long lastAccessDate;
    private String websiteUrl = "''";
    private String location = "''";
    private String aboutMe = "''";
    private int views = 0;
    private int upVotes = 0;
    private int downVotes = 0;
    private int accountId = 0;
    private int age = -1;

    public User(final int id, final int reputation, final String displayName, final long creationDate, final long lastAccessDate,
            final String websiteUrl, final String location, final String aboutMe, final int views, final int upVotes, final int downVotes,
            final int accountId, final int age) {
        this.id = id;
        this.reputation = reputation;
        this.displayName = displayName;
        this.creationDate = creationDate;
        this.lastAccessDate = lastAccessDate;
        this.websiteUrl = websiteUrl;
        this.location = location;
        this.aboutMe = aboutMe;
        this.views = views;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.accountId = accountId;
        this.age = age;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private int id;
        private int reputation = 0;
        private String displayName = "''";
        private long creationDate;
        private long lastAccessDate;
        private String websiteUrl = "''";
        private String location = "''";
        private String aboutMe = "''";
        private int views = 0;
        private int upVotes = 0;
        private int downVotes = 0;
        private int accountId = 0;
        private int age = -1;

        public UserBuilder id(final int id) {
            this.id = id;
            return this;
        }

        public UserBuilder reputation(final int reputation) {
            this.reputation = reputation;
            return this;
        }

        public UserBuilder displayName(final String displayName) {
            this.displayName = displayName;
            return this;
        }

        public UserBuilder creationDate(final long date) {
            this.creationDate = date;
            return this;
        }

        public UserBuilder lastAccessDate(final long date) {
            this.lastAccessDate = date;
            return this;
        }

        public UserBuilder websiteUrl(final String url) {
            this.websiteUrl = url;
            return this;
        }

        public UserBuilder location(final String location) {
            this.location = location;
            return this;
        }

        public UserBuilder aboutMe(final String aboutMe) {
            this.aboutMe = aboutMe;
            return this;
        }

        public UserBuilder views(final int views) {
            this.views = views;
            return this;
        }

        public UserBuilder upVotes(final int upVotes) {
            this.upVotes = upVotes;
            return this;
        }

        public UserBuilder downVotes(final int downVotes) {
            this.downVotes = downVotes;
            return this;
        }

        public UserBuilder accountId(final int accountId) {
            this.accountId = views;
            return this;
        }

        public UserBuilder age(final int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(id, reputation, displayName, creationDate, lastAccessDate, websiteUrl, location, aboutMe,
                    views, upVotes, downVotes, accountId, age);
        }
    }

}
