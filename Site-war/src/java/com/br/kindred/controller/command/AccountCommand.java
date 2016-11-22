/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.controller.command;

import com.br.kindred.model.dao.AccountDAO;
import com.br.kindred.model.entities.Account;
import com.br.kindred.model.entities.Accountinfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author First Place
 */
public class AccountCommand implements Command {
    
    AccountDAO accountDAO = lookupAccountDAOBean();
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;
    
    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    @Override
    public void execute() {
        String action = request.getParameter("command").split("\\.")[1];
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberStr = request.getParameter("remember");
        boolean remember;
        Account accountTemp = null;
        switch (action) {
            case "login":
                if (rememberStr.equals("on")) {
                    remember = true;
                } else {
                    remember = false;
                }
                accountTemp = accountDAO.readByUsername(username);
                if (accountTemp != null) {// Verifica se a conta existe
                    if (password.equals(accountTemp.getPassword())) { //Verifica senha da conta
                        if (remember) {
                            Cookie userC = new Cookie("User", accountTemp.getUsername());
                            Cookie passwordC = new Cookie("Password", accountTemp.getPassword());
                            userC.setMaxAge(60 * 60 * 24);
                            passwordC.setMaxAge(60 * 60 * 24);
                            response.addCookie(userC);
                            response.addCookie(passwordC);
                        }
                        request.getSession().setAttribute("account", accountTemp);
                        responsePage = "index.jsp";
                    } else { // Senha incorreta
                        request.getSession().setAttribute("error", "Senha incorreta");
                        responsePage = "error.jsp";
                    }
                } else { // Conta inexistente
                    request.getSession().setAttribute("error", "Não há conta com este login, por favor registre-se!");
                    responsePage = "register.jsp";//Na página register tem um verificador caso o usuário tente logar, porém n esteja registrado
                }
                break;
            case "register":
                accountTemp = accountDAO.readByUsername(username);
                if (accountTemp == null) {// Username disponível
                    String email = request.getParameter("email");
                    accountTemp = accountDAO.readByEmail(email);
                    if (accountTemp == null) {//Email disponível
                        if (password.equals(request.getParameter("password2"))) {// Verificação de senhas
                            Account account = new Account();
                            account.setUsername(username);
                            account.setPassword(password);
                            Accountinfo accountinfo = new Accountinfo();
                            accountinfo.setEmail(email);
                            accountinfo.setAccount(account);
                            account.setAccountinfo(accountinfo);
                            accountDAO.create(account);
                            request.getSession().setAttribute("account", account);
                            responsePage = "index.jsp";
                        } else {// Senhas não correpondem
                            request.getSession().setAttribute("error", "Senha não correspondem");
                            responsePage = "error.jsp";
                        }
                    } else {// Email indisponivel
                        request.getSession().setAttribute("error", "E-mail indisponível");
                        responsePage = "error.jsp";
                    }
                } else {
                    request.getSession().setAttribute("error", "Usuário já existe!");
                    responsePage = "error.jsp";
                }
                
                break;
            case "logout":
                Cookie[] cookies = request.getCookies();
                for (int i = 0; i < cookies.length; i++) {
//                    cookies[i].setValue("");
//                    cookies[i].setPath("/");
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
                request.getSession().invalidate();
                responsePage = request.getContextPath();
                break;
            case "updatePassword":
                long idAccount = Long.parseLong(request.getParameter("idAccount"));
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                String newPasswordConfirm = request.getParameter("newPasswordConfirm");
                
                if (newPassword.equals(newPasswordConfirm)) {
                    accountTemp = accountDAO.readById(idAccount);
                    if (accountTemp.getPassword().equals(oldPassword)) {
                        accountTemp.setPassword(newPassword);
                        accountDAO.update(accountTemp);
                        responsePage = "account.jsp";
                    } else {
                        request.getSession().setAttribute("error", "Senha incorreta!");
                        responsePage = "error.jsp";
                    }
                } else {
                    request.getSession().setAttribute("error", "Senhas não correpondem!");
                    responsePage = "error.jsp";
                }
                break;
            case "delete":
                idAccount = Long.parseLong(request.getParameter("idAccount"));
                accountTemp = accountDAO.readById(idAccount);
                accountDAO.delete(accountTemp);
                request.getSession().invalidate();
                responsePage = "index.jsp";
                break;
        }
    }
    
    @Override
    public String getResponsePage() {
        return this.responsePage;
    }
    
    private AccountDAO lookupAccountDAOBean() {
        try {
            Context c = new InitialContext();
            return (AccountDAO) c.lookup("java:global/Site/Site-ejb/AccountDAO!com.br.kindred.model.dao.AccountDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
