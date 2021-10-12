import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//            GetMenus menus = new GetMenus("top-of-lenoir");
//        "yyyy/MM/dd"
        GetMenus menus = new GetMenus("chase", "2021/09/27");
        System.out.println(menus.getMealsDuringPeriodAtStation("Dinner (5pm-8pm)", "The Griddle"));

        ArrayList<String> normGriddle = new ArrayList<String>(List.of(
                "Local Grass-fed Burger Patties",
                "Grilled Cheese Sandwich", "Garden Burger Patty",
                "Black Bean Burger", "Shoestring Fries", "American Cheese",
                "Vegan Cheddar Cheese", "Hamburger Bun", "Udi's Hamburger Bun"));

//            System.out.println(menus.getMealPeriods());
//            ArrayList<String> periods = menus.getMealPeriods();


//        GetHours todays_hours = new GetHours();
//            Spicy Crispy Chicken Sandwich
//       add code to create boolean hasmap of favorited items that are in the dining hall for each day

//            the griddle has a standard rotation, so does late night. alert on change in these stations
//            a system for favorites in other stations
    }
}
