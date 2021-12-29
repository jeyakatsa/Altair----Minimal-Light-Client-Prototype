## Research and Development for Teku Light Client Server

This Research and Development technical paper is created to help alleviate some bottlenecks within and during the light client server build for Teku.

**Problems & Solutions ordered top-down from recent-to-oldest**

# [Server](https://github.com/jeyakatsa/teku/tree/master/light-client)

## Problem:
Function `altair.LightClientUpdate["nextSyncCommittee"]["pubkeys"]` in Typescript needs to be converted into Java.

### Hypothesis:

1. Refactor function
##### Test Case/s:
- Creating `pubkeys.LightClientUpdate pubkeys;` insufficient.
##### Findings:
- This function example might work:
```
B b; 
b = new B(); 
b.doSomething();
```
##### Test Case/s:
- Implemented:
```
private LightClientUpdate pubkeys;
pubkeys = new void LightClientUpdate();
```
not sufficient.
##### Findings:
- Possibly `return Array.from(pubkeys).map((pk) => PublicKey.fromBytes(pk.valueOf() as Uint8Array));` function in Typescript could be clue to overall solution...
##### Test Case/s:
- Changed `public interface DeserializePubkeys extends PublicKey` function into `public class DeserializePubkeys`, insufficient.
- Implemented `private LightClientUpdate["syncCommittee"] pubkeys;`, insufficient.
- Implemented `LightClientUpdate pubkeys = example();`, worked... 
##### Findings
- `LightClientUpdate pubkeys = example();` function from above test case solved compiling error, expanding on this new discovery.
##### Test Case/s:
- Function: 
```
LightClientUpdate pubkeys = new LightClientUpdate() {
        public Bytes48 toBytesCompressed() {
            return null;
        }
        public void forceValidation() throws IllegalArgumentException {

        }
        public boolean isInGroup() {
            return false;
        }
        public boolean isValid() {
            return false;
        }
        public int hashCode() {
            return 0;
        }
        public boolean equals(Object obj) {
            return false;
        }
    };
```
Resolved `pubkeys` issue.

## Solution:
TBD (To Be Determined)

-----------------------------------------------------------------

## Problem:
Java having issues returning classes within functions.

### Hypothesis:

1. Create separate classes and return them solely?
##### Findings:
- Implementing a similar function: `public Engine getEngine() {return new Engine();}` might work.
##### Test Case/s:
- Implementing `return new BeaconBlockHeader();` works.
- Implementing `return new NodeSlot();` insufficient.
- Implementing getters and setters:
```
NodeSlot block;
NodeSlot getBlock() { return block;}
void setBlock() {this.block = block;}
``` 
insufficient.

## Solution:
Created [`ToBlockHeader`](https://github.com/jeyakatsa/teku/blob/master/light-client/src/main/java/tech/pegasys/teku/lightclient/utilities/ToBlockHeader.java) class.

-----------------------------------------------------------------


## Problem:
Issue importing `.block` from general `altair` class from Teku client using these Typescript functions as reference:

```
    slot: block.slot,
    proposerIndex: block.proposerIndex,
    parentRoot: block.parentRoot,
    stateRoot: block.stateRoot,
    bodyRoot: ssz.altair.BeaconBlockBody.hashTreeRoot(block.body),
```

### Hypothesis:

1. Find alternate class within client.
##### Findings:
- No `BeaconBlock` sole class within Teku client.
- `altair.LightClientUpdate` and `altair.BeaconBlock` have similar instances and a possible solution can be derived from [LightClientStore](https://github.com/jeyakatsa/teku/blob/master/light-client/src/main/java/tech/pegasys/teku/lightclient/client/LightClientStore.java) class.

## Solution:
imported `NodeSlot` and other corresponding classes to resolve issue.

-------------------------------------------------------------------

## Problem:
`participantPubkeys.push(pubkeys.get(i));` function within `public T[] getParticipantPubkeys()` insufficient.

### Hypothesis:

1. Refactor function/s.
##### Findings:
- Method cannot be pushed via `T` variable.
- The old "GenericArray" problem via Java could be a major diagnosis of this issue...

## Solution:
Created `Stack<T> participantPubkeys = new Stack<T>();`.

-------------------------------------------------------------------

## Problem:
`bits.length` function within `for (int i = 0; i < bits.length; i++)` function within `public T[] getParticipantPubkeys()` insufficient.

### Hypothesis:

1. Refactor function/s.
##### Findings: 
- [Vector Instantiating in Java](https://www.geeksforgeeks.org/java-util-vector-class-java/) quite possible for solution.
##### Test Cases:
- Created `Vector<boolean> bits = new Vector<boolean>();`, insufficient.
##### Findings: 
- Creating a `Vector` within a `Vector` might work via [Stack overflow question](https://stackoverflow.com/questions/6203226/how-to-create-an-array-of-vector-in-java).
##### Test Cases:
- Created `Vector<Vector<Boolean>> bits = new Vector<Vector<Boolean>>();`, insufficient.
- Added an integer `(100)` to `Vector` instantiation, insufficient.
- Created `List<Integer> bits = new ArrayList<Integer>();`, insufficient.
##### Findings:
- Created `int[] bits = new int[100];` which obiously works, only issue is that dependent on the [Lodestar Light Client Utils class](https://github.com/ChainSafe/lodestar/blob/master/packages/light-client/src/utils/utils.ts) as reference, the `BitVector` is directly correlated to `Vector<Boolean>` which doesn't translate into an integer array in Java...

## Solution:
Function to convert Vector into Array in Java implemented: 

```
Vector<Boolean> vector = new Vector<Boolean>();
Object[] bits = vector.toArray();
```

-----------------------------------------------------------------------------

## Problem:
`T[]` constant within `public T[] getParticipantPubkeys<T> () {}` function insufficient.

### Hypothesis:

1. Refactor function/s.
##### Findings: 
- [Typescript Documentation](https://www.typescriptlang.org/docs/handbook/2/functions.ht) in order to convert constant successfully into Java.
- [Typescript Generics](https://www.tutorialsteacher.com/typescript/typescript-generic).
##### Test Cases: 
- Added class to `T[]` function, insufficient.
- Refactored `T[]` into `Array<T>`, insufficient.
##### Findings:
- `T[]` constant most likely Generic Array, thus cannot be implemented as Generic Array due to Java constraints.
##### Test Cases: 
- `List<T> pubkeys = new ArrayList<T>();` function creation a possibility.
##### Findings:
- [Java Generic Types](https://docs.oracle.com/javase/tutorial/java/generics/types.html)
##### Test Cases: 
- Refunction function into `public T[] getParticipantPubkeys()`.

## Solution:
Function: `public T[] getParticipantPubkeys()` resolved issue.

-----------------------------------------------------------------------------

## Problem:
`rootArray.length` within `if (rootArray.length != expectedLength)` from `List<Bytes32> rootArray = new ArrayList<Bytes32>();` insufficient.

### Hypothesis:

1. Refactor function/s.
##### Test Cases: 
- Refactored 
```
    public void assertZeroHashes(Bytes32 rootArray, int expectedLength) {
        byte[] rootsArray = rootArray.toArray();
```
## Solution:
Refactored 
```
    public void assertZeroHashes(Bytes32 rootArray, int expectedLength) {
        byte[] rootsArray = rootArray.toArray();
```

-----------------------------------------------------------------------------

## Problem:
Function:
```
public boolean isZeroHash() {
    for (int i = 0; i < root.length; i++) {
        if (root[i] !== 0) {
            return false;
        }
    }
    return true;
}
```

Refusing to recognize `!==` as proper expression for `private Bytes32[] root;`.

### Hypothesis:
1. Refactor function/s.

##### Test Cases: 
- Refactoring `private Bytes32[] root;` into `private Bytes32 root[32];` not sufficient.
- Refactoring `private Bytes32[] root;` into `private Bytes32 root;` not sufficient.
- Refactoring `int i = 0; i < root.length; i++` into `byte i = 0; i < root.length; i++` not sufficient.

*Researching Primitive Data Types for Java.*

##### Findings:
- Most developers expect to see an `int` value  within Byte functions for 32-bit or 64-bit registers so changing `int` into `bytes` or any other primitive type will most likely not be sufficient.

##### Test Cases:
- Refactoring `root` into `int[] root = new int[20];` is insufficient as a test case.
- Refactoring `root[i] != 0` while keeping `root` into `int[] root = new int[20];` solves `!==` operand issue.

##### Findings:
- `!==` operand not sufficient for Java functions, yet `!=` cannot be applied to `Bytes32`.
- ***Interesting...*** `org.apache.tuweni.bytes.Bytes32` interface has a standard integer of 32.

##### Test Cases:
- Added getters and setters to root function, thus refactors for loop into ```for (int i = 0; i < getRoot().length; i++) {
            if (getRoot(i) != 0) {
                return false;}```, insufficient.

##### Findings:

- Function within `Bytes32` class:
```
  static Bytes32 random(Random generator) {
    byte[] array = new byte[32];
    generator.nextBytes(array);
    return wrap(array);
  }
```
could possibly provide solution, more research to be done.
- `root.toArray(array);` function created within
 ```
     static Utilities (Bytes32 root) {
        byte[] array = new byte[32];
        root.toArray(array);
        return array;
    }
```
quite possible to be refactored into `isZeroHash()` function.

##### Test Cases:
- Created `byte[] root = new byte[32];`, then refactored `root[i] != 0` function within if function. Problem likely solved.
- 

## Solution:
Created `byte[] root = new byte[32];`, then refactored `root[i] != 0` function within if function within 'isZeroHash()` function. Final function:
```
    byte[] root = new byte[32];

    public boolean isZeroHash() {
        for (int i = 0; i < root.length; i++) {
            if (root[i] != 0) {
                return false;
            }
        }
        return true;
    }
```
Entire function might need to be refactored (after entire light-client test against Gradle, but for now, it works).
______________________________________________________________
## Problem:

IntelliJ refusing to reveal compilation errors.

### Hypothesis:

1. Re-install IntelliJ
#### Result: 
*Re-Installed, problem not solved*

2. Redirect Environmental Variables
#### Result: 
The default is set to `$USER_HOME/.gradle`. It can be overridden in one of the following ways:

You can set the `GRADLE_USER_HOME` environment variable (for example, `%APPDATA%\.gradle`). For more information, refer to Gradle documentation. The variable's value is picked up automatically. The new path is reflected in the field.

You might need to restart your IDE in order for this change to take effect.

You can specify the location manually: type the location in the path or click the Browse button and in the dialog that opens, select the needed directory.

Manually entered values take precedence over the environment variables.

If the Gradle location is set in Use Gradle from with the Specified location option and its path is defined in the environment variable `GRADLE_HOME` or `PATH`, IntelliJ IDEA deduces this location and suggests its path as the default value.

*Tried implementing `/.gradle`, did not work, moving onto other possible solutions*

- "Use Gradle from [Specified Location], automatically directs to undesired file, currently trying to implement new re-direction"

#### Discovery:
`.gradle` files report compilation errors, `.java` files do not.

#### Trial & Error Approach:
Uninstall/delete all Java development kits and reinstall via IntelliJ.

## Solution:
First two Hypotheses were incorrect. The solution was to create an entirely new project using IntelliJ as IntelliJ doesn't coherently absorb projects built by other tools (i.e Eclipse, SpringBoot, etc).

______________________________________________________
## Problem:
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
You can view the source code here: 
#### [Function Source](https://github.com/jeyakatsa/teku/blob/master/light-client/src/main/java/tech/pegasys/teku/lightclient/server/Utilities.java)

### Hypothesis:

1. Refactor function into array
#### Result: 
*Recorded in Solution*

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
- - - Class: SszData ***<- possibility*** *Tested*
- - - Class: SszList ***<- possibility*** *Tested*
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
- - - Class: ArrayIntCache ***<- possibility*** *Tested*
- - - Class: Cache ***<- possibility*** *Tested*
- - - Class: IntCache ***<- possibility*** *Tested*
- - - Class: NoopIntCache ***<- possibility*** *Tested*
- - - Class: SoftRefIntCache ***<- possibility*** *Tested*
- - Package: tech.pegasys.teku.ssz.collections
- - - Class: SszBitlist
- - - Class: SszBitvector
- - - Class: SszByteList ***<- possibility*** *Tested*
- - - Class: SszBytes32Vector
- - - Class: SszByteVector
- - - Class: SszMutableBytes32Vector
- - - Class: SszMutablePrimitiveCollection
- - - Class: SszMutablePrimitiveList
- - - Class: SszMutablePrimitiveVector
- - - Class: SszUInt64List
- - Package: tech.pegasys.teku.ssz.collections.impl
- - - Class: AbstractSszMutablePrimitiveCollection ***<- possibility*** *Tested*
- - - Class: BitlistImpl
- - - Class: BitvectorImpl
- - - Class: SszBitlistImpl ***<- very strong possibility*** *Tested*
- - - Class: SszBitvectorImpl ***<- very strong possibility*** *Tested*
- - - Class: SszBytes32VectorImpl ***<- strong possibility*** *Tested*
- - - Class: SszByteVectorImpl ***<- strong possibility*** *Tested*
- - - Class: SszMutableBytes32VectorImpl ***<- strong possibility*** *Tested*
- - - Class: SszMutablePrimitiveListImpl ***<- possibility*** *Tested*
- - - Class: SszMutablePrimitiveVectorImpl ***<- possibility*** *Tested*
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
- - - Class: AbstractSszPrimitive ***<- possibility*** *Tested*
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
- - - Class: SszPrimitiveSchemas
- - - Class: SszSchema ***<- possibility*** *Tested*
- - - Class: SszSchemaHints
- - - Class: SszType
- - - Class: SszUnionSchema ***<- possibility*** *Tested*
- - - Class: SszVectorSchema
- - Package: tech.pegasys.teku.ssz.schema.collections
- - - Class: SszBitlistSchema ***<- possibility*** *Tested*
- - - Class: SszBitvectorSchema ***<- possibility*** *Tested*
- - - Class: SszByteListSchema
- - - Class: SszBytes32VectorSchema
- - - Class: SszByteVectorSchema
- - - Class: SszPrimitiveCollectionSchema
- - - Class: SszPrimitiveListSchema
- - - Class: SszPrimitiveVectorSchema
- - - Class: SszUInt64ListSchema
- - Package: tech.pegasys.teku.ssz.schema.collections.impl
- - - Class: SchemaUtils
- - - Class: SszBitlistSchemaImpl
- - - Class: SszBitvectorSchemaImpl
- - - Class: SszByteListSchemaImpl
- - - Class: SszBytes32VectorSchemaImpl
- - - Class: SszByteVectorSchemaImpl
- - - Class: SszPrimitiveListSchemaImpl
- - - Class: SszPrimitiveVectorSchemaImpl
- - - Class: SszUInt64ListSchemaImpl
- - Package: tech.pegasys.teku.ssz.schema.impl
- - - Class: AbstractSszCollectionSchema
- - - Class: AbstractSszContainerSchema
- - - Class: AbstractSszListSchema
- - - Class: AbstractSszPrimitiveSchema
- - - Class: AbstractSszVectorSchema
- - - Class: LoadingUtil
- - - Class: SszListSchemaImpl
- - - Class: SszUnionSchemaImpl
- - - Class: StoringUtil
- - Package: tech.pegasys.teku.ssz.sos
- - - Class: SimpleSszReader
- - - Class: SszByteArrayWriter
- - - Class: SszDeserializeException
- - - Class: SszField
- - - Class: SszLengthBounds
- - - Class: SszReader
- - - Class: SszWriter
- - Package: tech.pegasys.teku.ssz.tree
- - - Class: BranchNode
- - - Class: GIndexUtil
- - - Class: LazyBranchNode
- - - Class: LeafDataNode
- - - Class: LeafNode
- - - Class: SimpleBranchNode
- - - Class: SimpleLeafNode
- - - Class: SszNodeTemplate
- - - Class: SszSuperNode
- - - Class: TillIndexVisitor
- - - Class: TreeNode
- - - Class: TreeNodeSource
- - - Class: TreeNodeStore
- - - Class: TreeUpdates
- - - Class: TreeUtil
- - - Class: TreeVisitor
- - Package: tech.pegasys.teku.ssz.type
- - - Class: Bytes20
- - - Class: Bytes4
- - - Class: Bytes8
*Searched, Tested, Not Applicable*
- Folder: data/beaconrestapi/main
- - Package: tech.pegasys.teku.beaconrestapi
- - - Class: ListQueryParameterUtils
- Folder: networking/net/main
- - Package: tech.pegasys.teku.networking.nat
- - - Class: TekuRegistryListener
- Folder: networking/p2p/rpc
- - Package: tech.pegasys.teku.networking.p2p.rpc
- - - Class: RpcMethod
- - - Class: RpcRequestHandler ***<- extremely strong possibility***
- - - Class: RpcRequestHandler
- - - Class: RpcStreamController
- - - Class: StreamClosedException
- - - Class: StreamTimeoutException

## Solution:

The first hypothesis was the correct trajectory. Discovered a "Generic Array" needed to be created as a Class or Interface within the Light-Client folder/package. 

Code (*to be refactored if necessary*):
```
public class ArrayLike<T>
{
    private final Object[] arr;
    public final int number;
 
    // constructor
    public ArrayLike(int number)
    {
        // Creates a new object array of the specified length
        arr = new Object[number];
        this.number = number;
    }
 
    // Method to get object present at index `i` in the array
    T get(int i) {
        @SuppressWarnings("unchecked")
        final T e = (T)arr[i];
        return e;
    }
 
    // Method to set a value `e` at index `i` in the array
    void set(int i, T e) {
        arr[i] = e;
    }

}
```

**But**, Java does not support Generic Arrays so this code was implemented in response:

`List<Boolean> bits = new ArrayList<Boolean>();`
