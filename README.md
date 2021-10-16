#### Important:
`git pull` Teku Build Reference repo consistently to stay up-to-date.

*Do Not Use* reference *verbatim* as its dependencies are consistently being refactored/reorganized thus will create an infinite loop of issues for your build.

Being creative (adding your own ingredients to the recipe of this prototype) is extremely important. 

### Demo Reference:
- Build (Lodestar[written in Typescript]): https://github.com/ChainSafe/eth2-light-client-demo
- Test (Lodestar): https://light-client-demo.lodestar.casa/

### Server Reference:
- To Inform/update: https://github.com/ConsenSys/teku/issues/4230
- To Review: https://notes.ethereum.org/@ralexstokes/S1RSe1JlF

### Tools Needed:
- Spring Tool Suite 4 (for Demo): https://spring.io/tools
- Eclipse (for Server): https://www.eclipse.org/downloads/
- Gradle (for Server): https://gradle.org/install/

#### Teku Build Reference:
https://github.com/ConsenSys/teku

#### Altair----Minimal-Light-Client-Prototype Specs:
https://github.com/ethereum/consensus-specs/blob/dev/specs/altair/sync-protocol.md//

#### Beacon Chain----Full Specs:
https://github.com/ethereum/consensus-specs/blob/dev/specs/altair/beacon-chain.md

###### Full Nodes vs Light Nodes:
https://ethereum.org/en/developers/docs/nodes-and-clients/nodes-as-a-service/

Full node:

---Stores full blockchain data.

---Participates in block validation, verifies all blocks and states.

---All states can be derived from a full node.

---Serves the network and provides data on request.

Light node:

---Stores the header chain and requests everything else.

---Can verify the validity of the data against the state roots in the block headers.

---Useful for low capacity devices, such as embedded devices or mobile phones, which can't afford to store gigabytes of blockchain data

## Notes 
https://github.com/ethereum-cdap/cohort-one/blob/master/notes/jeremyakatsa/jeremyakatsa.md

