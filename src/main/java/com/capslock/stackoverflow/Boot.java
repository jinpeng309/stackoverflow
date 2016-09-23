package com.capslock.stackoverflow;

import com.capslock.stackoverflow.mapper.UserMapper;
import com.capslock.stackoverflow.model.User;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by alvin.
 */
@SpringBootApplication
public class Boot implements CommandLineRunner {
    @Autowired
    private UserMapper userMapper;

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

    public void run(final String... args) throws Exception {
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
