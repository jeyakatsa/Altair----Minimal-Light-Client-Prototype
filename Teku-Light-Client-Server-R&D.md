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
- - - Class: SszData ***<- possibility***
- - - Class: SszList ***<- possibility***
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
- - - Class: BitlistImpl ***<- possibility***
- - - Class: BitvectorImpl
- - - Class: SszBitlistImpl ***<- very strong possibility***
- - - Class: SszBitvectorImpl ***<- very strong possibility***
- - - Class: SszBytes32VectorImpl ***<- strong possibility***
- - - Class: SszByteVectorImpl ***<- strong possibility***
- - - Class: SszMutableBytes32VectorImpl ***<- strong possibility***
- - - Class: SszMutablePrimitiveListImpl ***<- possibility***
- - - Class: SszMutablePrimitiveVectorImpl ***<- possibility***
- - - Class: SszMutableUInt64ListImpl
- - - Class: SszPrimitiveListImpl
- - - Class: SszPrimitiveVectorImpl
- - - Class: SszUInt64ListImpl
- - Package: tech.pegasys.teku.ssz.containers
- - - Class: Containers *<-all most likely do not need to be checked*
- - Package: tech.pegasys.teku.ssz.impl
- - - Class: AbstractSszCollection
- - - Class: AbstractSszComposite
- - - Class: AbstractSszImmutableContainer
- - - Class: AbstractSszMutableCollection
- - - Class: AbstractSszMutableComposite
- - - Class: AbstractSszPrimitive ***<- possibility***
- - - Class: SszContainerImpl
- - - Class: SszListImpl
- - - Class: SszMutableContainerImpl
- - - Class: SszMutableListImpl
- - - Class: SszMutableVectorImpl
- - - Class: SszUnionImpl
- - - Class: SszUtils 
- - - Class: SszVectorImpl
- - Package: tech.pegasys.teku.ssz.primitive
- - - Class: SszBit
- - - Class: SszByte
- - - Class: SszBytes32
- - - Class: SszBytes4
- - - Class: SszNone
- - - Class: SszUInt256
- - - Class: SszUInt64
- - Package: tech.pegasys.teku.ssz.schema
- - - Class: SszCollectionSchema
- - - Class: SszCompositeSchema
- - - Class: SszContainerSchema
- - - Class: SszListSchema
- - - Class: SszPrimitiveSchema
-------------------------------------------------------
### Solution:

TBD (To Be Determined)
