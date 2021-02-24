package ourdus.ourdusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.OnlineClass;
import ourdus.ourdusspring.dto.OnlineClassDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.OnlineClassService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/oc")
@RequiredArgsConstructor
public class OnlineClassController {

    private final OnlineClassService onlineClassService;
    private JwtService jwtService;

    @GetMapping("")
    public ApiResult<List<OnlineClassDTO>> viewAllOC(){
        List<OnlineClass> onlineClasses = onlineClassService.findall();
        List<OnlineClassDTO> onlineClassDTOS = new ArrayList<>();
        if(onlineClasses != null){
            onlineClasses.stream().forEach(onlineClass -> {
                onlineClassDTOS.add(new OnlineClassDTO(onlineClass));
            });
        }
        return OK(onlineClassDTOS);
    }

    @GetMapping("{class_id}")
    public ApiResult<OnlineClassDTO> viewOneOC(@PathVariable("class_id") Long classId){
        return OK(new OnlineClassDTO(onlineClassService.findOne(classId)));
    }

    @PostMapping("new")
    public ApiResult<OnlineClassDTO> saveOC(HttpServletRequest req, @RequestBody OnlineClassDTO onlineClassDTO){
       // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        OnlineClass onlineClass = OnlineClass.createBuilder()
                                            .onlineClassDTO(onlineClassDTO)
                                            .build();
        return OK(new OnlineClassDTO(onlineClassService.save(onlineClassDTO.getCategoryId(), authorId, onlineClass)));
    }

    @PostMapping("{class_id}/delete")
    public ApiResult<String> deleteOC(HttpServletRequest req, @PathVariable("class_id") Long classId){
        //Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
//        if(onlineClassService.checkAuthor(authorId, classId))
//            new ForbiddenException("해당 클래스의 작가가 아니므로 접근할 수 없습니다.");
        onlineClassService.checkAuthor(authorId, classId);
        onlineClassService.delete(classId);
        return OK("삭제완료");
    }

    @PostMapping("{class_id}/edit")
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
}
