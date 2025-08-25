package hello.Apple;


import hello.Apple.repository.Item;
import hello.Apple.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


// 초기 테스트 검증 클래스
@Slf4j
@RequiredArgsConstructor
public class TestDataInit {


    private final ItemRepository itemRepository;

    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        itemRepository.save(new Item("네이버","http://youtube.com/watch?v=U3BJyWSSbro&t=28s","U3BJyWSSbro"));
        itemRepository.save((new Item("인프런","http://youtube.com/watch?v=1Lhfcz9MnmQ/" ,"1Lhfcz9MnmQ")));
    }
}
