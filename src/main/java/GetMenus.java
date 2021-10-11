import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
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
import java.util.stream.Collectors;

public class GetMenus {

    private final String date;
    private final String location;
    //    private final JSONObject json;
    private final String url;
    HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> map = new HashMap<>();
//    HashMap<String, ArrayList<ArrayList<String>> stationsDishes = new HashMap<>();

    private ArrayList<String> mealPeriods = new ArrayList<>();
    //    make hashmap to store all unique stations
    private ArrayList<String> openStations = new ArrayList<>();

    //    hard coded url for menus at different locations, menus may only be possible for chase and lenoir
//    alpine-bagel
//    top-of-lenoir
//    chase
    public GetMenus() throws IOException {
        this("chase", DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()));
    }

    public GetMenus(String diningLocation, String date) throws IOException {
        if (diningLocation.equals("chase")) {
            this.location = "chase";
//            https://dining.unc.edu/locations/chase/?date=2021-10-10
//            https://dining.unc.edu/locations/alpine-bagel/?date=2021-10-10
//            https://dining.unc.edu/locations/top-of-lenoir/?date=2021-10-10
        } else {
            this.location = "chase";
        }
        this.date = date;
        this.url = MessageFormat.format(
                "https://dining.unc.edu/locations/{1}/?date={0}", date, location);

//        sets up our scrapable object
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
        HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> map = new HashMap<>();


        Elements openStations = doc.getElementsByClass("c-tab");
//        this works, we are able to access active data
//        Elements mealPeriods = doc.getElementsByClass("c-tabs-nav__link is-active");
        Elements mealPeriodss = doc.getElementsByClass("c-tabs-nav__link");

//      gets the meal periods for this venue
        for (Element mealPeriod : mealPeriodss) {
            Elements mealPeriodName = mealPeriod.getElementsByClass("c-tabs-nav__link-inner");
            mealPeriods.add(mealPeriodName.text());
//            System.out.println(mealPeriodName.text());
//            System.out.println(venue);
//            System.out.println("done");
        }

//      gets the stations open during different meal periods for this venue
        for (Element station : openStations) {
            System.out.println(" ");
            Elements allStation = station.getElementsByClass("menu-station");

//          prints stations for the 6 differnt meal times and the dishes they currently have
            HashMap<String, ArrayList<String>> stationsDishes = new HashMap<>();
            for (Element thing : allStation) {
                Elements stationNames = thing.select("h4");
                Elements dishNames = thing.select("a");
//                System.out.println(" ");
//                System.out.println(stationNames.text());
                stationsDishes.put(stationNames.text(),new ArrayList<String>());
//                System.out.println(" ");

                for (Element dish : dishNames) {
                    stationsDishes.get(stationNames.text()).add(dish.text());
//                    System.out.println(dish.text());
                }
            }
            System.out.println(stationsDishes);
            System.out.println(" ");
        }
    }
}


