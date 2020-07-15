package ru.job4j.controller;

import ru.job4j.model.Candidate;
import ru.job4j.model.MemStore;
import ru.job4j.model.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CandidateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        MemStore.instOf().saveCandidate(new Candidate(Integer.valueOf(req.getParameter("id")), req.getParameter("name")));
        resp.sendRedirect(req.getContextPath() + "/candidate/save.do");
    }
}