package com.code.with.bisky.api;


import com.code.with.bisky.dto.*;
import com.code.with.bisky.service.StripeService;
import com.stripe.model.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/public/v2/stripe")
@AllArgsConstructor
public class StripeV2Api {

    private final StripeService stripeService;


    @PostMapping("/session/payment")
    @ResponseBody
    public SessionDto sessionPayment(@RequestBody SessionDto model) {

        return stripeService.createPaymentSession(model);
    }

    @PostMapping("/session/subscription")
    @ResponseBody
    public SessionDto createSubscriptionSession(@RequestBody SessionDto model) {

        return stripeService.createSubscriptionSession(model);
    }


}
