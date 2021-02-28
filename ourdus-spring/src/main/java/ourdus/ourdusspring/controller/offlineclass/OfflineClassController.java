package ourdus.ourdusspring.controller.offlineclass;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.comment.OfflineClassComment;
import ourdus.ourdusspring.dto.offlineclass.comment.OfflineClassCommentDTO;
import ourdus.ourdusspring.dto.offlineclass.OfflineClassDTO;
import ourdus.ourdusspring.dto.offlineclass.OfflineClassRequest;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.offlineclass.OfflineClassService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
public class OfflineClassController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private OfflineClassService offlineClassService;

    @GetMapping("c")
    public ApiResult<List<OfflineClassDTO>> viewOfflineclassList(){
        List <OfflineClass> offlineClassList=offlineClassService.findAll();
        List <OfflineClassDTO> offlineClassDTOList=new ArrayList<>();
        offlineClassList.stream().forEach(offlineClass -> {
            offlineClassDTOList.add(new OfflineClassDTO(offlineClass));
        });
        return OK(offlineClassDTOList);
    }

    @GetMapping("c/{class_id}")
    public ApiResult<OfflineClassDTO> viewOfflineClass(@PathVariable("class_id") Long classId){
        return OK(new OfflineClassDTO(offlineClassService.findOne(classId)));
    }

    @PostMapping("/t/c/new")
    public ApiResult<OfflineClassDTO> save(/*HttpServletRequest req,*/@RequestBody OfflineClassRequest offlineClassRequest)

    {
//         Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        OfflineClass offlineClass= OfflineClass
                .createBuilder()
                .offlineClassRequest(offlineClassRequest)
                .build();

        return OK(new OfflineClassDTO(offlineClassService.save(offlineClass,offlineClassRequest.getCategoryId(),offlineClassRequest.getAuthorId())));
    }

    @DeleteMapping("t/c/{class_id}/delete")
    public ApiResult<String> delete(HttpServletRequest req,@PathVariable("class_id") Long class_id){
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        return OK(offlineClassService.delete(class_id));
    }


    @PostMapping("t/c/{class_id}/edit")

    public ApiResult<OfflineClassDTO> modify(HttpServletRequest req, @PathVariable("class_id") Long class_id, @RequestBody OfflineClassRequest offlineClassRequest)
    {
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;

        OfflineClass offlineClass= OfflineClass
                .createBuilder()
                .offlineClassRequest(offlineClassRequest)
                .build();
        return OK(new OfflineClassDTO(offlineClassService.update(offlineClass,class_id)));
    }

    @PostMapping("/c/{class_id}/comment")
    public ApiResult<OfflineClassCommentDTO> addComment(/*HttpServletRequest req,*/ @PathVariable("class_id") Long classId,
                                                       @RequestBody OfflineClassCommentDTO commentDTO){
//        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long userId = 1L;
        OfflineClassComment comment = OfflineClassComment.createBuilder()
                .content(commentDTO.getContent())
                .build();
        return OK(new OfflineClassCommentDTO(offlineClassService.addComment(comment,classId,userId)));
    }


    @DeleteMapping("/c/comment/{comment_id}")
    public ApiResult<String> deleteAddress(@PathVariable("comment_id")Long commentId){
        return OK(offlineClassService.removeComment(commentId));
    }
}
