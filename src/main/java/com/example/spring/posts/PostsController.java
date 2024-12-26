package com.example.spring.posts;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostsController {
    
    @Autowired
    PostsService postsService;

    // 게시글 등록
    @GetMapping("/create")
    public ModelAndView createGet() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("posts/create");
        return mav;
    }

    // 게시글 등록
    @PostMapping("/create")
    public ModelAndView createPost(PostsVo postsVo, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        boolean created = postsService.create(postsVo);
        if (created) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 등록되었습니다.");
            mav.setViewName("redirect:/posts/");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 등록에 실패했습니다.");
            mav.setViewName("redirect:/posts/create");
        }
        return mav;
    }

    // 게시글 목록
    @GetMapping("/")
    public ModelAndView listGet(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(required = false) String searchType,
        @RequestParam(required = false) String searchKeyword
    ) {
        ModelAndView mav = new ModelAndView();
        int pageSize = 10; // 페이지당 게시글 수
        Map<String, Object> result = postsService.list(page, pageSize, searchType, searchKeyword);
        mav.addObject("postsVoList", result.get("postsVoList"));
        mav.addObject("pagination", result.get("pagination"));
        mav.addObject("searchType", result.get("searchType"));
        mav.addObject("searchKeyword", result.get("searchKeyword"));
        mav.setViewName("posts/list");
        return mav;
    }

    // 게시글 보기
    @GetMapping("/{id}")
    public ModelAndView readGet(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        PostsVo postsVo = postsService.read(id);
        mav.addObject("postsVo", postsVo);
        mav.setViewName("posts/read");
        return mav;
    }

    // 게시글 수정
    @GetMapping("/{id}/update")
    public ModelAndView updateGet(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        PostsVo postsVo = postsService.read(id);
        mav.addObject("postsVo", postsVo);
        mav.setViewName("posts/update");
        return mav;
    }

    // 게시글 수정
    @PostMapping("/{id}/update")
    public ModelAndView updatePost(@PathVariable("id") int id, PostsVo postsVo, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        boolean updated = postsService.update(postsVo);
        if (updated) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 수정되었습니다.");
            mav.setViewName("redirect:/posts/" + id);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 수정에 실패했습니다.");
            mav.setViewName("redirect:/posts/" + id + "/update");
        }
        return mav;
    }

    // 게시글 삭제
    @PostMapping("/{id}/delete")
    public ModelAndView deletePost(@PathVariable("id") int id, PostsVo postsVo, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        boolean deleted = postsService.delete(postsVo);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 삭제되었습니다.");
            mav.setViewName("redirect:/posts/");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 삭제에 실패했습니다.");
            mav.setViewName("redirect:/posts/" + id);
        }
        return mav;
    }
}
