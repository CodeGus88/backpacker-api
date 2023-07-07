package com.backpackerapi.backpacker.repositories.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseAddressRepository<M> extends JpaRepository<M, UUID> {

    List<M> findByEntityUuid(UUID entityUuid);

}