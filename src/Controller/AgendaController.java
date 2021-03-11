/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Helper.AgendaHelper;
import Model.Agendamento;
import Model.Cliente;
import Model.DAO.AgendamentoDAO;
import Model.DAO.ClienteDAO;
import Model.DAO.ServicoDAO;
import Model.Servico;
import Servico.Correio;
import View.Agenda;
import java.util.ArrayList;

/**
 *
 * @author Thiago Guerreiro
 */
public class AgendaController {
    
    private final Agenda view;
    private final AgendaHelper helper;

    public AgendaController(Agenda view) {
        this.view = view;
        this.helper = new AgendaHelper(view);
    }
    
    public void atualizaTabela() {
        // Busca lista com agendamentos no banco de dados
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        ArrayList<Agendamento> agendamentos = agendamentoDAO.selectAll();
        // Exibe esta lista na view
        helper.preencherTabela(agendamentos);
    }
    
    public void atualizaCliente() {
        // Busca cliente do banco de dados
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = clienteDAO.selectAll();
        // Exibe clientes no Combobox cliente
        helper.preencherClientes(clientes);
    }
    
    public void atualizaServico() {
        ServicoDAO servicoDAO = new ServicoDAO();
        ArrayList<Servico> servicos = servicoDAO.selectAll();
        
        helper.preencherServicos(servicos);
    }
    
    public void atualizaValor() {
        Servico servico = helper.obterServico();
        helper.setarValor(servico.getValor());
    }
    
    public void agendar() {
        // Busca Objeto Agendamento da Tela
        Agendamento agendamento = helper.obterModelo();
        // Salva Objeto no banco de dados
        new AgendamentoDAO().insert(agendamento);
        
        Correio correio = new Correio();
        correio.NotificarPorEmail(agendamento);
        // Insere na tabela
        atualizaTabela();
        helper.limparTela();
    }
}
