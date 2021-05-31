package ourdus.ourdusspring.controller.offlineclass;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.comment.OfflineClassComment;
import ourdus.ourdusspring.dto.offlineclass.OfflineClassDTO;
import ourdus.ourdusspring.dto.offlineclass.OfflineClassRequest;
import ourdus.ourdusspring.dto.offlineclass.comment.OfflineClassCommentDTO;
import ourdus.ourdusspring.service.offlineclass.OfflineClassService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api")
@Api("오프라인 클래스 정보 관리")
public class OfflineClassController {

    private final OfflineClassService offlineClassService;

    public OfflineClassController(OfflineClassService offlineClassService) {
        this.offlineClassService = offlineClassService;
    }

    @ApiOperation(value = "오프라인 클래스 전체 조회", notes = "오프라인 클래스의 전체 목록을 조회한다.")
    @GetMapping("c")
    public ApiResult<List<OfflineClassDTO>> viewOfflineclassList(){
        List <OfflineClassDTO> offlineClassDTOList = offlineClassService.findAll()
                .stream()
                .filter(offlineClass -> offlineClass != null)
                .map(OfflineClassDTO::new)
                .collect(Collectors.toList());
        return OK(offlineClassDTOList);
    }

    @ApiOperation(value = "오프라인 클래스 지역별 조회", notes = "지역별 오프라인 클래스 목록을 조회한다.")
    @GetMapping("c/region/{region_id}")
    public ApiResult<List<OfflineClassDTO>> viewRegionalOfflineClass(@PathVariable("region_id") int regionId){
        List <OfflineClass> offlineClassList=offlineClassService.findRegion(regionId);
        List <OfflineClassDTO> offlineClassDTOList=new ArrayList<>();
        offlineClassList.stream()
                .filter(offlineClass -> offlineClass != null)
                .forEach(offlineClass -> {
                    offlineClassDTOList.add(new OfflineClassDTO(offlineClass));
                });
        return OK(offlineClassDTOList);
    }


    @ApiOperation(value = "오프라인 클래스 상세 조회", notes = "해당 오프라인 클래스의 세부 정보를 조회한다.")
    @GetMapping("c/{class_id}")
    public ApiResult<OfflineClassDTO> viewOfflineClass(@PathVariable("class_id") Long classId){
        return OK(new OfflineClassDTO(offlineClassService.findOne(classId)));
    }

    @ApiOperation(value = "오프라인 클래스 개설", notes = "작가는 오프라인 클래스를 개설할 수 있다.")
    @PostMapping("/t/c/new")
    public ApiResult<OfflineClassDTO> save(@RequestBody OfflineClassRequest request) {
        return OK(new OfflineClassDTO(
                offlineClassService.save(new OfflineClass(request),request.getCategoryId(), getAuthUserId())));
    }

    @ApiOperation(value = "오프라인 클래스 삭제", notes = "작가는 개설한 오프라인 클래스를 삭제할 수 있다.")
    @DeleteMapping("t/c/{class_id}/delete")
    public ApiResult<String> delete(@PathVariable("class_id") Long class_id){
        return OK(offlineClassService.delete(class_id));
    }

    @ApiOperation(value = "오프라인 클래스 수정", notes = "작가는 개설한 오프라인 클래스를 수정할 수 있다.")
    @PostMapping("t/c/{class_id}/edit")
    public ApiResult<OfflineClassDTO> modify(@PathVariable("class_id") Long class_id, @Valid @RequestBody OfflineClassRequest offlineClassRequest) {
        OfflineClass offlineClass= OfflineClass
                .createBuilder()
                .offlineClassRequest(offlineClassRequest)
                .build();
        return OK(new OfflineClassDTO(offlineClassService.update(offlineClass,class_id)));
    }

    @ApiOperation(value = "오프라인 클래스 댓글 달기", notes = "사용자는 오프라인 클래스에 댓글을 달 수 있다.")
    @PostMapping("/c/{class_id}/comment")
    public ApiResult<OfflineClassCommentDTO> addComment(@PathVariable("class_id") Long classId,
                                                       @Valid @RequestBody OfflineClassCommentDTO commentDTO){
        OfflineClassComment comment = OfflineClassComment.createBuilder()
                .content(commentDTO.getContent())
                .build();
        return OK(new OfflineClassCommentDTO(offlineClassService.addComment(comment,classId,getAuthUserId())));
    }

    @ApiOperation(value = "오프라인 클래스 댓글 삭제", notes = "사용자는 오프라인 클래스에 댓글을 달 수 있다.")
    @DeleteMapping("/c/comment/{comment_id}")
    public ApiResult<String> deleteAddress(@PathVariable("comment_id")Long commentId){
        return OK(offlineClassService.removeComment(commentId));
    }
}
