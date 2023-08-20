import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Consultas {

    private RegistroDoTempoRepository registroDoTempoRepository;
    private Predicate<RegistroDoTempo> consultaPadrao;

    public Consultas(RegistroDoTempoRepository registroDoTempoRepository, Predicate<RegistroDoTempo> consultaPadrao){
        this.registroDoTempoRepository = registroDoTempoRepository;
        this.consultaPadrao = consultaPadrao;
    }    

    public List<String> datasEmQueChouveuMaisDe(double milimetros){
        return registroDoTempoRepository.getRegistros()
            .stream()
            .filter(r->r.getPrecipitacao() > milimetros)
            .map(r->r.getDia()+"/"+r.getMes()+"/"+r.getAno())
            .toList();
    }

    public String diaQueMaisChoveuNoAno(int ano){
        RegistroDoTempo registro = registroDoTempoRepository.getRegistros()
        .stream()
        .filter(reg->reg.getAno() == ano)
        .max(Comparator.comparing(RegistroDoTempo::getPrecipitacao))
        .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia()+"/"+registro.getMes()+"/"+registro.getAno()+", "+registro.getPrecipitacao();
        return resp;
    }

    public List<DataDTO> diasEmQue(){
        List<DataDTO> resp = new LinkedList<>();
        registroDoTempoRepository.getRegistros()
            .stream()
            .filter(consultaPadrao)
            .forEach(r->resp.add(new DataDTO(r.getDia(), r.getMes(), r.getAno())));
        return resp;
    }

    public void alteraConsultaPadrao(Predicate<RegistroDoTempo> consulta){
        this.consultaPadrao = consulta;
    }
}
