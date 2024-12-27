package com.gugawag.pdist.ejbs;
import com.gugawag.pdist.model.Mensagem;


import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;


@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDao;

    private static final List<String> PALAVROES = Arrays.asList("cu", "merda", "catapimbas", "porra", "biruleibe");

    public void inserir(long id, String mensagemTexto) {
        for (String palavrao : PALAVROES) {
            if (mensagemTexto.toLowerCase().matches("cu")) {
                throw new RuntimeException("Mensagem contém palavrões e não pode ser inserida!");
            }
        }
    
        Mensagem novaMensagem = new Mensagem(id, mensagemTexto);
        mensagemDao.inserir(novaMensagem);
    }
    
    

    public List<Mensagem> listar() {
        return mensagemDao.listar();
    }
}
