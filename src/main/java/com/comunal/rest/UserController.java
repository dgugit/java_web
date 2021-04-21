package com.comunal.rest;

import com.comunal.rest.dto.*;
import com.comunal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody String addNewUser (@Valid @RequestBody CreateUserDTO userDTO, HttpServletRequest request) {
        UserDTO user = userService.registerUser(userDTO);
        HttpSession session = request.getSession();
        session.setAttribute("currUser", user);
        session.setMaxInactiveInterval(2000);
        return "Вітаємо! Вас успішно зареєстровано.";
    }

    @PostMapping(path="/sigIn")
    public void sigIn (@RequestParam String login, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO user = userService.sigIn(login, password);
        HttpSession session = request.getSession();
        session.setAttribute("currUser", user);
        session.setMaxInactiveInterval(2000);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user/profile"));
        return;
    }

    @PutMapping
    public  @ResponseBody String updateUser (@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO currUser = (UserDTO) session.getAttribute("currUser");
        UserDTO user = userService.updateUser(userDTO, currUser.getLogin());
        session.setAttribute("currUser", user);
        session.setMaxInactiveInterval(2000);
        return "Успішно оновлено";
    }

    @PutMapping(path = "/password")
    public void updatePassword (@Valid @RequestBody UpdatePasswordDTO userDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO currUser = (UserDTO) session.getAttribute("currUser");
        UserDTO user = userService.updatePassword(userDTO, currUser.getLogin());
        session.setAttribute("currUser", user);
        session.setMaxInactiveInterval(2000);
        return;
    }

    @GetMapping(path="/logout")
    public void logOut (HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
    }

    @PostMapping(path="/request")
    public @ResponseBody String addNewRequest (@Valid @RequestBody CreateRequestDTO requestDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO currUser = (UserDTO) session.getAttribute("currUser");
        userService.createRequest(requestDTO, currUser);
        return "Заявка успішно створена.";
    }

    @RequestMapping("/profile")
    public String welcome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO currUser = (UserDTO) session.getAttribute("currUser");
        List<RequestDTO> requests = userService.getAllForUser(currUser);
        request.setAttribute("requests", requests);
        return "profile";
    }
}