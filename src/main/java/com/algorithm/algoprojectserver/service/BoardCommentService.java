package com.algorithm.algoprojectserver.service;

/**
 * File Name : BoardCommentService
 * Description : 게시물 댓글 작성 Service Mapper
 * Update : 2023-08-21
 */

import com.algorithm.algoprojectserver.dto.CommentDTO;

import java.util.List;

public interface BoardCommentService {

    int writeBoardComment(int boardNumber, String username, String commentContent);
    int updateBoardCommentCnt(int b_no);
    int deleteBoardComment(int pageNum);
    int updateBoardComment(int c_no, String content);

    List<CommentDTO> getBoardComments(int b_no);
    CommentDTO getBoardComment(int c_no);

    int getBoardCommentCount(int b_no);
}
