# 피드백

## UserRepositoryImpl

- `PrepareStatement`와 `ResutlSet` 자원을 사용하고 나서 잘 닫아 주는 것 좋은 것 같습니다.
- `ResultSet`은 `try-with-resources`를 안써서 `close()`를 써야 했지만, `close()`전에 null 체크 해주는 것도 좋았습니다.


## 로그인

- Session에 넣는 키 값들을 상수로 관리하면 좋습니다.
- 로그아웃을 HTTP Method `GET`으로 구현 하셨습니다. `GET` vs `POST` 차이점에 대해서 한 번 공부해 보시기 바랍니다.
- 로그아웃 시 `session.removeAttribute("user");`로 하는 방법이 옳은지 한 번 생각해 보세요.


## FrontServlet

- ControllerFactory를 `"controllerFactory"`의 키로 가져오고 있습니다.
  - 상수로 관리하면 좋습니다.
- 위와 별개로, [ControllerFactory.java](../../src/main/java/com/nhnacademy/shoppingmall/common/mvc/controller/ControllerFactory.java) 코드를 보면 같은 역할을 하는 코드가 이미 존재합니다. 이 코드를 이해하지 못하셨다면 ServletContext를 다시 한 번 공부해 보세요.
- 그리고, `Exception` 에외를 캐치하고 처리해주는 로직을 구현했습니다. 여기서 `resp.sendError()`로 400, 500 HTTP Status Code를 보내고 있습니다. 
  - 두 개의 상태 코드를 보내는 이유가 있을까요??
  - HTTP Status Code를 학습해 보시기 바랍니다. 
  - 발생하는 예외의 종류에 따라 응답해야 하는 코드가 분명히 다를 것입니다.!!

    
```java
public class FrontServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        this.controllerFactory = (ControllerFactory) getServletContext().getAttribute("controllerFactory");
        this.viewResolver = new ViewResolver();

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DbConnectionThreadLocal.initialize();
        try {
            // ...
        } catch (Exception e) {
            log.error("error:{}", e);
            DbConnectionThreadLocal.setSqlError(true);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");

        } finally {
            DbConnectionThreadLocal.reset();
        }
    }
}
```

## Filter

- 필터에서는 `doFilter` 호출 하는 것을 잊지 마세요.
- MyPage 접근을 막는 `LoginCheckFilter`는 잘못된 구현입니다.
- `@WebFilter(urlPatterns = "/mypage/*")` 이 필터가 `/mypage.do` 경로를 인식할지 고민해 보세요.
- 아래 있는 코드는 MyPage Controller 입니다. Controller 에서 Session을 찾아서 유저가 없으면 `redirect` 시키고 있습니다.
- 필터에서 해야할 일을 Controller에서 하고 있습니다. 이 코드 때문에 **필터가 작동되는 것 처럼 보이는 것** 뿐입니다. 

```java
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage.do")
public class UserMyPageController implements BaseController {
    private final UserAddressService userAddressService = new UserAddressServiceImpl(new UserAddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("user");
        if (Objects.nonNull(user)) {
            // ...
        } else {
            return "redirect:/login.do";
        }
        return "shop/user/mypage";
    }
}
```

## ApplicationListener

- `UserService` 객체를 왜 `ServletContext`에 넣었나요??
- ServletContext가 무슨 역할을 하고 어떤 의미인지 한 번 공부해 보시기 바랍니다.

```java
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            // ...
            context.setAttribute("userService", userService);
        } catch (Exception e) {
          DbConnectionThreadLocal.setSqlError(true);
        } finally {
          DbConnectionThreadLocal.reset();
        }
    }
}
```


## 메인 페이지

- 페이징 처리가 한 페이지에 상품이 2개씩 밖에 안보이는 것은 조금 아쉬운 것 같습니다.
  - 관리자 페이지에서 회원 관리까지 2개씩만 보여줘야 할 이유가 있었을까요??

## 상품

- 상품 등록시 이미지가 없는 경우 `no-image`를 DB에 넣고 있습니다. 다른 방법은 없을지 한 번 생각해 보면 좋을 것 같습니다.
- 상품 수정의 경우 역시 url를 넣는 것이 아니라 `file`을 넣어서 사진 이미지를 바꾸는 것이 옳습니다.
- `log.error("{}={}", part.getName(), formValue);` 로그 레벨을 `error`로 설정하고 값을 확인 하는 것은 좋지 않습니다. 로그 레벨의 역할이 있습니다.
- 이미지의 경로를 절대 경로로 설정 하지 말고 상대 경로로 할 수 있는 방법을 한 번 생각해 보세요.


수고하셨습니다. 👏👏

