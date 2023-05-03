package ru.pobopo.weather.service;

import com.google.protobuf.BoolValue;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.pobopo.weather.grpc.AuthServiceGrpc;
import ru.pobopo.weather.grpc.Credits;

@Component
public class AuthService {
    private final ManagedChannel channel;

    public AuthService() {
        String host = System.getenv("AUTH_HOST");
        String port = System.getenv("AUTH_PORT");
        if (StringUtils.isBlank(host) || StringUtils.isBlank(port)) {
            throw new RuntimeException("Missing auth host/port env variables!");
        }
        this.channel = ManagedChannelBuilder
            .forAddress(host, Integer.parseInt(port))
            .usePlaintext()
            .build();
    }

    public boolean authUser(String login, String password) {
        AuthServiceGrpc.AuthServiceBlockingStub stub = AuthServiceGrpc.newBlockingStub(channel);
        Credits credits = Credits.newBuilder().setLogin(login).setPassword(password).build();
        BoolValue result = stub.authUser(credits);
        return result.getValue();
    }
}
