package com.reminder.controller;

import com.reminder.util.Path;
import com.reminder.util.ViewUtil;
import spark.*;
import java.util.*;
import static com.reminder.config.App.*;

public class IndexController {
    public static Route serveIndexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", userDao.getAllUserNames());
        model.put("book", bookDao.getRandomBook());
        return ViewUtil.render(request, model, Path.Template.INDEX);
    };
}
