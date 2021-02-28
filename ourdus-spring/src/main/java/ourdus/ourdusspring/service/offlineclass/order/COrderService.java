package ourdus.ourdusspring.service.offlineclass.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.offlineclass.order.COrder;
import ourdus.ourdusspring.domain.offlineclass.order.CReservation;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.repository.offlineclass.order.COrderRepository;
import ourdus.ourdusspring.repository.offlineclass.order.CReservationRepository;
import ourdus.ourdusspring.repository.user.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class COrderService {

    private final COrderRepository cOrderRepository;
    private final UserRepository userRepository;
    private final CReservationRepository cReservationRepository;

    public COrderService (COrderRepository cOrderRepository,UserRepository userRepository,CReservationRepository cReservationRepository)
    {
        this.cOrderRepository=cOrderRepository;
        this.userRepository=userRepository;
        this.cReservationRepository=cReservationRepository;
    }

    public List<COrder> findAll()
    {
        return cOrderRepository.findAll();
    }

    public COrder findOne(Long userId)
    {
        COrder result= cOrderRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("해당하는 오프라인 클래스 주문이 없습니다."));
        return result;
    }

    public String save(COrder cOrder,Long userId, Long classId, Long bookingId)
    {
        Optional <User> resultUser=userRepository.findById(userId);
        Optional <CReservation> resultBooking=cReservationRepository.findById(bookingId);
        Optional <CReservation> resultClass=cReservationRepository.findById(classId);
        if(resultUser.isPresent()) {
            COrder cOrders= COrder
                    .builder()
                    .user(resultUser.get())
                    .booking(resultBooking.get())
                    .c_class(resultClass.get())
                    .build();
            cOrderRepository.save(cOrders);
            return "오프라인 클래스 주문 성공";
        }
        else
            throw new RuntimeException("오프라인 클래스 주문 실패");
    }

    public String delete(Long userId) {
        if(!cOrderRepository.existsById(userId))
            new NoSuchElementException("오프라인 클래스 주문 삭제 실패");
        cOrderRepository.deleteById(userId);
        return "오프라인 클래스 주문 삭제 성공";
    }
}