package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

@Repository
public class CommentRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * データベースで検索したコメント情報をオブジェクトにセット.
	 * 
	 */
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleld(rs.getInt("article_id"));
		return comment;
	};

	/**
	 * 取ってきた記事一つ一つの全コメントを取得
	 * 
	 * @param articled
	 * @return コメントリスト
	 */
	public List<Comment> findByArticled(int articleId) {
		String sql = "select id,name,content,article_id from comments where article_id=:articleId order by id desc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		return commentList;

	}

	/**
	 * コメント情報を挿入する.
	 * 
	 * @param comment
	 */
	public void insert(Comment comment) {
		String sql = "insert into comments (name,content,article_id) values(:name,:content,:articleId)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(sql, param);
	}

	/**
	 * コメント情報を削除する.
	 * 
	 * @param articleId
	 */
	public void deleteByArticleId(int articleId) {
		String sql = "delete from comments where article_id=articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}

}
