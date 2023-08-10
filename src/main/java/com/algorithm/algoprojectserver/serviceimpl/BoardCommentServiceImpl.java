package com.algorithm.algoprojectserver.serviceimpl;


import com.algorithm.algoprojectserver.Time;
import com.algorithm.algoprojectserver.dto.CommentDTO;
import com.algorithm.algoprojectserver.mapper.BoardCommentMapper;
import com.algorithm.algoprojectserver.service.BoardCommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

    BoardCommentMapper boardCommentMapper;

    public BoardCommentServiceImpl(BoardCommentMapper boardCommentMapper) {
        this.boardCommentMapper = boardCommentMapper;
    }

    @Override
    public int writeBoardComment(int boardNumber, String username, String commentContent) {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setB_no(boardNumber);
        commentDTO.setUser_id(username);
        commentDTO.setC_comment(commentContent);

        return boardCommentMapper.insertBoardComment(commentDTO);
    }

    @Override
    public int updateBoardCommentCnt(int b_no) {
        return boardCommentMapper.updateBoardCommentCnt(b_no);
    }

    @Override
    public int deleteBoardComment(int pageNum) {
        return boardCommentMapper.deleteBoardComment(pageNum);
    }

    @Override
    public int updateBoardComment(int c_no, String content) {
        return boardCommentMapper.updateBoardComment(c_no, content);
    }

    @Override
    public List<CommentDTO> getBoardComments(int b_no) {
        List<CommentDTO> commentDTOS = boardCommentMapper.selectBoardComments(b_no);

        for(CommentDTO commentDTO : commentDTOS) {
            LocalDateTime cCreate = commentDTO.getC_create();
            String commentTime = Time.txtDate(cCreate);
            commentDTO.setC_transCreate(commentTime);
        }

        return commentDTOS;


    }

    @Override
    public CommentDTO getBoardComment(int c_no) {
        return boardCommentMapper.selectBoardComment(c_no);
    }

    @Override
    public int getBoardCommentCount(int b_no) {
        return boardCommentMapper.selectBoardCommentCount(b_no);
    }
}
