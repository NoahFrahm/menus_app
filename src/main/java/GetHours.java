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
import java.util.stream.Collectors;

public class GetHours {

    private final String date;
    private final JSONObject json;
    private final String url;
    HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> map = new HashMap<>();
    private ArrayList<String> venues = new ArrayList<>();
    private ArrayList<String> restaurants = new ArrayList<>();

    public GetHours() throws IOException {
        this(DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()));
    }

    public GetHours(String date) throws IOException {
        this.date = date;
        this.url = MessageFormat.format(
                "https://dining.unc.edu/menu-hours/?date={0}", date);

        HttpClient client = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000).setSocketTimeout(1000).build();

        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        HttpResponse response = client.execute(get);

        String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines().parallel()
                .collect(Collectors.joining("\n"));

        Document doc = Jsoup.parse(content, "", Parser.xmlParser());
        Elements groups = doc.getElementsByClass("location-group-wrap");
        HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> map = new HashMap<>();

        for (Element group : groups) {
            Elements venue = group.getElementsByClass("location-venue-name");
            Elements restaurants = group.getElementsByClass("open-now-location-link");
            Elements hours = group.getElementsByClass("hours-info");
            map.put(venue.text(), new HashMap<>());
            this.venues.add(venue.text());

            ArrayList<String> keys = new ArrayList<>();

            for (Element restaurant : restaurants) {
                this.restaurants.add(restaurant.text());

                map.get(venue.text()).put(restaurant.text(), new ArrayList<>());
                keys.add(restaurant.text());
            }
            int i = 0;
            for (Element times : hours) {
                ArrayList<String> name_open_close = new ArrayList<>();
                ArrayList<ArrayList<String>> hr_info = new ArrayList<>();
                Elements time_info = times.getElementsByTag("span");

//              makes list of meal times at one location
                for (Element info : time_info) {
                    name_open_close.add(info.text());
                    if (name_open_close.size() == 3) {
                        map.get(venue.text()).get(keys.get(i)).add(name_open_close);
                        name_open_close = new ArrayList<String>();
                    }
                }
                i += 1;
            }
        }
        this.json = new JSONObject(map);
        this.map = map;
    }

    public ArrayList<String> getVenueLocations(String venue) {
//        get locations at specific venue
        ArrayList<String> locations = new ArrayList<String>();
        for (String key : map.get(venue).keySet()) {
            locations.add(key);
        }
        return locations;
    }

    public ArrayList<ArrayList<String>> getVenueLocationHours(String venue, String location) {
//        get hours of locations at a specific venue
        ArrayList<ArrayList<String>> hours = new ArrayList<ArrayList<String>>();
        for (ArrayList<String> hour : map.get(venue).get(location)) {
            hours.add(hour);
        }
        return hours;
    }

    public ArrayList<String> getVenues() {
//        get venues that are open
        return new ArrayList<String>(venues);
    }

    public ArrayList<String> getlocations() {
//        get locations that are open
        return new ArrayList<String>(restaurants);
    }

    public JSONObject getJson(){
//        get json object of this scraped data
        return new JSONObject(this.map);
    }

    public HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> getData(){
//        get json object of this scraped data
        return new HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>>(this.map);
    }
}

