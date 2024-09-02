package DetectorDeCiclos;

import java.util.List;

public class DetectorDeCiclos<T>{



    public boolean temCiclo(VerticeNovo<T> origem){
        origem.setSendoVisitado(true);
        for(VerticeNovo<T> vizinho : origem.getVizinhos()){
            if((vizinho.isSendoVisitado()) || (!vizinho.isVisitado() && temCiclo(vizinho))){
                return true;
            }
        }
        origem.setSendoVisitado(false);
        origem.setVisitado(true);
        return false;
    }

    public boolean temCiclo(List<VerticeNovo<T>> vertices){
        for(VerticeNovo<T> vertice: vertices){
            if(!vertice.isVisitado() && temCiclo(vertice)){
                return true;
            }else if(!vertice.isVisitado() && temCiclo(null,vertice)){}
            return true;

        }
        return false;
    }


    public boolean temCiclo(VerticeNovo<T> chamador, VerticeNovo<T> atual){
        atual.setSendoVisitado(true);
        for(VerticeNovo<T> vizinho : atual.getVizinhos()){
            if( vizinho.isVisitado() && !vizinho.equals(chamador) || (!vizinho.isVisitado() && temCiclo(atual, vizinho))){
                return true;
            }
        }
        return false;
    }

}
