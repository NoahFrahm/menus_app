import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FirebasePushFunctions push = new FirebasePushFunctions();
//        push.pushVenueHours(6);
//        push.pushFullMenus(6);
//        push.hoursTwoCleanup();
        push.hoursCleanup();


//        build function to delete x first entries while also ensuring that data amount is not below y
//        ie we delete the first 13 entries after adding 25 entries to our existing 18 in order to keep entries at
//        minimum to 30 but also to clean up and tuck away old data

//        use get venues to adjust for the empty dictionary here
//        push.pushFullMenus(6);

//        GetMenus menus = new GetMenus("chase", "2021/09/27");
//        GetMenus menus2 = new GetMenus("top-of-lenoir", "2021/09/27");
//        GetMenus menus3 = new GetMenus("alpine-bagel", "2021/09/27");

//        GetHours hours = new GetHours();
//        System.out.println(menus.getData());
//        System.out.println(hours.getData());

//        push.pushVenueHours(4);
    }
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


