package co.edu.escuelaing.RoundRobin;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ServiceDAO {

    private final MongoCollection<Document> logCollection;

    public ServiceDAO(MongoDatabase database) {

        this.logCollection = database.getCollection("log");
    }

    public void addLog(String message) {
        Document newLog = new Document("message: ", message)
                .append(", timestamp: ", System.currentTimeMillis());
        logCollection.insertOne(newLog);
    }

    public List<String> listLogs(int page) {
        List<String> logs = new ArrayList<>();
        logCollection.find().skip(page * 10).limit(10).forEach(log -> logs.add(log.toJson()));
        return logs;
    }

    public void updateLog(String message) {
        // En caso de que sea necesario actualizar un log
    }

    public void deleteLog(String message) {
        logCollection.deleteOne(eq("message", message));
    }
}

