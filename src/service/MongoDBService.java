package service;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import model.Mobil;
import org.bson.Document;

public class MongoDBService {
    private static final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private static final MongoDatabase db = client.getDatabase("rental_mobil");

    public static void simpanMobil(Mobil m) {
        Document doc = new Document("plat", m.getPlatNomor())
            .append("merk", m.getMerk())
            .append("tahun", m.getTahun())
            .append("tersedia", m.isTersedia());

        MongoCollection<Document> collection = db.getCollection("mobil");
        collection.insertOne(doc);
    }
}
