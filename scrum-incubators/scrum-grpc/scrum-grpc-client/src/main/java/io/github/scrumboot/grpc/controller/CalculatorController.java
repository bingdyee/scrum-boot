package io.github.scrumboot.grpc.controller;

import io.github.scrumboot.grpc.calculate.Calculator;
import io.github.scrumboot.grpc.calculate.SecuredCalculatorGrpc;
import io.github.scrumboot.grpc.calculate.UnSecuredCalculatorGrpc;
import io.grpc.ManagedChannel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bing D. Yee
 * @since 2022/05/19
 */
@RestController
@RequestMapping("/v1/calculate")
public class CalculatorController {

    @GrpcClient("grpc-server")
    private UnSecuredCalculatorGrpc.UnSecuredCalculatorBlockingStub unSecuredCalculatorService;

    @GrpcClient("grpc-server")
    private SecuredCalculatorGrpc.SecuredCalculatorBlockingStub securedCalculatorService;

    @GetMapping("/add")
    public Double add(double a, double b,
                      @RequestParam(defaultValue = "false", required = false) boolean secured) {
        final Calculator.CalculatorRequest request = Calculator.CalculatorRequest.newBuilder()
                .setNumber1(a).setNumber2(b).build();
        final Calculator.CalculatorResponse response = secured ?
                securedCalculatorService.calculate(request) : unSecuredCalculatorService.calculate(request);
        return response.getResult();
    }

}
