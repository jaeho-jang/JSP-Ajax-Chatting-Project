package chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ChatUnreadServlet")
public class ChatUnreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // UTF-8 방식으로 메시지를 받음
		response.setContentType("text/html; charset=UTF-8");
		String userID = request.getParameter("userID");
		if (userID == null || userID.equals("")) { // 넘어온 값이 없을 경우
			response.getWriter().write("0"); // 0을 반환해줌 (오류)
		} else {
			userID = URLDecoder.decode(userID, "UTF-8");
			response.getWriter().write(new ChatDAO().getAllUnreadChat(userID) + "");
		}
	}

}
