## Research and Development for Teku Light Client Server

This Research and Development technical paper is created to help alleviate some bottlenecks within and during the light client server build for Teku.

# [Server](https://github.com/jeyakatsa/teku/tree/master/light-client)

### Problem:
`ArrayIntCache<Boolean> bits` function needs to be an array, but is not being accepted as an array.

Full function: 
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
----------------------------------------------------
### Hypothesis:

1. Refactor function into array
#### Result: 
*Unresponsive*

----------------------------------------------

2. Find "ArrayLike" function/class within Teku repo and apply. 
Reference (in Typescript): 
```
export interface ArrayLike<T> {
  [n: number]: T;
  readonly length: number;
  [Symbol.iterator](): Iterator<T>;
}
```

#### Result:
*Serched:* 
- Folder: ssz/generator/main
- - Package: tech.pegasys.teku.ssz
- - - Class: ContainersGenerator
- - Package: tech.pegasys.teku.ssz.containers
- - - Class: ContainerSchemaTemplate
- - - Class: ContainerTemplate
- Folder: ssz/generator/test
- - Package: tech.pegasys.teku.ssz.containers
- - - Class: GeneratedClassesNotModified 
- Folder: ssz/main
- - Package: tech.pegasys.teku.ssz
- - - Class: InvalidValueSchemaException
- - - Class: Merkleizable
- - - Class: SimpleOffsetSerializable
- - - Class: SszCollection
- - - Class: SszComposite
- - - Class: SszContainer
- - - Class: SszData
- - - Class: SszList
- - - Class: SszMutableCollection
- - - Class: SszMutableComposite
- - - Class: SszMutableContainer
- - - Class: SszMutableData
- - - Class: SszMutableList
- - - Class: SszMutableRefComposite
- - - Class: SszMutableRefContainer
- - - Class: SszMutableRefList
- - - Class: SszMutableRefVector
- - - Class: szMutableVector
- - - Class: SszPrimitive
- - - Class: SszUnion
- - - Class: SszVector
- - Package: tech.pegasys.teku.ssz.cache
- - - Class: ArrayIntCache ***<- possibility***
- - - Class: Cache ***<- possibility***
- - - Class: IntCache ***<- possibility***
- - - Class: NoopIntCache ***<- possibility***
- - - Class: SoftRefIntCache ***<- possibility***
- - Package: tech.pegasys.teku.ssz.collections
- - - Class: SszBitlist
- - - Class: SszBitvector
- - - Class: SszByteList ***<- possibility***
- - - Class: SszBytes32Vector
- - - Class: SszByteVector
- - - Class: SszMutableBytes32Vector
- - - Class: SszMutablePrimitiveCollection
- - - Class: SszMutablePrimitiveList
- - - Class: SszMutablePrimitiveVector
- - - Class: SszUInt64List
- - Package: tech.pegasys.teku.ssz.collections.impl
- - - Class: AbstractSszMutablePrimitiveCollection ***<- possibility***
- - - Class: sszSerializationLength ***<- possibility***
- - - Class: BitvectorImpl
- 
-------------------------------------------------------
### Solution:

TBD (To Be Determined)
