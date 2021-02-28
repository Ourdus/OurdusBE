package ourdus.ourdusspring.controller.onlineclass;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;
import ourdus.ourdusspring.domain.onlineclass.comment.OnlineClassComment;
import ourdus.ourdusspring.dto.onlineclass.comment.OnlineClassCommentDTO;
import ourdus.ourdusspring.dto.onlineclass.OnlineClassDTO;
import ourdus.ourdusspring.dto.onlineclass.OnlineClassSimpleDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.onlineclass.OnlineClassService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OnlineClassController {


    private final OnlineClassService onlineClassService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/oc")
    public ApiResult<List<OnlineClassSimpleDTO>> viewAllOC(){
        List<OnlineClass> onlineClasses = onlineClassService.findall();
        List<OnlineClassSimpleDTO> onlineClassSimpleDTOS = new ArrayList<>();
        if(onlineClasses != null){
            onlineClasses.stream().forEach(onlineClass -> {
                onlineClassSimpleDTOS.add(new OnlineClassSimpleDTO(onlineClass));
            });
        }
        return OK(onlineClassSimpleDTOS);
    }

    @GetMapping("/oc/{class_id}")
    public ApiResult<OnlineClassDTO> viewOneOC(@PathVariable("class_id") Long classId){
        return OK(new OnlineClassDTO(onlineClassService.findOne(classId)));
    }

    @PostMapping("/t/oc/new")
    public ApiResult<OnlineClassDTO> saveOC(HttpServletRequest req, @RequestBody OnlineClassDTO onlineClassDTO){
//        Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        OnlineClass onlineClass = OnlineClass.createBuilder()
                                            .onlineClassDTO(onlineClassDTO)
                                            .build();
        return OK(new OnlineClassDTO(onlineClassService.save(onlineClassDTO.getCategoryId(), authorId, onlineClass)));
    }

    @PostMapping("/t/oc/{class_id}/delete")
    public ApiResult<String> deleteOC(HttpServletRequest req, @PathVariable("class_id") Long classId){
        //Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
//        if(onlineClassService.checkAuthor(authorId, classId))
//            new ForbiddenException("해당 클래스의 작가가 아니므로 접근할 수 없습니다.");
        onlineClassService.checkAuthor(authorId, classId);
        onlineClassService.delete(classId);
        return OK("삭제완료");
    }

    @PostMapping("/t/oc/{class_id}/edit")
    public ApiResult<OnlineClassDTO> modifyOC(HttpServletRequest req, @PathVariable("class_id") Long classId, @RequestBody OnlineClassDTO onlineClassDTO){
        //Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
//        if(onlineClassService.checkAuthor(authorId, classId))
//            new ForbiddenException("해당 클래스의 작가가 아니므로 접근할 수 없습니다.");
        OnlineClass onlineClass = OnlineClass.createBuilder()
                                        .onlineClassDTO(onlineClassDTO)
                                        .build();
        return OK(new OnlineClassDTO(onlineClassService.update(onlineClassDTO.getCategoryId(), classId, onlineClass)));
    }

    @PostMapping("/t/oc/{class_id}/comment")
    public ApiResult<OnlineClassCommentDTO> addComment(HttpServletRequest req,@PathVariable("class_id") Long classId,
                                                       @RequestBody OnlineClassCommentDTO commentDTO){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
//        Long userId = 1L;
        OnlineClassComment comment = OnlineClassComment.createBuilder()
                .content(commentDTO.getContent())
                .build();
        return OK(new OnlineClassCommentDTO(onlineClassService.addComment(comment,classId,userId)));
    }


    @DeleteMapping("/t/oc/comment/{comment_id}")
    public ApiResult<String> deleteComment(@PathVariable("comment_id")Long commentId){
        return OK(onlineClassService.removeComment(commentId));
    }
}
