package com.osttra.crds.services;

import com.osttra.crds.entities.ClientStaging;

public interface ClientStagingService {
    Long createClientStagingData(ClientStaging clientStaging);
    ClientStaging getClientStagingDataById(Long clientStagingId);
}
