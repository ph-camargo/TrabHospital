package Hospital;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Paciente {
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private ArrayList<Atendimento> atendimentos;
    private boolean ativo;

    public Paciente(){
        atendimentos = new ArrayList<Atendimento>();
        ativo = true; // Paciente começa como ativo por padrão
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void adicionarConsulta(Atendimento atendimento){
        atendimentos.add(atendimento);
    }

    public int getIdade(){
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);
        return periodo.getYears();
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void desativar() {
        ativo = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente paciente = (Paciente) obj;
        return nome.equals(paciente.nome) && sobrenome.equals(paciente.sobrenome);
    }

    @Override
    public String toString(){
        String retorno = "Nome: " + nome + " " + sobrenome;
        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = formatoBr.format(this.dataNascimento);
        retorno += "\nData de nascimento: " + data;
        retorno += "\nIdade: " + getIdade();
        return retorno;
    }
}