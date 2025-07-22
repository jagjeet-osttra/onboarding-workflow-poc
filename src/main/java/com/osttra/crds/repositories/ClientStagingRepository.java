package com.osttra.crds.repositories;

import com.osttra.crds.entities.ClientStaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStagingRepository extends JpaRepository<ClientStaging,Long> {
}
