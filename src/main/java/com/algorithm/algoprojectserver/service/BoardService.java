package com.algorithm.algoprojectserver.service;


import com.algorithm.algoprojectserver.PageHandler;
import com.algorithm.algoprojectserver.dto.BoardDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface BoardService {

    void writeBoard(BoardDTO boardDTO);
    void writeBoardViewCnt(int pageNum, HttpServletRequest request, HttpServletResponse response);
    void deleteBoard(int boardNum);
    void modifyBoard(int pageNum, BoardDTO boardDTO);

    int getCountAllBoard(String category);
    int getBoardOffset(int page, PageHandler pageHandler);

    List<BoardDTO> getAllBoardsByUser(String username);
    List<BoardDTO> getAllBoards(int offset, String category);
    List<BoardDTO> getAllBoardsBySearch(String keyword, int offset);

    BoardDTO getBoard(int pageNum);


}
