package com.danielleklaasen.moestuintje.data;

import com.danielleklaasen.moestuintje.R;
import com.danielleklaasen.moestuintje.model.SpecieItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecieDataProvider {
    // 2 data collections
    public static List<SpecieItem> specieItemList; // listItems are instances of dataItem class
    public static Map<String, SpecieItem> specieItemMap; // key, associated value

    static {
        // initialize both objects: List + Map
        specieItemList = new ArrayList<>();
        specieItemMap = new HashMap<>();

        addItem(new SpecieItem(null, "Zucchini", R.drawable.zucchini));
        addItem(new SpecieItem(null, "Tomato", R.drawable.tomato));
        addItem(new SpecieItem(null, "Garlic", R.drawable.garlic));
        addItem(new SpecieItem(null, "Strawberry", R.drawable.strawberry));
        addItem(new SpecieItem(null, "Raspberry", R.drawable.raspberry));
        addItem(new SpecieItem(null, "Potato", R.drawable.potato));
    }

    // creating instance of DataItem object and pass/reference it on to list + map
    private static void addItem(SpecieItem item){
        specieItemList.add(item);
        specieItemMap.put(item.getItemId(), item);
    }
}
