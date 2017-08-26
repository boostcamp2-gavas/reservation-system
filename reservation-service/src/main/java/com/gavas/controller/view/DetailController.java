package com.gavas.controller.view;

import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/details")
public class DetailController {

    private UserCommentService userCommentService;

    @Autowired
    public DetailController(UserCommentService userCommentService){
        this.userCommentService = userCommentService;
    }

    @GetMapping("/{productId}")
    public ModelAndView detailPage(@PathVariable Long productId) {
        ModelAndView mv = new ModelAndView("detail");
        TotalCommentStatusDto totalCommentStatusDto = userCommentService.getTotalCommentStatus(productId);
        mv.addObject(productId);
        mv.addObject("count",totalCommentStatusDto.getCount());
        mv.addObject("avg",totalCommentStatusDto.getAvg().setScale(2,2));
        return mv;
    }
}
