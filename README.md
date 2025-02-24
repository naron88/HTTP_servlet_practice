## 개요
서블릿(Servlet)을 직접 등록하여 사용하며, HTTP 요청과 응답을 다루는 다양한 서블릿을 포함하고 있습니다. 
`@ServletComponentScan`을 사용하여 서블릿을 자동 등록하고 실행합니다.

## request 관련 서블릿 목록
### 1. 요청 헤더 출력
**URL:** `http://localhost:8080/request-header?username=hello`
- HTTP 요청 헤더의 다양한 정보를 출력합니다.

```java
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // request 정보 출력
    printStartLine(request);
    // header 정보 출력
    printHeaders(request);
    // header 편리한 조회
    printHeaderUtils(request);
    // 기타 조회
    printEtc(request);

    response.getWriter().write("ok");
  }

  //start line 정보
  private void printStartLine(HttpServletRequest request) {
    System.out.println("--- REQUEST-LINE - start ---");

    System.out.println("request.getMethod() = " + request.getMethod()); //GET
    System.out.println("request.getProtocal() = " + request.getProtocol()); //HTTP/1.1
    System.out.println("request.getScheme() = " + request.getScheme()); //http
    // http://localhost:8080/request-header
    System.out.println("request.getRequestURL() = " + request.getRequestURL());
    // /request-test
    System.out.println("request.getRequestURI() = " + request.getRequestURI());
    //username=hi
    System.out.println("request.getQueryString() = " + request.getQueryString());
    System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
    System.out.println("--- REQUEST-LINE - end ---");
    System.out.println();
  }

  //Header 모든 정보
  private void printHeaders(HttpServletRequest request) {
    System.out.println("--- Headers - start ---");

//    Enumeration<String> headerNames = request.getHeaderNames();
//    while (headerNames.hasMoreElements()) {
//      String headerName = headerNames.nextElement();
//      System.out.println(headerName + ": " + request.getHeader(headerName));
//    }

    request.getHeaderNames().asIterator().forEachRemaining(
        headerName -> System.out.println(headerName + ":" + request.getHeader(headerName)));
    System.out.println("--- Headers - end ---");
    System.out.println();
  }

  //Header 편리한 조회
  private void printHeaderUtils(HttpServletRequest request) {
    System.out.println("--- Header 편의 조회 start ---");
    System.out.println("[Host 편의 조회]");
    System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
    System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
    System.out.println();

    System.out.println("[Accept-Language 편의 조회]");
    request.getLocales().asIterator()
        .forEachRemaining(locale -> System.out.println("locale = " + locale));
    System.out.println("request.getLocale() = " + request.getLocale());
    System.out.println();

    System.out.println("[cookie 편의 조회]");
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        System.out.println(cookie.getName() + ": " + cookie.getValue());
      }
    }
    System.out.println();
    System.out.println("[Content 편의 조회]");
    System.out.println("request.getContentType() = " + request.getContentType());
    System.out.println("request.getContentLength() = " + request.getContentLength());
    System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
    System.out.println("--- Header 편의 조회 end ---");
    System.out.println();
  }

  //기타 정보
  private void printEtc(HttpServletRequest request) {
    System.out.println("--- 기타 조회 start ---");
    System.out.println("[Remote 정보]");
    System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
    System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
    System.out.println("request.getRemotePort() = " + request.getRemotePort()); //
    System.out.println();

    System.out.println("[Local 정보]");
    System.out.println("request.getLocalName() = " + request.getLocalName()); //
    System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
    System.out.println("request.getLocalPort() = " + request.getLocalPort()); //\

    System.out.println("--- 기타 조회 end ---");
    System.out.println();
  }
}
```
![image](https://github.com/user-attachments/assets/fc6aef33-7893-486f-b872-270c04f7c32d)

![image](https://github.com/user-attachments/assets/e37ae57c-eb68-46b0-9488-3770b3f8f4ff)


### 2. 요청 파라미터 출력
**URL:** `http://localhost:8080/request-param?username=hello&username=kim&age=20`
- 요청 파라미터를 조회하고 여러 개의 동일한 파라미터도 처리할 수 있습니다.

```java
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse resp)
      throws ServletException, IOException {

    System.out.println("[전체 파라미터 조회] - start");

    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String paramName = parameterNames.nextElement();
      System.out.println(paramName + "=" +
          request.getParameter(paramName));
    }

    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> System.out.println(paramName +
            "=" + request.getParameter(paramName)));
    System.out.println("[전체 파라미터 조회] - end");
    System.out.println();

    System.out.println("[단일 파라미터 조회]");
    String username = request.getParameter("username");
    System.out.println("request.getParameter(username) = " + username);

    String age = request.getParameter("age");
    System.out.println("request.getParameter(age) = " + age);
    System.out.println();

    System.out.println("[이름이 같은 복수 파라미터 조회]");
    System.out.println("request.getParameterValues(username)");
    String[] usernames = request.getParameterValues("username");
    for (String name : usernames) {
      System.out.println("username=" + name);
    }

    resp.getWriter().write("ok");
  }
}
```
![image](https://github.com/user-attachments/assets/3ea01807-8057-46df-9967-18283eea10be)

### 3. JSON 형식 요청 바디 처리
**URL:** `http://localhost:8080/request-bodyjson`
- 클라이언트가 JSON 데이터를 `content-type: application/json`으로 전송하면 이를 객체로 변환하여 출력합니다.
```json
{
    "username": "kim",
    "age": "20"
}
```

```java
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-bodyjson")
public class RequestBodyJsonServlet extends HttpServlet {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    System.out.println("messageBody = " + messageBody);

    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    System.out.println("helloData.username = " + helloData.getUsername());
    System.out.println("helloData.age = " + helloData.getAge());

    response.getWriter().write("ok");
  }
}
```
![image](https://github.com/user-attachments/assets/9b6cd0dc-10cd-4444-92a8-fdc64240bca9)

## response 관련 서블릿 목록

### 1. 응답 헤더 설정
**URL:** `http://localhost:8080/response-header`
- HTTP 응답 헤더를 설정하고 다양한 응답을 제공합니다.

```java
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {

    //[status-line]
    response.setStatus(HttpServletResponse.SC_OK); //200

    //[response-headers]
    response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setHeader("Cache-Control", "no-cache, no-store, mustrevalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("my-header","hello");

    //[Header 편의 메서드]
    content(response);
    cookie(response);
    redirect(response);

    //[message body]
    PrintWriter writer = response.getWriter();
    writer.println("ok");
  }

  private void content(HttpServletResponse response) {
    //Content-Type: text/plain;charset=utf-8
    //Content-Length: 2
    //response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    //response.setContentLength(2); //(생략시 자동 생성)
  }

  private void cookie(HttpServletResponse response) {
    //Set-Cookie: myCookie=good; Max-Age=600;
    //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600); //600초
    response.addCookie(cookie);
  }

  private void redirect(HttpServletResponse response) throws IOException {
    //Status Code 302
    //Location: /basic/hello-form.html

    //response.setStatus(HttpServletResponse.SC_FOUND); //302
    //response.setHeader("Location", "/basic/hello-form.html");
    //response.sendRedirect("/basic/hello-form.html");
  }
}
```

### 2. JSON 응답 반환
**URL:** `http://localhost:8080/response-json`
- JSON 형식의 응답을 반환합니다.

```java
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {

    //Content-Type: application/json
    response.setHeader("content-type", "application/json");
    response.setCharacterEncoding("utf-8");

    HelloData data = new HelloData();
    data.setUsername("kim");
    data.setAge(20);

    //{"username":"kim","age":20}
    String result = objectMapper.writeValueAsString(data);
    response.getWriter().write(result);
  }
}
```

## 결론
서블릿의 기본 동작을 이해하고 다양한 요청 및 응답을 처리하는 방법을 연습할 수 있습니다.

