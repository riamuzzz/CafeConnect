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

import bean.User;

@WebFilter(urlPatterns = { "/scoremanager/main/*" })
public class LoginRequiredFilter implements Filter {
	/**
	 * doFilterメソッド フィルター処理を記述
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		// ログインユーザーを取得
		User user = (User)req.getSession(true).getAttribute("user");
		// ユーザーが存在しないまたは認証されていない場合
		if (user == null || !user.isAuthenticated()) {
			HttpServletResponse	res = ((HttpServletResponse) response);
			// ログインページへリダイレクト
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
