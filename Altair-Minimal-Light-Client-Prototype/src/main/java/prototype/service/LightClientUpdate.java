package prototype.service;

public class LightClientUpdate {
	
	// Python code below to be converted to Java...
	//    # Update beacon block header
	//    header: BeaconBlockHeader
	//    # Next sync committee corresponding to the header
	//    next_sync_committee: SyncCommittee
	//    next_sync_committee_branch: Vector[Bytes32, floorlog2(NEXT_SYNC_COMMITTEE_INDEX)]
	//    # Finality proof for the update header
	//    finality_header: BeaconBlockHeader
	//    finality_branch: Vector[Bytes32, floorlog2(FINALIZED_ROOT_INDEX)]
	//    # Sync committee aggregate signature
	//    sync_committee_bits: Bitvector[SYNC_COMMITTEE_SIZE]
	//    sync_committee_signature: BLSSignature
	//    # Fork version for the aggregate signature
	//    fork_version: Version
	
	//	def validate_light_client_update(snapshot: LightClientSnapshot,
	//            update: LightClientUpdate,
	//            genesis_validators_root: Root) -> None:
	//		# Verify update slot is larger than snapshot slot
	//		assert update.header.slot > snapshot.header.slot
	//		
	//		# Verify update does not skip a sync committee period
	//		snapshot_period = compute_epoch_at_slot(snapshot.header.slot) // EPOCHS_PER_SYNC_COMMITTEE_PERIOD
	//		update_period = compute_epoch_at_slot(update.header.slot) // EPOCHS_PER_SYNC_COMMITTEE_PERIOD
	//		assert update_period in (snapshot_period, snapshot_period + 1)
	//		
	//		# Verify update header root is the finalized root of the finality header, if specified
	//		if update.finality_header == BeaconBlockHeader():
	//		signed_header = update.header
	//		assert update.finality_branch == [Bytes32() for _ in range(floorlog2(FINALIZED_ROOT_INDEX))]
	//		else:
	//		signed_header = update.finality_header
	//		assert is_valid_merkle_branch(
	//		leaf=hash_tree_root(update.header),
	//		branch=update.finality_branch,
	//		depth=floorlog2(FINALIZED_ROOT_INDEX),
	//		index=get_subtree_index(FINALIZED_ROOT_INDEX),
	//		root=update.finality_header.state_root,
	//		)
	//		
	//		# Verify update next sync committee if the update period incremented
	//		if update_period == snapshot_period:
	//		sync_committee = snapshot.current_sync_committee
	//		assert update.next_sync_committee_branch == [Bytes32() for _ in range(floorlog2(NEXT_SYNC_COMMITTEE_INDEX))]
	//		else:
	//		sync_committee = snapshot.next_sync_committee
	//		assert is_valid_merkle_branch(
	//		leaf=hash_tree_root(update.next_sync_committee),
	//		branch=update.next_sync_committee_branch,
	//		depth=floorlog2(NEXT_SYNC_COMMITTEE_INDEX),
	//		index=get_subtree_index(NEXT_SYNC_COMMITTEE_INDEX),
	//		root=update.header.state_root,
	//		)
	//		
	//		# Verify sync committee has sufficient participants
	//		assert sum(update.sync_committee_bits) >= MIN_SYNC_COMMITTEE_PARTICIPANTS
	//		
	//		# Verify sync committee aggregate signature
	//		participant_pubkeys = [pubkey for (bit, pubkey) in zip(update.sync_committee_bits, sync_committee.pubkeys) if bit]
	//		domain = compute_domain(DOMAIN_SYNC_COMMITTEE, update.fork_version, genesis_validators_root)
	//		signing_root = compute_signing_root(signed_header, domain)
	//		assert bls.FastAggregateVerify(participant_pubkeys, signing_root, update.sync_committee_signature)	
	//		
	//		
	//		
	//	def apply_light_client_update(snapshot: LightClientSnapshot, update: LightClientUpdate) -> None:
	//	    snapshot_period = compute_epoch_at_slot(snapshot.header.slot) // EPOCHS_PER_SYNC_COMMITTEE_PERIOD
	//	    update_period = compute_epoch_at_slot(update.header.slot) // EPOCHS_PER_SYNC_COMMITTEE_PERIOD
	//	    if update_period == snapshot_period + 1:
	//	        snapshot.current_sync_committee = snapshot.next_sync_committee
	//	        snapshot.next_sync_committee = update.next_sync_committee
	//	    snapshot.header = update.header		
	//		
	//
	//	    
	//	def process_light_client_update(store: LightClientStore, update: LightClientUpdate, current_slot: Slot,
	//            genesis_validators_root: Root) -> None:
	//		validate_light_client_update(store.snapshot, update, genesis_validators_root)
	//		store.valid_updates.add(update)
	//		
	//		update_timeout = SLOTS_PER_EPOCH * EPOCHS_PER_SYNC_COMMITTEE_PERIOD
	//		if (
	//		sum(update.sync_committee_bits) * 3 >= len(update.sync_committee_bits) * 2
	//		and update.finality_header != BeaconBlockHeader()
	//		):
	//		# Apply update if (1) 2/3 quorum is reached and (2) we have a finality proof.
	//		# Note that (2) means that the current light client design needs finality.
	//		# It may be changed to re-organizable light client design. See the on-going issue consensus-specs#2182.
	//		apply_light_client_update(store.snapshot, update)
	//		store.valid_updates = set()
	//		elif current_slot > store.snapshot.header.slot + update_timeout:
	//		# Forced best update when the update timeout has elapsed
	//		apply_light_client_update(store.snapshot,
	//		                          max(store.valid_updates, key=lambda update: sum(update.sync_committee_bits)))
	//		store.valid_updates = set()	    
	    
	    
	
}
