package error;

@SuppressWarnings("serial")
public class ExcecaoGeral extends Exception {
    private String mensagem;

    public ExcecaoGeral() {
    }

    public String getMensagem() {
        return mensagem;
    }

    public ExcecaoGeral(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
