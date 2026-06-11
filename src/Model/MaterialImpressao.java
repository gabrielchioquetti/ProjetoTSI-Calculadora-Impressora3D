package Model;

public class MaterialImpressao {
    private String material;
    private String densidade;
    private double grama;

    public MaterialImpressao(){

    }
    public MaterialImpressao(String material, String densidade, double grama){
        this.material = material;
        this.densidade = densidade;
        this.grama = grama;
    }

        public String densidadeQualidade(){
            String personalizado = (densidade + " (" + grama + " R$/g)");
            return personalizado;
        }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getDensidade() {
        return densidade;
    }
    public void setDensidade(String densidade) {
        this.densidade = densidade;
    }
    public double getGrama() {
        return grama;
    }
    public void setGrama(double grama) {
        this.grama = grama;
    }
}
