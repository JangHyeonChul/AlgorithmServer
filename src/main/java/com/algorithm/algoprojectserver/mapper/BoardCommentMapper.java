package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : BoardCommentMapper
 * Description : 게시물 코멘트 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardCommentMapper {

    int insertBoardComment(CommentDTO commentDTO);
    int updateBoardCommentCnt(int boardNum);
    int updateBoardComment(@Param("c_no") int c_no, @Param("content") String content);
    int deleteBoardComment(int boardNum);


    List<CommentDTO> selectBoardComments(int b_no);
    CommentDTO selectBoardComment(int c_no);

    int selectBoardCommentCount(int b_no);

}
