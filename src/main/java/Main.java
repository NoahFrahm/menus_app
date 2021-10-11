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

public class Main {
        public static void main(String[] args) throws IOException {
            GetMenus menus = new GetMenus();
//        GetHours todays_hours = new GetHours();
//        System.out.println(todays_hours.getlocations());
    }
}
//    public static void main(String[] args) throws IOException {
//        // Http Builder
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime now = LocalDateTime.now();
//        String date = dtf.format(now);
//        System.out.println(dtf.format(now));
//
//        String url = MessageFormat.format(
//                "https://dining.unc.edu/locations/chase/?date={0}", date);
//
//        HttpClient client = HttpClientBuilder.create().build();
//        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
//                .setConnectTimeout(1000).setSocketTimeout(1000).build();
//
//        HttpGet get = new HttpGet(url);
//        get.setConfig(requestConfig);
//        HttpResponse response = client.execute(get);
//
//        String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines().parallel()
//                .collect(Collectors.joining("\n"));
//        Document doc = Jsoup.parse(content, "", Parser.xmlParser());
//
////      should use key = meal period, secondkey = station, value = list of meals
//        HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> map = new HashMap<>();
//
//
//        Elements openStations = doc.getElementsByClass("c-tab");
////        this works, we are able to access active data
////        Elements mealPeriods = doc.getElementsByClass("c-tabs-nav__link is-active");
//        Elements mealPeriods = doc.getElementsByClass("c-tabs-nav__link");
//
//////      gets the meal periods for this venue
////        for (Element mealPeriod : mealPeriods) {
////            Elements mealPeriodName = mealPeriod.getElementsByClass("c-tabs-nav__link-inner");
////            System.out.println(mealPeriodName.text());
//////            System.out.println(venue);
//////            System.out.println("done");
////        }
//
////      gets the stations open during different meal periods for this venue
//        for (Element station : openStations) {
//            Elements stationName = station.getElementsByClass("toggle-menu-station-data");
//            System.out.println(stationName);
//
////            System.out.println(stationName.text());
////            System.out.println(venue);
////            System.out.println("done");
//        }
////            Elements restaurants = group.getElementsByClass("open-now-location-link");
////            Elements hours = group.getElementsByClass("hours-info");
////            map.put(venue.text(), new HashMap<>());
////            ArrayList<String> keys = new ArrayList<>();
////
////            for (Element restaurant : restaurants) {
////                map.get(venue.text()).put(restaurant.text(), new ArrayList<>());
////                keys.add(restaurant.text());
////            }
////
////            int i = 0;
////
////            for (Element times : hours) {
////                ArrayList<String> name_open_close = new ArrayList<>();
////                ArrayList<ArrayList<String>> hr_info = new ArrayList<>();
////                Elements time_info = times.getElementsByTag("span");
////
//////              makes list of meal times at one location
////                for (Element info : time_info) {
////                    name_open_close.add(info.text());
//////                    System.out.println(name_open_close);
////                    if (name_open_close.size() == 3) {
////                        map.get(venue.text()).get(keys.get(i)).add(name_open_close);
////                        name_open_close = new ArrayList<String>();
////                    }
////                }
////                i += 1;
////            }
////        }
//////        here we convert our map to json
////        JSONObject json =  new JSONObject(map);
////        System.out.println(json);
//
//////        this code prints out the map in formatted style
////        for (String key : map.keySet()) {
//////            System.out.println("Today at " + key + " we have:");
//////            System.out.println(map.get(key));
////
////            for (String locations : map.get(key).keySet()) {
////                ArrayList<ArrayList<String>> times = map.get(key).get(locations);
//////                System.out.println(locations);
//////                System.out.println(times);
////
////                for (int i = 0; i < times.size(); i++) {
////                    ArrayList<String> hours = times.get(i);
//////                    times.get(i);
////                    System.out.println(locations + " open for " + hours.get(0) + " from " + hours.get(1) + " to " + hours.get(2));
////                }
////            }
////
////        }
//    }
//}


