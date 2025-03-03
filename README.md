# Light-weight Cache
**Overview**

The light-weight cache is an in-memory caching solution designed for microservices. It supports:
* Flexible data storage - store any type of object, including lists, maps and custom data structures.
* Time-based expiry - automatically removes stale cache entries after a configurable duration.
* Size-based eviction - maintains a maximum number of entries, using Least Recently Used (LRU) eviction.
* Thread safety - ensures safe access to cache in multi-threaded environment.

**Features**

* ✅ Simple API: put(), get(), remove() methods
* 🕒 Configurable expiration time
* 📏 Max size limits with automatic eviction
* 🔒 Thread-safe operations
* 🔄 Supports storing collections & complex objects

**Usage**

* Creating a Cache Instance
  ```FlexibleCache<List<String>> cache = new FlexibleCache<>(5, TimeUnit.MINUTES, 100);```
  First Parameter: Expiration time (e.g., 5 MINUTES)
  Second Parameter: Maximum cache size before eviction occurs (e.g., 100 items)

