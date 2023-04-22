package ru.pobopo.weather.service;

import com.google.protobuf.BoolValue;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javax.security.auth.message.AuthException;
import org.springframework.stereotype.Component;
import ru.pobopo.weather.grpc.AuthServiceGrpc;
import ru.pobopo.weather.grpc.Credits;

@Component
public class AuthService {
    private final ManagedChannel channel;

    public AuthService() {
        this.channel = ManagedChannelBuilder
            .forAddress("localhost", 50051)
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
