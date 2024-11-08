package io.github.skeffy.tellernet.controller;


import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Customer;
import io.github.skeffy.tellernet.model.Profile;
import io.github.skeffy.tellernet.service.ProfileBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileBuilder profileBuilder;

    public ProfileController(ProfileBuilder profileBuilder) {
        this.profileBuilder = profileBuilder;
    }

    @GetMapping(path = "/customer")
    public Profile getProfile(@RequestBody Customer customer) {
        return profileBuilder.createProfile(customer);
    }

    @GetMapping(path = "/account")
    public Profile getProfile(@RequestBody Account account) {
        return profileBuilder.createProfile(account);
    }
}
