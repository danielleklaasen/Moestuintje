package com.danielleklaasen.moestuintje.plants;

import com.danielleklaasen.moestuintje.model.PlantItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantsDataProvider {
    // 2 data collections
    public static List<PlantItem> dataItemList; // listItems are instances of dataItem class
    public static Map<String, PlantItem> dataItemMap; // key, associated value

    static {
        // initialize both objects: List + Map
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new PlantItem(null, "Zucchini", 0, "zucchini.jpg", "vegetable"));
        addItem(new PlantItem(null, "Tomato", 0, "tomato.jpg", "vegetable"));
        addItem(new PlantItem(null, "Garlic", 0, "garlic.jpg", "vegetable"));
        addItem(new PlantItem(null, "Strawberry", 0, "strawberry.jpg", "fruit"));
        addItem(new PlantItem(null, "Raspberry", 0, "raspberry.jpg", "fruit"));
        addItem(new PlantItem(null, "Potato", 1, "potato.jpg", "vegetable"));
    }

    // creating instance of DataItem object and pass/reference it on to list + map
    private static void addItem(PlantItem item){
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }
}
