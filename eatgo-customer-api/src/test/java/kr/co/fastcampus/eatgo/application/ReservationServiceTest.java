package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Reservation;
import kr.co.fastcampus.eatgo.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation(){

        Long restaurantId = 369L;
        Long userId = 1004L;
        String name = "John";
        String date = "2020-12-24";
        String time = "20:00";
        Integer partySize = 20;

        Reservation mockReservation = Reservation.builder().name(name).build();
//        given(reservationRepository.save(any())).willReturn(mockReservation);
        given(reservationRepository.save(any()))
                .will(invocation -> {
                    Reservation reservation = invocation.getArgument(0);
                    return reservation;
                });

        //mockReservation으로 이렇게 넣어주면 addReservation안에서 Reservation을 만들어준다는것을
        // 놓치게되는것같음 그래서 입력을 받은것을 그대로 돌려주는방식으로 바꿈
       Reservation reservation =
                reservationService.addReservation(restaurantId, userId, name, date, time, partySize);
       //얘네들을 활용해서 reservation이라는 객체를 만들어줌

        assertThat(reservation.getName()).isEqualTo(name);

        verify(reservationRepository).save(any(Reservation.class));
    }

}