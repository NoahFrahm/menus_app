import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirebasePushFunctions {
//    private Firestore db;

    public void FirebasePushFunctions() throws IOException {
//        InputStream serviceAccount = new FileInputStream("/Users/noahfrahm/Library/Mobile Documents" +
//                "/com~apple~CloudDocs/VScode workspaces/menus_app/menu-app-1bc31-" +
//                "firebase-adminsdk-55ja5-02744579fd.json");
//        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .build();
//        FirebaseApp.initializeApp(options);
//        this.db = FirestoreClient.getFirestore();
    }

    public void pushFullMenus(int infoAmount) throws InterruptedException, ExecutionException, IOException {
//        ArrayList<String> locations
//        this will push full menu for lenoir,chase,alpine
        ArrayList<String> namedLoc = new ArrayList<>(List.of("chase", "top-of-lenoir", "alpine-bagel"));
//        ArrayList<String> namedLoc = new ArrayList<>(locations);
        int infosize = infoAmount;

        InputStream serviceAccount = new FileInputStream("/Users/noahfrahm/Library/Mobile Documents" +
                "/com~apple~CloudDocs/VScode workspaces/menus_app/menu-app-1bc31-" +
                "firebase-adminsdk-55ja5-02744579fd.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat keyFormat = new SimpleDateFormat("yyyyMMdd");

        Date curDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        for (int i = 1; i <= infosize; i++) {
//            add if so we can fetch current date, implement smart popping later
            int days = 1;
            cal.add(Calendar.DATE, days);
            String fetchDate = dateFormat.format(cal.getTime());
            String keyDate = keyFormat.format(cal.getTime());

            System.out.println(fetchDate);
            HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>> info = new HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>();
            int maxErrors = 5;

            Thread.sleep(1000);
            for (int j = 0; j <= maxErrors; j++) {
                try {
                    GetMenus chaseMenus = new GetMenus("chase", fetchDate);
                    info.put("chase", chaseMenus.getData());
                    break;
                } catch (IOException e) {
                    System.out.println(j);
                }
            }
            Thread.sleep(1000);
            for (int k = 0; k <= maxErrors; k++) {
                try {
                    GetMenus lenoirMenus = new GetMenus("top-of-lenoir", fetchDate);
                    info.put("top-of-lenoir", lenoirMenus.getData());
                    break;
                } catch (IOException e) {
                    System.out.println(k);
                }
            }
            Thread.sleep(1000);
            for (int l = 0; l <= maxErrors; l++) {
                try {
                    GetMenus alpineMenus = new GetMenus("alpine-bagel", fetchDate);
                    info.put("alpine", alpineMenus.getData());
                    break;
                } catch (IOException e) {
                    System.out.println(l);
                }
            }
            DocumentReference docRef = db.collection("Full Menus").document(keyDate);
            ApiFuture<WriteResult> result = docRef.set(info);
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
    }

    public void pushVenueHours(int infoAmount) throws IOException, InterruptedException, ExecutionException {
//        this will push a list of full hours for locations open each day
        int infosize = infoAmount;

        InputStream serviceAccount = new FileInputStream("/Users/noahfrahm/Library/Mobile Documents" +
                "/com~apple~CloudDocs/VScode workspaces/menus_app/menu-app-1bc31-" +
                "firebase-adminsdk-55ja5-02744579fd.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat keyFormat = new SimpleDateFormat("yyyyMMdd");

        Date curDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        for (int i = 1; i <= infosize; i++) {
//            add if so we can fetch current date, implement smart popping later
            int days = 1;
            cal.add(Calendar.DATE, days);
            String fetchDate = dateFormat.format(cal.getTime());
            String keyDate = keyFormat.format(cal.getTime());

            System.out.println(fetchDate);
//            HashMap<String, HashMap<String, ArrayList<String>>> info = new HashMap<String, HashMap<String, ArrayList<String>>>();
            HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>> info = new HashMap<>();
            int maxErrors = 5;

            Thread.sleep(1000);
            for (int j = 0; j <= maxErrors; j++) {
                try {
                    GetHours dailyhours = new GetHours(fetchDate);
                    info = dailyhours.getData();
                    break;
                } catch (IOException e) {
                    System.out.println(j);
                }
            }

            DocumentReference docRef = db.collection("Daily Hours").document(keyDate);
            ApiFuture<WriteResult> result = docRef.set(info);
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
    }
}

