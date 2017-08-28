package com.gavas.controller.view;

import com.gavas.arguementresolver.AuthUser;
import com.gavas.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reservations")
@Slf4j
public class ReservationController {

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ModelAndView reservationPage(@AuthUser User user) {
        ModelAndView mv = new ModelAndView("myreservation");
        log.info(user.toString());
        mv.addObject(user);
        return mv;
    }
}
