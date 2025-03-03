package com.projectX.cache;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlexibleCache <T>{
    private final Map<String, CacheEntry<T>> cache = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private final int maxSize;

    public FlexibleCache(long ttl, TimeUnit unit, int maxSize){
        this.maxSize = maxSize;
        long ttlMillisecond = unit.toMillis(ttl);
        cleaner.scheduleAtFixedRate(() -> evictExpiredEntries(ttlMillisecond), ttlMillisecond, ttlMillisecond, TimeUnit.MILLISECONDS);
    }

    public void put(String key, T value){
        if(cache.size() >= maxSize){
            evictOldest();
        }
        cache.put(key, new CacheEntry<>(value, System.currentTimeMillis()));
    }

    public Optional<T> get(String key){
        CacheEntry<T> entry = cache.get(key);
        if(entry == null) return Optional.empty();
        return Optional.of(entry.value());
    }

    public void remove(String key){
        cache.remove(key);
    }

    public void clear(){
        cache.clear();
    }

    public int size(){
        return cache.size();
    }

    private void evictExpiredEntries(long ttlMillis){
        long now = System.currentTimeMillis();
        cache.entrySet().removeIf(entry -> now - entry.getValue().timestamp() > ttlMillis);
    }

    private void evictOldest(){
        if(cache.isEmpty()) return;
        String oldestKey = Collections.min(cache.entrySet(), Comparator.comparingLong(e -> e.getValue().timestamp())).getKey();
        cache.remove(oldestKey);
    }
}
