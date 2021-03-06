package com.capslock.stackoverflow;

import com.capslock.stackoverflow.mapper.TagsMapper;
import com.capslock.stackoverflow.mapper.UserMapper;
import com.capslock.stackoverflow.model.Tag;
import com.capslock.stackoverflow.model.User;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvin.
 */
@SpringBootApplication
public class Boot implements CommandLineRunner {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagsMapper tagsMapper;

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException, ParseException {

        SpringApplication app = new SpringApplication(Boot.class);
        app.setWebEnvironment(false);
        app.run(args);
    }

    public static String nullToEmpty(final String data) {
        if (Strings.isNullOrEmpty(data)) {
            return "''";
        }
        return data;
    }

    public void batchInsertTags() throws IOException, XMLStreamException {
        final File dataFile = new File("G:\\st\\Tags.xml");
        try (final FileInputStream inputStream = new FileInputStream(dataFile)) {
            final XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
            final List<Tag> tags = new ArrayList<>(1000);
            while (reader.hasNext()) {
                if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getName().toString().equals("row")) {
                    final int attributeCount = reader.getAttributeCount();
                    if (attributeCount > 0) {
                        try {
                            final Tag tag = readTagEntity(reader, attributeCount);
                            tags.add(tag);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (tags.size() >= 1000) {
                        tagsMapper.insertBatch(tags);
                        tags.clear();
                    }
                }
                reader.next();
            }
            if (!tags.isEmpty()) {
                tagsMapper.insertBatch(tags);
                tags.clear();
            }
            reader.close();
        }
    }

    private Tag readTagEntity(final XMLStreamReader reader, final int attributeCount) {
        final Tag tag = new Tag();
        for (int i = 0; i < attributeCount; i++) {
            final String value = Strings.nullToEmpty(reader.getAttributeValue(i));
            switch (reader.getAttributeName(i).toString()) {
                case "Id":
                    tag.setId(Integer.parseInt(value));
                    break;
                case "TagName":
                    tag.setTagName(value);
                    break;
                case "Count":
                    tag.setCount(Integer.parseInt(value));
                    break;
                case "ExcerptPostId":
                    tag.setExcerptPostId(Long.parseLong(value));
                    break;
                case "WikiPostId":
                    tag.setWikiPostId(Long.parseLong(value));
                    break;
                default:
                    break;
            }
        }
        return tag;
    }

    public void run(final String... args) throws Exception {
        batchInsertTags();
        batchInsertUser();
    }

    private void batchInsertUser() throws FileNotFoundException, XMLStreamException, ParseException {
        final File dataFile = new File("G:\\stackoverflow.com-Users\\Users.xml");
        final FileInputStream inputStream = new FileInputStream(dataFile);
        final XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
        //start document
        reader.next();
        //read users
        reader.next();
        //start document
        reader.next();
        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        final ArrayList<User> users = new ArrayList<>(1000);
        do {
            final int attrCount = reader.getAttributeCount();
            final User.UserBuilder userBuilder = User.builder();
            for (int i = 0; i < attrCount; i++) {
                final String name = reader.getAttributeName(i).toString();
                final String value = nullToEmpty(reader.getAttributeValue(i));
                switch (name) {
                    case "Id":
                        userBuilder.id(Integer.parseInt(value));
                        break;
                    case "Reputation":
                        userBuilder.reputation(Integer.parseInt(value));
                        break;
                    case "CreationDate":
                        userBuilder.creationDate(formatter.parse(value).getTime());
                        break;
                    case "DisplayName":
                        userBuilder.displayName(value);
                        break;
                    case "LastAccessDate":
                        userBuilder.lastAccessDate(formatter.parse(value).getTime());
                        break;
                    case "WebsiteUrl":
                        userBuilder.websiteUrl(value);
                        break;
                    case "AboutMe":
                        userBuilder.aboutMe(value);
                        break;
                    case "Location":
                        userBuilder.location(value);
                        break;
                    case "Views":
                        userBuilder.views(Integer.parseInt(value));
                        break;
                    case "UpVotes":
                        userBuilder.upVotes(Integer.parseInt(value));
                        break;
                    case "DownVotes":
                        userBuilder.downVotes(Integer.parseInt(value));
                        break;
                    case "AccountId":
                        userBuilder.accountId(Integer.parseInt(value));
                        break;
                    case "Age":
                        userBuilder.age(Integer.parseInt(value));
                        break;
                }
            }
            final User user = userBuilder.build();
            users.add(user);
            reader.next();
            reader.next();
            reader.next();
            if (users.size() >= 1000) {
                userMapper.insertBatch(users);
                users.clear();
            }
        } while (reader.hasNext());
    }
}
