package com.osttra.crds.services.impl;

import com.osttra.crds.entities.ClientStaging;
import com.osttra.crds.repositories.ClientStagingRepository;
import com.osttra.crds.services.ClientStagingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientStagingServiceImpl implements ClientStagingService {
    private final ClientStagingRepository clientStagingRepository;

    @Override
    public Long createClientStagingData(ClientStaging clientStaging) {
    ClientStaging clientStaging1 = clientStagingRepository.save(clientStaging);
    return clientStaging1.getId();
    }

    @Override
    public ClientStaging getClientStagingDataById(Long clientStagingId) {
        return clientStagingRepository.findById(clientStagingId).orElseThrow(()->
                new RuntimeException("Client Staging id doesn't exists. ID: "+clientStagingId));
    }
}
