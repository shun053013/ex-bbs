package com.example.domain;

/**
 * コメント情報を表すドメイン.
 * @author shun053012
 *
 */
public class Comment {
	 /**ID*/
	private Integer id;
	 /**名前*/
	private String name;
	 /**コメント*/
	private String content;
	 /**記事ID*/
	private Integer articleId;
	 
	 
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticleld() {
		return articleId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public void setArticleld(Integer articleld) {
		this.articleId = articleld;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleld=" + articleId + "]";
	}
	public Integer getId() {
		return id;
	}

}
