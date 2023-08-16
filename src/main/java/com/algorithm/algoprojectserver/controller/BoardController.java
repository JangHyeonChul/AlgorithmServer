package com.algorithm.algoprojectserver.controller;

/**
 * File Name : BoardController
 * Description : 커뮤니티 관련 컨트롤러
 * Update : 2023-08-15
 */


import com.algorithm.algoprojectserver.PageHandler;
import com.algorithm.algoprojectserver.dto.AlramDTO;
import com.algorithm.algoprojectserver.dto.BoardDTO;
import com.algorithm.algoprojectserver.dto.CommentDTO;
import com.algorithm.algoprojectserver.dto.RecommdationDTO;
import com.algorithm.algoprojectserver.service.AlramService;
import com.algorithm.algoprojectserver.service.BoardCommentService;
import com.algorithm.algoprojectserver.service.BoardService;
import com.algorithm.algoprojectserver.service.RecommdationService;
import com.algorithm.algoprojectserver.validator.BoardValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {


    BoardValidator boardValidator;
    BoardService boardService;
    BoardCommentService boardCommentService;
    RecommdationService recommdationService;
    AlramService alramService;

    public BoardController(BoardValidator boardValidator,
                           BoardService boardService,
                           BoardCommentService boardCommentService,
                           RecommdationService recommdationService,
                           AlramService alramService) {
        this.boardValidator = boardValidator;
        this.boardService = boardService;
        this.boardCommentService = boardCommentService;
        this.recommdationService = recommdationService;
        this.alramService = alramService;
    }

    /*
     * API : /board
     * Method : POST
     * DESCRIPTION : 게시물 목록을 불러오는 기능 수행
     * */

    @GetMapping("")
    public String board(@RequestParam(defaultValue = "1") Integer page, Model model) {
        return getBoard(page, null, model);

    }

    /*
     * API : /board/type/{category}
     * Method : GET
     * DESCRIPTION : 게시물 목록을 불러오는 기능 수행 category의 타입에 따라 분류됨
     * */


    @GetMapping("/type/{category}")
    public String boardCategoryQU(@PathVariable String category,
                                  @RequestParam(defaultValue = "1") Integer page, Model model) {

        return getBoard(page, category, model);
    }

    /*
     * API : /board/write
     * Method : GET
     * DESCRIPTION : 게시물 작성 하는 화면을 요청
     * */


    @GetMapping("/write")
    public String writeBoardPage(Model model) {

        model.addAttribute("boardDTO", new BoardDTO());

        return "/board/board-write";
    }

    /*
     * API : /board/page/{boardNum}
     * Method : GET
     * DESCRIPTION : boardNum에 해당하는 게시물 정보를 요청
     * */

    @GetMapping("/page/{boardNum}")
    @Transactional
    public String readBoard(@PathVariable("boardNum")int boardNumber,
                            @RequestParam(defaultValue = "1") int page, HttpServletRequest request, HttpServletResponse response, Model model ) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info("[요청 IP : {}] {} 게시물의 알림 업데이트", request.getRemoteAddr(), boardNumber);

        alramService.updateBoardAlram(boardNumber);

        log.info("[요청 IP : {}] {} 게시물의 조회수 업데이트", request.getRemoteAddr(), boardNumber);

        boardService.writeBoardViewCnt(boardNumber, request, response);
        BoardDTO board = boardService.getBoard(boardNumber);


        // 지워진 게시물일경우 게시물 목록을 보여주는 화면으로 redirect

        if (board.getB_delete() == 1) {
            return "redirect:/board";
        }



        List<CommentDTO> boardComments = boardCommentService.getBoardComments(boardNumber);

        int boardTotalCount = boardService.getCountAllBoard(null);
        PageHandler pageHandler = new PageHandler(boardTotalCount, page);

        int offset = boardService.getBoardOffset(page, pageHandler);
        List<BoardDTO> allBoards = boardService.getAllBoards(offset, null);

        model.addAttribute("page", page);
        model.addAttribute("offset", offset);
        model.addAttribute("boards", allBoards);
        model.addAttribute("ph", pageHandler);

        model.addAttribute("username", username);
        model.addAttribute("page", page);
        model.addAttribute("boardComments", boardComments);
        model.addAttribute("board", board);
        model.addAttribute("boardNumber", boardNumber);

        return "/board/board-info";
    }


    /*
     * API : /board/page/{boardNum}
     * Method : DELETE
     * DESCRIPTION : boardNum에 해당하는 게시물을 삭제처리
     * */

    @DeleteMapping("/page/{pageNum}")
    @ResponseBody
    @Transactional
    public boolean deleteBoard(@PathVariable("pageNum") int pageNum, HttpServletRequest request) {

        if (!boardValidator.boardDeleteVaildate(pageNum)) {

            log.info("[요청 IP : {}] {} 게시물의 삭제 요청 실패", request.getRemoteAddr(), pageNum);


            return false;
        }



        boardCommentService.deleteBoardComment(pageNum);

        log.info("[요청 IP : {}] {} 게시물의 댓글 내용 삭제 완료", request.getRemoteAddr(), pageNum);

        boardService.deleteBoard(pageNum);

        log.info("[요청 IP : {}] {} 게시물의 내용 삭제 완료", request.getRemoteAddr(), pageNum);

        return true;
    }


    /*
     * API : /board/page/{pageNum}
     * Method : GET
     * DESCRIPTION : pageNum에 해당하는 게시물을 업데이트
     * */

    @GetMapping("/update/{pageNum}")
    public String modifyBoard(@PathVariable("pageNum") int pageNum, Model model) {

        BoardDTO board = boardService.getBoard(pageNum);
        if (!boardValidator.boardDeleteVaildate(pageNum) || board.getB_delete() == 1) {
            return "redirect:/board";
        }

        model.addAttribute("board", board);
        return "/board/board-modify";

    }

    /*
     * API : /board/update/{pageNum}
     * Method : PATCH
     * DESCRIPTION : boardNum에 해당하는 게시물을 삭제처리
     * */

    @PatchMapping("/update/{pageNum}")
    public String modifyBoard(@PathVariable("pageNum") int pageNum,
                              @ModelAttribute("board") BoardDTO board,
                              HttpServletRequest request) {

        if (!boardValidator.boardDeleteVaildate(pageNum)) {



            return "redirect:/board";
        }

        log.info("[요청 IP : {}] {} 게시물의 내용을 업데이트", request.getRemoteAddr(), pageNum);

        boardService.modifyBoard(pageNum, board);

        return "redirect:/board";

    }


    /*
     * API : /board/write
     * Method : POST
     * DESCRIPTION : 게시물 작성
     * */


    @PostMapping("/write")
    public String postingBoard(@ModelAttribute BoardDTO boardDTO, BindingResult bindingResult, HttpServletRequest request) {

        // 게시물 작성하기전 유효성 검사

        log.info("[요청 IP : {}] 게시물 작성 요청", request.getRemoteAddr());

        boardValidator.validate(boardDTO, bindingResult);

        if(bindingResult.hasErrors()) {

            log.info("[요청 IP : {}] 게시물 작성 요청 유효성 검사 통과 실패", request.getRemoteAddr());

            return "/board/board-write";
        }

        log.info("[요청 IP : {}] 게시물 작성", request.getRemoteAddr());

        boardService.writeBoard(boardDTO);
        return "redirect:/board";
    }

    /*
     * API : /board/comment
     * Method : POST
     * DESCRIPTION : 댓글 작성
     * */

    @PostMapping("/comment")
    @ResponseBody
    @Transactional
    public List<CommentDTO> boardComment(@RequestParam("boardNumber") int boardNumber,
                                    @RequestParam("commentContent") String commentContent) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        BoardDTO board = boardService.getBoard(boardNumber);

        boardCommentService.writeBoardComment(boardNumber, username, commentContent);

        List<CommentDTO> boardComments = boardCommentService.getBoardComments(boardNumber);

        boardCommentService.updateBoardCommentCnt(boardNumber);

        String userId = board.getUser_id();

        AlramDTO alramDTO = new AlramDTO(boardNumber, userId);

        alramService.writeBoardAlram(alramDTO);

        for (CommentDTO commentDTO : boardComments) {
            String commentUserName = commentDTO.getUser_id();
            if (commentUserName.equals(username)) {
                commentDTO.setShowModify(true);
            }
        }

        return boardComments;
    }


    /*
     * API : /board/comment
     * Method : DELETE
     * DESCRIPTION : 댓글 삭제
     * */

    @DeleteMapping("/comment")
    @ResponseBody
    @Transactional
    public List<CommentDTO> boardCommentDelete(@RequestParam("commentNumber") int c_no,
                                   @RequestParam("boardNumber") int b_no) {

        boolean boardCommentValid = boardValidator.boardCommentVaildate(c_no);

        if (!boardCommentValid) {
            return null;
        }

        boardCommentService.deleteBoardComment(c_no);
        return  boardCommentService.getBoardComments(b_no);
    }


    /*
     * API : /board/comment/update
     * Method : GET
     * DESCRIPTION : 댓글 업데이트 화면 띄우기
     * */

    @GetMapping("/comment/update")
    @ResponseBody
    public CommentDTO boardCommentModify(@RequestParam("commentNumber") int c_no) {

        boolean boardCommentValid = boardValidator.boardCommentVaildate(c_no);

        if (!boardCommentValid) {
            return null;
        }

        return boardCommentService.getBoardComment(c_no);

    }

    /*
     * API : /board/comment/update
     * Method : PATCH
     * DESCRIPTION : 댓글 업데이트 수행
     * */

    @PatchMapping("/comment/update")
    @ResponseBody
    public boolean boardCommentModify(@RequestParam("commentNumber") int c_no,
                                         @RequestParam("commentContent") String content) {

        boolean boardCommentValid = boardValidator.boardCommentVaildate(c_no);

        if (!boardCommentValid) {
            return false;
        }


        boardCommentService.updateBoardComment(c_no, content);

        return true;

    }

    /*
     * API : /board/recommdation
     * Method : POST
     * DESCRIPTION : 댓글 추천수 업데이트
     * */

    @PostMapping("/recommdation")
    @ResponseBody
    @Transactional
    public RecommdationDTO boardRecommdation(@RequestParam("boardNumber") int boardNumber) {

        return recommdationService.writeBoardRecommdation(boardNumber);

    }



    private String getBoard(Integer page, String category, Model model) {
        int boardTotalCount = boardService.getCountAllBoard(category);
        PageHandler pageHandler = new PageHandler(boardTotalCount, page);

        int offset = boardService.getBoardOffset(page, pageHandler);
        List<BoardDTO> allBoards = boardService.getAllBoards(offset, category);

        model.addAttribute("page", page);
        model.addAttribute("offset", offset);
        model.addAttribute("boards", allBoards);
        model.addAttribute("ph", pageHandler);

        return "/board/board";
    }


}
