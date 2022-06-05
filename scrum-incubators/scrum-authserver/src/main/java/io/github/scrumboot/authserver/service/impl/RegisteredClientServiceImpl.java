package io.github.scrumboot.authserver.service.impl;

import io.github.scrumboot.authserver.service.RegisteredClientService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

/**
 * @author Bing D. Yee
 * @since 2022/05/30
 */
@Service
public class RegisteredClientServiceImpl implements RegisteredClientService {

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return null;
    }

}
