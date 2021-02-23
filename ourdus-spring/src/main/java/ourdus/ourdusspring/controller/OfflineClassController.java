package ourdus.ourdusspring.controller;


import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.OfflineClass;
import ourdus.ourdusspring.domain.OfflineClassComment;
import ourdus.ourdusspring.domain.OnlineClassComment;
import ourdus.ourdusspring.domain.Promotion;
import ourdus.ourdusspring.dto.*;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.OfflineClassService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
public class OfflineClassController {

    private OfflineClassService offlineClassService;
    private JwtService jwtService;

    public OfflineClassController(OfflineClassService offlineClassService)
    {
        this.offlineClassService=offlineClassService;
    }

    @GetMapping("/c")
    public ApiResult<List<OfflineClassDTO>> viewOfflineclassList(){
        List <OfflineClass> offlineClassList=offlineClassService.findAll();
        List <OfflineClassDTO> offlineClassDTOList=new ArrayList<>();
        if(offlineClassList!=null)
        {
            offlineClassList.stream().forEach(offlineClass -> {
                offlineClassDTOList.add(new OfflineClassDTO(offlineClass));
            });
        }
        return OK(offlineClassDTOList);
    }
    @GetMapping("/c/{class_id}")
    public ApiResult<OfflineClassDTO> viewOfflineClass(@PathVariable("class_id") Long classId){
        Optional<OfflineClass> offlineClass=offlineClassService.findOne(classId);
        offlineClass.orElseThrow(()->new NoSuchElementException("해당되는 프로모션 정보가 없습니다"));
        return OK(new OfflineClassDTO(offlineClass.get()));
    }

    @PostMapping("/t/c/new")
    public ApiResult<OfflineClassDTO> save(HttpServletRequest req,@RequestBody OfflineClassRequest offlineClassRequest)
    {
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        OfflineClass offlineClass= OfflineClass
                .builder()
                .name(offlineClassRequest.getName())
                .description(offlineClassRequest.getDescription())
                .price(offlineClassRequest.getPrice())
                .duration(offlineClassRequest.getDuration())
                .hit(offlineClassRequest.getHit())
                .max(offlineClassRequest.getMax())
                .level(offlineClassRequest.getLevel())
                .rate(offlineClassRequest.getRate())
                .like(offlineClassRequest.getLike())
                .place(offlineClassRequest.getPlace())
                .purchase(offlineClassRequest.getPurchase())
                .price(offlineClassRequest.getPrice())
                .build();

        return OK(new OfflineClassDTO(offlineClassService.save(offlineClass,offlineClassRequest.getCategoryId(),offlineClassRequest.getAuthorId())));
    }

    @DeleteMapping("/t/c/{class_id}/delete")
    public ApiResult<String> delete(HttpServletRequest req,@PathVariable("class_id") Long class_id){
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        return OK(offlineClassService.delete(class_id));
    }

    @PostMapping("/t/c/{class_id}/edit")
    public ApiResult<OfflineClassDTO> modify(HttpServletRequest req, @PathVariable("class_id") Long class_id, @RequestBody OfflineClassRequest offlineClassRequest)
    {
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;

        OfflineClass offlineClass= OfflineClass
                .builder()
                .name(offlineClassRequest.getName())
                .description(offlineClassRequest.getDescription())
                .price(offlineClassRequest.getPrice())
                .duration(offlineClassRequest.getDuration())
                .hit(offlineClassRequest.getHit())
                .max(offlineClassRequest.getMax())
                .level(offlineClassRequest.getLevel())
                .rate(offlineClassRequest.getRate())
                .like(offlineClassRequest.getLike())
                .place(offlineClassRequest.getPlace())
                .purchase(offlineClassRequest.getPurchase())
                .price(offlineClassRequest.getPrice())
                .build();
        return OK(new OfflineClassDTO(offlineClassService.update(offlineClass,class_id)));
    }

    @PostMapping("/t/c/{class_id}/comment")
    public ApiResult<OnlineClassCommentDTO> addComment(HttpServletRequest req, @PathVariable("class_id") Long classId,
                                                       @RequestBody OfflineClassCommentDTO commentDTO){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        OfflineClassComment comment = OfflineClassComment.createBuilder()
                .content(commentDTO.getContent())
                .build();
        return OK(new OnlineClassCommentDTO(offlineClassService.addComment(comment,classId,userId)));
    }


    @DeleteMapping("/t/c/comment/{comment_id}")
    public ApiResult<String> deleteAddress(@PathVariable("comment_id")Long commentId){
        return OK(offlineClassService.removeComment(commentId));
    }
}
