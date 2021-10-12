import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GetMenus {

    private final String date;
    private final String location;
    private final JSONObject json;
    private final String url;
    HashMap<String, HashMap<String, ArrayList<String>>> data = new HashMap<>();
    private ArrayList<String> mealPeriods = new ArrayList<>();
//    private ArrayList<String> openStations = new ArrayList<>();

    //    hard coded url for menus at different locations, menus may only be possible for chase and lenoir
//    alpine-bagel
//    top-of-lenoir
//    chase
    public GetMenus() throws IOException {
        this("chase", DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()));
    }

    public GetMenus(String diningLocation, String date) throws IOException {
//        if (diningLocation.equals("chase")) {
//            this.location = "chase";
////            https://dining.unc.edu/locations/chase/?date=2021-10-10
////            https://dining.unc.edu/locations/alpine-bagel/?date=2021-10-10
////            https://dining.unc.edu/locations/top-of-lenoir/?date=2021-10-10
//        } else {
//            this.location = "chase";
//        }
        String loc = diningLocation;
        switch (loc) {
            case "chase":
                this.location = "chase";
                break;
            case "alpine-bagel":
                this.location = "alpine-bagel";
                break;
            case "top-of-lenoir":
                this.location = "top-of-lenoir";
                break;
            default:
                this.location = "chase";
        }
        this.date = date;
        this.url = MessageFormat.format(
                "https://dining.unc.edu/locations/{1}/?date={0}", date, location);

//      sets up our scrapable object
        HttpClient client = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000).setSocketTimeout(1000).build();
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        HttpResponse response = client.execute(get);
        String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines().parallel()
                .collect(Collectors.joining("\n"));
        Document doc = Jsoup.parse(content, "", Parser.xmlParser());

//      should use key = meal period, secondkey = station, value = list of meals
        HashMap<String, HashMap<String, ArrayList<String>>> map = new HashMap<>();

        Elements openStations = doc.getElementsByClass("c-tab");
//        this works, we are able to access active data
//        Elements mealPeriods = doc.getElementsByClass("c-tabs-nav__link is-active");
        Elements mealPeriodss = doc.getElementsByClass("c-tabs-nav__link");

//      gets the meal periods for this venue
        for (Element mealPeriod : mealPeriodss) {
            Elements mealPeriodName = mealPeriod.getElementsByClass("c-tabs-nav__link-inner");
            mealPeriods.add(mealPeriodName.text());
        }

//      gets the stations open during different meal periods for this venue
        int i = 0;
        for (Element station : openStations) {
            Elements allStation = station.getElementsByClass("menu-station");

//          prints stations for the 6 differnt meal times and the dishes they currently have
            HashMap<String, ArrayList<String>> stationsDishes = new HashMap<>();
            for (Element thing : allStation) {
                Elements stationNames = thing.select("h4");
                Elements dishNames = thing.select("a");
                stationsDishes.put(stationNames.text(), new ArrayList<String>());

                for (Element dish : dishNames) {
                    stationsDishes.get(stationNames.text()).add(dish.text());
                }
            }
            map.put(mealPeriods.get(i), stationsDishes);
            i += 1;
        }
        this.json = new JSONObject(map);
        this.data = map;

//        prints map in formatted version
        for (String melp : map.keySet()) {
            System.out.println(" ");
            System.out.println(melp);

            for (String sati : map.get(melp).keySet()) {
                System.out.println(" ");
                System.out.println(sati);
                System.out.println(map.get(melp).get(sati));
            }
        }
    }

    public HashMap<String, HashMap<String, ArrayList<Boolean>>> isOnMenu(String menuItem) {
//        returns list of meal periods where menu item is present, and stations it is at
//        hashmap of first key = meal period, second key = station, value = boolean not important
        HashMap<String, HashMap<String, ArrayList<Boolean>>> presence = new HashMap<String, HashMap<String, ArrayList<Boolean>>>();
        for (String period : data.keySet()) {
            for (String station : data.get(period).keySet()){
                ArrayList<Boolean> thisStationDishes = new ArrayList<>();
                for(String dish : data.get(period).get(station)){
                    if (dish.equals(menuItem)){
                        thisStationDishes.add(true);
                        presence.put(period, null);
                        presence.get(period).put(station, thisStationDishes);
                    }
                }
            }
        }
        return presence;
    }

    public ArrayList<String> getMealPeriods() {
//        get all meal periods
        return new ArrayList<String>(mealPeriods);
    }

    public ArrayList<String> getStationsDuringPeriod(String mealPeriod) {
//        gets all stations open during the specified meal period
        ArrayList<String> openStations = new ArrayList<>();
        for (String key : data.get(mealPeriod).keySet()) {
            openStations.add(key);
        }
        return openStations;
    }

    public ArrayList<String> getMealsDuringPeriodAtStation(String mealPeriod, String station) {
//        gets all dishes served at the specified station during the specified meal period
        ArrayList<String> dishes = new ArrayList<>(data.get(mealPeriod).get(station));
        return dishes;
    }
}