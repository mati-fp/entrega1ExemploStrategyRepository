public class App {
    public static void main(String[] args) {
        RegistroDoTempoRepository registroDoTempoRepository = new RegistroDoTempoRepository("poa_temps.txt");
        Consultas consultas = new Consultas(registroDoTempoRepository, r->r.getVelocidadeDoVento() > 5);
        System.out.println("Dia em que mais choveu no ano de 1980: ");
        System.out.println(consultas.diaQueMaisChoveuNoAno(1980));
        System.out.println("Datas em que choveu mais de 90 milimetros");
        consultas.datasEmQueChouveuMaisDe(90)
            .forEach(System.out::println);
        System.out.println("Datas em que a velocidade do vento foi maior que 5");
        consultas.diasEmQue()
            .forEach(System.out::println);
        System.out.println("Datas em que a temperatura media foi maior que 30 graus");
        consultas.alteraConsultaPadrao(r->r.getTemperaturaMedia() > 30);
        consultas.diasEmQue()
            .forEach(System.out::println);
    }
}
