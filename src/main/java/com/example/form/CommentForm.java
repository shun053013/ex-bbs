package com.example.form;

/**コメント情報を受け取るフォーム
 * @author shun053012
 *
 */
public class CommentForm {
	
	/**コメント者名*/
	private String name;
	/**コメント内容*/
	private String content;
	/**記事ID*/
	private String articleId;
	
	
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
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articled) {
		this.articleId = articled;
		
	}
	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
