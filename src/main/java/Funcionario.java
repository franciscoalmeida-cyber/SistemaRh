import java.time.LocalDate;

public class Funcionario {
    private int matricula;
    private String nome;
    private LocalDate dataNascimento;
    private String setor;
    private String cargo;
    private double salario;

    public Funcionario(int matricula, String nome, LocalDate dataNascimento, String setor, String cargo, double salario) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.setor = setor;
        this.cargo = cargo;
        this.salario = salario;
    }

    // Getters
    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getSetor() { return setor; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }
}
