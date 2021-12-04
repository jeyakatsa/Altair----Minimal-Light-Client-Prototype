# [Light Client Server](https://github.com/jeyakatsa/teku/tree/master/light-client)

## [Step-By-Step Guide](https://hackmd.io/ZFINvY5fRUGrLK-BteZrug?view)

## [Research & Development](https://github.com/jeyakatsa/Altair----Minimal-Light-Client-Prototype/blob/main/Teku-Light-Client-Server-R%26D.md)

### Below Information can be found via the Step-By-Step Guide:

#### Important:
`git pull` Teku Build Reference repo consistently to stay up-to-date.

### Server Reference:
To Review: 
- [Lodestar Light Client Reference](https://github.com/ChainSafe/lodestar/tree/master/packages/light-client)
- [Light Client Types](https://github.com/ChainSafe/lodestar/blob/master/packages/types/src/altair/types.ts)
- [Light Client Ssz Types](https://github.com/ChainSafe/lodestar/blob/master/packages/types/src/altair/sszTypes.ts)

To Read: 
- [Notes by Alex Stokes](https://notes.ethereum.org/@ralexstokes/S1RSe1JlF)

### Demo Reference:
- [Build (Lodestar[written in Typescript])](https://github.com/ChainSafe/eth2-light-client-demo)
- [Test (Lodestar)](https://light-client-demo.lodestar.casa/)

### Coding Tools Needed:
- [IntelliJ (*best* for Server & Demo)](https://www.jetbrains.com/idea/)
- [Eclipse (*free* for Server)](https://www.eclipse.org/downloads/)
- [Spring Tool Suite 4 (*free* for Demo)](https://spring.io/tools)

***Gradle install not needed if IntelliJ is used as primary tool.***

### Build/Test Tool Needed:
- [Gradle (*free* for Server)](https://gradle.org/install/)

#### [Teku Build Reference](https://github.com/ConsenSys/teku)
*Do Not Use* Teku Build Reference *verbatim* as its dependencies are consistently being refactored/reorganized thus will create an infinite loop of issues for your build.
Being creative (adding your own ingredients to the recipe of this prototype) is extremely important. 

#### [Altair----Minimal-Light-Client-Prototype Specs](https://github.com/ethereum/consensus-specs/blob/dev/specs/altair/sync-protocol.md//)

#### [Beacon Chain----Full Specs](https://github.com/ethereum/consensus-specs/blob/dev/specs/altair/beacon-chain.md)

#### [Full Nodes vs Light Nodes](https://ethereum.org/en/developers/docs/nodes-and-clients/nodes-as-a-service/)

Full node:

---Stores full blockchain data.

---Participates in block validation, verifies all blocks and states.

---All states can be derived from a full node.

---Serves the network and provides data on request.

Light node:

---Stores the header chain and requests everything else.

---Can verify the validity of the data against the state roots in the block headers.

---Useful for low capacity devices, such as embedded devices or mobile phones, which can't afford to store gigabytes of blockchain data

##### [More Notes](https://github.com/ethereum-cdap/cohort-one/blob/master/notes/jeremyakatsa/jeremyakatsa.md)
