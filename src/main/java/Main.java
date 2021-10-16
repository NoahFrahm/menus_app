import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
//        GetMenus menus = new GetMenus("top-of-lenoir");
//        "yyyy/MM/dd"
//        GetMenus menus = new GetMenus("chase");
//        HashMap<String, HashMap<String, ArrayList<String>>> info = menus.getData();

        InputStream serviceAccount = new FileInputStream("/Users/noahfrahm/Library/Mobile Documents" +
                "/com~apple~CloudDocs/VScode workspaces/menus_app/menu-app-1bc31-" +
                "firebase-adminsdk-55ja5-02744579fd.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        ArrayList<String> namedLoc = new ArrayList<>(List.of("chase", "top-of-lenoir", "alpine-bagel"));

        int infosize = 3;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat keyFormat = new SimpleDateFormat("yyyyMMdd");

        Date curDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
//        HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> info = new HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>>();
        for (int i = 1; i <= infosize; i++) {
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


//        GetMenus menus = new GetMenus("chase", "2021/09/27");
//        System.out.println(menus.getMealsDuringPeriodAtStation("Dinner (5pm-8pm)", "The Griddle"));


// Use the application default credentials
        // Use a service account
//        InputStream serviceAccount = new FileInputStream("/Users/noahfrahm/Library/Mobile Documents" +
//                "/com~apple~CloudDocs/VScode workspaces/menus_app/menu-app-1bc31-" +
//                "firebase-adminsdk-55ja5-02744579fd.json");
//        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .build();
//        FirebaseApp.initializeApp(options);
//        Firestore db = FirestoreClient.getFirestore();
//
//        DocumentReference docRef = db.collection("users").document("1");
////        DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()
//        // Add document data  with id "alovelace" using a hashmap
////        Map<String, Object> data = new HashMap<>();
////        data.put("first", "Ada");
////        data.put("last", "Lovelace");
////        data.put("born", 185);
//        //asynchronously write data
//        ApiFuture<WriteResult> result = docRef.set(info);
//
//        // result.get() blocks on response
//        System.out.println("Update time : " + result.get().getUpdateTime());


//        ArrayList<String> normGriddle = new ArrayList<String>(List.of(
//                "Local Grass-fed Burger Patties",
//                "Grilled Cheese Sandwich", "Garden Burger Patty",
//                "Black Bean Burger", "Shoestring Fries", "American Cheese",
//                "Vegan Cheddar Cheese", "Hamburger Bun", "Udi's Hamburger Bun"));


//            System.out.println(menus.getMealPeriods());
//            ArrayList<String> periods = menus.getMealPeriods();


//       GetHours todays_hours = new GetHours();
//       Spicy Crispy Chicken Sandwich
//       add code to create boolean hasmap of favorited items that are in the dining hall for each day


//            the griddle has a standard rotation, so does late night. alert on change in these stations
//            a system for favorites in other stations
    }
}
