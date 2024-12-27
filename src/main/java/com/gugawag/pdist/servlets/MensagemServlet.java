package com.gugawag.pdist.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import com.gugawag.pdist.ejbs.MensagemService;

import com.gugawag.pdist.model.Mensagem;


@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet {

    @EJB(lookup = "java:module/mensagemService")
    private MensagemService mensagemService;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String operacao = request.getParameter("oper");
        PrintWriter resposta = response.getWriter();

        switch (operacao) {
            case "1": {
                long id = Integer.parseInt(request.getParameter("id"));
                String textoMensagem = request.getParameter("mensagem");

                try {
                    mensagemService.inserir(id, textoMensagem);
                    resposta.println("Mensagem inserida com sucesso!");
                } catch (IllegalArgumentException e) {
                    resposta.println("Erro: " + e.getMessage());
                }
                break;
            }
            case "2": {
                for (Mensagem mensagem : mensagemService.listar()) {
                    resposta.println(mensagem.getMensagem());
                }
                break;
            }
        }
    }
}
