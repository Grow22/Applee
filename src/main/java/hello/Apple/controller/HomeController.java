package hello.Apple.controller;


import hello.Apple.domain.Information;
import hello.Apple.repository.Item;
import hello.Apple.repository.ItemSearchCond;
import hello.Apple.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private final ItemService itemService;

    // home 메서드
    @GetMapping("/")
    public String home(@ModelAttribute("welcomeName") String welcomeName, Model model)
    {
        if(welcomeName != null) {
            model.addAttribute("welcomeName", welcomeName);
        }
        List<Item> lists = itemService.findAll(new ItemSearchCond());
        model.addAttribute("lists", lists);
        System.out.println("lists = " + lists);
        return "search";
    }

    // Get Mapping
    // Search Method
    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    // Post Mapping
    // Search Method
    @PostMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {

        // 검색 이름(keyword) 에 맞는 결과들 반환
        List<Item> lists = itemService.findAll(new ItemSearchCond(keyword));

        model.addAttribute("lists", lists);
        return "search";
    }

    // 글 추가 HTML 이동 메서드
    // GET Mapping
    @GetMapping("/add")
    public String moveAddForm(Model model) {
        model.addAttribute("item", new Item()); // 빈 model 객체 사용
        return "add-form";
    }

    // 글 추가 메서드
    // Post Mapping
    @PostMapping("/add")
    public String addNewItem(@ModelAttribute Item item, Model model) {


        String fullUrl = "https://www.youtube.com/watch?v=" + item.getVideoId();
        item.setUrl(fullUrl);
        // (1) 입력된 item 저장
        Item savedItem = itemService.save(item);

        // (2) search 페이지로 이동하기 전 모든 데이터를 model 에 저장
        List<Item> lists = itemService.findAll(new ItemSearchCond());
        model.addAttribute("lists", lists);

        for (Item list : lists) {

            System.out.println(list);
        }
        return "redirect:/";
    }


    @PostMapping("/likes/{itemId}")
    public ResponseEntity<Item> plusLike(@PathVariable Long itemId) {

        // itemId 에 해당하는 item 을 find
        Item item =itemService.findById(new ItemSearchCond(itemId));

        // 좋아요를 증가
        item.likePlus();

        // 결과 반환
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
