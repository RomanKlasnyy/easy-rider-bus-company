import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EasyRider {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);
        JsonNode data = objectMapper.readTree(scanner.nextLine());

        Map<String, List<String>> stopsByType = new HashMap<>();
        for (JsonNode node : data) {
            String stopType = node.get("stop_type").asText();
            String stopName = node.get("stop_name").asText();
            stopsByType.computeIfAbsent(stopType, k -> new ArrayList<>()).add(stopName);
        }

        List<String> transferStops = new ArrayList<>();
        Map<String, Integer> stopCounts = new HashMap<>();
        for (List<String> stopList : stopsByType.values()) {
            for (String name : stopList) {
                stopCounts.put(name, stopCounts.getOrDefault(name, 0) + 1);
                if (stopCounts.get(name) > 1) {
                    transferStops.add(name);
                }
            }
        }

        System.out.println("On demand stops test:");
        List<String> results = new ArrayList<>();
        for (String stop : stopsByType.get("O")) {
            for (String tStop : new String[]{"S", "F", ""}) {
                for (String transfer : transferStops) {
                    if (contains(stopsByType.get(tStop), transfer) || contains(stopsByType.get(""), transfer)) {
                        results.add(stop);
                        break;
                    }
                }
            }
        }

        if (!results.isEmpty()) {
            System.out.println("Wrong stop type: " + results);
        } else {
            System.out.println("OK");
        }
    }

    private static boolean contains(List<String> s, String e) {
        return s != null && s.contains(e);
    }
}
