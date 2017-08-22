package com.gavas.controller.rest;

import com.gavas.domain.dto.ErrorResponseDto;
import com.gavas.exception.EmptyQueryResultException;
import com.gavas.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usercomments")
public class UserCommentRestController {

    private UserCommentService userCommentService;

    @Autowired
    public UserCommentRestController(UserCommentService userCommentService) {
        this.userCommentService = userCommentService;
    }

    @GetMapping("/{userCommentId}/images")
    public ResponseEntity<List<Long>>  getFileIdByUserCommentId(@PathVariable Long userCommentId){
        List<Long> fileIdList = userCommentService.getFileIdByUserCommentId(userCommentId);
        return new ResponseEntity<>(fileIdList, HttpStatus.OK);
    }

    @ExceptionHandler(EmptyQueryResultException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidException(EmptyQueryResultException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("400", exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
