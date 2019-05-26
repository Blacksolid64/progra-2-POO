package com.mycompany.progra_graficos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Joshua
 */
public class Main {

    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	ArrayList<Voto> votos = new ArrayList<>();
	Voto voto;
	Random r = new Random();
	int v;
	for (int i = 0; i < 100; i++) {
		voto = new Voto();
		for (int j = 0; j < 1 + r.nextInt(7); j++) {
			v = 1 + r.nextInt(12);
			while (voto.contains(v)) v = 1 + r.nextInt(12);
			voto.add(v);
                                                      
		}
		votos.add(voto);
	}
	Stv stv;
	try {
            stv = new Stv(votos, 5);                                
		stv.contarTodosLosVotos(); 
		while (!stv.plazasLlenadas()) {
			stv.repartirLosVotosSobrantes();
			if (!stv.plazasLlenadas())
				stv.eliminarPartidoConMenosVotos();
		}
                 stv.getVotosPorCandidato().forEach((CANDIDATOS,VOTOS) -> dataset.setValue(VOTOS.size(), "Votos totales","CANDIDATO # " + CANDIDATOS) );
                                    
                JFreeChart chart = ChartFactory.createBarChart("Tarea#3","Candidato","Cantidad de votos",dataset,PlotOrientation.VERTICAL,false, true,false);
                CategoryPlot p=chart.getCategoryPlot();
                ChartFrame frame= new ChartFrame("Gráfico de barras: Votos",chart);
                frame.setVisible(true);
                frame.setSize(750,350);
	} catch (Exception e) {
	}

}
    
}
