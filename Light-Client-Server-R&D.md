## Research and Development for Light Client Server

This Research and Development technical paper is created to help alleviate some bottlenecks within and during the light client server build for Teku.

# [Server](https://github.com/jeyakatsa/teku/tree/master/light-client)

### Problem:
`ArrayIntCache<Boolean> bits` function needs to be an array, but is not being accepted as an array

full function: 
``` 
public int sumBits(ArrayIntCache<Boolean> bits) {
  int sum = 0;
  for (boolean bit: bits) {
    if (bit) {
      sum++;
    }
  }
  return sum;
}
```  
#### Function Source: https://github.com/jeyakatsa/teku/blob/master/light-client/src/main/java/tech/pegasys/teku/lightclient/server/Utilities.java

### Hypothesis:

1. Refactor function into array.
2. Find "ArrayLike" function/class within Teku repo.

### Solution:

TBD (To Be Determined)