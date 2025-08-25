package hello.Apple;

import hello.Apple.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class AppleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppleApplication.class, args);
	}

	// TestDataInit 스프링 빈 등록
	@Bean
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		System.out.println("테스트 호출");
		return new TestDataInit(itemRepository);
	}
}
