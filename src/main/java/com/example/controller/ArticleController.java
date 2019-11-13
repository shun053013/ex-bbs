package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事情報を操作するコントローラー.
 * 
 * @author shun053012
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleRepository repository;
	@Autowired
	private CommentRepository commentrepository;

	@ModelAttribute
	public ArticleForm setupform() {
		return new ArticleForm();
	}
	@ModelAttribute
	public CommentForm commentform() {
		return new CommentForm();
	}

	/**
	 * 記事を全件検索してリクエストスコープに格納. 記事ごとのコメントリストを検索
	 * 
	 * @param model
	 * @return 記事一覧を表示
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = repository.findAll();
		for (Article article : articleList) {
			List<Comment> commentList = commentrepository.findByArticled(article.getId());
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);
		return "input";
	}

	/**
	 * 記事情報をデータベースに挿入.
	 * 
	 * @param form
	 * @return 記事投稿画面にリダイレクト
	 */
	@RequestMapping("/input")
	public String insertArticle(ArticleForm form) {

		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		repository.insert(article);
		
		return "redirect:/article";
	}
	/**
	 * コメント情報をデータベースに挿入.
	 * @param commentform
	 * @return 記事投稿画面にリダイレクト
	 */
	@RequestMapping("/output")
	public String insertComment(CommentForm commentform) {
	Comment comment=new Comment();
	BeanUtils.copyProperties(commentform,comment);
	comment.setArticleld(Integer.parseInt(commentform.getArticleId()));
	commentrepository.insert(comment);
	return "redirect:/article";
	}
	
	/**　記事、コメントを削除.
	 * @param id
	 * @param articleId
	 * @return 記事投稿画面にリダイレクト
	 */
	@RequestMapping("/delete")
	public String deleteArticle(Integer id,Integer articleId) {
		repository.deleteById(id);
		commentrepository.deleteByArticleId(articleId);
		return "redirect:/article";
	}
	
}
