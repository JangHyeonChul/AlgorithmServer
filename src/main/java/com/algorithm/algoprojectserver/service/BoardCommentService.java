package com.algorithm.algoprojectserver.service;


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
