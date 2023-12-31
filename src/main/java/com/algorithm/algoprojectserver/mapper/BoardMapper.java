package com.algorithm.algoprojectserver.mapper;

/**
 * File Name : BoardMapper
 * Description : 게시물 관련 Mybatis 데이터베이스 Mapping
 * Update : 2023-08-18
 */


import com.algorithm.algoprojectserver.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    void insertBoard(BoardDTO boardDTO);
    int countAllBoard(String category);

    List<BoardDTO> selectAllBoards(@Param("offset") int offset, @Param("category") String category);
    List<BoardDTO> selectAllBoardsByUser(String username);
    List<BoardDTO> selectAllBoardsBySearch(@Param("keyword") String keyword, @Param("offset") int offset);

    BoardDTO selectBoard(int pageNum);

    void updateBoardViewCnt(int pageNum);
    void updateBoard(@Param("pageNum") int pageNum, @Param("boardDTO") BoardDTO boardDTO);

    void deleteBoard(int boardNum);

}
