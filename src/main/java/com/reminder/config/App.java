package com.reminder.config;


import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.reminder.controller.BookController;
import com.reminder.controller.IndexController;
import com.reminder.controller.LoginController;
import com.reminder.dao.BookDao;
import com.reminder.dao.UserDao;
import com.reminder.util.Filters;
import com.reminder.util.Path;
import com.reminder.util.ViewUtil;


public class App {

        // Declare dependencies
        public static BookDao bookDao;
        public static UserDao userDao;

        public static void main(String[] args) {

            // Instantiate your dependencies
            bookDao = new BookDao();
            userDao = new UserDao();

            // Configure Spark
            port(4567);
            staticFiles.location("/public");
            staticFiles.expireTime(600L);
            enableDebugScreen();

            // Set up before-filters (called before each get/post)
            before("*",                  Filters.addTrailingSlashes);
            before("*",                  Filters.handleLocaleChange);

            // Set up routes
            get(Path.Web.INDEX, IndexController.serveIndexPage);
            get(Path.Web.BOOKS, BookController.fetchAllBooks);
            get(Path.Web.ONE_BOOK, BookController.fetchOneBook);
            get(Path.Web.LOGIN, LoginController.serveLoginPage);
            post(Path.Web.LOGIN, LoginController.handleLoginPost);
            post(Path.Web.LOGOUT, LoginController.handleLogoutPost);
            get("*", ViewUtil.notFound);

            //Set up after-filters (called after each get/post)
            after("*", Filters.addGzipHeader);

        }

    }