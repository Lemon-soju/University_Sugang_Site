package lemonsoju_group.lemonsoju_artifact.interceptor;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 교수 학생 역할 구분하기 쉽게 하려고 만들었음 -> 이후 시간이 있으면 인터셉터를 이용해서 교수와 학생 페이지
 * 디렉토리를 따로 만들어서 서로 접근할 수 없도록 할 예정
 */
@Slf4j
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("교수 학생 계정 구분 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute(SessionConst.LOGIN_ROLE) == null){
            log.info("미인증 사용자 요청");
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
