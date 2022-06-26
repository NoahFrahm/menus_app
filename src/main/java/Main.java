import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FirebasePushFunctions push = new FirebasePushFunctions();
        Date datum = new Date();
//        System.out.println(datum);

        push.hoursCleanup(false);
//        push.pushVenueHours(6, datum);
//        push.hoursCleanup(true);

//        push.pushFullMenus(6);
//        push.hoursTwoCleanup();
//        push.hoursCleanup();


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


