package org.excel;

import java.util.*;

public class AgenciaMapping {
    private static final Map<String, String> AGENCIA_MAPPING = new HashMap<>();

    public AgenciaMapping() {
        // Agregar correspondencias al mapa
        AGENCIA_MAPPING.put("TB_CAL", "14161 - CALLAO");
        AGENCIA_MAPPING.put("TB_STA", "14162 - SANTA ANITA");
        AGENCIA_MAPPING.put("TB_INDEP","14175 - INDEPENDENCIA");
        // Agrega más correspondencias según sea necesario
    }

    public String obtenerAgenciaSeleccionada(String clave) {
        return AGENCIA_MAPPING.get(clave);
    }
}
