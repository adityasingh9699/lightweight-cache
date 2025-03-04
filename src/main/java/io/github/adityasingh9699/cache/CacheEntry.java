package io.github.adityasingh9699.cache;

public record CacheEntry <T>(T value, long timestamp) {
}