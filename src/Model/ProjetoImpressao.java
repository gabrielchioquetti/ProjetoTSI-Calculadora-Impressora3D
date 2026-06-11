package Model;

public class ProjetoImpressao {
    private String nomeArquivo;
    private String descricaoProjeto;
    private double pesoModelo;
    private double pesoSuporte;
    private int quantidadePecas;
    private double tempoImpressao;
    private double tempoPreparo;

    public ProjetoImpressao(){}

    public ProjetoImpressao(String nomeArquivo, String descricaoProjeto, double pesoModelo, double pesoSuporte, int quantidadePecas, double tempoImpressao, double tempoPreparo) {
        this.nomeArquivo = nomeArquivo;
        this.descricaoProjeto = descricaoProjeto;
        this.pesoModelo = pesoModelo;
        this.pesoSuporte = pesoSuporte;
        this.quantidadePecas = quantidadePecas;
        this.tempoImpressao = tempoImpressao;
        this.tempoPreparo = tempoPreparo;
    }


    public String getNomeArquivo() {
        return nomeArquivo;
    }


    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }


    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }


    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }


    public double getPesoModelo() {
        return pesoModelo;
    }


    public void setPesoModelo(double pesoModelo) {
        this.pesoModelo = pesoModelo;
    }


    public double getPesoSuporte() {
        return pesoSuporte;
    }


    public void setPesoSuporte(double pesoSuporte) {
        this.pesoSuporte = pesoSuporte;
    }


    public int getQuantidadePecas() {
        return quantidadePecas;
    }


    public void setQuantidadePecas(int quantidadePecas) {
        this.quantidadePecas = quantidadePecas;
    }


    public double getTempoImpressao() {
        return tempoImpressao;
    }


    public void setTempoImpressao(double tempoImpressao) {
        this.tempoImpressao = tempoImpressao;
    }


    public double getTempoPreparo() {
        return tempoPreparo;
    }


    public void setTempoPreparo(double tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

}