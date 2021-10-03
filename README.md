#### Altair----Minimal-Light-Client-Prototype Specs

https://github.com/ethereum/consensus-specs/blob/dev/specs/altair/sync-protocol.md//

#### Beacon Chain----Full Specs

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

