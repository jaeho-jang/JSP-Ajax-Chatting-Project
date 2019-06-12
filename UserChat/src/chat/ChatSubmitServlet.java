package chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");
		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") 
				|| chatContent == null || chatContent.equals("")) {
			response.getWriter().write("0");
		} else if (fromID.equals(toID)){ // 로그인한 계정이 그 계정에게 스스로 메시지를 보내는 경우
			response.getWriter().write("-1"); // -1을 반환하여 오류 발생시킴
		} else {
			fromID = URLDecoder.decode(fromID, "UTF-8"); // URLDecoder.decode는 웹에 인코딩되어 넘어간 문자들을 그대로 가져와줌
			toID = URLDecoder.decode(toID, "UTF-8");
			HttpSession session = request.getSession();
			if (!fromID.equals((String) session.getAttribute("userID"))) {
				response.getWriter().write("");
				return;
			}
			chatContent = URLDecoder.decode(chatContent, "UTF-8");
			response.getWriter().write(new ChatDAO().submit(fromID, toID, chatContent) + "");
		}
	}

}
