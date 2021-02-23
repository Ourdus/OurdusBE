package ourdus.ourdusspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.COrder;
import ourdus.ourdusspring.domain.CReservation;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.COrderDTO;
import ourdus.ourdusspring.repository.COrderRepository;
import ourdus.ourdusspring.repository.CReservationRepository;
import ourdus.ourdusspring.repository.UserRepository;

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

    public Optional<COrder> findOne(Long userId)
    {
        Optional <COrder> result= cOrderRepository.findById(userId);
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