package com.nykj.base.example.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cq on 4/20/16.
 */
public class MapBuildUtil<K, V> {

    private Map<K, V> map;

    public MapBuildUtil() {
        this.map = new HashMap<K, V>();
    }

    public MapBuildUtil<K, V> put(K key, V value) {
        this.map.put(key, value);
        return this;
    }

    public Map<K, V> build() {
        return this.map;
    }

}
