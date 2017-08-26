package com.gavas.controller.view;

import com.gavas.arguementresolver.AuthUser;
import com.gavas.domain.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reservations")
public class ReservationController {


    @GetMapping
    public ModelAndView reservationPage(@AuthUser User user) {
        ModelAndView mv = new ModelAndView("myreservation");
        mv.addObject(user);
        return mv;
    }
}
