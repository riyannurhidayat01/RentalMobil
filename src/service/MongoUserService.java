package service;

import com.mongodb.client.*;
import org.bson.Document;

public class MongoUserService {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public MongoUserService() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("rental_mobil");
        userCollection = database.getCollection("user");
    }

    public boolean registerUser(String username, String password) {
        if (isUsernameExist(username)) {
            return false;
        }
        Document newUser = new Document("username", username)
                .append("password", password);
        userCollection.insertOne(newUser);
        return true;
    }

    public boolean isUsernameExist(String username) {
        Document query = new Document("username", username);
        return userCollection.find(query).first() != null;
    }

    public boolean validateLogin(String username, String password) {
        Document query = new Document("username", username)
                .append("password", password);
        return userCollection.find(query).first() != null;
    }

    public void closeConnection() {
        mongoClient.close();
    }
}
