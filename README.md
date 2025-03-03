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

* Adding Data to Cache

  ```cache.put("fruits", List.of("Apple", "Banana", "Mango"));```

* Retrieving Data

  ```Optional<List<String>> cachedFruits = cache.get("fruits");```

  ```System.out.println(cachedFruits.orElse(Collections.emptyList()));```

* Expiry & Eviction Behavior

  ```Thread.sleep(6000); // Simulate delay (6 sec)```

  ```System.out.println(cache.get("fruits")); // Expected: Empty (Expired!)```


**📜 License**

This project is licensed under the MIT License.

**📬 Contact**

If you have any questions or issues, feel free to reach out!

* Author: Aditya Kumar Singh

* Email: adityasingh.aks96@gmail.com

* GitHub: github.com/aditya-singh-9699







