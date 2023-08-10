package com.algorithm.algoprojectserver.mapper;


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
