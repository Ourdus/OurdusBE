package ourdus.ourdusspring.controller.onlineclass;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;
import ourdus.ourdusspring.domain.onlineclass.comment.OnlineClassComment;
import ourdus.ourdusspring.dto.onlineclass.OnlineClassDTO;
import ourdus.ourdusspring.dto.onlineclass.OnlineClassSimpleDTO;
import ourdus.ourdusspring.dto.onlineclass.comment.OnlineClassCommentDTO;
import ourdus.ourdusspring.service.onlineclass.OnlineClassService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api")
@Api(value = "온라인 클래스 정보 관리")
public class OnlineClassController {

    private final OnlineClassService onlineClassService;

    public OnlineClassController(OnlineClassService onlineClassService) {
        this.onlineClassService = onlineClassService;
    }

    @ApiOperation(value = "온라인 클래스 전체 조회", notes = "온라인 클래스를 전체 조회한다.")
    @GetMapping("/oc")
    public ApiResult<List<OnlineClassSimpleDTO>> viewAllOC() {
        List<OnlineClassSimpleDTO> onlineClassSimpleDTOS = onlineClassService.findAll()
                .stream()
                .filter(onlineClass -> onlineClass != null)
                .map(OnlineClassSimpleDTO::new)
                .collect(Collectors.toList());

        return OK(onlineClassSimpleDTOS);
    }

    @ApiOperation(value = "온라인 클래스 상세 조회", notes = "온라인 클래스의 세부 정보를 조회한다.")
    @GetMapping("/oc/{class_id}")
    public ApiResult<OnlineClassDTO> viewOneOC(@PathVariable("class_id") Long classId) {
        return OK(new OnlineClassDTO(onlineClassService.findOne(classId)));
    }

    @ApiOperation(value = "온라인 클래스 개설", notes = "작가는 온라인 클래스를 개설할 수 있다.")
    @PostMapping("/t/oc/new")
    public ApiResult<OnlineClassDTO> saveOC(@Valid @RequestBody OnlineClassDTO onlineClassDTO) {
        return OK(new OnlineClassDTO(
                onlineClassService.save(onlineClassDTO.getCategoryId(), getAuthUserId(), new OnlineClass(onlineClassDTO))));
    }

    @ApiOperation(value = "온라인 클래스 삭제", notes = "작가는 자신이 개설한 온라인 클래스를 삭제할 수 있다.")
    @PostMapping("/t/oc/{class_id}/delete")
    public ApiResult<String> deleteOC(@PathVariable("class_id") Long classId) {
        onlineClassService.delete(classId, getAuthUserId());
        return OK("삭제완료");
    }

    @ApiOperation(value = "온라인 클래스 수정", notes = "작가는 자신이 개설한 온라인 클래스 정보를 수정할 수 있다.")
    @PostMapping("/t/oc/{class_id}/edit")
    public ApiResult<OnlineClassDTO> modifyOC(@PathVariable("class_id") Long classId, @Valid @RequestBody OnlineClassDTO onlineClassDTO) {
        return OK(new OnlineClassDTO(
                onlineClassService.update(onlineClassDTO.getCategoryId(), classId, new OnlineClass(onlineClassDTO), getAuthUserId())));
    }

    // TODO : 온라인 클래스 리뷰 로직 추가

    @ApiOperation(value = "온라인 클래스 댓글 달기", notes = "유저는 온라인 클래스에 댓글을 달 수 있다.")
    @PostMapping("/t/oc/{class_id}/comment")
    public ApiResult<OnlineClassCommentDTO> addComment(@PathVariable("class_id") Long classId,
                                                       @Valid @RequestBody String comment) {
        return OK(new OnlineClassCommentDTO(
                onlineClassService.addComment(new OnlineClassComment(comment), classId, getAuthUserId())));
    }

    @ApiOperation(value = "온라인 클래스 댓글 삭제", notes = "온라인 클래스에 댓글을 단 유저는 삭제할 수 있다.")
    @DeleteMapping("/t/oc/comment/{comment_id}")
    public ApiResult<String> deleteComment(@PathVariable("comment_id") Long commentId) {
        return OK(onlineClassService.removeComment(commentId, getAuthUserId()));
    }
}
