package com.projectX.cache;

public record CacheEntry <T> (T value, long timestamp){ }
