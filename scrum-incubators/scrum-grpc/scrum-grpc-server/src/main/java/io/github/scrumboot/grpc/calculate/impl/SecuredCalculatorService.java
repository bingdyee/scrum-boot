package io.github.scrumboot.grpc.calculate.impl;

import io.github.scrumboot.grpc.calculate.Calculator;
import io.github.scrumboot.grpc.calculate.SecuredCalculatorGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Bing D. Yee
 * @since 2022/05/19
 */
@Slf4j
@GrpcService
public class SecuredCalculatorService extends SecuredCalculatorGrpc.SecuredCalculatorImplBase {

    @Override
    public void calculate(Calculator.CalculatorRequest request, StreamObserver<Calculator.CalculatorResponse> responseObserver) {
        responseObserver.onError(new RuntimeException("Under maintenance!"));
    }

}
