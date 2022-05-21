package io.github.scrumboot.grpc.calculate.impl;

import io.github.scrumboot.grpc.calculate.Calculator;
import io.github.scrumboot.grpc.calculate.UnSecuredCalculatorGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Bing D. Yee
 * @since 2022/05/19
 */
@Slf4j
@GrpcService
public class UnSecuredCalculatorService extends UnSecuredCalculatorGrpc.UnSecuredCalculatorImplBase {

    @Override
    public void calculate(Calculator.CalculatorRequest request, StreamObserver<Calculator.CalculatorResponse> responseObserver) {
        final Calculator.CalculatorResponse.Builder responseBuilder = Calculator.CalculatorResponse.newBuilder();
        switch (request.getOperation()) {
            case ADD:
                responseBuilder.setResult(request.getNumber1() + request.getNumber2());
                break;
            case SUBTRACT:
                responseBuilder.setResult(request.getNumber1() - request.getNumber2());
                break;
            case MULTIPLY:
                responseBuilder.setResult(request.getNumber1() * request.getNumber2());
                break;
            case DIVIDE:
                responseBuilder.setResult(request.getNumber1() / request.getNumber2());
                break;
            default:
                responseObserver.onError(new RuntimeException("Unsupported operation type!"));
                return;
        }
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

}
