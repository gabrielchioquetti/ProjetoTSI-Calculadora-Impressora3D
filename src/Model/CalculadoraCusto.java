package Model;

public class CalculadoraCusto {

    public double custoHora(double preco_impressora, double horas_impressao){ 
        double horas_totais = 2 * 365 * horas_impressao;
        double custo_hora = preco_impressora / horas_totais;
        return custo_hora * horas_impressao;
    }

    public double custoManutencao(double tempo_impressao){
        return tempo_impressao * 0.50;
    }

    public double custoMaterial(double gramas_utilizadas, double custo_por_grama){
        return gramas_utilizadas * custo_por_grama;
    }

    public double custoEnergia(double potencia_watts, double horas_impressao, double valor_kwh){
        double energia_kwh = (potencia_watts / 1000.0) * horas_impressao;
        return energia_kwh * valor_kwh;
    }

    public double custoMaoObra(double horas_trabalho, double valor_hora){
        return horas_trabalho * valor_hora;
    }

    public double taxaFalha(double material_previsto){
        return material_previsto * 1.10;
    }

    public double margemLucro(double custo_total, double porcentagem_lucro){
        double multiplicador = 1 + (porcentagem_lucro / 100.0);
        return custo_total * multiplicador;
    }

    public double custoTotal(double custo_material, double custo_maquina, double custo_energia, double custo_mao_obra, double manutencao){
        return custo_material + custo_maquina + custo_energia + custo_mao_obra + manutencao;
    }
}