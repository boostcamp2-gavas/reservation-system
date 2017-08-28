package com.gavas.controller.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gavas.domain.Review;
import com.gavas.service.FileService;
import com.gavas.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReveiwRestController {
    private FileService fileService;
    private ReviewService reviewService;

    @Autowired
    public ReveiwRestController(FileService fileService, ReviewService reviewService){
        this.fileService = fileService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public Integer createReview(@RequestParam("review") String reviewString, @RequestParam("files") MultipartFile[] files){
        Review review = null;
        try {
            review = new ObjectMapper().readValue(reviewString, Review.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer reviewId;
        if(files != null) {
            List<Integer> addedFileList = fileService.saveFiles(review.getUserId(),files);
            reviewId = reviewService.addReviewWithFiles(review, addedFileList);
        } else {
            reviewId = reviewService.addReview(review);
        }
        return reviewId;
    }
}