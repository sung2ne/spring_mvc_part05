package com.example.spring.posts;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostsController {
    
    @Autowired
    PostsService postsService;

    // 게시글 등록 (화면, GET)
    @GetMapping("/posts/create")
    public String create() {
        return "posts/create";
    }

    // 게시글 등록 (처리, POST)
    @PostMapping("/posts/create")
    public String createPost(PostsVo postsVo, RedirectAttributes redirectAttributes) {
        boolean created = postsService.create(postsVo);
        if (created) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 등록되었습니다.");
            return "redirect:/posts/";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "게시글 등록에 실패했습니다.");
        return "redirect:/posts/create";
    }

    // 게시글 목록
    @GetMapping("/posts/")
    public String listGet(
        Model model,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(required = false) String searchType,
        @RequestParam(required = false) String searchKeyword
    ) {
        int pageSize = 10; // 페이지당 게시글 수
        Map<String, Object> result = postsService.list(page, pageSize, searchType, searchKeyword);
        model.addAttribute("postsVoList", result.get("postsVoList"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("searchType", result.get("searchType"));
        model.addAttribute("searchKeyword", result.get("searchKeyword"));
        return "posts/list";
    }

    // 게시글 보기
    @GetMapping("/posts/{id}")
    public String readGet(@PathVariable("id") int id, Model model) {
        model.addAttribute("postsVo", postsService.read(id));
        return "posts/read";
    }

    // 게시글 수정 (화면, GET)
    @GetMapping("/posts/{id}/update")
    public String updateGet(@PathVariable("id") int id, Model model) {
        model.addAttribute("postsVo", postsService.read(id));
        return "posts/update";
    }

    // 게시글 수정 (처리, POST)
    @PostMapping("/posts/{id}/update")
    public String updatePost(@PathVariable("id") int id, PostsVo postsVo, RedirectAttributes redirectAttributes) {
        if (postsService.update(postsVo)) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 수정되었습니다.");
            return "redirect:/posts/" + id;
        }
        redirectAttributes.addFlashAttribute("errorMessage", "게시글 수정에 실패했습니다.");
        return "redirect:/posts/" + id + "/update";
    }

    // 게시글 삭제
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable("id") int id, PostsVo postsVo, RedirectAttributes redirectAttributes) {
        boolean deleted = postsService.delete(postsVo);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 삭제되었습니다.");
            return "redirect:/posts/";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "게시글 삭제에 실패했습니다.");
        return("redirect:/posts/" + id);
    }
}
