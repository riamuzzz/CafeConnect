package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CafeUser;

@WebFilter(urlPatterns = { "/cafeconnect/cafe/*" })
public class CafeLoginRequiredFilter implements Filter {
	/**
	 * doFilterメソッド フィルター処理を記述
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		// ログインユーザーを取得
		CafeUser cafeUser = (CafeUser)req.getSession(true).getAttribute("cafe_user");
		// ユーザーが存在しないまたは認証されていない場合
		if (cafeUser == null || !cafeUser.isAuthenticated()) {
			HttpServletResponse	res = ((HttpServletResponse) response);
			// ログインページへリダイレクト
			res.sendRedirect(req.getContextPath() + "/cafeconnect/CafeLogin.action");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
