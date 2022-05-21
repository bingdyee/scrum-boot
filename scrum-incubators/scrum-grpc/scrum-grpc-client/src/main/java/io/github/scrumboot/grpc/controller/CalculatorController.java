package io.github.scrumboot.grpc.controller;

import io.github.scrumboot.grpc.calculate.Calculator;
import io.github.scrumboot.grpc.calculate.SecuredCalculatorGrpc;
import io.github.scrumboot.grpc.calculate.UnSecuredCalculatorGrpc;
import io.grpc.ManagedChannel;
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

    private final UnSecuredCalculatorGrpc.UnSecuredCalculatorBlockingStub unSecuredCalculatorService;

    private final SecuredCalculatorGrpc.SecuredCalculatorBlockingStub securedCalculatorService;

    public CalculatorController(ManagedChannel managedChannel) {
        this.unSecuredCalculatorService = UnSecuredCalculatorGrpc.newBlockingStub(managedChannel);
        this.securedCalculatorService = SecuredCalculatorGrpc.newBlockingStub(managedChannel);
    }

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
