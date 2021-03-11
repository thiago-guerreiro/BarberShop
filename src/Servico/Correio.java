/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;

import Model.Agendamento;

/**
 *
 * @author Thiago Guerreiro
 */
public class Correio {
    
    public void NotificarPorEmail(Agendamento agendamento) {
        String emailFormatado = formatarEmail(agendamento);
        String destinatario = agendamento.getCliente().getEmail();
        
        Email email = new Email("Agendamento BarberShop", emailFormatado, destinatario);
        email.enviar();
    }

    private String formatarEmail(Agendamento agendamento) {
        String nomeCliente = agendamento.getCliente().getNome();
        String servico = agendamento.getServico().getDescricao();
        String dataAgendamento = agendamento.getDataFormatada();
        String horaAgendamento = agendamento.getHoraFormatada();
        float valor = agendamento.getValor();
        
        return "Olá, " + nomeCliente + ", vai dar um tapa no visu? Seu agendamento para " + 
                servico + " está marcado para o dia " + dataAgendamento + 
                " às " + horaAgendamento + "O valor será R$ " + valor;
    }
}
