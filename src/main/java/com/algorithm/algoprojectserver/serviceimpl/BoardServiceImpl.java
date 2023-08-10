package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.PageHandler;
import com.algorithm.algoprojectserver.Time;
import com.algorithm.algoprojectserver.dto.BoardDTO;
import com.algorithm.algoprojectserver.mapper.BoardMapper;
import com.algorithm.algoprojectserver.service.BoardService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public void writeBoard(BoardDTO boardDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boardDTO.setUser_id(username);

        boardMapper.insertBoard(boardDTO);

    }

    @Override
    public void writeBoardViewCnt(int pageNum, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie setCookie = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("viewCntPage")) {
                setCookie = cookie;
            }
        }

        if (setCookie == null) {
            Cookie viewCntPage = new Cookie("viewCntPage", String.valueOf(pageNum));
            viewCntPage.setMaxAge(24 * 60 * 60);
            viewCntPage.setHttpOnly(true);
            response.addCookie(viewCntPage);
            boardMapper.updateBoardViewCnt(pageNum);
        }

        if (setCookie != null) {
            if(!setCookie.getValue().contains(String.valueOf(pageNum))) {
                setCookie.setValue(setCookie.getValue() + "_" + pageNum);
                setCookie.setMaxAge(24 * 60 * 60);
                setCookie.setHttpOnly(true);
                response.addCookie(setCookie);
                boardMapper.updateBoardViewCnt(pageNum);
            }
        }
    }

    @Override
    public void deleteBoard(int boardNum) {
        boardMapper.deleteBoard(boardNum);
    }

    @Override
    public void modifyBoard(int pageNum, BoardDTO boardDTO) {
        boardMapper.updateBoard(pageNum, boardDTO);
    }


    @Override
    public int getCountAllBoard(String category) {

        return boardMapper.countAllBoard(category);
    }

    @Override
    public int getBoardOffset(int page, PageHandler pageHandler) {
        return (page - 1)* pageHandler.getPageSize();
    }

    @Override
    public List<BoardDTO> getAllBoardsByUser(String username) {
        return boardMapper.selectAllBoardsByUser(username);
    }


    @Override
    public List<BoardDTO> getAllBoards(int offset, String category) {
        List<BoardDTO> boardDTOS = boardMapper.selectAllBoards(offset, category);

        for (BoardDTO boardDTO : boardDTOS) {
            LocalDateTime bCreate = boardDTO.getB_create();
            String createTime = Time.txtDate(bCreate);
            boardDTO.setB_transCreate(createTime);

        }

        return boardDTOS;

    }

    @Override
    public List<BoardDTO> getAllBoardsBySearch(String keyword, int offset) {
        return boardMapper.selectAllBoardsBySearch(keyword, offset);
    }

    @Override
    public BoardDTO getBoard(int pageNum) {
        BoardDTO boardDTO = boardMapper.selectBoard(pageNum);
        LocalDateTime bCreate = boardDTO.getB_create();
        String boardCreate = Time.txtDate(bCreate);
        boardDTO.setB_transCreate(boardCreate);
        return boardDTO;
    }

}
