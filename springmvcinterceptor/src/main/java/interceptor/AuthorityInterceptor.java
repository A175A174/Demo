package interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 拦截器
 */
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //解析HandlerMethod
//        String methodName = handlerMethod.getMethod().getName();
//        String className = handlerMethod.getBean().getClass().getName();
//        StringBuffer requestBuffer = new StringBuffer();
//        Map paramMap = request.getParameterMap();
//        Iterator it = paramMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            String mapKey = (String) entry.getKey();
//            String mapValue = Arrays.toString((String[]) entry.getValue());
//            requestBuffer.append(mapKey).append("=").append(mapValue);
//        }
//        System.out.println("路径：" + className);
//        System.out.println("方法：" + methodName);
//        System.out.println("参数：" + requestBuffer);

        response.reset();//要先reset，否则报异常
        response.setCharacterEncoding("UTF-8");//设置编码，否则会乱码
        response.setContentType("application/json;charset=UTF-8");//这里要设置返回值的类型，因为是json接口。
        PrintWriter out = response.getWriter();
        Map resultMap = new HashMap();
        resultMap.put("success", false);
        resultMap.put("msg", "请登录管理员");
        out.print(JsonUtil.obj2String(resultMap));
        out.flush();
        out.close();//写完要关闭

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
