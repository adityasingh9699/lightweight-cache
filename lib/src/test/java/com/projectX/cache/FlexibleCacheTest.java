package com.projectX.cache;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FlexibleCacheTest {

    @Test
    public void testCacheStoresAndRetrievesData(){
        FlexibleCache<List<String>> cache = new FlexibleCache<>(5, TimeUnit.MINUTES, 10);
        cache.put("fruits", List.of("apple", "orange", "grapes"));
        cache.put("vegetables", List.of("potato", "tomato", "cabbage"));
        cache.put("cars", List.of("Audi", "BMW", "Mercedes"));
        cache.put("bikes", List.of("yamaha", "ducati", "honda"));
        Optional<List<String>> fruits = cache.get("fruits");
        Optional<List<String>> vegetables = cache.get("vegetables");
        Optional<List<String>> cars = cache.get("cars");
        Optional<List<String>> bikes = cache.get("bikes");

        assertTrue(fruits.isPresent());
        assertTrue(vegetables.isPresent());
        assertTrue(cars.isPresent());
        assertTrue(bikes.isPresent());

        assertEquals(List.of("apple", "orange", "grapes"), fruits.get());
        assertEquals(List.of("potato", "tomato", "cabbage"), vegetables.get());
        assertEquals(List.of("Audi", "BMW", "Mercedes"), cars.get());
        assertEquals(List.of("yamaha", "ducati", "honda"), bikes.get());
    }

    @Test
    public void testCacheExpiration() throws InterruptedException {
        FlexibleCache<List<String>> cache = new FlexibleCache<>(2, TimeUnit.SECONDS, 2);
        cache.put("fruits", List.of("apple", "banana"));

        Thread.sleep(3000);
        Optional<List<String>> value = cache.get("fruits");
        assertTrue(value.isEmpty());

    }

    @Test
    public void testCacheMaxSizeEviction() {
        FlexibleCache<String> cache = new FlexibleCache<>(5, TimeUnit.MINUTES, 2);
        cache.put("k1", "v1");
        cache.put("k2", "v2");
        cache.put("k3", "v3");  // This should evict the oldest (k1)

        assertTrue(cache.get("k1").isEmpty());
        assertTrue(cache.get("k2").isPresent());
        assertTrue(cache.get("k3").isPresent());
    }
}
